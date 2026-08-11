package com.smconsulting.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Gestionnaire global des exceptions de l'API.
 *
 * @RestControllerAdvice permet de centraliser la gestion des erreurs pour
 * TOUS les contrôleurs de l'application, plutôt que de dupliquer des blocs
 * try/catch dans chaque méthode. Dès qu'une exception listée ci-dessous est
 * levée n'importe où dans un contrôleur, Spring intercepte automatiquement
 * l'erreur et exécute la méthode correspondante ci-dessous pour construire
 * une réponse HTTP propre.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Intercepte les erreurs de validation Bean Validation (@Valid) levées
     * quand les données du formulaire de contact ne respectent pas les règles
     * définies dans ContactRequestDTO (champ vide, e-mail invalide, etc.).
     *
     * On transforme la liste brute d'erreurs Spring en une simple Map
     * "nom du champ -> message d'erreur", beaucoup plus facile à exploiter
     * côté frontend pour afficher le bon message sous le bon champ du
     * formulaire.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> gererErreursDeValidation(MethodArgumentNotValidException ex) {
        Map<String, String> erreurs = new HashMap<>();

        for (FieldError erreurDeChamp : ex.getBindingResult().getFieldErrors()) {
            erreurs.put(erreurDeChamp.getField(), erreurDeChamp.getDefaultMessage());
        }

        ErrorResponse reponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Le formulaire contient des erreurs. Merci de corriger les champs indiqués.",
                erreurs
        );

        return ResponseEntity.badRequest().body(reponse);
    }

    /**
     * Filet de sécurité : intercepte toute autre exception non prévue afin de
     * ne jamais renvoyer une erreur brute (ou une pile d'appels Java) au
     * client, ce qui serait à la fois peu professionnel et un risque de
     * sécurité (fuite d'informations internes sur le serveur).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> gererErreurInattendue(Exception ex) {
        ErrorResponse reponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Une erreur inattendue est survenue. Merci de réessayer dans quelques instants.",
                null
        );

        return ResponseEntity.internalServerError().body(reponse);
    }
}
