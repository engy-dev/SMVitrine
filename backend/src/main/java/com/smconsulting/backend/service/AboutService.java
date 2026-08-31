package com.smconsulting.backend.service;

import com.smconsulting.backend.dto.AboutSectionResponse;
import com.smconsulting.backend.dto.AboutValueResponse;
import com.smconsulting.backend.entity.AboutContent;
import com.smconsulting.backend.repository.AboutContentRepository;
import com.smconsulting.backend.repository.AboutValueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

@Service
public class AboutService {

    private final AboutContentRepository aboutContentRepository;
    private final AboutValueRepository aboutValueRepository;

    public AboutService(AboutContentRepository aboutContentRepository,
                         AboutValueRepository aboutValueRepository) {
        this.aboutContentRepository = aboutContentRepository;
        this.aboutValueRepository = aboutValueRepository;
    }

    public AboutSectionResponse getAboutSection(String pageSlug) {
        AboutContent content = aboutContentRepository.findByPage_Slug(pageSlug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun contenu 'about' pour la page '" + pageSlug + "'"));

        List<String> paragraphes = Arrays.stream(content.getBody().split("\\n\\s*\\n"))
                .map(String::trim)
                .filter(p -> !p.isEmpty())
                .toList();

        List<AboutValueResponse> valeurs = aboutValueRepository
                .findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(pageSlug)
                .stream()
                .map(v -> new AboutValueResponse(v.getTitle(), v.getDescription()))
                .toList();

        return new AboutSectionResponse(content.getEyebrowText(), content.getTitle(), paragraphes, valeurs);
    }
}