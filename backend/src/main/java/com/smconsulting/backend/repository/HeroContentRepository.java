package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.HeroContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HeroContentRepository extends JpaRepository<HeroContent, UUID> {

    Optional<HeroContent> findByPage_Slug(String slug);
}