FROM openjdk:17-oracle
EXPOSE 8181
ARG JAR_FILE=*.jar
COPY Stock-Analyzer-0.0.1-SNAPSHOT.jar analyzer.jar
ENTRYPOINT ["java", "-jar", "/analyzer.jar"]

#FROM maven:3.8.5-openjdk-17 AS builder
#WORKDIR /app
#COPY . /app/.
#RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

#FROM eclipse-temurin:17-jre-alpine
#WORKDIR /app
#COPY --from=builder /app/target/*.jar /app/*.jar
#EXPOSE 8181
#ENTRYPOINT ["java", "-jar", "/app/*.jar"]

#FROM alpine:3.10
#CMD echo "Hello"