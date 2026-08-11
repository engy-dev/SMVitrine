package com.smconsulting.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration CORS (Cross-Origin Resource Sharing).
 *
 * Pourquoi c'est nécessaire ?
 * ----------------------------
 * En développement, le frontend Vue tourne sur son propre serveur (Vite, par
 * défaut http://localhost:5173) tandis que le backend Spring Boot tourne sur
 * http://localhost:8080. Ce sont donc deux "origines" différentes du point de
 * vue du navigateur, qui bloque par défaut les requêtes JavaScript faites
 * d'une origine vers une autre (politique de sécurité "same-origin").
 *
 * Cette classe autorise explicitement le frontend à appeler l'API backend.
 * L'origine autorisée est lue depuis application.properties afin de pouvoir
 * facilement la changer entre les environnements (dev / production) sans
 * toucher au code.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Valeur injectée depuis application.properties (clé : app.cors.allowed-origin).
     * En développement, elle vaut http://localhost:5173 (port par défaut de Vite).
     * En production, elle devra pointer vers le nom de domaine réel du site
     * (ex : https://www.sm-consulting.fr).
     */
    @Value("${app.cors.allowed-origin}")
    private String allowedOrigin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")            // s'applique à toutes les routes de notre API
                .allowedOrigins(allowedOrigin)     // autorise uniquement le frontend officiel
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
