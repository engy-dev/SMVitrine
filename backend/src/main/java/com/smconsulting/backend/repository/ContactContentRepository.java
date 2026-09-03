package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.ContactContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContactContentRepository extends JpaRepository<ContactContent, UUID> {

    Optional<ContactContent> findByPage_Slug(String slug);
}