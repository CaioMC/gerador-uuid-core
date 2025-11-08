#!/bin/bash
set -e

# =============================
# Deploy da aplicação Spring Boot + PostgreSQL no Kubernetes local com Docker
# =============================

NAMESPACE="gerenciador-oficina-dev"
K8S_PATH="./devops/k8s/dev"

echo "🚀 Iniciando deploy Kubernetes no namespace: $NAMESPACE"

# Verifica se o namespace existe
if ! kubectl get namespace "$NAMESPACE" &>/dev/null; then
  echo "Criando namespace $NAMESPACE..."
  kubectl apply -f $K8S_PATH/main/namespace.yaml
else
  echo "Namespace $NAMESPACE já existe."
fi

echo "Aplicando secrets..."
kubectl apply -f $K8S_PATH/database/env/postgres-secret.yaml -n $NAMESPACE

echo "Aplicando configmap..."
kubectl apply -f $K8S_PATH/database/env/configmap.yaml -n $NAMESPACE

echo "Subindo PostgreSQL..."
kubectl apply -f $K8S_PATH/database/postgres-deployment.yaml -n $NAMESPACE

echo "Criando service para expor o PostgreSQL..."
kubectl apply -f $K8S_PATH/database/postgres-service.yaml -n $NAMESPACE

echo "Criando service para expor aplicação principal..."
kubectl apply -f $K8S_PATH/main/services.yaml -n $NAMESPACE

echo "Aplicando Horizontal Pod Autoscaler..."
kubectl apply -f $K8S_PATH/main/hpa.yaml -n $NAMESPACE

echo "✅ Deploy concluído!"
echo "-------------------------------------------"
echo "Verifique os pods com:"
echo "  kubectl get pods -n $NAMESPACE"
echo ""
echo "Acesse a aplicação em: http://localhost:8081/swagger-ui/index.html"
echo "-------------------------------------------"
