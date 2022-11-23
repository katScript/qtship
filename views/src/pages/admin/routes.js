export default [
  {
    name: 'admin-list-order',
    path: '/admin/order',
    meta: {
      isAdmin: true,
    },
    component: () => import("@/pages/admin/order/ListOrder.vue"),
  },
  {
    name: 'admin-create-update-order',
    path: '/admin/order/:action/:id?',
    meta: {
      isAdmin: true,
    },
    component: () => import("@/pages/admin/order/CreateOrder.vue"),
  },
  {
    name: 'admin-list-customer',
    path: '/admin/customer',
    meta: {
      isAdmin: true,
    },
    component: () => import("@/pages/admin/customer/ListCustomer.vue"),
  },
]
