<template>
  <div class="app-vue">
    <router-view></router-view>
    
  </div>
</template>
<script>
  import 'vue-select/dist/vue-select.css';
  export default {
    name: 'App',
    components: {

    }
  }
</script>

<style scoped>
  @import "@/style/css/login.css";
  @import "@/style/css/register.css";
  @import "@/style/css/navbar-client.css";
  @import "@/style/css/client-management.css";
  @import "@/style/css/client-orders.css";
  @import "@/style/css/global.css";
  @import 'vue3-easy-data-table/dist/style.css';
  @import 'vue3-timeline/dist/style.css'
</style>