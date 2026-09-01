package com.smconsulting.backend.service;

import com.smconsulting.backend.dto.PageContentResponse;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    private final HeroService heroService;
    private final AboutService aboutService;
    private final ServicesSectionService servicesSectionService;
    private final MethodSectionService methodSectionService;
    private final TestimonialsSectionService testimonialsSectionService;

    public PageService(HeroService heroService,
                        AboutService aboutService,
                        ServicesSectionService servicesSectionService,
                        MethodSectionService methodSectionService,
                        TestimonialsSectionService testimonialsSectionService) {
        this.heroService = heroService;
        this.aboutService = aboutService;
        this.servicesSectionService = servicesSectionService;
        this.methodSectionService = methodSectionService;
        this.testimonialsSectionService = testimonialsSectionService;
    }

    public PageContentResponse getPageContent(String slug) {
        return new PageContentResponse(
                heroService.getHeroSection(slug),
                aboutService.getAboutSection(slug),
                servicesSectionService.getServicesSection(slug),
                methodSectionService.getMethodSection(slug),
                testimonialsSectionService.getTestimonialsSection(slug)
        );
    }
}