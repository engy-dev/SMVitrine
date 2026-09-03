package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.HeroStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HeroStatRepository extends JpaRepository<HeroStat, UUID> {

    List<HeroStat> findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(String slug);
}