# Microservicio con Jacoco y Sonar
### Plugins para Eclipse (opcional):
Para Jacoco:
https://www.eclemma.org/
Para SonarLint, buscarlo en el Eclipse Marketplace
### Ejecucion:
Ejecucion de tests que incluyen la cobertura con Jacoco:
mvn clean test
Ejecucion de Sonar:
mvn sonar:sonar

### Extras:
DevTools: Acceder a http://localhost:8082/h2-console/ para ver consola de base en memoria
Actuator: Ver Apis de Acturator: http://localhost:8082/actuator

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* https://www.baeldung.com/sonar-qube
### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

