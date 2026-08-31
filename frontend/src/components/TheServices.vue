<script setup>
/**
 * TheServices.vue — présente l'offre de conseil de SM Consulting.
 *
 * Les services ne suivent pas un ordre obligatoire (ce n'est pas un
 * processus séquentiel) : ils sont donc présentés sous forme de grille de
 * cartes plutôt que d'une liste numérotée, afin de ne pas suggérer un ordre
 * qui n'existe pas. Voir TheMethod.vue pour la partie qui, elle, décrit une
 * vraie séquence (le déroulé d'une mission).
 *
 * Les données sont définies dans un tableau JavaScript `services` plutôt que
 * codées en dur dans le template : cela rend le contenu facile à modifier,
 * réordonner ou étendre sans toucher à la structure HTML/CSS.
 */
defineProps({
  services: {
    type: Object,
    default: null
  }
})
</script>

<template>
  <section v-if="services" id="services" class="section services">
    <div class="container">
      <header class="services__header">
        <p class="eyebrow">{{ services.eyebrow }}</p>
        <h2 class="services__title">
          {{ services.titre }}
        </h2>
        <p class="services__intro">
          {{ services.intro }}
        </p>
      </header>

      <div class="services__grid">
        <article v-for="service in services.services" :key="service.titre" class="service-card">
          <svg class="service-card__icon" viewBox="0 0 40 40" aria-hidden="true">
            <circle cx="20" cy="20" r="18" fill="none" stroke="var(--color-line)" stroke-width="1" />
            <circle cx="20" cy="20" r="4" fill="var(--color-gold)" />
          </svg>
          <h3 class="service-card__title">{{ service.titre }}</h3>
          <p class="service-card__description">{{ service.description }}</p>
        </article>
      </div>
    </div>
  </section>
  <section v-else id="services" class="section services">
    <div class="container">
      <p class="eyebrow">Chargement…</p>
    </div>
  </section>
</template>

<style scoped>
.services__header {
  max-width: 640px;
}

.services__title {
  margin-top: var(--space-2);
  font-size: clamp(1.9rem, 3.2vw, 2.5rem);
}

.services__intro {
  margin-top: var(--space-3);
  color: rgba(23, 36, 31, 0.72);
  font-size: 1.05rem;
}

.services__grid {
  margin-top: var(--space-6);
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: var(--space-4);
}

.service-card {
  padding: var(--space-4);
  background-color: var(--color-paper-alt);
  border: 1px solid var(--color-line);
  border-radius: var(--radius-md);
  transition: transform 160ms ease, box-shadow 160ms ease;
}

.service-card:hover {
  transform: translateY(-4px);
  box-shadow: var(--shadow-card);
}

.service-card__icon {
  width: 40px;
  height: 40px;
}

.service-card__title {
  margin-top: var(--space-3);
  font-size: 1.15rem;
}

.service-card__description {
  margin-top: var(--space-1);
  font-size: 0.95rem;
  color: rgba(23, 36, 31, 0.7);
}

@media (max-width: 900px) {
  .services__grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 640px) {
  .services__grid {
    grid-template-columns: 1fr;
  }
}
</style>
