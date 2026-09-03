<script setup>
/**
 * TheHero.vue — première section visible de la page ("above the fold").
 *
 * Élément signature du site : une illustration SVG en forme de "réseau de
 * nœuds" reliés entre eux, symbolisant les liens que SM Consulting tisse
 * entre les talents et les organisations. Elle est purement décorative
 * (aria-hidden) et animée très légèrement en CSS (pulsation douce des
 * nœuds) pour donner un peu de vie à la page sans distraire le visiteur.
 *
 * L'objectif premier de cette section reste la conversion : le titre, le
 * sous-titre et le bouton d'action doivent être compris en quelques
 * secondes par un visiteur qui découvre le site pour la première fois.
 */

  defineProps({
    hero: {
      type: Object,
      default: null
    }
  })
</script>

<template>
  <section id="accueil" class="hero">
    <!-- Illustration décorative en réseau de nœuds, positionnée en fond de
         section. aria-hidden="true" car elle n'apporte aucune information
         supplémentaire à un lecteur d'écran. -->
    <svg
      class="hero__network"
      viewBox="0 0 900 600"
      preserveAspectRatio="xMidYMid slice"
      aria-hidden="true"
    >
      <g stroke="var(--color-line-on-forest)" stroke-width="1">
        <line x1="120" y1="120" x2="360" y2="220" />
        <line x1="360" y1="220" x2="300" y2="420" />
        <line x1="360" y1="220" x2="600" y2="160" />
        <line x1="600" y1="160" x2="780" y2="300" />
        <line x1="600" y1="160" x2="560" y2="380" />
        <line x1="300" y1="420" x2="560" y2="380" />
        <line x1="560" y1="380" x2="740" y2="480" />
        <line x1="120" y1="120" x2="300" y2="420" />
      </g>
      <g class="hero__nodes">
        <circle cx="120" cy="120" r="6" fill="var(--color-gold)" />
        <circle cx="360" cy="220" r="8" fill="var(--color-gold)" />
        <circle cx="600" cy="160" r="6" fill="var(--color-blush)" />
        <circle cx="780" cy="300" r="5" fill="var(--color-gold)" />
        <circle cx="300" cy="420" r="7" fill="var(--color-blush)" />
        <circle cx="560" cy="380" r="9" fill="var(--color-gold)" />
        <circle cx="740" cy="480" r="5" fill="var(--color-gold)" />
      </g>
    </svg>

    <div class="container hero__inner">
      <div v-if="hero" class="hero__content">
        <p class="eyebrow">{{ hero.eyebrow }}</p>

        <h1 class="hero__title">
          {{ hero.titreAvant }}
          <em v-if="hero.titreAccent">{{ hero.titreAccent }}</em>
          {{ hero.titreApres }}
        </h1>

        <p class="hero__subtitle">
          {{ hero.sousTitre }}
        </p>

        <div class="hero__actions">
          <a :href="hero.ctaPrincipalUrl" class="btn-primary">{{ hero.ctaPrincipalLabel }}</a>
          <a :href="hero.ctaSecondaireUrl" class="btn-secondary">{{ hero.ctaSecondaireLabel }}</a>
        </div>

        <dl class="hero__stats">
          <div v-for="(stat, index) in hero.stats" :key="index" class="hero__stat">
            <dt>{{ stat.valeur }}</dt>
            <dd>{{ stat.libelle }}</dd>
          </div>
        </dl>
      </div>
      <div v-else class="hero__content">
        <p class="eyebrow">Chargement…</p>
      </div>
    </div>
  </section>
</template>

<style scoped>
.hero {
  position: relative;
  overflow: hidden;
  background-color: var(--color-forest);
  color: var(--color-paper);
  padding: var(--space-7) 0 var(--space-6);
}

.hero__network {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  opacity: 0.9;
}

/* Légère pulsation des nœuds pour suggérer un réseau "vivant". Chaque nœud
   est décalé dans le temps pour éviter un effet trop mécanique/synchronisé. */
.hero__nodes circle {
  animation: pulse 3.6s ease-in-out infinite;
}
.hero__nodes circle:nth-child(2) { animation-delay: 0.4s; }
.hero__nodes circle:nth-child(3) { animation-delay: 0.8s; }
.hero__nodes circle:nth-child(4) { animation-delay: 1.2s; }
.hero__nodes circle:nth-child(5) { animation-delay: 1.6s; }
.hero__nodes circle:nth-child(6) { animation-delay: 2.0s; }
.hero__nodes circle:nth-child(7) { animation-delay: 2.4s; }

@keyframes pulse {
  0%, 100% { opacity: 0.55; }
  50% { opacity: 1; }
}

.hero__inner {
  position: relative; /* passe au-dessus du SVG de fond */
}

.hero__content {
  max-width: 700px;
}

.hero__content .eyebrow {
  color: var(--color-gold-light);
}

.hero__title {
  margin-top: var(--space-2);
  font-size: clamp(2.4rem, 5vw, 3.6rem);
  color: var(--color-paper);
}

.hero__title em {
  font-style: italic;
  color: var(--color-gold-light);
}

.hero__subtitle {
  margin-top: var(--space-3);
  max-width: 560px;
  font-size: 1.1rem;
  line-height: 1.65;
  color: rgba(245, 247, 243, 0.86);
}

.hero__actions {
  display: flex;
  flex-wrap: wrap;
  gap: var(--space-2);
  margin-top: var(--space-4);
}

.hero__stats {
  display: flex;
  flex-wrap: wrap;
  gap: var(--space-5);
  margin: var(--space-6) 0 0;
  padding-top: var(--space-4);
  border-top: 1px solid var(--color-line-on-forest);
}

.hero__stat dt {
  font-family: var(--font-display);
  font-size: 1.9rem;
  color: var(--color-gold-light);
}

.hero__stat dd {
  margin: 0.2rem 0 0;
  max-width: 180px;
  font-size: 0.88rem;
  color: rgba(245, 247, 243, 0.75);
}

@media (max-width: 640px) {
  .hero__stats {
    gap: var(--space-4);
  }
}
</style>
