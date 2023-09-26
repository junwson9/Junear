#!/bin/bash
# import 설정 정보 (dockerImage, dockerTag)
source ./property.sh

# docker image build
dockerImageFull=${dockerImage}:${dockerTag}
dockerLatestTag=${dockerImage}:latest

echo "[DOCKER IMAGE BUILD]=========================="
docker build -t ${dockerImageFull} .
docker image tag ${dockerImageFull} ${dockerLatestTag}

docker push ${dockerImageFull}
docker push ${dockerLatestTag}
