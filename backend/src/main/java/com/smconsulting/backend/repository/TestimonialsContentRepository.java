package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.TestimonialsContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TestimonialsContentRepository extends JpaRepository<TestimonialsContent, UUID> {

    Optional<TestimonialsContent> findByPage_Slug(String slug);
}