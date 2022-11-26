<script setup>
import { defineProps, defineEmits, computed, watch, reactive, ref } from "vue";
import { province, district, ward } from "@/services/region";
import { save } from "@/services/office";
import { useStore } from "vuex";
import { message } from "ant-design-vue";
import { pick } from "lodash"

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
  if (props.detail.action == "update") {
    data.id = formState.id;
  }
  Object.assign(data, {
    ...data,
    ...values,
    ...pick(formState, ['province', 'district', 'ward']),
  });

  store.dispatch("setLoading", true);
  try {
    await save(data);
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
          label="Tên bưu cục"
          name="name"
          :rules="[{ required: true, message: 'Vui lòng nhập tên bưu cục !' }]"
        >
          <a-input v-model:value="formState.name" />
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

        <a-form-item v-if="action" :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" html-type="submit" :loading="isLoading">{{
            title
          }}</a-button>
        </a-form-item>
      </a-form>
    </div>
    <template #footer>
      <a-button type="danger" @click="handleClose">Đóng</a-button>
    </template>
  </a-modal>
</template>
