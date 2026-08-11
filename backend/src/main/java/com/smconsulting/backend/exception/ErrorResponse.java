package com.smconsulting.backend.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Structure JSON standard renvoyée au frontend en cas d'erreur.
 *
 * Avoir un format d'erreur cohérent sur toute l'API permet au frontend de
 * traiter les erreurs de manière uniforme (voir contactService.js), plutôt
 * que de devoir gérer un format différent pour chaque type d'erreur.
 */
public class ErrorResponse {

    /** Horodatage de l'erreur, utile pour le débogage et les logs. */
    private LocalDateTime horodatage;

    /** Code HTTP renvoyé (ex : 400 pour une erreur de validation). */
    private int statut;

    /** Message d'erreur générique, lisible par un humain. */
    private String message;

    /**
     * Détail des erreurs de validation, champ par champ
     * (ex : { "email": "L'adresse e-mail n'est pas valide." }).
     * Ce champ est vide (null) pour les erreurs qui ne sont pas liées à la
     * validation d'un formulaire.
     */
    private Map<String, String> erreursDeChamp;

    public ErrorResponse(int statut, String message, Map<String, String> erreursDeChamp) {
        this.horodatage = LocalDateTime.now();
        this.statut = statut;
        this.message = message;
        this.erreursDeChamp = erreursDeChamp;
    }

    public LocalDateTime getHorodatage() {
        return horodatage;
    }

    public int getStatut() {
        return statut;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErreursDeChamp() {
        return erreursDeChamp;
    }
}
