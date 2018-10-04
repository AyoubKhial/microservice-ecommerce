FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/stock-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} stock-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/stock-service.jar"]