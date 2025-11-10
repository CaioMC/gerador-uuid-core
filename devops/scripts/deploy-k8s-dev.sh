#!/bin/bash
set -e

# =============================
# Deploy da aplicação Spring Boot + PostgreSQL no Kubernetes local com Docker
# =============================

NAMESPACE="gerador-uuid-dev"
NAMESPACE_METRICS="kube-system"
K8S_PATH="./devops/k8s/dev"

echo "-------------------------------------------"
echo "-------------------------------------------"
echo "----------------FASE 1---------------------"
echo "-------------------------------------------"
echo "-------------------------------------------"

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

echo "Subindo aplicação Gerador UUID Spring Boot..."
kubectl apply -f $K8S_PATH/main/deployment.yaml -n $NAMESPACE

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
echo "-------------------------------------------"
echo "----------------FASE 2---------------------"
echo "-------------------------------------------"
echo "-------------------------------------------"

echo "🚀 Iniciando deploy Kubernetes no namespace: $NAMESPACE_METRICS"

if ! kubectl get namespace "$NAMESPACE_METRICS" &>/dev/null; then
  echo "Criando namespace $NAMESPACE_METRICS..."
  kubectl apply -f $K8S_PATH/metrics/namespace.yaml
else
  echo "Namespace $NAMESPACE_METRICS já existe."
fi

echo "Aplicando conta..."
kubectl apply -f $K8S_PATH/metrics/serviceaccount.yaml -n $NAMESPACE_METRICS

echo "Aplicando roles..."
kubectl apply -f $K8S_PATH/metrics/role/clusterrole.yaml -n $NAMESPACE_METRICS

echo "Aplicando roles bind..."
kubectl apply -f $K8S_PATH/metrics/role/bind/rolebind.yaml -n $NAMESPACE_METRICS

echo "Aplicando cluster roles bind..."
kubectl apply -f $K8S_PATH/metrics/role/bind/clusterrolebinding.yaml -n $NAMESPACE_METRICS

echo "Subindo aplicação Metrics Server..."
kubectl apply -f $K8S_PATH/metrics/deployment.yaml -n $NAMESPACE_METRICS

echo "Criando service para expor o Metrics Server..."
kubectl apply -f $K8S_PATH/metrics/service.yaml -n $NAMESPACE_METRICS

echo "Subindo APIService para acesso consumo de metricas..."
kubectl apply -f $K8S_PATH/metrics/apiservice.yaml -n $NAMESPACE_METRICS

echo "✅ Deploy concluído!"
echo "-------------------------------------------"
echo "Verifique os pods com:"
echo "  kubectl get pods -n $NAMESPACE_METRICS"
echo ""