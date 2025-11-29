#!/bin/bash
set -e

# ==============================================================================
# Variáveis de Configuração
# ==============================================================================
NAMESPACE="gerador-uuid-prod"
APP_PATH="./devops/k8s/prod"
KONG_PATH="${APP_PATH}/kong-gateway"

# Códigos de cores ANSI
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
RED='\033[0;31m'
NC='\033[0m' # No Color

# ==============================================================================
# Funções de Deploy
# ==============================================================================

# Função para exibir mensagens de status
log_status() {
    echo -e "${YELLOW}>> $1${NC}"
}

# Função para exibir mensagens de sucesso
log_success() {
    echo -e "${GREEN}✅ $1${NC}"
}

# Função para aplicar manifestos
apply_manifests() {
    local path=$1
    local description=$2
    log_status "Aplicando $description em $path..."
    find "$path" -maxdepth 1 -name "*.yaml" -print0 | while IFS= read -r -d $'\0' file; do
        log_status "   -> Aplicando $(basename "$file")..."
        kubectl apply -f "$file" -n "$NAMESPACE"
    done
}

# Função para instalar o Kong via Helm
install_kong_helm() {
    log_status "Instalando o Kong Ingress Controller via Helm..."
    helm repo add kong https://charts.konghq.com
    helm repo update

    # Instala o Kong no namespace 'kong' (padrão )
    helm install kong kong/kong -n kong --create-namespace --wait
    log_success "Kong Ingress Controller instalado com sucesso."
}

# ==============================================================================
# Início do Deploy
# ==============================================================================

log_status "========================================================"
log_status "🚀 INICIANDO DEPLOY DA APLICAÇÃO E KONG GATEWAY NO EKS"
log_status "========================================================"
log_status "Namespace de destino: ${NAMESPACE}"

# 1. Verificação e Criação do Namespace
log_status "1. Verificando e criando o Namespace..."
kubectl get namespace "$NAMESPACE" &> /dev/null || kubectl apply -f "${APP_PATH}/main/namespace.yaml"

# 2. Deploy de Secrets e ConfigMaps (Database)
log_status "2. Aplicando Configurações de Banco de Dados (Secrets e ConfigMaps)..."
apply_manifests "${APP_PATH}/database/env" "Secrets e ConfigMaps"

# 3. Deploy da Aplicação Spring Boot (Deployment e Service)
log_status "3. Aplicando Deploy da Aplicação Spring Boot..."
kubectl apply -f "${APP_PATH}/main/deployment.yaml" -n "$NAMESPACE"
kubectl apply -f "${APP_PATH}/main/services.yaml" -n "$NAMESPACE" # Service ClusterIP

# 4. Instalação do Kong Ingress Controller
log_status "4. Instalando o Kong Ingress Controller..."
install_kong_helm

# 5. Deploy do Kong Gateway (Plugins e Ingress)
log_status "4. Aplicando Configuração do Kong Gateway (Plugins e Ingress)..."
apply_manifests "$KONG_PATH" "Plugins e Ingress"

# 6. Aplicando HPA
log_status "5. Aplicando Horizontal Pod Autoscaler (HPA)..."
kubectl apply -f "${APP_PATH}/main/hpa.yaml" -n "$NAMESPACE"

# 7. Verificação Final
log_status "6. Verificando status dos Pods..."
kubectl get pods -n "$NAMESPACE"

log_status "========================================================"
log_success "DEPLOY COMPLETO! A aplicação está sendo exposta pelo Kong Gateway."
log_status "========================================================"
