// Point d'entrée JavaScript de l'application Vue.
//
// C'est ici que l'on crée l'application Vue, qu'on lui attache les styles
// globaux, puis qu'on la "monte" sur l'élément <div id="app"> défini dans
// index.html.

import { createApp } from 'vue'
import './style.css'
import App from './App.vue'

createApp(App).mount('#app')
