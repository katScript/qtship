<script setup>
import { defineEmits, reactive, ref } from "vue";
import { SearchOutlined, CloseCircleOutlined } from "@ant-design/icons-vue";
import { province } from "@/services/region";

const emits = defineEmits(["on-handle-find"]);

const filters = reactive({
  name: "",
  phone: "",
  provinceId: "",
});

const handleFind = () => {
  emits("on-handle-find", filters);
};

const provinces = ref([]);

const handleGetProvince = async () => {
  const { data } = await province();
  provinces.value = data;
};

const handleReset = () => {
  filters.name = "";
  filters.phone = "";
  filters.provinceId = "";
  emits("on-handle-find", filters);
};
//
handleGetProvince();
</script>
<template>
  <a-row type="flex" class="my-4" :gutter="[16, 16]" align="top">
    <a-col :span="6">
      <a-input v-model:value="filters.name" placeholder="Tên bưu cục" />
    </a-col>
    <a-col :span="6">
      <a-input v-model:value="filters.phone" placeholder="Số điện thoại" />
    </a-col>
    <a-col :span="6">
      <a-select v-model:value="filters.provinceId" style="width: 100%">
        <a-select-option value="">- Tỉnh / thành phố -</a-select-option>
        <a-select-option
          v-for="(province, index) in provinces"
          :value="province.province_id"
          :key="index"
        >
          {{ province.province_name }}</a-select-option
        >
      </a-select>
    </a-col>
    <a-col :span="6">
      <a-button type="primary" @click="handleFind">
        <template #icon>
          <SearchOutlined style="transform: translate(0px, -3px)" />
        </template>
        Tìm kiếm
      </a-button>
      <a-button type="danger" class="ms-4" @click="handleReset">
        <template #icon>
          <CloseCircleOutlined style="transform: translate(0px, -3px)" />
        </template>
        Đặt lại
      </a-button>
    </a-col>
  </a-row>
</template>
