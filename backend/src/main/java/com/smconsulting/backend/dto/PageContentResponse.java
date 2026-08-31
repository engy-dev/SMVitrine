package com.smconsulting.backend.dto;

public record PageContentResponse(
        HeroSectionResponse hero,
        AboutSectionResponse about
) {
}