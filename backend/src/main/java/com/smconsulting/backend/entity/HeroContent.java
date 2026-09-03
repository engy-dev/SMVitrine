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
@Table(name = "hero_content", schema = "vitrine")
public class HeroContent {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", nullable = false, unique = true)
    private Page page;

    @Column(name = "eyebrow_text")
    private String eyebrowText;

    @Column(name = "headline_before", nullable = false)
    private String headlineBefore;

    @Column(name = "headline_emphasis")
    private String headlineEmphasis;

    @Column(name = "headline_after")
    private String headlineAfter;

    private String subheadline;

    @Column(name = "cta_label")
    private String ctaLabel;

    @Column(name = "cta_url")
    private String ctaUrl;

    @Column(name = "cta_secondary_label")
    private String ctaSecondaryLabel;

    @Column(name = "cta_secondary_url")
    private String ctaSecondaryUrl;

    public UUID getId() {
        return id;
    }

    public Page getPage() {
        return page;
    }

    public String getEyebrowText() {
        return eyebrowText;
    }

    public String getHeadlineBefore() {
        return headlineBefore;
    }

    public String getHeadlineEmphasis() {
        return headlineEmphasis;
    }

    public String getHeadlineAfter() {
        return headlineAfter;
    }

    public String getSubheadline() {
        return subheadline;
    }

    public String getCtaLabel() {
        return ctaLabel;
    }

    public String getCtaUrl() {
        return ctaUrl;
    }

    public String getCtaSecondaryLabel() {
        return ctaSecondaryLabel;
    }

    public String getCtaSecondaryUrl() {
        return ctaSecondaryUrl;
    }
}