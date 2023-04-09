<script setup>
import {AimOutlined, PhoneOutlined, UserOutlined} from "@ant-design/icons-vue";
import {defineEmits, defineProps, reactive  } from "vue";

const props = defineProps({
    name: {
        type: String,
        default: ''
    },
    phone: {
        type: String,
        default: ''
    },
    address: {
        type: String,
        default: ''
    }
});
const emits = defineEmits(['sender-change']);

const data = reactive({
    name: props.name,
    phone: props.phone,
    address: props.address
});
</script>

<template>
    <div class="border">
        <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Thông tin người gửi</div>
        <div class="p-3">
            <a-form-item label="Thông tin người gửi" name="['senderInfo', 'name']"
                         :rules="[{ required: true, message: 'Vui lòng nhập thông tin người gửi!' }]">
                <a-input v-model:value="data.name"
                         placeholder="Thông tin người gửi"
                         @change="emits('sender-change', data)"
                >
                    <template #prefix>
                        <UserOutlined class="me-2"/>
                    </template>
                </a-input>
            </a-form-item>
            <a-form-item label="Số điện thoại người gửi" name="phone"
                         :rules="[{ required: true, message: 'Vui lòng nhập số điện thoại người gửi!' }]">
                <a-input v-model:value="data.phone"
                         placeholder="Số điện thoại người gửi"
                         @change="emits('sender-change', data)"
                >
                    <template #prefix>
                        <PhoneOutlined class="me-2"/>
                    </template>
                </a-input>
            </a-form-item>
            <a-form-item label="Địa chỉ người gửi" name="address"
                         :rules="[{ required: true, message: 'Vui lòng nhập địa chỉ người gửi!' }]">
                <a-input v-model:value="data.address"
                         placeholder="Địa chỉ người gửi"
                         @change="emits('sender-change', data)"
                >
                    <template #prefix>
                        <AimOutlined class="me-2"/>
                    </template>
                </a-input>
            </a-form-item>
        </div>
    </div>
</template>