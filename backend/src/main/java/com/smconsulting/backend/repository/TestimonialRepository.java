package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TestimonialRepository extends JpaRepository<Testimonial, UUID> {

    List<Testimonial> findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(String slug);
}