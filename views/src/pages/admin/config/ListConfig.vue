<script setup>
import { ref } from "vue";
import { scope, save } from "@/services/config";
import { message } from "ant-design-vue";
import { omit } from "lodash";
// import { useStore } from "vuex";

// const store = useStore();

const feeConfig = ref([]);

const handleGetFeeConfig = async () => {
  const { data } = await scope('fee');
  feeConfig.value = data.map(x => (omit(x, ['updatedAt', 'createdAt'])));
}

const handleSetFeeConfig = async () => {
  await save(feeConfig.value);
  message.success('Thành công');
  handleGetFeeConfig();
}
//
handleGetFeeConfig();
const name = [
  'VAT',
  'Phí nhiên liệu',
  'Giới hạn khối lượng',
  'Phí khối lượng',
];
const unit = [
  '%',
  '%',
  'kg',
  '%',
];
</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">
      Cài đặt
    </div>
    <div class="mt-2 p-2" v-if="feeConfig.length">
      <div class="fs-4">Cài đặt giá</div>
      <div class="">
        <div class="mb-2" v-for="(config, index) in feeConfig" :key="index" style="max-width: 500px;">
          <div class="font-weight-bold text-capitalize mb-1 mt-2">{{ name[index] }}: </div>
          <a-input-number
            style="width: 100%;"
            v-model:value="config.value"
            v-if="config.type == 'int'"
            :placeholder="name[index]"
            :addon-after="unit[index]"
          />
        </div>
      </div> 
      <a-button type="primary" @click="handleSetFeeConfig">Lưu</a-button>
    </div>    
  </div>
</template>
