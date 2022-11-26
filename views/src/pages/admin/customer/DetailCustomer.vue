<script setup>
import { defineProps, defineEmits, computed, watch, reactive, ref } from "vue";
import { province, district, ward } from "@/services/region";
import { update } from "@/services/customer";
import { useStore } from "vuex";
import { message } from "ant-design-vue";

const store = useStore();

const props = defineProps({
  detail: {
    type: Object,
    required: true,
  },
});

const emits = defineEmits(["on-close-detail"]);

const formState = reactive({});
const provinces = ref([]);
const districts = ref([]);
const wards = ref([]);

const isLoading = computed(() => store.getters.isLoading);
const action = computed(() => {
  const action = {
    update: 1,
    create: 2,
  };

  return action[props.detail.action] || null;
});
const title = computed(() => {
  const title = {
    update: "Cập nhật",
    create: "Tạo mới",
  };

  return title[props.detail.action] || "Chi tiết";
});
// Handle Address
const handleGetProvince = async () => {
  const { data } = await province();
  provinces.value = data;
};
const handleGetDistrict = async (provinceId) => {
  const { data } = await district(provinceId);
  districts.value = data;
};
const handleGetWard = async (districtId) => {
  const { data } = await ward(districtId);
  wards.value = data;
};
const requiredWard = computed(
  () => formState.provinceId && formState.districtId && wards.value.length
);
watch(
  () => formState.provinceId,
  () => {
    if (!formState.provinceId) {
      formState.province = "";
      districts.value = [];
      return;
    }
    const province = provinces.value.find(
      (x) => x.province_id == formState.provinceId
    );
    if (province) {
      formState.province = province.province_name;
      handleGetDistrict(formState.provinceId);
    }
  }
);
watch(
  () => formState.districtId,
  () => {
    if (!formState.districtId) {
      formState.district = "";
      wards.value = [];
      return;
    }
    const district = districts.value.find(
      (x) => x.district_id == formState.districtId
    );
    if (district) {
      formState.district = district.district_name;
      handleGetWard(formState.districtId);
    }
  }
);
watch(
  () => formState.wardId,
  () => {
    if (!formState.wardId) {
      formState.ward = "";
      wards.value = [];
      return;
    }
    const ward = wards.value.find((x) => x.ward_id == formState.wardId);
    if (ward) {
      formState.ward = ward?.ward_name;
    }
  }
);
watch(
  () => props.detail.show,
  () => {
    if (props.detail.action == "update") {
      Object.assign(formState, props.detail.data);
      formState.provinceId = formState.addressSet[0].provinceId;
      formState.districtId = formState.addressSet[0].districtId;
      formState.wardId = formState.addressSet[0].wardId;
      formState.street = formState.addressSet[0].street;
      formState.holderName = formState.forControls[0].holderName;
      formState.cardNumber = formState.forControls[0].cardNumber;
      formState.bank = formState.forControls[0].bank;
      formState.address = formState.forControls[0].address;
      handleGetDistrict(formState.provinceId);
      handleGetWard(formState.districtId);
    }

    if (props.detail.action == "create") {
      for (const key in formState) {
        delete formState[key];
      }
    }
  },
  { deep: true }
);
const handleClose = (value = false) => {
  emits("on-close-detail", value);
};

const onFinish = async (values) => {
  const data = {};
  data.id = action.value == 1 ? formState.id : null;
  data.fullName = values.fullName;
  data.userName = values.userName;
  data.customerId = formState.customerId || null;
  data.companyName = values.companyName;
  data.phone = values.phone;
  data.email = values.email;
  data.gender = null;
  data.dob = null;
  data.cidFront = null;
  data.cidBack = null;
  data.subscription = null;
  data.addressSet = [
    {
      id: action.value == 1 ? formState.addressSet[0].id : null,
      primary: action.value == 1 ? formState.addressSet[0].primary : false,
      provinceId: values.provinceId,
      districtId: values.districtId,
      wardId: values.wardId,
      street: values.street,
      province: formState.province,
      district: formState.district,
      ward: formState.ward,
    },
  ];

  data.forControls = [
    {
      holderName: formState.holderName,
      cardNumber: formState.cardNumber,
      bank: formState.bank,
      address: formState.address,
      id: action.value == 1 ? formState.forControls[0].id : null,
    },
  ];

  if (!data.id) {
    delete data.id;
  }
  if (!data.addressSet[0].id) {
    delete data.addressSet[0].id;
  }
  if (!data.forControls[0].id) {
    delete data.forControls[0].id;
  }
  store.dispatch("setLoading", true);
  try {
    await update(data);
    message.success("Thành công");
  } catch (e) {
    console.log(e.response.data);
    message.error("Thất bại");
  }
  store.dispatch("setLoading", false);
  handleClose(true);
};

const onFinishFailed = (errorInfo) => {
  console.log("Failed:", errorInfo);
};

//
handleGetProvince();
</script>

<template>
  <a-modal
    :visible="detail.show"
    :title="title + ' khách hàng'"
    width="40%"
    style="top: 20px"
    @cancel="handleClose"
  >
    <div class="content">
      <a-form
        :model="formState"
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        autocomplete="off"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
       <a-form-item
          label="Cửa hàng / công ty"
          name="companyName"
          :rules="[
            { required: true, message: 'Vui lòng nhập cửa hàng / công ty !' },
          ]"
        >
          <a-input v-model:value="formState.companyName" />
        </a-form-item>
        <a-form-item
          label="Tài khoản"
          name="userName"
          :rules="[{ required: true, message: 'Vui lòng nhập tài khoản !' }]"
        >
          <a-input v-model:value="formState.userName" />
        </a-form-item>

        <a-form-item
          label="Tên khách hàng"
          name="fullName"
          :rules="[
            { required: true, message: 'Vui lòng nhập tên khách hàng !' },
          ]"
        >
          <a-input v-model:value="formState.fullName" />
        </a-form-item>

        <a-form-item
          label="Số điện thoại"
          name="phone"
          :rules="[
            { required: true, message: 'Vui lòng nhập số điện thoại !' },
          ]"
        >
          <a-input v-model:value="formState.phone" />
        </a-form-item>

        <a-form-item
          label="Địa chỉ email"
          name="email"
          :rules="[
            {
              required: true,
              type: 'email',
              message: 'Vui lòng nhập địa chỉ email !',
            },
          ]"
        >
          <a-input v-model:value="formState.email" />
        </a-form-item>

        <a-form-item
          label="Tỉnh / thành phố"
          name="provinceId"
          :rules="[
            {
              required: true,
              message: 'Vui lòng nhập tỉnh / thành phố !',
            },
          ]"
        >
          <a-select v-model:value="formState.provinceId" style="width: 100%">
            <a-select-option value="">- Tỉnh / thành phố -</a-select-option>
            <a-select-option
              v-for="(province, index) in provinces"
              :value="province.province_id"
              :key="index"
            >
              {{ province.province_name }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item
          label="Quận / huyện "
          name="districtId"
          :rules="[
            {
              required: true,
              message: 'Vui lòng nhập quận / huyện !',
            },
          ]"
        >
          <a-select v-model:value="formState.districtId" style="width: 100%">
            <a-select-option value="">- Quận / huyện -</a-select-option>
            <a-select-option
              v-for="(district, index) in districts"
              :value="district.district_id"
              :key="index"
            >
              {{ district.district_name }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item
          label="Xã / phường"
          name="wardId"
          :rules="[
            {
              required: requiredWard,
              message: 'Vui lòng nhập xã / phường !',
            },
          ]"
        >
          <a-select v-model:value="formState.wardId" style="width: 100%">
            <a-select-option value="">- Xã / phường -</a-select-option>
            <a-select-option
              v-for="(ward, index) in wards"
              :value="ward.ward_id"
              :key="index"
            >
              {{ ward.ward_name }}</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item
          label="Địa chỉ cụ thể"
          name="street"
          :rules="[
            {
              required: true,
              message: 'Vui lòng nhập Địa chỉ cụ thể !',
            },
          ]"
        >
          <a-input
            v-model:value="formState.street"
            placeholder="Địa chỉ cụ thể: Số nhà, Tên đường..."
          />
        </a-form-item>
        <a-form-item
          label="Tên chủ tài khoản"
          name="holderName"
          :rules="[
            {
              required: true,
              message: 'Vui lòng nhập tên chủ tài khoản !',
            },
          ]"
        >
          <a-input v-model:value="formState.holderName" />
        </a-form-item>
        <a-form-item
          label="Số tài khoản"
          name="cardNumber"
          :rules="[{ required: true, message: 'Vui lòng nhập số tài khoản !' }]"
        >
          <a-input v-model:value="formState.cardNumber" />
        </a-form-item>

        <a-form-item
          label="Ngân hàng"
          name="bank"
          :rules="[{ required: true, message: 'Vui lòng nhập ngân hàng !' }]"
        >
          <a-input v-model:value="formState.bank" />
        </a-form-item>

        <a-form-item
          label="Chi nhánh"
          name="address"
          :rules="[
            {
              required: true,
              message: 'Vui lòng nhập chi nhánh!',
            },
          ]"
        >
          <a-input v-model:value="formState.address" />
        </a-form-item>

        <a-form-item v-if="action" :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" html-type="submit" :loading="isLoading">{{ title }}</a-button>
        </a-form-item>
      </a-form>
    </div>
    <template #footer>
      <a-button type="danger" @click="handleClose">Đóng</a-button>
    </template>
  </a-modal>
</template>
