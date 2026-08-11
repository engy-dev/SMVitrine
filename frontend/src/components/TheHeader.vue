<script setup>
/**
 * TheHeader.vue — barre de navigation fixe en haut du site.
 *
 * Contient le logo, les liens d'ancrage vers les différentes sections de la
 * page, et un bouton d'appel à l'action toujours visible. Sur mobile, les
 * liens sont masqués derrière un menu "hamburger" pour ne pas encombrer
 * l'écran (voir la variable réactive `menuOuvert` ci-dessous).
 */
import { ref } from 'vue'

// `ref` crée une variable réactive : quand sa valeur change, Vue met
// automatiquement à jour toutes les parties du template qui en dépendent
// (ici : la classe CSS du menu mobile et l'icône du bouton).
const menuOuvert = ref(false)

function basculerMenu() {
  menuOuvert.value = !menuOuvert.value
}

// Ferme le menu mobile automatiquement après avoir cliqué sur un lien,
// pour éviter qu'il reste ouvert au-dessus du contenu une fois la page
// scrollée jusqu'à la section ciblée.
function fermerMenu() {
  menuOuvert.value = false
}
</script>

<template>
  <header class="header">
    <div class="container header__inner">
      <a href="#accueil" class="header__logo" @click="fermerMenu">
        <span class="header__logo-mark" aria-hidden="true">
          <svg viewBox="0 0 40 40" width="30" height="30">
            <circle cx="10" cy="12" r="3" fill="var(--color-gold)" />
            <circle cx="30" cy="12" r="3" fill="var(--color-gold)" />
            <circle cx="20" cy="28" r="3" fill="var(--color-gold)" />
            <line x1="10" y1="12" x2="20" y2="28" stroke="var(--color-forest)" stroke-width="1.5" />
            <line x1="30" y1="12" x2="20" y2="28" stroke="var(--color-forest)" stroke-width="1.5" />
            <line x1="10" y1="12" x2="30" y2="12" stroke="var(--color-forest)" stroke-width="1.5" />
          </svg>
        </span>
        <span class="header__logo-text">SM Consulting</span>
      </a>

      <!-- Navigation principale : masquée sur mobile tant que le menu n'est
           pas ouvert (voir la classe dynamique "header__nav--ouvert"). -->
      <nav class="header__nav" :class="{ 'header__nav--ouvert': menuOuvert }">
        <a href="#services" @click="fermerMenu">Services</a>
        <a href="#methode" @click="fermerMenu">Notre méthode</a>
        <a href="#a-propos" @click="fermerMenu">À propos</a>
        <a href="#avis" @click="fermerMenu">Ils nous font confiance</a>
        <a href="#contact" class="btn-primary header__nav-cta" @click="fermerMenu">
          Demander un échange
        </a>
      </nav>

      <!-- Bouton hamburger, visible uniquement sur petit écran (voir CSS). -->
      <button
        class="header__toggle"
        type="button"
        :aria-expanded="menuOuvert"
        aria-label="Ouvrir ou fermer le menu de navigation"
        @click="basculerMenu"
      >
        <span></span>
        <span></span>
        <span></span>
      </button>
    </div>
  </header>
</template>

<style scoped>
.header {
  position: sticky;
  top: 0;
  z-index: 50;
  background-color: rgba(245, 247, 243, 0.92);
  backdrop-filter: blur(8px);
  border-bottom: 1px solid var(--color-line);
}

.header__inner {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-top: var(--space-2);
  padding-bottom: var(--space-2);
}

.header__logo {
  display: inline-flex;
  align-items: center;
  gap: 0.6rem;
  text-decoration: none;
}

.header__logo-text {
  font-family: var(--font-display);
  font-size: 1.15rem;
  font-weight: 500;
  color: var(--color-forest);
  letter-spacing: 0.01em;
}

.header__nav {
  display: flex;
  align-items: center;
  gap: var(--space-4);
}

.header__nav a {
  text-decoration: none;
  color: var(--color-ink);
  font-size: 0.95rem;
  font-weight: 500;
}

.header__nav a:hover {
  color: var(--color-forest);
}

.header__nav-cta {
  padding: 0.65rem 1.35rem;
  font-size: 0.88rem;
}

.header__toggle {
  display: none;
  flex-direction: column;
  justify-content: center;
  gap: 5px;
  width: 34px;
  height: 34px;
  background: transparent;
  border: none;
  padding: 0;
}

.header__toggle span {
  display: block;
  height: 2px;
  background-color: var(--color-forest);
  border-radius: 2px;
}

/* --- Version mobile ------------------------------------------------------ */
@media (max-width: 860px) {
  .header__toggle {
    display: flex;
  }

  .header__nav {
    position: absolute;
    top: 100%;
    left: 0;
    right: 0;
    flex-direction: column;
    align-items: flex-start;
    gap: var(--space-3);
    padding: var(--space-3) var(--space-4) var(--space-4);
    background-color: var(--color-paper-alt);
    border-bottom: 1px solid var(--color-line);
    /* Le menu est présent dans le DOM mais masqué visuellement tant que
       l'utilisateur ne l'a pas ouvert, pour permettre une transition douce. */
    transform: translateY(-8px);
    opacity: 0;
    pointer-events: none;
    transition: opacity 160ms ease, transform 160ms ease;
  }

  .header__nav--ouvert {
    transform: translateY(0);
    opacity: 1;
    pointer-events: auto;
  }

  .header__nav-cta {
    width: 100%;
  }
}
</style>
