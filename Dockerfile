FROM openjdk:11
EXPOSE 8089
ADD target/Kaddem-0.0.1-SNAPSHOT.jar kaddem-0.0.1-snapshot.jar
ENTRYPOINT ["java", "-jar", "/kaddem-0.0.1-snapshot.jar "]