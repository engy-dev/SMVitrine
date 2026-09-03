<script setup>
/**
 * TheContactForm.vue — formulaire de capture de prospects.
 *
 * C'est le composant le plus important du site puisque sa seule vocation est
 * de convertir un visiteur en prospect qualifié. Il gère lui-même :
 *   - l'état des champs du formulaire (via `reactive`),
 *   - l'état de la soumission (en cours / succès / erreur),
 *   - l'affichage des erreurs de validation renvoyées par le backend,
 *     champ par champ (voir GlobalExceptionHandler.java côté serveur).
 */
import { reactive, ref } from 'vue'
import { envoyerDemandeContact } from '../services/contactService.js'

// `reactive` est adapté ici car on manipule un seul objet regroupant tous
// les champs du formulaire (par opposition à `ref`, plus adapté à une valeur
// unique et isolée comme `envoiEnCours` ci-dessous).
const formulaire = reactive({
  nomComplet: '',
  email: '',
  telephone: '',
  entreprise: '',
  sujet: '',
  message: '',
  consentement: false
})

// Liste des sujets proposés dans le formulaire : garder cette liste ici
defineProps({
  contact: {
    type: Object,
    default: null
  }
})

// États de l'interface, indépendants des données du formulaire lui-même.
const envoiEnCours = ref(false)
const envoiReussi = ref(false)
const messageErreurGlobal = ref('')
const erreursDeChamp = reactive({})

/**
 * Réinitialise tous les messages d'erreur affichés avant une nouvelle
 * tentative de soumission, pour ne pas mélanger d'anciennes erreurs avec
 * les nouvelles.
 */
function reinitialiserErreurs() {
  messageErreurGlobal.value = ''
  Object.keys(erreursDeChamp).forEach((cle) => delete erreursDeChamp[cle])
}

/**
 * Gère la soumission du formulaire : appelle l'API backend et met à jour
 * l'état de l'interface selon le résultat.
 */
async function soumettreFormulaire() {
  reinitialiserErreurs()
  envoiEnCours.value = true

  try {
    await envoyerDemandeContact(formulaire)
    envoiReussi.value = true

    // Vide le formulaire après un envoi réussi, au cas où l'utilisateur
    // souhaiterait consulter à nouveau la page sans la recharger.
    Object.keys(formulaire).forEach((cle) => {
      formulaire[cle] = cle === 'consentement' ? false : ''
    })
  } catch (erreur) {
    if (erreur.erreursDeChamp) {
      Object.assign(erreursDeChamp, erreur.erreursDeChamp)
    }
    messageErreurGlobal.value =
      erreur.message || "Une erreur est survenue. Merci de réessayer."
  } finally {
    envoiEnCours.value = false
  }
}
</script>

<template>
  <section id="contact" class="section contact">
    <div class="container contact__grid">
      <div v-if="contact" class="contact__intro">
        <p class="eyebrow">{{ contact.eyebrow }}</p>
        <h2 class="contact__title">
          {{ contact.titre }}
        </h2>
        <p class="contact__text">
          {{ contact.texte }}
        </p>

        <ul class="contact__points">
          <li v-for="(point, index) in contact.points" :key="index">{{ point }}</li>
        </ul>
      </div>

      <div class="contact__form-wrapper">
        <!-- État "succès" : remplace entièrement le formulaire par un
             message de confirmation, pour ne pas laisser l'utilisateur
             face à un formulaire vide sans confirmation claire. -->
        <div v-if="envoiReussi" class="contact__success" role="status">
          <h3>Merci, votre message a bien été envoyé !</h3>
          <p>Notre équipe revient vers vous sous 48h ouvrées.</p>
        </div>

        <form v-else class="contact-form" novalidate @submit.prevent="soumettreFormulaire">
          <div class="contact-form__row">
            <div class="contact-form__field">
              <label for="nomComplet">Nom complet *</label>
              <input
                id="nomComplet"
                v-model="formulaire.nomComplet"
                type="text"
                name="nomComplet"
                autocomplete="name"
                required
              />
              <p v-if="erreursDeChamp.nomComplet" class="contact-form__erreur">
                {{ erreursDeChamp.nomComplet }}
              </p>
            </div>

            <div class="contact-form__field">
              <label for="email">Adresse e-mail *</label>
              <input
                id="email"
                v-model="formulaire.email"
                type="email"
                name="email"
                autocomplete="email"
                required
              />
              <p v-if="erreursDeChamp.email" class="contact-form__erreur">
                {{ erreursDeChamp.email }}
              </p>
            </div>
          </div>

          <div class="contact-form__row">
            <div class="contact-form__field">
              <label for="telephone">Téléphone</label>
              <input
                id="telephone"
                v-model="formulaire.telephone"
                type="tel"
                name="telephone"
                autocomplete="tel"
              />
              <p v-if="erreursDeChamp.telephone" class="contact-form__erreur">
                {{ erreursDeChamp.telephone }}
              </p>
            </div>

            <div class="contact-form__field">
              <label for="entreprise">Entreprise</label>
              <input
                id="entreprise"
                v-model="formulaire.entreprise"
                type="text"
                name="entreprise"
                autocomplete="organization"
              />
              <p v-if="erreursDeChamp.entreprise" class="contact-form__erreur">
                {{ erreursDeChamp.entreprise }}
              </p>
            </div>
          </div>

          <div class="contact-form__field">
            <label for="sujet">Sujet de votre demande</label>
            <select id="sujet" v-model="formulaire.sujet" name="sujet">
              <option value="" disabled>Sélectionnez un sujet</option>
              <option v-for="sujet in contact?.sujets" :key="sujet" :value="sujet">
                {{ sujet }}
              </option>
            </select>
          </div>

          <div class="contact-form__field">
            <label for="message">Votre message *</label>
            <textarea
              id="message"
              v-model="formulaire.message"
              name="message"
              rows="5"
              required
            ></textarea>
            <p v-if="erreursDeChamp.message" class="contact-form__erreur">
              {{ erreursDeChamp.message }}
            </p>
          </div>

          <div class="contact-form__field contact-form__field--checkbox">
            <label for="consentement">
              <input
                id="consentement"
                v-model="formulaire.consentement"
                type="checkbox"
                name="consentement"
                required
              />
              J'accepte d'être recontacté(e) par SM Consulting au sujet de ma demande. *
            </label>
            <p v-if="erreursDeChamp.consentement" class="contact-form__erreur">
              {{ erreursDeChamp.consentement }}
            </p>
          </div>

          <!-- Message d'erreur global (ex : serveur injoignable) -->
          <p v-if="messageErreurGlobal" class="contact-form__erreur contact-form__erreur--globale" role="alert">
            {{ messageErreurGlobal }}
          </p>

          <button type="submit" class="btn-primary contact-form__submit" :disabled="envoiEnCours">
            {{ envoiEnCours ? 'Envoi en cours…' : 'Envoyer ma demande' }}
          </button>

          <p class="contact-form__mentions">
            * Champs obligatoires. Les informations recueillies via ce
            formulaire sont utilisées uniquement par SM Consulting pour vous
            recontacter dans le cadre de votre demande.
          </p>
        </form>
      </div>
    </div>
  </section>
</template>

<style scoped>
.contact {
  background-color: var(--color-forest);
  color: var(--color-paper);
}

.contact__grid {
  display: grid;
  grid-template-columns: 0.9fr 1.1fr;
  gap: var(--space-6);
  align-items: start;
}

.contact__intro .eyebrow {
  color: var(--color-gold-light);
}

.contact__title {
  margin-top: var(--space-2);
  font-size: clamp(1.9rem, 3.2vw, 2.5rem);
  color: var(--color-paper);
}

.contact__text {
  margin-top: var(--space-3);
  color: rgba(245, 247, 243, 0.8);
  font-size: 1.02rem;
}

.contact__points {
  margin: var(--space-4) 0 0;
  padding: 0;
  list-style: none;
  display: flex;
  flex-direction: column;
  gap: var(--space-1);
}

.contact__points li {
  padding-left: 1.4rem;
  position: relative;
  font-size: 0.95rem;
  color: rgba(245, 247, 243, 0.86);
}

.contact__points li::before {
  content: '';
  position: absolute;
  left: 0;
  top: 0.5em;
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background-color: var(--color-gold);
}

.contact__form-wrapper {
  background-color: var(--color-paper-alt);
  border-radius: var(--radius-md);
  padding: var(--space-4);
  box-shadow: var(--shadow-card);
}

.contact-form__row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: var(--space-3);
}

.contact-form__field {
  margin-top: var(--space-3);
  display: flex;
  flex-direction: column;
  gap: 0.4rem;
}

.contact-form__row .contact-form__field:first-child,
.contact-form__row .contact-form__field:nth-child(2) {
  margin-top: 0;
}

.contact-form__field label {
  font-size: 0.85rem;
  font-weight: 600;
  color: var(--color-forest);
}

.contact-form__field input,
.contact-form__field select,
.contact-form__field textarea {
  font-family: var(--font-body);
  font-size: 0.95rem;
  padding: 0.65rem 0.8rem;
  border: 1px solid var(--color-line);
  border-radius: var(--radius-sm);
  background-color: var(--color-paper);
  color: var(--color-ink);
}

.contact-form__field textarea {
  resize: vertical;
  min-height: 110px;
}

.contact-form__erreur {
  margin: 0;
  font-size: 0.82rem;
  color: #b3401f;
}

.contact-form__erreur--globale {
  margin-top: var(--space-3);
}

.contact-form__submit {
  margin-top: var(--space-4);
  width: 100%;
}

.contact-form__submit:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}

.contact-form__mentions {
  margin-top: var(--space-2);
  font-size: 0.78rem;
  color: rgba(23, 36, 31, 0.55);
}

.contact__success {
  padding: var(--space-5);
  text-align: center;
}

.contact__success h3 {
  color: var(--color-forest);
}

.contact__success p {
  margin-top: var(--space-2);
  color: rgba(23, 36, 31, 0.7);
}

.contact-form__field--checkbox label {
  display: flex;
  align-items: flex-start;
  gap: 0.5rem;
  font-weight: 400;
  color: var(--color-ink);
}

.contact-form__field--checkbox input[type='checkbox'] {
  width: auto;
  margin-top: 0.2rem;
}

@media (max-width: 860px) {
  .contact__grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 560px) {
  .contact-form__row {
    grid-template-columns: 1fr;
  }
  .contact-form__row .contact-form__field:nth-child(2) {
    margin-top: var(--space-3);
  }
}
</style>
