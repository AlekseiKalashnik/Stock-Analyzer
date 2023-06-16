#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY --from=builder /app/target/*.jar /app/*.jar
#EXPOSE 8181
#ENTRYPOINT ["java", "-jar", "/app/*.jar"]
FROM alpine:3.10
CMD echo "Hello"