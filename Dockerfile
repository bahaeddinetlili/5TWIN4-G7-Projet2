# Création de l'image de projet à partir de jar dans le nexus
FROM openjdk:12-alpine

#EXPOSE 8085

ADD http://192.168.33.10:8081/repository/maven-snapshots/ /app.jar


# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]
##############################################

