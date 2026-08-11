<script setup>
/**
 * TheTestimonials.vue — avis clients.
 *
 * IMPORTANT : les témoignages ci-dessous sont des exemples de démonstration
 * (contenu "placeholder") destinés à montrer la mise en page. Avant la mise
 * en ligne du site, remplacez-les par de vrais témoignages, avec l'accord
 * explicite des personnes citées (nom, fonction, entreprise).
 */
const temoignages = [
  {
    citation:
      "SM Consulting nous a aidés à structurer notre fonction RH alors que nous passions de 30 à 80 salariés en un an. Un accompagnement concret, jamais théorique.",
    auteur: 'Camille Bernard',
    fonction: 'Directrice Générale, Atlas Industrie',
  },
  {
    citation:
      "Le travail sur notre marque employeur a directement fait baisser notre délai moyen de recrutement. L'équipe comprend vite les enjeux business, pas seulement RH.",
    auteur: 'Julien Ferreira',
    fonction: 'DRH, Groupe Norden',
  },
  {
    citation:
      "Un accompagnement au changement mené avec beaucoup d'écoute auprès de nos équipes, dans une période sensible pour l'entreprise.",
    auteur: 'Sophie Lacombe',
    fonction: 'Fondatrice, Studio Lacombe',
  },
]
</script>

<template>
  <section id="avis" class="section testimonials">
    <div class="container">
      <header class="testimonials__header">
        <p class="eyebrow">Ils nous font confiance</p>
        <h2 class="testimonials__title">Ce que nos clients disent de nos missions</h2>
      </header>

      <div class="testimonials__grid">
        <blockquote v-for="temoignage in temoignages" :key="temoignage.auteur" class="testimonial-card">
          <p class="testimonial-card__quote">« {{ temoignage.citation }} »</p>
          <footer class="testimonial-card__footer">
            <span class="testimonial-card__auteur">{{ temoignage.auteur }}</span>
            <span class="testimonial-card__fonction">{{ temoignage.fonction }}</span>
          </footer>
        </blockquote>
      </div>
    </div>
  </section>
</template>

<style scoped>
.testimonials__header {
  max-width: 640px;
}

.testimonials__title {
  margin-top: var(--space-2);
  font-size: clamp(1.9rem, 3.2vw, 2.5rem);
}

.testimonials__grid {
  margin-top: var(--space-6);
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: var(--space-4);
}

.testimonial-card {
  margin: 0;
  padding: var(--space-4);
  background-color: var(--color-paper-alt);
  border: 1px solid var(--color-line);
  border-radius: var(--radius-md);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.testimonial-card__quote {
  font-family: var(--font-display);
  font-style: italic;
  font-size: 1.08rem;
  line-height: 1.55;
  color: var(--color-forest);
}

.testimonial-card__footer {
  margin-top: var(--space-3);
  display: flex;
  flex-direction: column;
  font-style: normal;
}

.testimonial-card__auteur {
  font-weight: 700;
  font-size: 0.92rem;
}

.testimonial-card__fonction {
  font-size: 0.85rem;
  color: rgba(23, 36, 31, 0.6);
}

@media (max-width: 900px) {
  .testimonials__grid {
    grid-template-columns: 1fr;
  }
}
</style>
