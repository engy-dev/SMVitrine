package com.smconsulting.backend.service;

import com.smconsulting.backend.dto.ServiceOfferingResponse;
import com.smconsulting.backend.dto.ServicesSectionResponse;
import com.smconsulting.backend.entity.ServicesContent;
import com.smconsulting.backend.repository.ServiceOfferingRepository;
import com.smconsulting.backend.repository.ServicesContentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ServicesSectionService {

    private final ServicesContentRepository servicesContentRepository;
    private final ServiceOfferingRepository serviceOfferingRepository;

    public ServicesSectionService(ServicesContentRepository servicesContentRepository,
                                   ServiceOfferingRepository serviceOfferingRepository) {
        this.servicesContentRepository = servicesContentRepository;
        this.serviceOfferingRepository = serviceOfferingRepository;
    }

    public ServicesSectionResponse getServicesSection(String pageSlug) {
        ServicesContent content = servicesContentRepository.findByPage_Slug(pageSlug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun contenu 'services' pour la page '" + pageSlug + "'"));

        List<ServiceOfferingResponse> services = serviceOfferingRepository
                .findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(pageSlug)
                .stream()
                .map(s -> new ServiceOfferingResponse(s.getTitle(), s.getDescription()))
                .toList();

        return new ServicesSectionResponse(content.getEyebrowText(), content.getTitle(), content.getIntroText(), services);
    }
}