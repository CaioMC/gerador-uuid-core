#!/bin/bash
# =========================================================
# Script simples para gerar carga em um endpoint HTTP
# =========================================================

URL="http://localhost:8081/actuator/health"

# Quantidade de requisições simultâneas (processos paralelos)
CONCURRENCY=1000

# Duração total do teste em segundos
DURATION=20

echo "Iniciando teste de carga por ${DURATION}s em:"
echo "  $URL"
echo "📊 Concorrência: $CONCURRENCY processos"

# Função que faz requisições contínuas
flood() {
  end=$((SECONDS + DURATION))
  while [ $SECONDS -lt $end ]; do
    curl -s -o /dev/null -w "%{http_code}\n" "$URL" &
  done
}

# Inicia múltiplos processos em paralelo
for i in $(seq 1 $CONCURRENCY); do
  flood &
done

wait
echo "✅ Teste concluído!"
