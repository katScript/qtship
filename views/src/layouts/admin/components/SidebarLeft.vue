<script setup>
import {
  DoubleLeftOutlined,
  DoubleRightOutlined,
  HomeOutlined,
  ShoppingCartOutlined,
  ShopOutlined,
  TeamOutlined,
  LogoutOutlined,
  CarOutlined,
  MoneyCollectOutlined,
  ClusterOutlined,
  SettingOutlined,
} from "@ant-design/icons-vue";
import { defineEmits, defineProps, onMounted, ref, watch } from "vue";
import { RouterLink, useRouter, useRoute } from "vue-router";
import common from "@/utils/common";

const router = useRouter();
const route = useRoute();

const openKeys = ref("");
const selectedKeys = ref("");

const emits = defineEmits(["on-click-menu"]);

const props = defineProps({
  openMenu: Boolean,
});

const orderList = [
  {
    parent: 1,
    name: "Tất cả đơn hàng",
    path: "/admin/order/list",
  },
  {
    parent: 1,
    name: "Đơn hàng chờ xử lý",
    path: "/admin/order/list?status=" + common.TYPE_ORDER_PENDING,
  },
  {
    parent: 1,
    name: "Đơn hàng đang xử lý",
    path: "/admin/order/list?status=" + common.TYPE_ORDER_PROCESSING,
  },
  {
    parent: 1,
    name: "Đơn hàng đã xử lý",
    path: "/admin/order/list?status=" + common.TYPE_ORDER_DONE,
  },
  {
    parent: 1,
    name: "Đơn hàng phát sinh",
    path: "/admin/order/list?status=" + common.TYPE_ORDER_OCCURRED,
  },
  {
    parent: 1,
    name: "Đơn hàng bị hủy",
    path: "/admin/order/list?status=" + common.TYPE_ORDER_CANCEL,
  },
  {
    parent: 1,
    name: "Đơn hàng trả lại",
    path: "/admin/order/list?status=" + common.TYPE_ORDER_RETURN,
  },
];

const createOrderList = [
  {
    parent: 2,
    name: "Tạo đơn hàng mới",
    path: "/admin/order",
  }
];

const customerList = [
  {
    parent: 3,
    name: "Quản lý khách hàng",
    path: "/admin/customer",
  },
];

const shipperList = [
  {
    parent: 4,
    name: "Quản lý người giao hàng",
    path: "/admin/shipper",
  },
];

const officeList = [
  {
    parent: 5,
    name: "Quản lý bưu cục",
    path: "/admin/office",
  },
];

const configList = [
  {
    parent: 6,
    name: "Cài đặt",
    path: "/admin/config",
  },
];

const priceSetList = [
  {
    parent: 7,
    name: "Tạo bảng giá mới",
    path: "/admin/price",
  },
];

const toggleOpenMenu = () => {
  emits("on-click-menu", !props.openMenu);
};

const handleSignOut = () => {
  common.SIGNOUT();
  router.push("/");
};

const handleChangeRoute = () => {
  if (!props.openMenu) {
    return;
  }
  selectedKeys.value = [route.fullPath.replace(/\/[0-9]/g, "")];
  openKeys.value =
    [
      [...orderList, ...createOrderList, ...customerList].find(
        (x) => x.path == selectedKeys.value
      )?.parent,
    ] || [];
};

onMounted(() => {
  handleChangeRoute();
});

watch(
  () => route.fullPath,
  () => {
    handleChangeRoute();
  }
);

const handleClickItem = (inList = false, path) => {
  if (inList) {
    return router.push(path);
  }
};
const onOpenChange = (key) => {
  openKeys.value = [key[key.length - 1]];
};
</script>

<template>
  <div class="position-relative h-100">
    <RouterLink :to="'/'" class="d-block d-lg-none my-3">
      <img src="@/images/logo-branch.png" alt="Logo branch" width="75" />
    </RouterLink>
    <div class="d-lg-flex align-items-center justify-content-between d-none">
      <RouterLink :to="'/'" v-if="openMenu">
        <img src="@/images/logo-branch.png" alt="Logo branch" width="150" />
      </RouterLink>
      <DoubleLeftOutlined
        v-if="openMenu"
        @click="toggleOpenMenu"
        class="half-circle-left"
      />
      <DoubleRightOutlined
        v-else
        @click="toggleOpenMenu"
        class="half-circle-right"
      />
    </div>
    <a-menu
      mode="inline"
      :inline-collapsed="!openMenu"
      class="mt-5"
      v-model:openKeys="openKeys"
      v-model:selectedKeys="selectedKeys"
      @openChange="onOpenChange"
    >
      <a-menu-item key="1" style="background: #bf1e2d; color: #ffffff">
        <template #icon>
          <HomeOutlined />
        </template>
        Trang chủ
      </a-menu-item>
      <a-sub-menu :key="1" style="background: #bf1e2d; color: #ffffff">
        <template #icon>
          <ShoppingCartOutlined />
        </template>
        <template #title>Quản lý đơn hàng</template>
        <a-menu-item
          :key="option.path"
          v-for="option in orderList"
          @click="handleClickItem(true, option.path)"
          >{{ option.name }}</a-menu-item
        >
      </a-sub-menu>
      <a-sub-menu :key="2" style="background: #bf1e2d; color: #ffffff">
        <template #icon>
          <ShopOutlined />
        </template>
        <template #title>Tạo đơn hàng tại bưu cục</template>
        <a-menu-item
          :key="option.path"
          v-for="option in createOrderList"
          @click="handleClickItem(true, option.path)"
          >{{ option.name }}</a-menu-item
        >
      </a-sub-menu>
      <a-menu-item
        style="background: #bf1e2d; color: #ffffff"
        :key="option.parent"
        v-for="option in customerList"
        @click="handleClickItem(true, option.path)"
      >
        <template #icon>
          <TeamOutlined />
        </template>
        {{ option.name }}</a-menu-item
      >
      <a-menu-item
        style="background: #bf1e2d; color: #ffffff"
        :key="option.parent"
        v-for="option in shipperList"
        @click="handleClickItem(true, option.path)"
      >
        <template #icon>
          <CarOutlined />
        </template>
        {{ option.name }}</a-menu-item
      >
      <a-menu-item
        style="background: #bf1e2d; color: #ffffff"
        :key="option.parent"
        v-for="option in officeList"
        @click="handleClickItem(true, option.path)"
      >
        <template #icon>
          <ClusterOutlined />
        </template>
        {{ option.name }}</a-menu-item
      >
      <a-menu-item
        style="background: #bf1e2d; color: #ffffff"
        :key="option.parent"
        v-for="option in configList"
        @click="handleClickItem(true, option.path)"
      >
        <template #icon>
          <SettingOutlined />
        </template>
        {{ option.name }}
      </a-menu-item>
      <a-menu-item
        style="background: #bf1e2d; color: #ffffff"
        :key="option.parent"
        v-for="option in priceSetList"
        @click="handleClickItem(true, option.path)"
      >
        <template #icon>
          <MoneyCollectOutlined />
        </template>
        {{ option.name }}
      </a-menu-item>
    </a-menu>
    <div class="position-absolute bottom-0 left-0 right-0 w-100">
      <div
        class="bg-dark text-white p-2 text-center"
        style="cursor: pointer"
        @click="handleSignOut"
      >
        <LogoutOutlined class="me-2" style="transform: translate(0px, -3px)" />
        <span v-if="openMenu">Đăng xuất</span>
      </div>
    </div>
  </div>
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
