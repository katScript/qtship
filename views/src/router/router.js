import {createWebHistory, createRouter} from 'vue-router'
import LoginPage from '@/components/LoginPage.vue'
import RegisterPage from '@/components/RegisterPage.vue'
import ManagementClientPage from '@/components/ManagementClient.vue'
import OrdersClient from '@/components/OrdersClient.vue'
import IndexPage from '@/components/IndexPage.vue'
import CreateOrderClient from '@/components/CreateOrderClient.vue'
import warehouseProductClient from '@/components/WarehouseProductClient.vue'
import ManagementCashClient from '@/components/ManagementCashClient.vue'
import ManageAccountInfo from '@/components/ManageAccountInfo.vue'
import CreateProductClient from '@/components/CreateProductClient.vue'
import ManagementShipper from '@/components/ManagementShipper.vue'
import ManageAccountShipperInfo from '@/components/ManageAccountShipperInfo.vue'
import ManagementAdmin from '@/components/ManagementAdmin.vue'

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
        {path: '/client/warehouse-product', component: warehouseProductClient},
        {path: '/client/management-cash', component: ManagementCashClient},
        {path: '/client/account-info', component: ManageAccountInfo},
        {path: '/client/warehouse/create-product', component: CreateProductClient},
        {path: '/shipper/management', component: ManagementShipper},
        {path: '/shipper/account-info', component: ManageAccountShipperInfo},
        {path: '/admin/management', component: ManagementAdmin},
       
    ]
});

export default router;