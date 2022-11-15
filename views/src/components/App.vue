<template>
  <AdminLayout v-if="isRouteAuth">
    <RouterView />
  </AdminLayout>
  <div class="app-vue" v-else>
    <router-view></router-view>
  </div>
</template>
<script setup>
import { useRoute } from 'vue-router'
import AdminLayout from "@/layouts/admin/AdminLayout.vue";
import { computed } from "vue";

const route = useRoute();

const isRouteAuth = computed(() => route.meta.isAdmin);
</script>

<style scoped>
@import "@/style/css/login.css";
@import "@/style/css/register.css";
@import "@/style/css/navbar-client.css";
@import "@/style/css/client-management.css";
@import "@/style/css/client-orders.css";
@import "@/style/css/global.css";
@import "@/style/css/admin.css";
</style>