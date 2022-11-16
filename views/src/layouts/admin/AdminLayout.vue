<script setup>
import SidebarLeft from './components/SidebarLeft.vue';
import { ref } from "vue";

const openMenu = ref(true);

const handleOpenMenu = (value) => {
  openMenu.value = value;
}
</script>
<template>
  <a-row style="min-height: 100vh;">
    <a-col :span="openMenu ? 4 : 1.5" class="border-l border">
      <SidebarLeft
        :openMenu="openMenu"
        @on-click-menu="handleOpenMenu"
      />
    </a-col>
    <a-col :span="!openMenu ? 22.5 : 20" class="content">
      <slot></slot>
    </a-col>
  </a-row>

</template>

<style scoped>
.content {
  height: 100vh;
  overflow-y: scroll;
}
</style>