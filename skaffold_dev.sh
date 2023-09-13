#!/bin/sh

skaffold dev \
--default-repo=registry.insecure.io:5000 \
--cleanup=false
--wait-for-deletions-max=2m0s
#--filename=skaffold.yaml \
#--port-forward=true --wait-for-deletions-max=2m0s \
#--status-check=true --verbosity info --timestamps=true --kubeconfig=/home/albert/.kube/config