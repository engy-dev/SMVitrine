package com.smconsulting.backend.dto;

public record PageContentResponse(
        HeroSectionResponse hero,
        AboutSectionResponse about,
        ServicesSectionResponse services,
        MethodSectionResponse method,
        TestimonialsSectionResponse testimonials
) {
}