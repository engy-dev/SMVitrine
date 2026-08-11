package com.smconsulting.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) représentant les données envoyées par le
 * formulaire de contact du site (composant Vue "TheContactForm.vue").
 *
 * Pourquoi un DTO séparé de l'entité ContactRequest ?
 * ----------------------------------------------------
 * On ne veut jamais exposer directement notre entité JPA dans l'API :
 *   - Cela évite qu'un client externe puisse envoyer des champs qu'il ne
 *     devrait pas contrôler (ex : l'id, la date d'envoi...).
 *   - Cela permet de faire évoluer le modèle de base de données sans casser
 *     le contrat de l'API, et inversement.
 *   - Cela permet d'ajouter des règles de validation spécifiques à l'entrée
 *     utilisateur (voir les annotations ci-dessous), différentes des
 *     contraintes de la base de données.
 *
 * Les annotations @NotBlank, @Email, @Size proviennent de la spécification
 * Bean Validation (Jakarta Validation). Elles sont vérifiées automatiquement
 * par Spring dès qu'on ajoute l'annotation @Valid devant le paramètre dans le
 * contrôleur (voir ContactController). Si une contrainte n'est pas respectée,
 * Spring lève une exception que l'on intercepte dans GlobalExceptionHandler.
 */
public class ContactRequestDTO {

    @NotBlank(message = "Le nom complet est obligatoire.")
    @Size(max = 150, message = "Le nom complet ne doit pas dépasser 150 caractères.")
    private String nomComplet;

    @NotBlank(message = "L'adresse e-mail est obligatoire.")
    @Email(message = "L'adresse e-mail n'est pas valide.")
    private String email;

    // Le téléphone est optionnel : pas d'annotation @NotBlank ici.
    @Size(max = 30, message = "Le numéro de téléphone n'est pas valide.")
    private String telephone;

    @Size(max = 150, message = "Le nom de l'entreprise ne doit pas dépasser 150 caractères.")
    private String entreprise;

    @Size(max = 150, message = "Le sujet ne doit pas dépasser 150 caractères.")
    private String sujet;

    @NotBlank(message = "Le message ne peut pas être vide.")
    @Size(max = 2000, message = "Le message ne doit pas dépasser 2000 caractères.")
    private String message;

    public ContactRequestDTO() {
    }

    // --- Getters et setters -------------------------------------------------

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
