package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceOfferingRepository extends JpaRepository<ServiceOffering, UUID> {

    List<ServiceOffering> findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(String slug);
}