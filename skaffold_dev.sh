#!/bin/sh
# options: https://skaffold.dev/docs/references/cli/#skaffold-dev
skaffold dev \
--default-repo=registry.insecure.io:5000 \
#--cleanup=false
#--cleanup=false \
#--filename=skaffold.yaml \
#--port-forward=true --wait-for-deletions-max=2m0s \
#--status-check=true --verbosity info --timestamps=true --kubeconfig=/home/albert/.kube/config