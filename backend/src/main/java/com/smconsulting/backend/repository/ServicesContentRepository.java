package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.ServicesContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ServicesContentRepository extends JpaRepository<ServicesContent, UUID> {

    Optional<ServicesContent> findByPage_Slug(String slug);
}