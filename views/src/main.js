import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/router'
import VueSelect from 'vue-select'
import axios from 'axios'
import VueAxios from 'vue-axios'
import VueApexCharts from 'vue3-apexcharts'
// import { globalCookiesConfig } from "vue3-cookies";

// globalCookiesConfig({
//   expireTimes: "1d",
//   path: "/",
//   domain: "",
//   secure: true,
//   sameSite: "Lax",
// });

// Complie scss to css : sass path-scss path-css
const app = createApp(App);
app.use(router);
app.component('v-select', VueSelect)
app.use(VueAxios, axios)
app.use(VueApexCharts)
app.component('vue-apexchart', VueApexCharts)

app.mount('#app')