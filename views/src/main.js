import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/router'
import VueSelect from 'vue-select'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueApexCharts from 'vue3-apexcharts'
import { vue3Debounce } from 'vue-debounce'
import store from './store'

// Complie scss to css : sass path-scss path-css
const app = createApp(App);
app.use(router);
app.component('v-select', VueSelect)
app.use(VueAxios, axios)
app.use(VueApexCharts)
app.component('vue-apexchart', VueApexCharts)
app.use(store);
app.directive('debounce', vue3Debounce({ lock: true }))
app.mount('#app')