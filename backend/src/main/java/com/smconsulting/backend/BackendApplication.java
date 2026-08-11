package com.smconsulting.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l'application.
 *
 * Ce projet est construit avec Spring Boot 4.1, qui repose sur Spring
 * Framework 7 et Jakarta EE 11 (voir les commentaires de backend/pom.xml
 * pour le détail de cette base technique). Cette classe elle-même n'a pas
 * changé par rapport à un projet Spring Boot 3 : l'annotation
 * @SpringBootApplication et le mécanisme de démarrage restent identiques.
 *
 * C'est le point d'entrée du programme : c'est cette méthode "main" qui est
 * exécutée quand on démarre le serveur (via "mvn spring-boot:run" ou
 * "java -jar backend.jar").
 *
 * L'annotation @SpringBootApplication est en réalité un raccourci qui combine
 * trois annotations :
 *   - @Configuration      : indique que cette classe peut définir des beans Spring
 *   - @EnableAutoConfiguration : demande à Spring Boot de configurer automatiquement
 *                                l'application selon les dépendances présentes
 *                                (ex : configure automatiquement un serveur Tomcat
 *                                car spring-boot-starter-web est dans le pom.xml)
 *   - @ComponentScan      : demande à Spring de scanner ce package (et ses
 *                           sous-packages) à la recherche de classes annotées
 *                           (@RestController, @Service, @Repository, etc.)
 *                           afin de les enregistrer automatiquement.
 */
@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        // Démarre le serveur Spring Boot embarqué (Tomcat par défaut sur le port 8080).
        SpringApplication.run(BackendApplication.class, args);
    }

}
