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
@Table(name = "hero_stats", schema = "vitrine")
public class HeroStat {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id", nullable = false)
    private Page page;

    @Column(name = "stat_value", nullable = false)
    private String statValue;

    @Column(name = "stat_label", nullable = false)
    private String statLabel;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Column(name = "is_published", nullable = false)
    private Boolean published;

    public UUID getId() {
        return id;
    }

    public String getStatValue() {
        return statValue;
    }

    public String getStatLabel() {
        return statLabel;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Boolean getPublished() {
        return published;
    }
}