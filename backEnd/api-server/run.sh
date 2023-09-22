#!/bin/bash
# import 설정 정보 (dockerImage, dockerTag)
source ./property.sh

# docker image build
dockerImageFull=dockerImage:dockerTag;

echo "[DOCKER IMAGE BUILD]=========================="
docker build -t dockerImageFull

docker push dockerImageFull

