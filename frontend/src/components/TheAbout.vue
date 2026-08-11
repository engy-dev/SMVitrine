<script setup>
/**
 * TheAbout.vue — présentation du cabinet et de ses valeurs.
 *
 * Section volontairement sobre : sur un site à vocation commerciale, le
 * visiteur doit comprendre rapidement "qui parle" avant de laisser ses
 * coordonnées. On y trouve un court texte de positionnement ainsi qu'une
 * liste de valeurs, plus facilement scannable qu'un long paragraphe.
 */
const valeurs = [
  {
    titre: 'Proximité de terrain',
    description: "Nos consultants passent du temps dans vos équipes, pas seulement en salle de réunion.",
  },
  {
    titre: 'Transparence',
    description: 'Des recommandations honnêtes, y compris quand elles sont inconfortables à entendre.',
  },
  {
    titre: 'Résultats mesurables',
    description: "Chaque mission démarre avec des indicateurs de succès définis ensemble dès le départ.",
  },
]
</script>

<template>
  <section id="a-propos" class="section about">
    <div class="container about__grid">
      <div class="about__text">
        <p class="eyebrow">À propos de SM Consulting</p>
        <h2 class="about__title">
          Un cabinet indépendant, au service des équipes autant que des dirigeants
        </h2>
        <p class="about__paragraph">
          Fondé par des praticiens RH passés par la direction d'équipes en
          PME comme en grand groupe, SM Consulting a été créé avec une
          conviction simple&nbsp;: la performance d'une organisation repose
          d'abord sur la façon dont elle traite ses collaborateurs.
        </p>
        <p class="about__paragraph">
          Nous intervenons partout en France, auprès d'entreprises de 20 à
          2&nbsp;000 collaborateurs, dans des secteurs variés — industrie,
          services, tech, associations. Notre taille volontairement humaine
          nous permet de rester exigeants sur la qualité de chaque mission.
        </p>
      </div>

      <ul class="about__values">
        <li v-for="valeur in valeurs" :key="valeur.titre" class="about__value">
          <h3>{{ valeur.titre }}</h3>
          <p>{{ valeur.description }}</p>
        </li>
      </ul>
    </div>
  </section>
</template>

<style scoped>
.about__grid {
  display: grid;
  grid-template-columns: 1.2fr 1fr;
  gap: var(--space-6);
  align-items: start;
}

.about__title {
  margin-top: var(--space-2);
  font-size: clamp(1.9rem, 3.2vw, 2.5rem);
}

.about__paragraph {
  margin-top: var(--space-3);
  font-size: 1.02rem;
  color: rgba(23, 36, 31, 0.78);
}

.about__values {
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: column;
  gap: var(--space-3);
}

.about__value {
  padding: var(--space-3);
  border-left: 2px solid var(--color-gold);
  background-color: var(--color-paper-alt);
  border-radius: 0 var(--radius-sm) var(--radius-sm) 0;
}

.about__value h3 {
  font-size: 1.05rem;
  color: var(--color-forest);
}

.about__value p {
  margin-top: 0.35rem;
  font-size: 0.92rem;
  color: rgba(23, 36, 31, 0.7);
}

@media (max-width: 860px) {
  .about__grid {
    grid-template-columns: 1fr;
  }
}
</style>
