FROM openjdk:11
EXPOSE 8089
COPY target/kaddem-0.0.1-SNAPSHOT.jar kaddem-0.0.1-SNAPSHOT.jar
CMD java  -jar kaddem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/kaddem-0.0.1-snapshot.jar "]