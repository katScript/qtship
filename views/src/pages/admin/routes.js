export default [
  {
    name: 'admin-list-order',
    path: '/admin/order',
    meta: {
      isAdmin: true,
    },
    component: () => import("@/pages/admin/order/ListOrder.vue"),
  }
]
