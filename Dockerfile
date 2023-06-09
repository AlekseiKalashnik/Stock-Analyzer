FROM openjdk:17-oracle
EXPOSE 8181
ARG JAR_FILE=*.jar
COPY target/Stock-Analyzer-0.0.1-SNAPSHOT.jar analyzer.jar
ENTRYPOINT ["java", "-jar", "/analyzer.jar"]