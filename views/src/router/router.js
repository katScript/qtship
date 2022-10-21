import {createWebHistory, createRouter} from 'vue-router'
import LoginPage from '@/components/LoginPage.vue'
import RegisterPage from '@/components/RegisterPage.vue'
import ManagementClientPage from '@/components/ManagementClient.vue'
import OrdersClient from '@/components/OrdersClient.vue'
import IndexPage from '@/components/IndexPage.vue'
import CreateOrderClient from '@/components/CreateOrderClient.vue'
import StockProductClient from '@/components/StockProductClient.vue'

const history = createWebHistory();
const router = createRouter({
    history,
    routes: [
        {path: '/', component: IndexPage},
        {path: '/login-page', component: LoginPage},
        {path: '/register-page', component: RegisterPage},
        {path: '/client/management', component: ManagementClientPage},
        {path: '/client/orders', component: OrdersClient},
        {path: '/client/orders/create', component: CreateOrderClient},
        {path: '/client/stock-product', component: StockProductClient},
    ]
});

export default router;