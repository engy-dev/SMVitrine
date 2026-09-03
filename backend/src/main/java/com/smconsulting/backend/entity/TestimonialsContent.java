package com.smconsulting.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "testimonials_content", schema = "vitrine")
public class TestimonialsContent {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", nullable = false, unique = true)
    private Page page;

    @Column(name = "eyebrow_text")
    private String eyebrowText;

    @Column(nullable = false)
    private String title;

    public UUID getId() {
        return id;
    }

    public Page getPage() {
        return page;
    }

    public String getEyebrowText() {
        return eyebrowText;
    }

    public String getTitle() {
        return title;
    }
}