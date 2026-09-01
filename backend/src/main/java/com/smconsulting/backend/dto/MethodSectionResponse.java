package com.smconsulting.backend.dto;

import java.util.List;

public record MethodSectionResponse(
        String eyebrow,
        String titre,
        List<MethodStepResponse> etapes
) {
}