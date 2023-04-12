<script setup>
import {defineEmits, defineProps, reactive, ref} from "vue";
import {
  AccountBookOutlined,
  CheckCircleOutlined
} from "@ant-design/icons-vue";
import {listActive} from "@/services/coupon";
import common from "@/utils/common";

const props = defineProps({
  otherInfo: {
    type: Object,
    default: () => {
      return {
        note: '',
        coupon: '',
        returnCode: ''
      }
    }
  }
});
const emits = defineEmits(['other-info-change']);

const data = reactive({
  note: props.otherInfo.note,
  coupon: props.otherInfo.coupon,
  returnCode: props.otherInfo.returnCode
});

const coupons = ref([{
  code: '',
  value: '',
  rule: ''
}]);

const handleSetCoupon = (code) => {
  data.coupon = code;
  emits('other-info-change', data);
}

const handleGetCoupon = async () => {
  const {data} = await listActive();
  coupons.value = data;
}

handleGetCoupon();
</script>
<template>
  <div class="border mt-3">
    <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Thông tin thêm</div>
    <div class="p-3">
      <a-textarea v-model:value="data.note" class="my-2" style="resize: none;"
                  placeholder="Ghi chú đơn hàng"
                  :rows="6"
                  @change="emits('other-info-change', data)"
      />
      <div class="d-flex justify-content-between my-2">
        <a-input v-model:value="data.coupon"
                 class="me-2"
                 placeholder="Mã giảm giá"
                 @change="emits('other-info-change', data)"
        >
          <template #prefix>
            <AccountBookOutlined class="me-2"/>
          </template>
        </a-input>
        <a-button type="primary">Áp dụng</a-button>
      </div>
      <a-popover title="Danh sách mã giảm giá" trigger="click">
        <template #content>
          <div class="content">
            <div
                class="d-flex p-2 border-top justify-content-between align-items-center"
                v-for="(coupon, index) in coupons" :key="index">
              <div>
                <div>
                  <b>{{ coupon.code }}</b>
                </div>
                <small>Giảm giá {{ coupon.value }}
                  {{ coupon.rule === 'base' ? "VND" : "%" }}</small>
              </div>
              <CheckCircleOutlined style="color: green; cursor: pointer;"
                                   @click="handleSetCoupon(coupon.code)"/>
            </div>
          </div>
        </template>
        <a-button>Danh sách mã giảm giá</a-button>
      </a-popover>
      <a-form-item label="Trường hợp hoàn hàng" :name="['otherInfo', 'returnCode']" class="my-2">
        <a-select v-model:value="data.returnCode"
                  style="width: 100%"
                  @change="emits('other-info-change', data)"
        >
          <a-select-option value="">- Trường hợp hoàn hàng -</a-select-option>
          <a-select-option v-for="(type, index) in common.RETURN_TYPE"
                           :value="type.value" :key="index">{{ type.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </div>
  </div>
</template>