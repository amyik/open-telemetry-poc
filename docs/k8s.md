## ingress 생성 오류
```bash
 - Error from server (InternalError): error when creating "STDIN": Internal error occurred: failed calling webhook "validate.nginx.ingress.kubernetes.io": failed to call webhook: Post "https://ingress-nginx-controller-admission.ingress-nginx.svc:443/networking/v1/ingresses?timeout=10s": tls: failed to verify certificate: x509: certificate signed by unknown authority
```

```bash
kubectl delete -A ValidatingWebhookConfiguration ingress-nginx-admission
```


# kubeadm을 이용한 설치방법

## 1. 준비
### Contaainer Runtime설치
- cgroup drivers설정 : ubuntu는 systemd를 선택
- [링크참조](https://kubernetes.io/docs/setup/production-environment/container-runtimes/)

### kubeadm, kubelet, kubectl설치
- [링크참조](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/install-kubeadm/)
## 2. Cluster 구성하기

- [링크참조](https://kubernetes.io/docs/setup/production-environment/tools/kubeadm/create-cluster-kubeadm/)

### Cluster 구성
``` bash
# kubectl drain <node name> --delete-emptydir-data --force --ignore-daemonsets # optional
# sudo kubeadm reset # optional
# iptables -F && iptables -t nat -F && iptables -t mangle -F && iptables -X # optional
# ipvsadm -C # optional
rm ~/.kube/config
sudo kubeadm init --pod-network-cidr 10.244.0.0/16
sudo cp /etc/kubernetes/admin.conf ~/.kube/config
sudo chmod 666 ~/.kube/config

# kubectl taint nodes --all node-role.kubernetes.io/control-plane- # optional
#  10.244.0.0/16 은 아래에서 배포할 flannel의 기본값
```

### Pod network add-on 배포
- [링크](https://github.com/flannel-io/flannel#deploying-flannel-manually
``` bash
kubectl apply -f https://github.com/flannel-io/flannel/releases/latest/download/kube-flannel.yml
```

### Ingress Controller 배포
- [버전 호환표](https://github.com/kubernetes/ingress-nginx)
- [링크참조](https://kubernetes.github.io/ingress-nginx/deploy/)
``` bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.8.2/deploy/static/provider/cloud/deploy.yaml
```

### 외부 machine에서 cluster 사용 시 ( kubelet 사용 )


```bash
scp albert@172.30.1.39:/home/albert/.kube/config /home/albert/.kube/config

export KUBECONFIG='/home/albert/.kube/config' # optionnal
```
