package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.MethodStep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MethodStepRepository extends JpaRepository<MethodStep, UUID> {

    List<MethodStep> findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(String slug);
}