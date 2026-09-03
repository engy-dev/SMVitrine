package com.smconsulting.backend.dto;
import java.util.UUID;

/**
 * DTO renvoyé au frontend après le traitement d'une demande de contact.
 *
 * On reste volontairement minimaliste : le frontend a juste besoin d'un
 * message de confirmation à afficher à l'utilisateur, et éventuellement de
 * l'identifiant créé (utile pour du debug ou un futur suivi).
 */
public class ContactResponseDTO {

    private String message;
    private UUID id;

    public ContactResponseDTO() {
    }

    public ContactResponseDTO(String message, UUID id) {
        this.message = message;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
