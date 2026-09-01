package com.smconsulting.backend.service;

import com.smconsulting.backend.dto.MethodSectionResponse;
import com.smconsulting.backend.dto.MethodStepResponse;
import com.smconsulting.backend.entity.MethodContent;
import com.smconsulting.backend.repository.MethodContentRepository;
import com.smconsulting.backend.repository.MethodStepRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MethodSectionService {

    private final MethodContentRepository methodContentRepository;
    private final MethodStepRepository methodStepRepository;

    public MethodSectionService(MethodContentRepository methodContentRepository,
                                 MethodStepRepository methodStepRepository) {
        this.methodContentRepository = methodContentRepository;
        this.methodStepRepository = methodStepRepository;
    }

    public MethodSectionResponse getMethodSection(String pageSlug) {
        MethodContent content = methodContentRepository.findByPage_Slug(pageSlug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun contenu 'method' pour la page '" + pageSlug + "'"));

        List<MethodStepResponse> etapes = methodStepRepository
                .findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(pageSlug)
                .stream()
                .map(s -> new MethodStepResponse(
                        String.format("%02d", s.getStepNumber()),
                        s.getTitle(),
                        s.getDescription()))
                .toList();

        return new MethodSectionResponse(content.getEyebrowText(), content.getTitle(), etapes);
    }
}