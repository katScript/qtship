import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/router'
import VueSelect from 'vue-select'
import axios from 'axios'
import VueAxios from 'vue-axios'

// Complie scss to css : sass path-scss path-css
const app = createApp(App);
app.use(router);
app.component('v-select', VueSelect)
app.use(VueAxios, axios)
app.mount('#app')