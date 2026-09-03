package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.ContactPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactPointRepository extends JpaRepository<ContactPoint, UUID> {

    List<ContactPoint> findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(String slug);
}