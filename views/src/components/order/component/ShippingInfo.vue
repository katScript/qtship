<script setup>
import {
  UserOutlined,
  PhoneOutlined
} from "@ant-design/icons-vue";
import {defineProps, reactive, ref, defineEmits} from "vue";
import {province, district, ward} from "@/services/region";

const props = defineProps({
  shippingInfo: {
    type: Object,
    default: () => {
      return {
        name: '',
        phone: '',
        province: '',
        provinceId: null,
        district: '',
        districtId: null,
        ward: '',
        wardId: null,
        street: ''
      }
    }
  }
});

const emits = defineEmits(['shipping-info-change'])

const data = reactive({
  name: props.shippingInfo.name,
  phone: props.shippingInfo.phone,
  province: props.shippingInfo.province,
  provinceId: props.shippingInfo.provinceId,
  district: props.shippingInfo.district,
  districtId: props.shippingInfo.districtId,
  ward: props.shippingInfo.ward,
  wardId: props.shippingInfo.wardId,
  street: props.shippingInfo.street
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
  console.log({...data})
  districts.value = data;
}

const handleGetWard = async (districtId) => {
  resetWardData();
  const {data} = await ward(districtId);
  wards.value = data;
}

// process address data
const resetDistrictData = () => {
  districts.value = [];
  data.district = '';
  data.districtId = null;
}

const resetWardData = () => {
  wards.value = [];
  data.ward = '';
  data.wardId = null;
}

const provinceProcess = () => {
  const province = provinces.value.find(x => x.province_id === data.provinceId);
  if (province) {
    data.province = province.province_name;
    handleGetDistrict(data.provinceId).then(() => {
      districtProcess();
    });
  } else {
    resetDistrictData();
  }

  emits('shipping-info-change', data);
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

  emits('shipping-info-change', data);
}

const wardProcess = () => {
  const ward = wards.value.find(x => x.ward_id === data.wardId);
  if (ward) {
    data.ward = ward?.ward_name;
    data.wardId = ward?.ward_id;
  }

  emits('shipping-info-change', data);
}

// startup
handleGetProvince();
</script>

<template>
  <div class="border mt-3">
    <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Thông tin người nhận
    </div>
    <div class="p-3">
      <a-form-item label="Tên người nhận" :name="['shippingInfo', 'name']">
        <a-input v-model:value="data.name"
                 placeholder="Tên người nhận"
                 @change="emits('shipping-info-change', data)">
          <template #prefix>
            <UserOutlined class="me-2"/>
          </template>
        </a-input>
      </a-form-item>
      <a-form-item label="Số điện thoại người nhận" :name="['shippingInfo', 'phone']">
        <a-input v-model:value="data.phone"
                 placeholder="Số điện thoại người nhận"
                 @change="emits('shipping-info-change', data)">
          <template #prefix>
            <PhoneOutlined class="me-2"/>
          </template>
        </a-input>
      </a-form-item>
      <a-form-item label="Tỉnh / thành phố" :name="['shippingInfo', 'provinceId']">
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
      <a-form-item label="Quận / huyện " :name="['shippingInfo', 'districtId']">
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
      <a-form-item label="Xã / phường" :name="['shippingInfo', 'wardId']">
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
      <a-form-item label="Địa chỉ cụ thể" :name="['shippingInfo', 'street']">
        <a-input v-model:value="data.street"
                 placeholder="Địa chỉ cụ thể: Số nhà, Tên đường..."
                 @change="emits('shipping-info-change', data)"
        />
      </a-form-item>
    </div>
  </div>
</template>