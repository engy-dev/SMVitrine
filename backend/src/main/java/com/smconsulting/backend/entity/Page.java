package com.smconsulting.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "pages", schema = "vitrine")
public class Page {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String slug;

    public UUID getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }
}