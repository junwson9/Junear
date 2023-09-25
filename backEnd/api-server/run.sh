#!/bin/bash


echo "도커 러닝 시작 ========================="
container_name="junear-spring"
image_name="dls3145/junear-api:latest"

echo "도커 이미지 pull 시작 ====================="
docker pull ${image_namem}
echo "도커 이미지 pull 성공====================="

# Check if the container is running
if docker ps --format "{{.Names}}" | grep -q "$container_name"; then
    echo "'$container_name' 가 실행중입니다"
    docker stop junear-spring
    docker rm junear-spring
    docker run -d -p 8080:8080 --name ${container_name} --network junear-net --env PROFILE=dev ${image_name}
else
    echo "'$container_name' 가 실행중이지 않습니다."
    docker run -d -p 8080:8080 --name ${container_name} --network junear-net --env PROFILE=dev ${image_name}
fi

echo "새로운 서버 생성 성공=========="

echo "===============기존 이미지 삭제=============="
docker rmi ${image_name}
docker rmi -f $(docker images -f "dangling=true" -q) || true