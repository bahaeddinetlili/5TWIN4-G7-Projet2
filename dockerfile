FROM openjdk:8
EXPOSE 8089
ADD target/KaddemProject.jar KaddemProject.jar
ENTRYPOINT ["java","-jar","/KaddemProject.jar"]