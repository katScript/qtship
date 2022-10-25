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
  @import "./style/css/login.css";
  @import "./style/css/register.css";
  @import "./style/css/navbar-client.css";
  @import "./style/css/client-management.css";
  @import "./style/css/client-orders.css";
</style>