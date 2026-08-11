package com.smconsulting.backend.dto;

/**
 * DTO renvoyé au frontend après le traitement d'une demande de contact.
 *
 * On reste volontairement minimaliste : le frontend a juste besoin d'un
 * message de confirmation à afficher à l'utilisateur, et éventuellement de
 * l'identifiant créé (utile pour du debug ou un futur suivi).
 */
public class ContactResponseDTO {

    private String message;
    private Long id;

    public ContactResponseDTO() {
    }

    public ContactResponseDTO(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
