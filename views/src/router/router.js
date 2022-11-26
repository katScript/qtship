import { createWebHistory, createRouter } from 'vue-router'
import LoginComponent from '@/components/user/LoginComponent'
import RegisterPage from '@/components/user/RegisterComponent.vue'
import ForgetPasswordComponent from '@/components/user/ForgetPasswordComponent.vue'
import ResetPasswordComponent from '@/components/user/ResetPasswordComponent.vue'
import ManagementClientPage from '@/components/customer/ManagementClient.vue'
import OrdersClient from '@/components/order/OrdersClient.vue'
import IndexPage from '@/components/IndexPage.vue'
import SaveOrderClient from '@/components/order/CreateOrderClient'
import warehouseProductClient from '@/components/warehouse-product/WarehouseProductClient.vue'
import ManagementCashClient from '@/components/ManagementCashClient.vue'
import ManageAccountCustomerInfo from '@/components/customer/CustomerDetail.vue'
import ShipperOrder from '@/components/shipper/ShipperOrder.vue'
import ManageAccountShipperInfo from '@/components/shipper/ShipperDetail.vue'
import OrderDetailCustomer from '@/components/order/OrderDetail.vue'
import ManagementAdmin from '@/components/admin/ManagementAdmin.vue'
import ManagementOrderAdmin from '@/components/admin/order/ManagementOrderAdmin.vue'
import CreateOrderAdmin from '@/components/admin/create-order/CreateOrderAdmin.vue'
import ManagementCustomerAdmin from '@/components/admin/customer/ManagementCustomerAdmin.vue'
import ManagementFeeAdmin from '@/components/admin/fee/ManagementFeeAdmin.vue'


import adminRoutes from "@/pages/admin/routes";

const history = createWebHistory();
const router = createRouter({
  history,
  routes: [
    { path: '/', component: IndexPage },
    { path: '/login-page', component: LoginComponent },
    { path: '/register-page', component: RegisterPage },
    { path: '/forget-password', component: ForgetPasswordComponent },
    { path: '/reset-password/:token', component: ResetPasswordComponent },
    { path: '/customer/management', component: ManagementClientPage },
    { path: '/customer/orders/:status?', component: OrdersClient },
    { path: '/customer/order/:action/:id?', component: SaveOrderClient },
    { path: '/customer/warehouse-product', component: warehouseProductClient },
    { path: '/customer/management-cash', component: ManagementCashClient },
    { path: '/customer/account-info', component: ManageAccountCustomerInfo },
    { path: '/shipper/management', component: ShipperOrder },
    { path: '/shipper/account-info', component: ManageAccountShipperInfo },
    { path: '/client/orders/detail/:customerId/:orderId', component: OrderDetailCustomer },
    { path: '/admin/management', component: ManagementAdmin },
    { path: '/admin/management/order/:status', component: ManagementOrderAdmin },
    { path: '/admin/management/create-order', component: CreateOrderAdmin },
    { path: '/admin/management/customer', component: ManagementCustomerAdmin },
    { path: '/admin/management/fee-code', component: ManagementFeeAdmin },
    ...adminRoutes,
  ]
});

export default router;