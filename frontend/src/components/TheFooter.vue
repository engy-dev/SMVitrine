<script setup>
/**
 * TheFooter.vue — pied de page du site.
 *
 * Contient les informations légales minimales attendues sur un site
 * professionnel français (mentions légales, coordonnées) ainsi qu'un rappel
 * de la navigation principale. Le contenu des coordonnées ci-dessous est un
 * exemple à remplacer par les vraies informations de l'entreprise avant la
 * mise en ligne (adresse, SIRET, etc. — voir aussi le README pour la page
 * "Mentions légales" à créer séparément, obligatoire en France).
 */
const anneeCourante = new Date().getFullYear()
</script>

<template>
  <footer class="footer">
    <div class="container footer__grid">
      <div>
        <p class="footer__logo">SM Consulting</p>
        <p class="footer__baseline">
          Cabinet de conseil en ressources humaines — recrutement, formation
          et organisation.
        </p>
      </div>

      <nav class="footer__nav" aria-label="Navigation du pied de page">
        <a href="#services">Services</a>
        <a href="#methode">Notre méthode</a>
        <a href="#a-propos">À propos</a>
        <a href="#contact">Contact</a>
      </nav>

      <div class="footer__contact">
        <p><a href="https://maps.app.goo.gl/jBwNX1gpTxGw1AL4A" target="_blank">8 Rue des Bles, 93210 Saint-Denis</a></p>
        <p><a href="mailto:contact@sm-consulting.fr">contact@sm-consulting.fr</a></p>
        <p><a href="tel:+33158340155">01 58 34 01 55</a></p>
      </div>
    </div>

    <div class="container footer__bottom">
      <p>© {{ anneeCourante }} SM Consulting. Tous droits réservés.</p>
      <p><a href="#">Mentions légales</a> · <a href="#">Politique de confidentialité</a></p>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background-color: #122420;
  color: rgba(245, 247, 243, 0.75);
  padding-top: var(--space-6);
}

.footer__grid {
  display: grid;
  grid-template-columns: 1.3fr 1fr 1fr;
  gap: var(--space-5);
  padding-bottom: var(--space-5);
  border-bottom: 1px solid var(--color-line-on-forest);
}

.footer__logo {
  font-family: var(--font-display);
  font-size: 1.2rem;
  color: var(--color-paper);
}

.footer__baseline {
  margin-top: var(--space-1);
  font-size: 0.9rem;
  max-width: 280px;
}

.footer__nav,
.footer__contact {
  display: flex;
  flex-direction: column;
  gap: 0.6rem;
  font-size: 0.9rem;
}

.footer__nav a,
.footer__contact a {
  text-decoration: none;
  color: inherit;
}

.footer__nav a:hover,
.footer__contact a:hover {
  color: var(--color-gold-light);
}

.footer__bottom {
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: var(--space-2);
  padding: var(--space-3) 0 var(--space-4);
  font-size: 0.8rem;
}

.footer__bottom a {
  text-decoration: none;
  color: inherit;
}

.footer__bottom a:hover {
  color: var(--color-gold-light);
}

@media (max-width: 700px) {
  .footer__grid {
    grid-template-columns: 1fr;
  }
}
</style>
