<script setup>
import { computed, reactive, ref, watch } from "vue";
import { useRoute } from "vue-router";
import { UserOutlined, PhoneOutlined, AimOutlined, AccountBookOutlined, CheckCircleOutlined } from "@ant-design/icons-vue";
import common from "@/utils/common";
import { province, district, ward } from "@/services/outService";
import { listActive } from "@/services/coupon";
import { dataSample, handleResetData, handleSetData } from "./configOrder";
import ProductOrder from "../components/ProductOrder.vue";
import { message } from "ant-design-vue";

const route = useRoute();

const data = reactive({
  ...dataSample
});

const form = ref(null);
const listOrder = ref([{}]);
const activeKey = ref(0);
const placeTake = ref('default');
const provinces = ref([]);
const districts = ref([]);
const wards = ref([]);
const coupons = ref([]);
const showCoupon = ref(false);
const startCheck = ref(false);
const endCheck = ref(false);
const hasProductError = ref(false);
const getTitle = computed(() => {
  const title = {
    update: 'Cập nhật đơn hàng',
    create: 'Tạo mới đơn hàng',
  }
  return title[route.params?.action] || 'đơn hàng';
});

//
const onFinish = (values) => {
  startCheck.value = true;
  endCheck.value = false;
  setTimeout(() => {
    if (hasProductError.value) {
      return message.error('Vui lòng kiểm tra lại thông tin sản phẩm');
    } else {
      listOrder.value[activeKey.value] = values;
      activeKey.value = activeKey.value + 1;
      listOrder.value[activeKey.value] = listOrder.value[activeKey.value] || {};
      if (!Object.keys(listOrder.value[activeKey.value]).length) {
        handleResetData(data);
      }
      if (activeKey.value == listOrder.value.length - 1) {
        message.success('Thêm đơn hàng mới thành công');
      } else {
        message.success('Cập nhật lại đơn hàng thành công');
      }
      endCheck.value = true;
    }
  }, 100);
};
const handleErrorProduct = (value) => {
  hasProductError.value = value;
  startCheck.value = false;
};
const handleThrowProduct = (value) => {
  data.products = value;
}
const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};
const handleGetProvince = async () => {
  const { data } = await province();
  provinces.value = data.results;
}
const handleGetDistrict = async (provinceId) => {
  const { data } = await district(provinceId);
  districts.value = data.results;
}
const handleGetWard = async (districtId) => {
  const { data } = await ward(districtId);
  wards.value = data.results;
}
const handleGetCoupon = async () => {
  const { data } = await listActive();
  coupons.value = data;
}
//
const handleSetCoupon = (code) => {
  showCoupon.value = false;
  data.coupon = code;
}
const handleTabClick = (key) => {
  if (activeKey.value == key) {
    return;
  }
  handleSetData(data, listOrder.value[key]);
  form.value.clearValidate();
  if (key == listOrder.value.length - 1) {
    handleResetData(data);
  }
}

//
handleGetCoupon();
handleGetProvince();
watch(() => data.shippingAddressProvinceId, () => {
  if (!data.shippingAddressProvinceId) {
    data.shippingAddressProvince = '';
    districts.value = [];
    return;
  }
  const province = provinces.value.find(x => x.province_id == data.shippingAddressProvinceId);
  if (province) {
    data.shippingAddressProvince = province.province_name;
    handleGetDistrict(data.shippingAddressProvinceId);
  }
});
watch(() => data.shippingAddressDistrictId, () => {
  if (!data.shippingAddressDistrictId) {
    data.shippingAddressDistrict = '';
    wards.value = [];
    return;
  }
  const district = districts.value.find(x => x.district_id == data.shippingAddressDistrictId);
  if (district) {
    data.shippingAddressDistrict = district.district_name;
    handleGetWard(data.shippingAddressDistrictId);
  }
})
watch(() => data.shippingAddressWardId, () => {
  if (!data.shippingAddressWardId) {
    data.shippingAddressWard = '';
    wards.value = [];
    return;
  }
  const ward = wards.value.find(x => x.ward_id == data.shippingAddressWardId);
  if (ward) {
    data.shippingAddressWard = ward?.ward_name;
  }
})

</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">{{ getTitle }}</div>
    <a-tabs v-model:activeKey="activeKey" @tabClick="handleTabClick">
      <a-tab-pane v-for="(order, index) in listOrder" :key="index"
        :tab="order.shippingAddressName || 'Đơn hàng ' + index" />
    </a-tabs>
    <a-form :model="data" layout="vertical" name="basic" autocomplete="off" @finish="onFinish" ref="form"
      @finishFailed="onFinishFailed">
      <a-form-item :wrapper-col="{ offset: 16, span: 16 }">
        <a-button html-type="submit" :type="activeKey == listOrder.length - 1 ? 'primary' : ''" ref="submitList">{{
            activeKey == listOrder.length - 1 ? 'Thêm đơn hàng mới' : 'Cập nhật lại đơn hàng'
        }}</a-button>
      </a-form-item>
      <a-row type="flex" class="my-4" :gutter="[10, 10]" align="top">
        <a-col :span="8">
          <div class="border">
            <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin người gửi</div>
            <div class="p-3">
              <a-form-item label="Thông tin người gửi" name="senderName"
                :rules="[{ required: true, message: 'Vui lòng nhập thông tin người gửi!' }]">
                <a-input v-model:value="data.senderName" placeholder="Thông tin người gửi">
                  <template #prefix>
                    <UserOutlined class="me-2" />
                  </template>
                </a-input>
              </a-form-item>
              <a-form-item label="Số điện thoại người gửi" name="senderPhone"
                :rules="[{ required: true, message: 'Vui lòng nhập số điện thoại người gửi!' }]">
                <a-input v-model:value="data.senderPhone" placeholder="Số điện thoại người gửi">
                  <template #prefix>
                    <PhoneOutlined class="me-2" />
                  </template>
                </a-input>
              </a-form-item>
              <a-form-item label="Địa chỉ người gửi" name="senderAddress"
                :rules="[{ required: true, message: 'Vui lòng nhập địa chỉ người gửi!' }]">
                <a-input v-model:value="data.senderAddress" placeholder="Địa chỉ người gửi">
                  <template #prefix>
                    <AimOutlined class="me-2" />
                  </template>
                </a-input>
              </a-form-item>
            </div>
          </div>

          <div class="border mt-3">
            <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin người nhận</div>
            <div class="p-3">
              <a-form-item label="Tên người nhận" name="shippingAddressName"
                :rules="[{ required: true, message: 'Vui lòng nhập tên người nhận!' }]">
                <a-input v-model:value="data.shippingAddressName" placeholder="Tên người nhận">
                  <template #prefix>
                    <UserOutlined class="me-2" />
                  </template>
                </a-input>
              </a-form-item>
              <a-form-item label="Số điện thoại người nhận" name="shippingAddressPhone"
                :rules="[{ required: true, message: 'Vui lòng nhập số điện thoại người nhận!' }]">
                <a-input v-model:value="data.shippingAddressPhone" placeholder="Số điện thoại người nhận">
                  <template #prefix>
                    <PhoneOutlined class="me-2" />
                  </template>
                </a-input>
              </a-form-item>
              <a-form-item label="Tỉnh / thành phố" name="shippingAddressProvinceId"
                :rules="[{ required: true, message: 'Vui lòng nhập tỉnh / thành phố người nhận!' }]">
                <a-select v-model:value="data.shippingAddressProvinceId" style="width: 100%">
                  <a-select-option value="">- Tỉnh / thành phố -</a-select-option>
                  <a-select-option v-for="(province, index) in provinces" :value="province.province_id" :key="index">
                    {{
                        province.province_name
                    }}</a-select-option>
                </a-select>
              </a-form-item>
              <a-form-item label="Quận / huyện " name="shippingAddressDistrictId"
                :rules="[{ required: true, message: 'Vui lòng nhập quận / huyện người nhận!' }]">
                <a-select v-model:value="data.shippingAddressDistrictId" style="width: 100%">
                  <a-select-option value="">- Quận / huyện -</a-select-option>
                  <a-select-option v-for="(district, index) in districts" :value="district.district_id" :key="index">
                    {{
                        district.district_name
                    }}</a-select-option>
                </a-select>
              </a-form-item>
              <a-form-item label="Xã / phường" name="shippingAddressWardId"
                :rules="[{ required: true, message: 'Vui lòng nhập xã / phường người nhận!' }]">
                <a-select v-model:value="data.shippingAddressWardId" style="width: 100%">
                  <a-select-option value="">- Xã / phường -</a-select-option>
                  <a-select-option v-for="(ward, index) in wards" :value="ward.ward_id" :key="index">
                    {{
                        ward.ward_name
                    }}</a-select-option>
                </a-select>
              </a-form-item>
              <a-form-item label="Địa chỉ cụ thể" name="shippingAddressStreet"
                :rules="[{ required: true, message: 'Vui lòng nhập Địa chỉ cụ thể người nhận!' }]">
                <a-input v-model:value="data.shippingAddressStreet"
                  placeholder="Địa chỉ cụ thể: Số nhà, Tên đường..." />
              </a-form-item>
            </div>
          </div>
        </a-col>
        <a-col :span="8">
          <div class="border">
            <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Phương thức giao & lấy hàng</div>
            <div class="p-3">
              <a-form-item label="Phương thức giao hàng" name="shippingType"
                :rules="[{ required: true, message: 'Vui lòng nhập phương thức giao hàng!' }]">
                <a-select v-model:value="data.shippingType" style="width: 100%">
                  <a-select-option value="">- Phương thức giao hàng -</a-select-option>
                  <a-select-option v-for="(type, index) in common.TYPE_SHIPPING" :value="type.value" :key="index">{{
                      type.name
                  }}</a-select-option>
                </a-select>
              </a-form-item>
              <a-radio-group v-model:value="placeTake">
                <a-radio class="d-block mb-2" :value="'default'">Gửi hàng tại bưu cục</a-radio>
                <a-radio style="d-block my-2" :value="'option'">Lấy hàng tận nơi</a-radio>
              </a-radio-group>
              <div v-if="placeTake == 'option'" class="my-2">
                <a-form-item label="Địa điểm lấy hàng" name="warehouse"
                  :rules="[{ required: true, message: 'Vui lòng nhập địa điểm lấy hàng!' }]">
                  <a-select v-model:value="data.warehouse" style="width: 100%">
                    <a-select-option class="my-2" value="">- Địa điểm lấy hàng -</a-select-option>
                  </a-select>
                </a-form-item>
                <a-form-item label="Thời gian lấy hàng" name="shippingTime"
                  :rules="[{ required: true, message: 'Vui lòng nhập thời gian lấy hàng!' }]">
                  <a-date-picker v-model:value="data.shippingTime" class="my-2" style="width: 100%" show-time
                    placeholder="Thời gian lấy hàng" />
                </a-form-item>
              </div>
              <div class="border-top mt-2 pt-2">
                <a-form-item label="Trả phí ship" name="shipPayer"
                  :rules="[{ required: true, message: 'Vui lòng nhập trả phí ship!' }]">
                  <a-radio-group v-model:value="data.shipPayer">
                    <a-radio class="d-block mb-2" :value="true">Shop trả phí ship</a-radio>
                    <a-radio style="d-block my-2" :value="false">Người nhận trả phí ship</a-radio>
                  </a-radio-group>
                </a-form-item>
              </div>
            </div>
          </div>

          <div class="border mt-3">
            <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin thêm</div>
            <div class="p-3">
              <a-textarea v-model:value="data.note" class="my-2" style="resize: none;" placeholder="Ghi chú đơn hàng"
                :rows="6" />
              <div class="d-flex justify-content-between my-2">
                <a-input v-model:value="data.coupon" class="me-2" placeholder="Mã giảm giá">
                  <template #prefix>
                    <AccountBookOutlined class="me-2" />
                  </template>
                </a-input>
                <a-button type="primary">Áp dụng</a-button>
              </div>
              <a-popover title="Danh sách mã giảm giá" trigger="click" v-model:visible="showCoupon">
                <template #content>
                  <div class="content">
                    <div class="d-flex p-2 border-top justify-content-between align-items-center"
                      v-for="(coupon, index) in coupons" :key="index">
                      <div>
                        <div>
                          <b>{{ coupon.code }}</b>
                        </div>
                        <small>Giảm giá {{ coupon.value }} {{ coupon.rule == 'base' ? "VND" : "%" }}</small>
                      </div>
                      <CheckCircleOutlined style="color: green; cursor: pointer;"
                        @click="handleSetCoupon(coupon.code)" />
                    </div>
                  </div>
                </template>
                <a-button>Danh sách mã giảm giá</a-button>
              </a-popover>
              <a-form-item label="Trường hợp hoàn hàng" name="returnCode" class="my-2"
                :rules="[{ required: true, message: 'Vui lòng nhập trường hợp hoàn hàng!' }]">
                <a-select v-model:value="data.returnCode" style="width: 100%">
                  <a-select-option value="">- Trường hợp hoàn hàng -</a-select-option>
                  <a-select-option v-for="(type, index) in common.RETURN_TYPE" :value="type.value" :key="index">{{
                      type.name
                  }}</a-select-option>
                </a-select>
              </a-form-item>
            </div>
          </div>
        </a-col>
        <a-col :span="8">
          <ProductOrder :startValidate="startCheck" :endValidate="endCheck" @on-error-product="handleErrorProduct"
            @on-throw-product="handleThrowProduct" />
        </a-col>
      </a-row>
    </a-form>
  </div>
</template>
<style scoped>
.content {
  height: 250px;
  width: 200px;
  overflow-y: scroll;
  overflow-x: hidden;
}
</style>