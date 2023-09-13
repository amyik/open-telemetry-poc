
## jaeger설치 전 cert manager 설치가 필요하다.
https://cert-manager.io/docs/installation/helm/

```bash
# 설치
helm repo add jetstack https://charts.jetstack.io
helm repo update
helm install \
  cert-manager jetstack/cert-manager \
  --namespace cert-manager \
  --create-namespace \
  --version v1.12.4 \
  --set installCRDs=true
```

```bash
# 삭제
kubectl get Issuers,ClusterIssuers,Certificates,CertificateRequests,Orders,Challenges --all-namespaces
helm --namespace cert-manager delete cert-manager
kubectl delete namespace cert-manager
```

## jaeger operator 설치
```bash
kubectl create namespace observability
kubectl create -f https://github.com/jaegertracing/jaeger-operator/releases/download/v1.49.0/jaeger-operator.yaml -n observability
```
