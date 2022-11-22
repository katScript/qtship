<script setup>
import { defineProps, defineEmits, reactive, watch } from 'vue';
import { SearchOutlined, CloseCircleOutlined } from '@ant-design/icons-vue';
import common from '@/utils/common';

const props = defineProps({
  status: {
    type: String,
    default: ''
  },
})

const emits = defineEmits(['on-handle-find']);

const filters = reactive({
  orderCode: '',
  orderPhone: '',
  orderStatus: '',
  orderTime: '',
  orderTimeAbout: '',
  orderType: '',
});

const handleFind = () => {
  emits('on-handle-find', filters);
}

const handleReset = () => {
  filters.orderCode = '';
  filters.orderPhone = '';
  filters.orderStatus = '';
  filters.orderTime = '';
  filters.orderTimeAbout = '';
  filters.orderType = '';
  emits('on-handle-find', filters);
}

watch(() => props.status, () => {
  handleReset()
});
</script>
<template>
  <a-row type="flex" class="my-4" :gutter="[16, 16]" align="top">
    <a-col :span="6">
      <a-input-number style="width: 100%;" v-model:value="filters.orderCode" addon-before="ORD" placeholder="Mã đơn hàng" />
    </a-col>
    <a-col :span="6">
      <a-input v-model:value="filters.orderPhone" placeholder="Số điện thoại người nhận" />
    </a-col>
    <a-col :span="6" v-if="!status">
      <a-select v-model:value="filters.orderStatus" style="width: 100%">
        <a-select-option value="" hidden>Trạng thái đơn hàng</a-select-option>
        <a-select-option
          v-for="(type, index) in common.ALL_TYPE"
          :value="type.value"
          :key="index"
        >
          {{ type.name }}
        </a-select-option>
      </a-select>
    </a-col>
    <a-col :span="6" v-if="status == 'PROCESSING'">
      <a-select v-model:value="filters.orderStatus" style="width: 100%">
        <a-select-option value="" hidden>Trạng thái đơn hàng</a-select-option>
        <a-select-option
          v-for="(type, index) in common.PROCESSING_TYPE"
          :value="type.value"
          :key="index"
        >
          {{ type.name }}
        </a-select-option>
      </a-select>
    </a-col>
    <a-col :span="6">
      <a-select v-model:value="filters.orderTime" style="width: 100%">
        <a-select-option value="" hidden>Thời gian tạo đơn</a-select-option>
        <a-select-option v-for="(type, index) in common.TIME_CREATE" :value="type.value" :key="index">{{ type.name }}</a-select-option>
      </a-select>
      <a-range-picker format="YYYY-MM-DD" :placeholder="['Từ ngày', 'Đến ngày']" v-if="filters.orderTime == 'timeCOTimeAbout'" class="mt-2" v-model:value="filters.orderTimeAbout" />
      
    </a-col>
    <a-col :span="6">
      <a-select v-model:value="filters.orderType" style="width: 100%">
        <a-select-option value="" hidden>Dịch vụ giao hàng</a-select-option>
        <a-select-option v-for="(type, index) in common.TYPE_SHIPPING" :value="type.value" :key="index">{{ type.name }}</a-select-option>
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