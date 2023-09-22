FROM openjdk:17-jdk-slim AS builder
ENV APP_NAME = junear
WORKDIR app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN apt-get update && \
    apt-get install dos2unix && \
    apt-get clean
RUN dos2unix gradlew
RUN chmod +x ./gradlew
RUN ./gradlew bootjar

FROM openjdk:17-jdk-slim

ENV TZ Asia/Seoul
RUN	cp /usr/share/zoneinfo/${TZ} /etc/localtime && \
    echo "${TZ}" > /etc/timezone && \
    cat "/etc/localtime"

WORKDIR app


COPY --from=builder ./app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Duser.timezone=Asia/Seoul","-Dspring.profiles.active=${PROFILE}" ,"app.jar"]

