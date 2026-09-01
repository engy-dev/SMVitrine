package com.smconsulting.backend.dto;

import java.util.List;

public record TestimonialsSectionResponse(
        String eyebrow,
        String titre,
        List<TestimonialResponse> temoignages
) {
}