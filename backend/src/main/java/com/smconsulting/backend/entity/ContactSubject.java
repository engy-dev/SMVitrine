package com.smconsulting.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

// Pas de page_id ici : contrairement à ContactPoint, cette liste n'est
// pas rattachée à une page précise (voir le commentaire SQL). Pas de
// relation ManyToOne vers Page, donc.
@Entity
@Table(name = "contact_subjects", schema = "vitrine")
public class ContactSubject {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String label;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Column(name = "is_published", nullable = false)
    private Boolean published;

    public UUID getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Boolean getPublished() {
        return published;
    }
}