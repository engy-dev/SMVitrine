<script setup>
/**
 * App.vue — composant racine de l'application.
 *
 * Ce fichier est volontairement simple : son unique rôle est d'assembler,
 * dans l'ordre de lecture, les différentes sections de la page d'accueil.
 * Chaque section est un composant autonome situé dans src/components/.
 *
 * Cette organisation "un composant = une section" facilite la maintenance :
 * pour modifier la section "Services", on ouvre uniquement TheServices.vue
 * sans risquer d'impacter le reste de la page.
 *
 * <script setup> est la syntaxe recommandée depuis Vue 3 pour écrire des
 * composants avec la Composition API : tout ce qui est déclaré ici
 * (imports, variables, fonctions) est automatiquement exposé au <template>
 * ci-dessous, sans avoir besoin d'un bloc "export default { ... }" explicite.
 */
  import TheHeader from './components/TheHeader.vue'
  import TheHero from './components/TheHero.vue'
  import TheServices from './components/TheServices.vue'
  import TheMethod from './components/TheMethod.vue'
  import TheAbout from './components/TheAbout.vue'
  import TheTestimonials from './components/TheTestimonials.vue'
  import TheContactForm from './components/TheContactForm.vue'
  import TheFooter from './components/TheFooter.vue'
  import { ref, onMounted } from 'vue'
  import { apiGet } from './config/api'

  const page = ref(null)
  const error = ref(null)

  async function fetchPageContent(slug = 'home') {
    try {
      page.value = await apiGet(`/api/vitrine/page?slug=${slug}`)
      error.value = null
    } catch (err) {
      error.value = err
    }
  }

  onMounted(() => {
    fetchPageContent('home')
  })
</script>

<template>
  <!-- L'en-tête reste fixe/visible en haut de page pendant le défilement,
       afin que le bouton d'appel à l'action reste toujours accessible. -->
  <TheHeader />

  <main>
    <TheHero :hero="page?.hero"/>
    <TheServices />
    <TheMethod />
    <TheAbout :about="page?.about" />
    <TheTestimonials />
    <TheContactForm />
  </main>

  <TheFooter />
</template>
