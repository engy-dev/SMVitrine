package com.smconsulting.backend.dto;

import java.util.List;

public record ContactSectionResponse(
        String eyebrow,
        String titre,
        String texte,
        List<String> points,
        List<String> sujets
) {
}