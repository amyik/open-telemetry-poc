#!/bin/sh

skaffold dev \
--default-repo=registry.insecure.io:5000 \
#--filename skaffold.yaml \
#--port-forward=true --wait-for-deletions-max=2m0s \
#--status-check=true --verbosity info --timestamps=true --kubeconfig=/home/albert/.kube/config