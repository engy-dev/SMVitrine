<script setup>
/**
 * TheMethod.vue — déroulé chronologique d'une mission SM Consulting.
 *
 * Contrairement à la section "Services" (voir TheServices.vue), le contenu
 * ici est une véritable séquence : chaque étape suit logiquement la
 * précédente. C'est pourquoi, cette fois, une numérotation (01, 02, 03, 04)
 * est pertinente : elle porte une information réelle sur l'ordre du
 * déroulement d'une mission, plutôt que d'être un simple habillage visuel.
 */
  defineProps({
    method: {
      type: Object,
      default: null
    }
  })
</script>

<template>
  <section v-if="method" id="methode" class="section method">
    <div class="container">
      <header class="method__header">
        <p class="eyebrow">{{ method.eyebrow }}</p>
        <h2 class="method__title">{{ method.titre }}</h2>
      </header>

      <ol class="method__timeline">
        <li v-for="etape in method.etapes" :key="etape.numero" class="method__step">
          <span class="method__number" aria-hidden="true">{{ etape.numero }}</span>
          <h3 class="method__step-title">{{ etape.titre }}</h3>
          <p class="method__step-description">{{ etape.description }}</p>
        </li>
      </ol>
    </div>
  </section>
  <section v-else id="methode" class="section method">
    <div class="container">
      <p class="eyebrow">Chargement…</p>
    </div>
  </section>
</template>

<style scoped>
.method {
  background-color: var(--color-paper-alt);
  border-top: 1px solid var(--color-line);
  border-bottom: 1px solid var(--color-line);
}

.method__header {
  max-width: 640px;
}

.method__title {
  margin-top: var(--space-2);
  font-size: clamp(1.9rem, 3.2vw, 2.5rem);
}

.method__timeline {
  list-style: none;
  margin: var(--space-6) 0 0;
  padding: 0;
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: var(--space-4);
}

.method__step {
  position: relative;
  padding-top: var(--space-3);
  border-top: 2px solid var(--color-gold);
}

.method__number {
  display: block;
  font-family: var(--font-display);
  font-style: italic;
  font-size: 1.6rem;
  color: var(--color-gold);
}

.method__step-title {
  margin-top: var(--space-1);
  font-size: 1.2rem;
}

.method__step-description {
  margin-top: var(--space-1);
  font-size: 0.92rem;
  color: rgba(23, 36, 31, 0.7);
}

@media (max-width: 900px) {
  .method__timeline {
    grid-template-columns: repeat(2, 1fr);
    row-gap: var(--space-5);
  }
}

@media (max-width: 560px) {
  .method__timeline {
    grid-template-columns: 1fr;
  }
}
</style>
