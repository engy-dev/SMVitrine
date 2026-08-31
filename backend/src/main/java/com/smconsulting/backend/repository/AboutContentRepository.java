package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.AboutContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AboutContentRepository extends JpaRepository<AboutContent, UUID> {

    Optional<AboutContent> findByPage_Slug(String slug);
}