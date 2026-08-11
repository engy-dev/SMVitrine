// contactService.js
// ============================================================================
// Petite couche d'abstraction au-dessus d'Axios pour parler à l'API backend.
//
// Centraliser les appels HTTP dans un fichier "service" (plutôt que d'appeler
// axios directement depuis le composant Vue) présente plusieurs avantages :
//   - Un seul endroit à modifier si l'URL de l'API ou son contrat change.
//   - Le composant Vue (TheContactForm.vue) reste concentré sur l'affichage
//     et n'a pas besoin de connaître les détails de l'API (URL, formats...).
//   - Facilite l'écriture de tests unitaires du composant en simulant
//     (mock) ce service.
// ============================================================================

import axios from 'axios'

// L'URL de base de l'API est lue depuis une variable d'environnement Vite
// (voir le fichier .env.example à la racine du frontend). Cela permet de
// pointer facilement vers un backend différent entre développement et
// production, sans modifier le code source.
//
// Convention Vite : seules les variables préfixées par VITE_ sont exposées
// au code frontend, pour éviter d'exposer par erreur des secrets serveur.
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080'

// Instance Axios pré-configurée avec l'URL de base et les en-têtes par
// défaut, réutilisée pour tous les appels de ce service.
const apiClient = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
})

/**
 * Envoie les données du formulaire de contact au backend.
 *
 * @param {Object} donneesFormulaire - les champs saisis par l'utilisateur
 *   (nomComplet, email, telephone, entreprise, sujet, message)
 * @returns {Promise<Object>} les données de la réponse en cas de succès
 * @throws {Object} un objet normalisé { message, erreursDeChamp } en cas d'échec,
 *   directement exploitable par le composant pour afficher les erreurs
 */
export async function envoyerDemandeContact(donneesFormulaire) {
  try {
    const reponse = await apiClient.post('/api/contact', donneesFormulaire)
    return reponse.data
  } catch (erreur) {
    // Si le backend a répondu avec un code d'erreur (400, 500...), le corps
    // de sa réponse suit le format défini par ErrorResponse.java côté Java :
    // { statut, message, erreursDeChamp }. On le propage tel quel.
    if (erreur.response && erreur.response.data) {
      throw erreur.response.data
    }

    // Sinon, il s'agit probablement d'un problème réseau (backend éteint,
    // pas de connexion internet...) : on renvoie un message générique.
    throw {
      message:
        "Impossible de contacter le serveur. Vérifiez votre connexion et réessayez.",
      erreursDeChamp: null,
    }
  }
}
