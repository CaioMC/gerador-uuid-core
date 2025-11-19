#!/bin/bash
set -e

# =============================
# Deploy da aplicação Spring Boot no Kubernetes EKS
# =============================

NAMESPACE="gerador-uuid-prod"
K8S_PATH="./devops/k8s/prod"

echo "Iniciando deploy Kubernetes no namespace: $NAMESPACE"

# Verifica se o namespace existe
kubectl get namespace $NAMESPACE || kubectl apply -f $K8S_PATH/main/namespace.yaml

echo "Aplicando secrets..."
kubectl apply -f $K8S_PATH/database/env/aws-rds-postgres-secret.yaml -n $NAMESPACE
kubectl apply -f $K8S_PATH/database/env/aws-rds-postgres-configmap.yaml -n $NAMESPACE

echo "Subindo aplicação Spring Boot..."
kubectl apply -f $K8S_PATH/main/deployment.yaml -n $NAMESPACE

echo "Criando service para expor aplicação..."
kubectl apply -f $K8S_PATH/main/services.yaml -n $NAMESPACE

echo "Aplicando Horizontal Pod Autoscaler..."
kubectl apply -f $K8S_PATH/main/hpa.yaml -n $NAMESPACE

echo "✅ Deploy realizado com sucesso no EKS!"
