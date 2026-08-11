package com.smconsulting.backend.controller;

import com.smconsulting.backend.dto.ContactRequestDTO;
import com.smconsulting.backend.dto.ContactResponseDTO;
import com.smconsulting.backend.model.ContactRequest;
import com.smconsulting.backend.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST exposant l'API publique du site vitrine.
 *
 * Ce contrôleur est volontairement simple : le site étant uniquement destiné
 * à capter des prospects, il n'expose qu'un seul point d'entrée public :
 * l'envoi du formulaire de contact.
 *
 * @RestController = @Controller + @ResponseBody : indique à Spring que
 * cette classe gère des requêtes HTTP et que les valeurs retournées par ses
 * méthodes doivent être automatiquement converties en JSON (via Jackson)
 * plutôt que d'être interprétées comme le nom d'une page HTML à afficher.
 *
 * @RequestMapping("/api/contact") préfixe toutes les routes de ce
 * contrôleur : la méthode envoyerDemandeContact() ci-dessous répond donc à
 * "POST /api/contact".
 */
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * Reçoit les données du formulaire de contact envoyées par le frontend
     * Vue.js (voir frontend/src/services/contactService.js) et les enregistre.
     *
     * @PostMapping indique que cette méthode répond uniquement aux requêtes
     * HTTP POST (on crée une nouvelle ressource : une demande de contact).
     *
     * @RequestBody indique à Spring de désérialiser le corps JSON de la
     * requête HTTP directement vers un objet ContactRequestDTO.
     *
     * @Valid déclenche la validation automatique du DTO selon les annotations
     * définies dans ContactRequestDTO (@NotBlank, @Email, etc.). Si une règle
     * n'est pas respectée, Spring lève une MethodArgumentNotValidException,
     * interceptée par GlobalExceptionHandler pour renvoyer une réponse claire
     * (code HTTP 400) au frontend.
     *
     * @return une réponse HTTP 201 (Created) contenant un message de
     * confirmation si tout s'est bien passé.
     */
    @PostMapping
    public ResponseEntity<ContactResponseDTO> envoyerDemandeContact(
            @Valid @RequestBody ContactRequestDTO dto) {

        ContactRequest demandeSauvegardee = contactService.traiterNouvelleDemande(dto);

        ContactResponseDTO reponse = new ContactResponseDTO(
                "Merci ! Votre demande a bien été envoyée. Notre équipe vous recontactera sous 48h.",
                demandeSauvegardee.getId()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(reponse);
    }
}
