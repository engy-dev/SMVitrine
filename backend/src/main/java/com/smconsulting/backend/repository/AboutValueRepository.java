package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.AboutValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AboutValueRepository extends JpaRepository<AboutValue, UUID> {

    List<AboutValue> findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(String slug);
}