<script setup>
import {
  DoubleLeftOutlined,
  DoubleRightOutlined,
  HomeOutlined,
  ShoppingCartOutlined
} from '@ant-design/icons-vue';
import { defineEmits, defineProps } from "vue";
import { RouterLink, useRouter } from 'vue-router';

const router = useRouter();

const emits = defineEmits(['on-click-menu']);

const props = defineProps({
  openMenu: Boolean
})
const toggleOpenMenu = () => {
  emits('on-click-menu', !props.openMenu);
}

const orderList = [
  {
    name: 'Tất cả',
    path: '/admin/order',
  },
  {
    name: 'Đơn hàng đang xử lý',
    path: '/admin/order?status=1',
  },
  {
    name: 'Đơn hàng đã xử lý',
    path: '/admin/order?status=2',
  },
  {
    name: 'Đơn hàng phát sinh',
    path: '/admin/order?status=3',
  },
  {
    name: 'Đơn hàng bị hủy',
    path: '/admin/order?status=4',
  }
]
const handleClickItem = (inList = false, path) => {
  if (inList) {
    return router.push(path);
  }
}
</script>

<template>
  <div class="d-flex align-items-center justify-content-between">
    <RouterLink :to="'/'" v-if="openMenu">
      <img src="@/images/logo-branch.png" alt="Logo branch" width="150" />
    </RouterLink>

    <DoubleLeftOutlined v-if="openMenu" @click="toggleOpenMenu" class="half-circle-left" />
    <DoubleRightOutlined v-else @click="toggleOpenMenu" class="half-circle-right" />

  </div>
  <a-menu mode="inline" :inline-collapsed="!openMenu" class="mt-5">
    <a-menu-item key="1" style="background: #bf1e2d; color: #ffffff;">
      <template #icon>
        <HomeOutlined />
      </template>
      Trang chủ
    </a-menu-item>
    <a-sub-menu key="sub-1" style="background: #bf1e2d; color: #ffffff;">
      <template #icon>
        <ShoppingCartOutlined />
      </template>
      <template #title>Quản lý đơn hàng</template>
      <a-menu-item :key="'sub-1' + index" v-for="(option, index) in orderList" @click="handleClickItem(true, option.path)">{{ option.name }}</a-menu-item>
    </a-sub-menu>
    <a-sub-menu key="sub-2" style="background: #bf1e2d; color: #ffffff;">
      <template #icon>
        <ShoppingCartOutlined />
      </template>
      <template #title>Tạo đơn hàng tại bưu cục</template>
      <a-menu-item key="sub-21">Tạo đơn hàng mới</a-menu-item>
    </a-sub-menu>
  </a-menu>
</template>

<style scoped>
.half-circle-left {
  border-top-left-radius: 25px;
  border-bottom-left-radius: 25px;
  padding: 20px 0 20px 15px;
  background-color: #bf1e2d;
  color: #ffffff;
}

.half-circle-right {
  border-top-right-radius: 25px;
  border-bottom-right-radius: 25px;
  padding: 20px 35px 20px 15px;
  background-color: #bf1e2d;
  color: #ffffff;
}

.menu-item {

  background-color: #bf1e2d !important;
  color: #ffffff;
}
</style>