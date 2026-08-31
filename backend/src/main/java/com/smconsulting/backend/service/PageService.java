package com.smconsulting.backend.service;

import com.smconsulting.backend.dto.PageContentResponse;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    private final HeroService heroService;
    private final AboutService aboutService;

    public PageService(HeroService heroService, AboutService aboutService) {
        this.heroService = heroService;
        this.aboutService = aboutService;
    }

    public PageContentResponse getPageContent(String slug) {
        return new PageContentResponse(
                heroService.getHeroSection(slug),
                aboutService.getAboutSection(slug)
        );
    }
}