FROM maven:3.8.4-openjdk-17 AS builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17-oracle
EXPOSE 8181
ARG JAR_FILE=*.jar
COPY --from=builder /app/*.jar /app/*.jar
ENTRYPOINT ["java", "-jar", "/app/*.jar"]