<script setup>
import {reactive, ref, defineProps} from "vue";
import common from "@/utils/common";
import {listWarehouse} from "@/services/warehouse";

const props = defineProps(['customerId']);

const data = reactive({
    type: '',
    place: 'default',
    warehouseId: '',
    time: '',
    objPay: ''
});

const warehouses = ref([]);

const handleGetWarehouse = async () => {
    const {data} = await listWarehouse(props.customerId);
    warehouses.value = data;
}

handleGetWarehouse();
</script>

<template>
    <div class="border">
        <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Phương thức giao & lấy hàng
        </div>
        <div class="p-3">
            <a-form-item label="Phương thức giao hàng" name="type"
                         :rules="[{ required: true, message: 'Vui lòng nhập phương thức giao hàng!' }]">
                <a-select v-model:value="data.type" style="width: 100%">
                    <a-select-option value="">- Phương thức giao hàng -</a-select-option>
                    <a-select-option v-for="(type, index) in common.TYPE_SHIPPING"
                                     :value="type.value" :key="index">{{ type.name }}
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-radio-group v-model:value="data.place">
                <a-radio class="d-block mb-2" :value="'default'">Gửi hàng tại bưu cục</a-radio>
                <a-radio class="d-block my-2" :value="'option'">Lấy hàng tận nơi</a-radio>
            </a-radio-group>
            <div v-if="data.place === 'option'" class="my-2">
                <a-form-item label="Địa điểm lấy hàng" name="warehouse"
                             :rules="[{ required: true, message: 'Vui lòng chọn địa điểm lấy hàng!' }]">
                    <a-select v-model:value="data.warehouseId" style="width: 100%">
                        <a-select-option class="my-2" value="">- Địa điểm lấy hàng -
                        </a-select-option>
                        <a-select-option v-for="(type, index) in warehouses" :value="type.id"
                                         :key="index">{{ type.name }}
                        </a-select-option>
                    </a-select>
                </a-form-item>
                <a-form-item label="Thời gian lấy hàng" name="time"
                             :rules="[{ required: true, message: 'Vui lòng nhập thời gian lấy hàng!' }]">
                    <a-date-picker v-model:value="data.time" class="my-2"
                                   style="width: 100%" show-time
                                   placeholder="Thời gian lấy hàng"/>
                </a-form-item>
            </div>
            <div class="border-top mt-2 pt-2">
                <a-form-item label="Trả phí ship" name="objPay"
                             :rules="[{ required: true, message: 'Vui lòng nhập trả phí ship!' }]">
                    <a-radio-group v-model:value="data.objPay">
                        <a-radio class="d-block mb-2" :value="true">Shop trả phí ship</a-radio>
                        <a-radio class="d-block my-2" :value="false">Người nhận trả phí ship
                        </a-radio>
                    </a-radio-group>
                </a-form-item>
            </div>
        </div>
    </div>
</template>