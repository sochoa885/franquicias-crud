FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/franchises-crud-0.0.1.jar
COPY ${JAR_FILE} franchises-crud.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "franchises-crud.jar"]