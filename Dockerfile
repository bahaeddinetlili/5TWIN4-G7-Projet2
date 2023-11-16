FROM openjdk:11
EXPOSE 8089
COPY kaddem-0.0.1-SNAPSHOT.jar kaddem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/kaddem-0.0.1-snapshot.jar "]