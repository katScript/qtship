import {createWebHistory, createRouter} from 'vue-router'
import LoginPage from '@/components/LoginPage.vue'

const history = createWebHistory();
const router = createRouter({
    history,
    routes: [
        {path: '/authenication', component: LoginPage}
    ]
});

export default router;