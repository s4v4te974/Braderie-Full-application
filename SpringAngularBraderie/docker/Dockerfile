#Permet d'indiquer l'images qui sera utiliser pour la construction de notre container
FROM adoptopenjdk/openjdk8:ubi

#permet d'exposer un port 
EXPOSE 9000

#permet de definir ce qui sera construit au moment de 
ARG JAR_FILE=*.jar

#permet d copier le fichier jar vers un executable
COPY ${JAR_FILE} app.jar

#permet a notre container d'etre un executable
ENTRYPOINT ["java","-jar","/app.jar"]