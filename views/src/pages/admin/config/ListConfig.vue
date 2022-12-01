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

</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">
      Cài đặt
    </div>
    <div class="mt-2 p-2" v-if="feeConfig.length">
      <div class="fs-4">Cài đặt giá</div>
      <div class="d-flex my-2 flex-wrap gap-3">
        <div class="flex-fill" v-for="(config, index) in feeConfig" :key="index" style="max-width: 300px;">
          <span class="font-weight-bold text-capitalize me-3">{{ config.key.replaceAll('_', ' ') }}: </span>
          <a-input-number style="width: 100%;" v-model:value="config.value" v-if="config.type == 'int'" :placeholder="config.key" />
        </div>
      </div> 
      <a-button type="primary" @click="handleSetFeeConfig">Lưu</a-button>
    </div>    
  </div>
</template>
