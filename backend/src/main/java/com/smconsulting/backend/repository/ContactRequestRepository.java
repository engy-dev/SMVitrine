package com.smconsulting.backend.repository;

import com.smconsulting.backend.model.ContactRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository Spring Data JPA pour l'entité ContactRequest.
 *
 * Il ne contient aucune implémentation : en étendant JpaRepository, on hérite
 * "gratuitement" de toutes les méthodes CRUD de base (save, findById, findAll,
 * deleteById, count, etc.). Spring génère l'implémentation automatiquement au
 * démarrage de l'application, en analysant le nom de l'entité (ContactRequest)
 * et le type de sa clé primaire (Long).
 *
 * On pourrait ajouter ici des méthodes de requêtes personnalisées si besoin,
 * par exemple :
 *     List<ContactRequest> findByEmail(String email);
 * Spring Data JPA générerait alors automatiquement la requête SQL correspondante
 * simplement à partir du nom de la méthode.
 */
@Repository
public interface ContactRequestRepository extends JpaRepository<ContactRequest, Long> {
}
