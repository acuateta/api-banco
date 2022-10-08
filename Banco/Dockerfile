FROM openjdk:8-jdk-alpine
LABEL maintainer="acuateta@gmail.com"
WORKDIR /workspace
ADD ./target/app*.jar app.jar

ENTRYPOINT exec java -jar /workspace/app.jar
EXPOSE 8080