package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.MethodContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MethodContentRepository extends JpaRepository<MethodContent, UUID> {

    Optional<MethodContent> findByPage_Slug(String slug);
}