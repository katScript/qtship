<script setup>
import {reactive, ref, defineProps, defineEmits} from "vue";
import common from "@/utils/common";
import {listWarehouse} from "@/services/warehouse";
import {officeList} from "@/services/office";

const props = defineProps({
  customerId: {
    type: Number,
    default: null
  },
  shippingType: {
    type: Object,
    default: () => {
      return {
        type: '',
        warehouseId: null,
        officeId: null,
        time: '',
        objPay: true
      }
    }
  },
  isCustomer: {
    type: Boolean,
    default: true
  }
});
const emits = defineEmits(['shipping-type-change']);

const data = reactive({
  type: props.shippingType.type,
  warehouseId: props.shippingType.warehouseId,
  officeId: props.shippingType.officeId,
  time: props.shippingType.time,
  objPay: props.shippingType.objPay
});

const warehouses = ref([]);
const offices = ref([]);

const handleGetWarehouse = async () => {
  const {data} = await listWarehouse(props.customerId);
  warehouses.value = data;
}

const handleGetOffice = async () => {
  const {data} = await officeList();
  offices.value = data;
}

handleGetWarehouse();
handleGetOffice();
</script>

<template>
  <div class="border mt-3">
    <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Phương thức giao & lấy hàng
    </div>
    <div class="p-3">
      <a-form-item label="Phương thức giao hàng" :name="['shippingType', 'type']">
        <a-select v-model:value="data.type" style="width: 100%"
                  @change="emits('shipping-type-change', data)">
          <a-select-option value="">- Phương thức giao hàng -</a-select-option>
          <a-select-option v-for="(type, index) in common.TYPE_SHIPPING"
                           :value="type.value" :key="index">{{ type.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item v-if="props.isCustomer" label="Địa điểm lấy hàng" :name="['shippingType', 'warehouseId']">
        <a-select v-model:value="data.warehouseId"
                  style="width: 100%"
                  @change="emits('shipping-type-change', data)"
        >
          <a-select-option class="my-2" value="">- Địa điểm lấy hàng -
          </a-select-option>
          <a-select-option v-for="(type, index) in warehouses" :value="type.id"
                           :key="index">{{ type.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item v-else label="Bưu cục nhận hàng" :name="['shippingType', 'officeId']">
        <a-select v-model:value="data.officeId"
                  style="width: 100%"
                  @change="emits('shipping-type-change', data)"
        >
          <a-select-option class="my-2" value="">- Địa điểm nhận hàng -
          </a-select-option>
          <a-select-option v-for="(of, index) in offices" :value="of.id"
                           :key="index">{{ of.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="Thời gian lấy hàng" :name="['shippingType', 'time']">
        <a-date-picker v-model:value="data.time" class="my-2"
                       style="width: 100%" show-time
                       placeholder="Thời gian lấy hàng"
                       @change="emits('shipping-type-change', data)"
        />
      </a-form-item>
      <div class="border-top mt-2 pt-2">
        <a-form-item label="Trả phí ship" :name="['shippingType', 'objPay']">
          <a-radio-group v-model:value="data.objPay"
                         @change="emits('shipping-type-change', data)">
            <a-radio class="d-block mb-2" :value="true">Shop trả phí ship</a-radio>
            <a-radio class="d-block my-2" :value="false">Người nhận trả phí ship
            </a-radio>
          </a-radio-group>
        </a-form-item>
      </div>
    </div>
  </div>
</template>