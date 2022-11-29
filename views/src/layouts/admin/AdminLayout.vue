<script setup>
import SidebarLeft from "./components/SidebarLeft.vue";
import { ref, computed, onMounted, onUnmounted } from "vue";
import { useStore } from "vuex";

const store = useStore();
const openMenu = ref(true);

const isLoading = computed(() => store.getters.isLoading);
const handleOpenMenu = (value) => {
  openMenu.value = value;
};
const resizeMenu = () => {
  if (window.innerWidth < 900) {
    handleOpenMenu(false);
  } else {
    handleOpenMenu(true);
  }
}

onMounted(() => {
  window.addEventListener("resize", resizeMenu);
});

onUnmounted(() => {
  window.removeEventListener("resize", resizeMenu);
});

</script>
<template>
  <a-row style="min-height: 100vh">
    <a-col
      :xs="{ span: 4 }"
      :md="{ span: 3 }"
      :lg="{ span: openMenu ? 4 : 1 }"
      class="border-l border"
    >
      <SidebarLeft :openMenu="openMenu" @on-click-menu="handleOpenMenu" />
    </a-col>
    <a-col
      :xs="{ span: 20 }"
      :md="{ span: 21 }"
      :lg="{ span: !openMenu ? 23 : 20 }"
    >
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
.content::-webkit-scrollbar {
  display: none;
}

.content {
  -ms-overflow-style: none;
  scrollbar-width: none;
}
</style>
<style>
.ant-spin-nested-loading > div > .ant-spin {
  max-height: 100vh !important;
}
.ant-spin-dot-item {
  background-color: #bf1e2d !important;
}
</style>
