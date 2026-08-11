package com.smconsulting.backend.service;

import com.smconsulting.backend.dto.ContactRequestDTO;
import com.smconsulting.backend.model.ContactRequest;
import com.smconsulting.backend.repository.ContactRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Couche "service" : c'est ici que vit la logique métier liée aux demandes de
 * contact. Le contrôleur (ContactController) ne fait que recevoir la requête
 * HTTP et déléguer le travail réel à cette classe.
 *
 * Séparer le contrôleur du service est une bonne pratique car :
 *   - Cela garde les contrôleurs légers et faciles à lire.
 *   - Cela permet de réutiliser cette logique ailleurs (ex : un futur import
 *     en masse, une tâche planifiée, un autre contrôleur...).
 *   - Cela facilite grandement l'écriture de tests unitaires (on peut tester
 *     ContactService sans avoir besoin de démarrer un serveur HTTP).
 *
 * L'annotation @Service indique à Spring que cette classe est un composant
 * métier à instancier automatiquement (elle devient alors un "bean" injectable
 * dans d'autres classes, comme le contrôleur, via l'injection de dépendances).
 */
@Service
public class ContactService {

    // Logger standard (SLF4J) : à utiliser à la place de System.out.println
    // dans une vraie application, car il permet de configurer les niveaux de
    // log (INFO, WARN, ERROR...) et le format de sortie.
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    private final ContactRequestRepository contactRequestRepository;

    /**
     * Injection de dépendances par constructeur (méthode recommandée par
     * Spring plutôt que l'injection par champ avec @Autowired) : Spring
     * fournit automatiquement une instance de ContactRequestRepository quand
     * il crée ce service.
     */
    public ContactService(ContactRequestRepository contactRequestRepository) {
        this.contactRequestRepository = contactRequestRepository;
    }

    /**
     * Traite une nouvelle demande de contact envoyée depuis le site :
     *   1. Convertit le DTO (données brutes venant du frontend) en entité JPA.
     *   2. Horodate la demande côté serveur (on ne fait jamais confiance à une
     *      date envoyée par le client).
     *   3. Sauvegarde la demande en base de données.
     *   4. (Optionnel, voir README) Envoie une notification par e-mail à
     *      l'équipe commerciale de SM Consulting.
     *
     * @param dto les données validées du formulaire de contact
     * @return l'entité ContactRequest telle qu'enregistrée en base (avec son id généré)
     */
    public ContactRequest traiterNouvelleDemande(ContactRequestDTO dto) {
        ContactRequest demande = new ContactRequest(
                dto.getNomComplet(),
                dto.getEmail(),
                dto.getTelephone(),
                dto.getEntreprise(),
                dto.getSujet(),
                dto.getMessage(),
                LocalDateTime.now()
        );

        ContactRequest demandeSauvegardee = contactRequestRepository.save(demande);

        log.info("Nouvelle demande de contact enregistrée (id={}, email={})",
                demandeSauvegardee.getId(), demandeSauvegardee.getEmail());

        // TODO (production) : brancher ici un service d'envoi d'e-mail
        // (ex : Spring Mail, SendGrid, Brevo/Sendinblue...) pour notifier
        // automatiquement l'équipe commerciale de SM Consulting, ainsi qu'un
        // e-mail de confirmation automatique au prospect. Voir le README pour
        // plus de détails sur cette évolution possible.

        return demandeSauvegardee;
    }
}
