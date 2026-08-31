package com.smconsulting.backend.dto;

import java.util.List;

public record AboutSectionResponse(
        String eyebrow,
        String titre,
        List<String> paragraphes,
        List<AboutValueResponse> valeurs
) {
}