package com.smconsulting.backend.repository;

import com.smconsulting.backend.entity.ContactSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactSubjectRepository extends JpaRepository<ContactSubject, UUID> {

    List<ContactSubject> findByPublishedTrueOrderBySortOrderAsc();
}