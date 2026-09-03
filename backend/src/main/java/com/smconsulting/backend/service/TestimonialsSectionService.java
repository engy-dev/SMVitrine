package com.smconsulting.backend.service;

import com.smconsulting.backend.dto.TestimonialResponse;
import com.smconsulting.backend.dto.TestimonialsSectionResponse;
import com.smconsulting.backend.entity.Testimonial;
import com.smconsulting.backend.entity.TestimonialsContent;
import com.smconsulting.backend.repository.TestimonialRepository;
import com.smconsulting.backend.repository.TestimonialsContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TestimonialsSectionService {

    private final TestimonialsContentRepository testimonialsContentRepository;
    private final TestimonialRepository testimonialRepository;

    public TestimonialsSectionService(TestimonialsContentRepository testimonialsContentRepository,
                                       TestimonialRepository testimonialRepository) {
        this.testimonialsContentRepository = testimonialsContentRepository;
        this.testimonialRepository = testimonialRepository;
    }

    public TestimonialsSectionResponse getTestimonialsSection(String pageSlug) {
        TestimonialsContent content = testimonialsContentRepository.findByPage_Slug(pageSlug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun contenu 'testimonials' pour la page '" + pageSlug + "'"));

        List<TestimonialResponse> temoignages = testimonialRepository
                .findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(pageSlug)
                .stream()
                .map(t -> new TestimonialResponse(t.getQuote(), t.getClientName(), buildFonction(t)))
                .toList();

        return new TestimonialsSectionResponse(content.getEyebrowText(), content.getTitle(), temoignages);
    }

    // Combine client_role et client_company en une seule ligne d'affichage
    // ("Rôle, Entreprise"), en gérant les cas où l'un des deux (ou les
    // deux) est absent, plutôt que de stocker cette mise en forme en base.
    private String buildFonction(Testimonial testimonial) {
        String role = testimonial.getClientRole();
        String company = testimonial.getClientCompany();

        boolean hasRole = role != null && !role.isBlank();
        boolean hasCompany = company != null && !company.isBlank();

        if (hasRole && hasCompany) {
            return role + ", " + company;
        } else if (hasRole) {
            return role;
        } else if (hasCompany) {
            return company;
        } else {
            return "";
        }
    }
}