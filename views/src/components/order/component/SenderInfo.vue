<script setup>
import {AimOutlined, PhoneOutlined, UserOutlined} from "@ant-design/icons-vue";
import {defineEmits, defineProps, ref, watch} from "vue";

const props = defineProps({
  senderInfo: {
    type: Object,
    default: () => ({
      name: '',
      phone: '',
      address: ''
    })
  }
});
const emits = defineEmits(['sender-info-change']);

const data = ref({
    name: props.senderInfo.name,
    phone: props.senderInfo.phone,
    address: props.senderInfo.address
});

watch(props, () => {
  data.value = props.senderInfo;
});

</script>

<template>
    <div class="border mt-3">
        <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Thông tin người gửi</div>
        <div class="p-3">
            <a-form-item label="Thông tin người gửi" :name="['senderInfo', 'name']">
                <a-input v-model:value="data.name"
                         placeholder="Thông tin người gửi"
                         @change="emits('sender-info-change', data)"
                >
                    <template #prefix>
                        <UserOutlined class="me-2"/>
                    </template>
                </a-input>
            </a-form-item>
            <a-form-item label="Số điện thoại người gửi" :name="['senderInfo', 'phone']">
                <a-input v-model:value="data.phone"
                         placeholder="Số điện thoại người gửi"
                         @change="emits('sender-info-change', data)"
                >
                    <template #prefix>
                        <PhoneOutlined class="me-2"/>
                    </template>
                </a-input>
            </a-form-item>
            <a-form-item label="Địa chỉ người gửi" :name="['senderInfo', 'address']">
                <a-input v-model:value="data.address"
                         placeholder="Địa chỉ người gửi"
                         @change="emits('sender-info-change', data)"
                >
                    <template #prefix>
                        <AimOutlined class="me-2"/>
                    </template>
                </a-input>
            </a-form-item>
        </div>
    </div>
</template>