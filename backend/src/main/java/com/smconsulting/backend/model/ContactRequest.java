package com.smconsulting.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

/**
 * Entité JPA représentant une demande de contact envoyée depuis le site vitrine.
 *
 * Une "entité" est une classe Java qui correspond directement à une table en
 * base de données : chaque instance de ContactRequest correspond à une ligne
 * dans la table "contact_requests", et chaque attribut correspond à une colonne.
 *
 * C'est Hibernate (le moteur ORM utilisé par Spring Data JPA) qui se charge de
 * créer la table et de faire la conversion Java <-> SQL automatiquement.
 */
@Entity
@Table(name = "contact_requests")
public class ContactRequest {

    /**
     * Identifiant unique de la demande, généré automatiquement par la base de
     * données (auto-incrément). C'est notre clé primaire.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Nom complet de la personne qui a rempli le formulaire. */
    @Column(nullable = false)
    private String nomComplet;

    /** Adresse e-mail de contact, utilisée pour recontacter le prospect. */
    @Column(nullable = false)
    private String email;

    /** Numéro de téléphone (optionnel). */
    @Column
    private String telephone;

    /** Nom de l'entreprise du prospect (optionnel mais très utile pour le suivi commercial). */
    @Column
    private String entreprise;

    /**
     * Sujet / besoin principal exprimé par le prospect
     * (ex : "Recrutement", "Formation", "Accompagnement au changement"...).
     */
    @Column
    private String sujet;

    /** Message libre décrivant la demande du prospect. */
    @Column(length = 2000)
    private String message;

    /**
     * Date et heure d'envoi de la demande, renseignée automatiquement par le
     * serveur au moment de l'enregistrement (voir ContactService).
     */
    @Column(nullable = false)
    private LocalDateTime dateEnvoi;

    /**
     * Constructeur vide requis par JPA/Hibernate : le framework l'utilise en
     * interne pour reconstruire des objets à partir de la base de données.
     */
    public ContactRequest() {
    }

    /**
     * Constructeur pratique utilisé par notre code métier (ContactService)
     * pour créer une nouvelle demande à partir des données du formulaire.
     */
    public ContactRequest(String nomComplet, String email, String telephone,
                           String entreprise, String sujet, String message,
                           LocalDateTime dateEnvoi) {
        this.nomComplet = nomComplet;
        this.email = email;
        this.telephone = telephone;
        this.entreprise = entreprise;
        this.sujet = sujet;
        this.message = message;
        this.dateEnvoi = dateEnvoi;
    }

    // --- Getters et setters -------------------------------------------------
    // Ils sont nécessaires car Hibernate et Jackson (sérialisation JSON) s'appuient
    // sur la convention JavaBean (getX()/setX()) pour lire et écrire les champs.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }
}
