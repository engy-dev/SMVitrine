<script setup>
/**
 * TheAbout.vue — présentation du cabinet et de ses valeurs.
 *
 * Section volontairement sobre : sur un site à vocation commerciale, le
 * visiteur doit comprendre rapidement "qui parle" avant de laisser ses
 * coordonnées. On y trouve un court texte de positionnement ainsi qu'une
 * liste de valeurs, plus facilement scannable qu'un long paragraphe.
 */
  import { ref, onMounted } from 'vue'
  import { apiGet } from '../config/api'
  // Rempli de manière asynchrone par l'appel à l'API ci-dessous ; null tant
  // que la réponse n'est pas arrivée, ce que le template doit gérer.
  const about = ref(null)

  onMounted(async () => {
    try {
      about.value = await apiGet('/api/vitrine/about?page=home')
    } catch (error) {
      console.error('Impossible de charger la section "À propos" :', error)
    }
  })

</script>

<template>
  <section id="a-propos" class="section about">
    <div class="container about__grid">
      <template v-if="about">
        <div class="about__text">
          <p class="eyebrow">{{ about.eyebrow }}</p>
          <h2 class="about__title">
            {{ about.titre }}
          </h2>
          <p
            v-for="(paragraphe, index) in about.paragraphes"
            :key="index"
            class="about__paragraph"
          >
            {{ paragraphe }}
          </p>
        </div>

        <ul class="about__values">
          <li v-for="valeur in about.valeurs" :key="valeur.titre" class="about__value">
            <h3>{{ valeur.titre }}</h3>
            <p>{{ valeur.description }}</p>
          </li>
        </ul>
      </template>
      <p v-else class="about__paragraph">Chargement…</p>
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
