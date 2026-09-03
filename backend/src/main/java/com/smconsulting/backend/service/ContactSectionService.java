package com.smconsulting.backend.service;

import com.smconsulting.backend.dto.ContactSectionResponse;
import com.smconsulting.backend.entity.ContactContent;
import com.smconsulting.backend.repository.ContactContentRepository;
import com.smconsulting.backend.repository.ContactPointRepository;
import com.smconsulting.backend.repository.ContactSubjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ContactSectionService {

    private final ContactContentRepository contactContentRepository;
    private final ContactPointRepository contactPointRepository;
    private final ContactSubjectRepository contactSubjectRepository;

    public ContactSectionService(ContactContentRepository contactContentRepository,
                                  ContactPointRepository contactPointRepository,
                                  ContactSubjectRepository contactSubjectRepository) {
        this.contactContentRepository = contactContentRepository;
        this.contactPointRepository = contactPointRepository;
        this.contactSubjectRepository = contactSubjectRepository;
    }

    public ContactSectionResponse getContactSection(String pageSlug) {
        ContactContent content = contactContentRepository.findByPage_Slug(pageSlug)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Aucun contenu 'contact' pour la page '" + pageSlug + "'"));

        List<String> points = contactPointRepository
                .findByPage_SlugAndPublishedTrueOrderBySortOrderAsc(pageSlug)
                .stream()
                .map(com.smconsulting.backend.entity.ContactPoint::getLabel)
                .toList();

        List<String> sujets = contactSubjectRepository
                .findByPublishedTrueOrderBySortOrderAsc()
                .stream()
                .map(com.smconsulting.backend.entity.ContactSubject::getLabel)
                .toList();

        return new ContactSectionResponse(content.getEyebrowText(), content.getTitle(), content.getIntroText(), points, sujets);
    }
}