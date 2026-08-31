package com.smconsulting.backend.service;

import com.smconsulting.backend.dto.HeroSectionResponse;
import com.smconsulting.backend.dto.HeroStatResponse;
import com.smconsulting.backend.entity.HeroContent;
import com.smconsulting.backend.repository.HeroContentRepository;
import com.smconsulting.backend.repository.HeroStatRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HeroService {

    private final HeroContentRepository heroContentRepository;
    private final HeroStatRepository heroStatRepository;

    public HeroService(HeroContentRepository heroContentRepository,
                        HeroStatRepository heroStatRepository) {
        this.heroContentRepository = heroContentRepository;
        this.heroStatRepository = heroStatRepository;
    }

    public HeroSectionResponse getHeroSection(String pageSlug) {
        HeroContent content = heroContentRepository.findByPage_Slug(pageSlug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun contenu 'hero' pour la page '" + pageSlug + "'"));

        List<HeroStatResponse> stats = heroStatRepository
                .findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(pageSlug)
                .stream()
                .map(s -> new HeroStatResponse(s.getStatValue(), s.getStatLabel()))
                .toList();

        return new HeroSectionResponse(
                content.getEyebrowText(),
                content.getHeadlineBefore(),
                content.getHeadlineEmphasis(),
                content.getHeadlineAfter(),
                content.getSubheadline(),
                content.getCtaLabel(),
                content.getCtaUrl(),
                content.getCtaSecondaryLabel(),
                content.getCtaSecondaryUrl(),
                stats
        );
    }
}