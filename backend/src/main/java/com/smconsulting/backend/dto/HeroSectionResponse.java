package com.smconsulting.backend.dto;

import java.util.List;

public record HeroSectionResponse(
        String eyebrow,
        String titreAvant,
        String titreAccent,
        String titreApres,
        String sousTitre,
        String ctaPrincipalLabel,
        String ctaPrincipalUrl,
        String ctaSecondaireLabel,
        String ctaSecondaireUrl,
        List<HeroStatResponse> stats
) {
}