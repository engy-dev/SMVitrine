package com.smconsulting.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "testimonials", schema = "vitrine")
public class Testimonial {

    @Id
    @GeneratedValue
    private UUID id;

    // Nullable côté base (voir commentaire du schéma d'origine : un
    // témoignage peut ne pas être rattaché à une page précise), donc pas
    // de nullable = false ici contrairement aux autres entités "enfant"
    // (ServiceOffering, MethodStep, HeroStat).
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private Page page;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "client_role")
    private String clientRole;

    @Column(name = "client_company")
    private String clientCompany;

    @Column(nullable = false)
    private String quote;

    @Column(name = "avatar_url")
    private String avatarUrl;

    private Short rating;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Column(name = "is_published", nullable = false)
    private Boolean published;

    public UUID getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientRole() {
        return clientRole;
    }

    public String getClientCompany() {
        return clientCompany;
    }

    public String getQuote() {
        return quote;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Short getRating() {
        return rating;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Boolean getPublished() {
        return published;
    }
}