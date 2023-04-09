<script setup>
import {
    UserOutlined,
    PhoneOutlined
} from "@ant-design/icons-vue";
import {computed, defineProps, reactive, ref} from "vue";
import {province, district, ward} from "@/services/region";

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
    },
    province: {
        type: String,
        default: ''
    },
    provinceId: {
        type: Number,
        default: null
    },
    district: {
        type: String,
        default: ''
    },
    districtId: {
        type: Number,
        default: null
    },
    ward: {
        type: String,
        default: ''
    },
    wardId: {
        type: Number,
        default: null
    },
    street: {
        type: String,
        default: ''
    }
});

const data = reactive({
    name: props.name,
    phone: props.phone,
    address: props.address,
    province: props.province,
    provinceId: props.provinceId,
    district: props.district,
    districtId: props.districtId,
    ward: props.ward,
    wardId: props.wardId,
    street: props.street
});

const provinces = ref([{
    province_id: '',
    province_name: ''
}]);

const districts = ref([{
    district_id: '',
    district_name: ''
}]);

const wards = ref([{
    ward_id: '',
    ward_name: ''
}]);

// get address data
const handleGetProvince = async () => {
    const {data} = await province();
    provinces.value = data;
}

const handleGetDistrict = async (provinceId) => {
    resetDistrictData();
    const {data} = await district(provinceId);
    districts.value = data;
}

const requiredDistrict = computed(() => data.provinceId && districts.value.length);

const handleGetWard = async (districtId) => {
    resetWardData();
    const {data} = await ward(districtId);
    wards.value = data;
}

const requiredWard = computed(() => data.provinceId && data.districtId && wards.value.length);

// process address data
const resetDistrictData = () => {
    districts.value = [];
    data.district = '';
    data.districtId = '';
}

const resetWardData = () => {
    wards.value = [];
    data.ward = '';
    data.wardId = '';
}

const provinceProcess = () => {
    const province = provinces.value.find(x => x.province_id === data.provinceId);
    if (province) {
        data.province = province.province_name;
        handleGetDistrict(data.province).then(() => {
            districtProcess();
        });
    } else {
        resetDistrictData();
    }
}

const districtProcess = () => {
    const district = districts.value.find(x => x.district_id === data.districtId);
    if (district) {
        data.district = district.district_name;
        handleGetWard(data.districtId).then(() => {
            wardProcess();
        });
    } else {
        resetWardData();
    }
}

const wardProcess = () => {
    const ward = wards.value.find(x => x.ward_id === data.wardId);
    if (ward) {
        data.ward = ward?.ward_name;
        data.wardId = ward?.ward_id;
    }
}

// startup
handleGetProvince();
</script>

<template>
    <div class="border mt-3">
        <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Thông tin người nhận
        </div>
        <div class="p-3">
            <a-form-item label="Tên người nhận" name="name"
                         :rules="[{ required: true, message: 'Vui lòng nhập tên người nhận!' }]">
                <a-input v-model:value="data.name" placeholder="Tên người nhận">
                    <template #prefix>
                        <UserOutlined class="me-2"/>
                    </template>
                </a-input>
            </a-form-item>
            <a-form-item label="Số điện thoại người nhận" name="phone"
                         :rules="[{ required: true, message: 'Vui lòng nhập số điện thoại người nhận!' }]">
                <a-input v-model:value="data.phone"
                         placeholder="Số điện thoại người nhận">
                    <template #prefix>
                        <PhoneOutlined class="me-2"/>
                    </template>
                </a-input>
            </a-form-item>
            <a-form-item label="Tỉnh / thành phố" name="provinceId"
                         :rules="[{ required: true, message: 'Vui lòng nhập tỉnh / thành phố người nhận!' }]">
                <a-select v-model:value="data.provinceId" style="width: 100%" @change="provinceProcess">
                    <a-select-option value=''>- Tỉnh / thành phố -</a-select-option>
                    <a-select-option v-for="(province, index) in provinces"
                                     :value="province.province_id" :key="index">
                        {{
                            province.province_name
                        }}
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="Quận / huyện " name="districtId"
                         :rules="[{ required: requiredDistrict, message: 'Vui lòng nhập quận / huyện người nhận!' }]">
                <a-select v-model:value="data.districtId" style="width: 100%" @change="districtProcess">
                    <a-select-option value=''>- Quận / huyện -</a-select-option>
                    <a-select-option v-for="(district, index) in districts"
                                     :value="district.district_id" :key="index">
                        {{
                            district.district_name
                        }}
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="Xã / phường" name="wardId"
                         :rules="[{ required: requiredWard, message: 'Vui lòng nhập xã / phường người nhận!' }]">
                <a-select v-model:value="data.wardId" style="width: 100%" @change="wardProcess">
                    <a-select-option value=''>- Xã / phường -</a-select-option>
                    <a-select-option v-for="(ward, index) in wards" :value="ward.ward_id"
                                     :key="index">
                        {{
                            ward.ward_name
                        }}
                    </a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="Địa chỉ cụ thể" name="street"
                         :rules="[{ required: true, message: 'Vui lòng nhập Địa chỉ cụ thể người nhận!' }]">
                <a-input v-model:value="data.street"
                         placeholder="Địa chỉ cụ thể: Số nhà, Tên đường..."/>
            </a-form-item>
        </div>
    </div>
</template>