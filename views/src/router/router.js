import {createWebHistory, createRouter} from 'vue-router'
import LoginPage from '@/components/LoginPage.vue'
import RegisterPage from '@/components/RegisterPage.vue'
import ManagementClientPage from '@/components/ManagementClient.vue'
import OrdersClient from '@/components/order/OrdersClient.vue'
import IndexPage from '@/components/IndexPage.vue'
import CreateOrderClient from '@/components/order/CreateOrderClient.vue'
import WarehouseProductClient from '@/components/warehouse-product/WarehouseProductClient.vue'
import ManagementCashClient from '@/components/ManagementCashClient.vue'
import ManageAccountCustomerInfo from '@/components/customer/CustomerDetail.vue'
import ShipperOrder from '@/components/shipper/ShipperOrder.vue'
import ManageAccountShipperInfo from '@/components/shipper/ShipperDetail.vue'
import OrderDetailCustomer from '@/components/order/OrderDetail.vue'
import ManagementAdmin from '@/components/admin/ManagementAdmin.vue'
import ManagementOrderAdmin from '@/components/admin/order/ManagementOrderAdmin.vue'
import CreateOrderAdmin from '@/components/admin/create-order/CreateOrderAdmin.vue'
import ManagementCustomerAdmin from '@/components/admin/customer/ManagementCustomerAdmin.vue'

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
        {path: '/client/warehouse-product', component: WarehouseProductClient},
        {path: '/client/management-cash', component: ManagementCashClient},
        {path: '/client/account-info', component: ManageAccountCustomerInfo},
        {path: '/shipper/management', component: ShipperOrder},
        {path: '/shipper/account-info', component: ManageAccountShipperInfo},
        {path: '/client/orders/detail/:customerId/:orderId', component: OrderDetailCustomer},
        {path: '/admin/management', component: ManagementAdmin},
        {path: '/admin/management/order/:status', component: ManagementOrderAdmin},
        {path: '/admin/management/create-order', component: CreateOrderAdmin},
        {path: '/admin/management/customer', component: ManagementCustomerAdmin},
    ]
});

export default router;