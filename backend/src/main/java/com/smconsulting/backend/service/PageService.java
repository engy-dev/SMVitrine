package com.smconsulting.backend.service;

import com.smconsulting.backend.dto.PageContentResponse;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    private final HeroService heroService;
    private final AboutService aboutService;
    private final ServicesSectionService servicesSectionService;

    public PageService(HeroService heroService,
                        AboutService aboutService,
                        ServicesSectionService servicesSectionService) {
        this.heroService = heroService;
        this.aboutService = aboutService;
        this.servicesSectionService = servicesSectionService;
    }

    public PageContentResponse getPageContent(String slug) {
        return new PageContentResponse(
                heroService.getHeroSection(slug),
                aboutService.getAboutSection(slug),
                servicesSectionService.getServicesSection(slug)
        );
    }
}