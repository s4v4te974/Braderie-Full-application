Informations concernant le projet

url de l'application : 
	
	http://localhost:9000/braderie
	
Lien vers le service REST: 

	http://localhost:9000/braderie/swagger-ui.html

Information de connexion : 

	adresse de la base de données PostgreSql = jdbc:postgresql://localhost:5432/braderie
	nom du User de la base de données = lab
	mot de passe de la base de données = lab123


************************** INFORMATIONS COMPLEMENTAIRES********************************

Afin de créer la base de données ( les données étant inclus dans l'application), il est nécessaire de modifier la valeur 
de "spring.jpa.hibernate.ddl-auto=" et de lui assigner la valeur "create" dans le fichier application.properties
(projectName/src/main/ressources/application.properties):

--------spring.jpa.hibernate.ddl-auto=create-----

Si la base est déjà crée/existante, il est nécessaire de modifier la valeur 
de "spring.jpa.hibernate.ddl-auto=" et de lui assigner la valeur "validate" dans le fichier application.properties
(projectName/src/main/ressources/application.properties):

--------spring.jpa.hibernate.ddl-auto=validate-----
