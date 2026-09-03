package com.smconsulting.backend.service;

import com.smconsulting.backend.dto.ContactRequestDTO;
import com.smconsulting.backend.model.ContactRequest;
import com.smconsulting.backend.repository.ContactRequestRepository;
import com.smconsulting.backend.repository.PageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ContactService {

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    private final ContactRequestRepository contactRequestRepository;
    private final PageRepository pageRepository;

    public ContactService(ContactRequestRepository contactRequestRepository,
                           PageRepository pageRepository) {
        this.contactRequestRepository = contactRequestRepository;
        this.pageRepository = pageRepository;
    }

    /**
     * @param ipAddress adresse IP du visiteur, extraite par le contrôleur
     *                   depuis la requête HTTP (jamais fournie par le
     *                   client lui-même dans le corps JSON)
     * @param userAgent  en-tête User-Agent, également extrait côté serveur
     */
    public ContactRequest traiterNouvelleDemande(ContactRequestDTO dto, String ipAddress, String userAgent) {
        ContactRequest demande = new ContactRequest(
                dto.getNomComplet(),
                dto.getEmail(),
                dto.getTelephone(),
                dto.getEntreprise(),
                dto.getSujet(),
                dto.getMessage(),
                Boolean.TRUE.equals(dto.getConsentement()),
                ipAddress,
                userAgent,
                LocalDateTime.now()
        );

        // Rattache la demande à la page d'accueil si elle existe. Site
        // mono-page pour l'instant : "home" est en dur, comme pour
        // PageController. Ne fait volontairement pas échouer l'envoi du
        // formulaire si la page n'est pas trouvée (ce n'est pas une
        // raison valable de perdre un prospect).
        pageRepository.findBySlug("home").ifPresent(demande::setPage);

        ContactRequest demandeSauvegardee = contactRequestRepository.save(demande);

        log.info("Nouvelle demande de contact enregistrée (id={}, email={}, statut={})",
                demandeSauvegardee.getId(), demandeSauvegardee.getEmail(), demandeSauvegardee.getStatus());

        return demandeSauvegardee;
    }
}