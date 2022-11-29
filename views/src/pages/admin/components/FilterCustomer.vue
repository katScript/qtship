<script setup>
import { defineEmits, reactive } from "vue";
import { SearchOutlined, CloseCircleOutlined } from "@ant-design/icons-vue";

const emits = defineEmits(["on-handle-find"]);

const filters = reactive({
  code: "",
  name: "",
  company: "",
  phone: "",
  email: "",
});

const handleFind = () => {
  emits("on-handle-find", filters);
};

const handleReset = () => {
  filters.code = "";
  filters.name = "";
  filters.company = "";
  filters.phone = "";
  filters.email = "";
  emits("on-handle-find", filters);
};
</script>
<template>
  <a-row type="flex" class="my-4" :gutter="[16, 16]" align="top">
    <a-col :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 6 }">
      <a-input-number
        style="width: 100%"
        v-model:value="filters.code"
        addon-before="CTM"
        placeholder="Mã khách hàng"
      />
    </a-col>
    <a-col :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 6 }">
      <a-input v-model:value="filters.name" placeholder="Tên khách hàng" />
    </a-col>
    <a-col :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 6 }">
      <a-input
        v-model:value="filters.company"
        placeholder="Tên cửa hàng / công ty"
      />
    </a-col>
    <a-col :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 6 }">
      <a-input v-model:value="filters.phone" placeholder="Số điện thoại" />
    </a-col>
    <a-col :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 6 }">
      <a-input v-model:value="filters.email" placeholder="Email" />
    </a-col>
    <a-col :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 6 }">
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
