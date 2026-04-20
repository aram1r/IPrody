FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/libraryservice.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
LABEL authors="alex"