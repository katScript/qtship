<script setup>
import {
  DoubleLeftOutlined,
  DoubleRightOutlined,
  HomeOutlined,
  ShoppingCartOutlined
} from '@ant-design/icons-vue';
import { defineEmits, defineProps, onMounted, ref, watch } from "vue";
import { RouterLink, useRouter, useRoute } from 'vue-router';
import common from "@/utils/common";

const router = useRouter();
const route = useRoute();
const emits = defineEmits(['on-click-menu']);

const props = defineProps({
  openMenu: Boolean
})
const toggleOpenMenu = () => {
  emits('on-click-menu', !props.openMenu);
}
const openKeys = ref('');
const selectedKeys = ref('');

onMounted(() => {
  selectedKeys.value = [route.fullPath.replace(/\/[0-9]/g, '')]
  openKeys.value = [[...orderList, ...createOrderList].find(x => x.path == selectedKeys.value)?.parent] || [];
})

watch(
  () => route.fullPath,
  () => {
  selectedKeys.value = [route.fullPath]
  openKeys.value = [[...orderList, ...createOrderList].find(x => x.path == selectedKeys.value)?.parent] || [];
})

const orderList = [
  {
    parent: 1,
    name: 'Tất cả đơn hàng',
    path: '/admin/order',
  },
  {
    parent: 1,
    name: 'Đơn hàng chờ xử lý',
    path: '/admin/order?status=' + common.TYPE_ORDER_PENDING,
  },
  {
    parent: 1,
    name: 'Đơn hàng đang xử lý',
    path: '/admin/order?status=' + common.TYPE_ORDER_PROCESSING,
  },
  {
    parent: 1,
    name: 'Đơn hàng đã xử lý',
    path: '/admin/order?status=' + common.TYPE_ORDER_DONE,
  },
  {
    parent: 1,
    name: 'Đơn hàng phát sinh',
    path: '/admin/order?status=' + common.TYPE_ORDER_OCCURRED,
  },
  {
    parent: 1,
    name: 'Đơn hàng bị hủy',
    path: '/admin/order?status=' + common.TYPE_ORDER_CANCEL,
  },
  {
    parent: 1,
    name: 'Đơn hàng trả lại',
    path: '/admin/order?status=' + common.TYPE_ORDER_RETURN,
  }
];

const createOrderList = [
  {
    parent: 2,
    name: 'Tạo đơn hàng mới',
    path: '/admin/order/create',
  },
  {
    parent: 2,
    name: 'Cập nhật đơn hàng',
    path: '/admin/order/update',
  },
]

const handleClickItem = (inList = false, path) => {
  if (inList) {
    return router.push(path);
  }
}
const onOpenChange = key => {
 openKeys.value = [key[key.length - 1]]
};
</script>

<template>
  <div class="d-flex align-items-center justify-content-between">
    <RouterLink :to="'/'" v-if="openMenu">
      <img src="@/images/logo-branch.png" alt="Logo branch" width="150" />
    </RouterLink>
    <DoubleLeftOutlined v-if="openMenu" @click="toggleOpenMenu" class="half-circle-left" />
    <DoubleRightOutlined v-else @click="toggleOpenMenu" class="half-circle-right" />
  </div>
  <a-menu mode="inline" :inline-collapsed="!openMenu" class="mt-5" v-model:openKeys="openKeys"
    v-model:selectedKeys="selectedKeys" @openChange="onOpenChange">
    <a-menu-item key="1" style="background: #bf1e2d; color: #ffffff;">
      <template #icon>
        <HomeOutlined />
      </template>
      Trang chủ
    </a-menu-item>
    <a-sub-menu :key="1" style="background: #bf1e2d; color: #ffffff;">
      <template #icon>
        <ShoppingCartOutlined />
      </template>
      <template #title>Quản lý đơn hàng</template>
      <a-menu-item :key="option.path" v-for="option in orderList" @click="handleClickItem(true, option.path)">{{
          option.name
      }}</a-menu-item>
    </a-sub-menu>
    <a-sub-menu :key="2" style="background: #bf1e2d; color: #ffffff;">
      <template #icon>
        <ShoppingCartOutlined />
      </template>
      <template #title>Tạo đơn hàng tại bưu cục</template>
      <a-menu-item :key="option.path" v-for="option in createOrderList" @click="handleClickItem(true, option.path)">{{
          option.name
      }}</a-menu-item>
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