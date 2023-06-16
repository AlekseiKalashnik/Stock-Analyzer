FROM openjdk:17-oracle
#EXPOSE 8181
ARG JAR_FILE=*.jar
COPY --from=builder /app/*.jar /app/*.jar
ENTRYPOINT ["java", "-jar", "/app/*.jar"]
#FROM alpine:3.10
#CMD echo "Hello"