#!/bin/sh

skaffold debug \
--filename skaffold.yaml --default-repo registry.insecure.io:5000 \
--port-forward=true --wait-for-deletions-max=2m0s \
--status-check=true --verbosity trace --timestamps=true --kubeconfig=/home/albert/.kube/config