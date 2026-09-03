package com.smconsulting.backend.controller;

import com.smconsulting.backend.dto.ContactRequestDTO;
import com.smconsulting.backend.dto.ContactResponseDTO;
import com.smconsulting.backend.model.ContactRequest;
import com.smconsulting.backend.service.ContactService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> envoyerDemandeContact(
            @Valid @RequestBody ContactRequestDTO dto,
            HttpServletRequest request) {

        // getRemoteAddr() renvoie l'IP directe du client. Si l'application
        // est un jour placée derrière un reverse proxy (nginx, load
        // balancer...), il faudra lire l'en-tête X-Forwarded-For à la
        // place — non géré ici pour rester simple tant que ce n'est pas
        // le cas.
        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        ContactRequest demandeSauvegardee = contactService.traiterNouvelleDemande(dto, ipAddress, userAgent);

        ContactResponseDTO reponse = new ContactResponseDTO(
                "Merci ! Votre demande a bien été envoyée. Notre équipe vous recontactera sous 48h.",
                demandeSauvegardee.getId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
    }
}