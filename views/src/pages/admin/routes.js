export default [
  {
    name: 'admin-list',
    path: '/admin',
    meta: {
      isAdmin: true,
    },
    component: () => import("@/pages/admin/order/ListOrder.vue"),
  },
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
  {
    name: 'admin-list-shipper',
    path: '/admin/shipper',
    meta: {
      isAdmin: true,
    },
    component: () => import("@/pages/admin/shipper/ListShipper.vue"),
  },
  {
    name: 'admin-list-office',
    path: '/admin/office',
    meta: {
      isAdmin: true,
    },
    component: () => import("@/pages/admin/office/ListOffice.vue"),
  },
  {
    name: 'admin-list-config',
    path: '/admin/config',
    meta: {
      isAdmin: true,
    },
    component: () => import("@/pages/admin/config/ListConfig.vue"),
  },
]
