import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/router'
// Complie scss to css : sass path-scss path-css
const app = createApp(App);
app.use(router);

app.mount('#app')
