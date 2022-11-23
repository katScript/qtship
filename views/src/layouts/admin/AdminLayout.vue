<script setup>
import SidebarLeft from './components/SidebarLeft.vue';
import { ref, computed} from "vue";
import { useStore } from 'vuex';

const store = useStore();
const openMenu = ref(true);

const isLoading = computed(() => store.getters.isLoading);

const handleOpenMenu = (value) => {
  openMenu.value = value;
}
</script>
<template>
  <a-row style="min-height: 100vh;">
    <a-col :span="openMenu ? 4 : 1" class="border-l border">
      <SidebarLeft
        :openMenu="openMenu"
        @on-click-menu="handleOpenMenu"
      />
    </a-col>
    <a-col :span="!openMenu ? 23 : 20">
      <a-spin :spinning="isLoading" size="large" tip="Vui lòng chờ...">
      <div class="content">
        <slot></slot>
      </div>
      </a-spin>
    </a-col>
  </a-row>

</template>

<style scoped>
.content {
  height: 100vh;
  overflow-y: scroll;
}
</style>
<style>
.ant-spin-nested-loading > div > .ant-spin {
  max-height: 100vh!important;
}
.ant-spin-dot-item {
  background-color: #BF1E2D!important;
}
</style>