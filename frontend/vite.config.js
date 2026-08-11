// Configuration de Vite, l'outil de build utilisé pour développer et
// construire le frontend Vue.js.
//
// Vite fournit :
//   - un serveur de développement ultra-rapide avec rechargement à chaud
//     (Hot Module Replacement) pendant qu'on code,
//   - une commande de build qui génère des fichiers statiques optimisés
//     (HTML/CSS/JS minifiés) prêts à être déployés sur n'importe quel
//     hébergeur statique (Netlify, Vercel, Nginx, etc.) ou servis
//     directement par le backend Spring Boot (voir README).

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  // Le plugin officiel Vue permet à Vite de comprendre les fichiers .vue
  // (composants "Single File Component" combinant template, script et style).
  plugins: [vue()],

  server: {
    // Port du serveur de développement. C'est cette adresse
    // (http://localhost:5173) qui doit correspondre à la valeur configurée
    // côté backend dans application.properties (app.cors.allowed-origin).
    port: 5173,
  },
})
