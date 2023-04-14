<script setup>
import {computed, reactive, ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";
import {province, district, ward} from "@/services/region";
import {detailOrder} from "@/services/order";
import {listActive} from "@/services/coupon";
import {
  dataSample,
  handleResetData,
  handleSetData
} from "./configOrder";
import {message, notification} from "ant-design-vue";
import {isEmpty} from "lodash";

// ========================================================================
import SenderInfo from "@/components/order/component/SenderInfo";
import ShippingType from "@/components/order/component/ShippingType";
import ShippingInfo from "@/components/order/component/ShippingInfo";
import AdminProductSelector from "@/pages/admin/order/AdminProductSelector";
import OtherInfo from "@/components/order/component/OtherInfo";



const route = useRoute();
const router = useRouter();

const data = reactive({
  ...dataSample,
});
const form = ref(null);
const listOrder = ref([{}]);
const provinces = ref([]);
const districts = ref([]);
const wards = ref([]);
const coupons = ref([]);
const hasProductError = ref(false);
const actionShowReceiver = ref(false);
const getTitle = computed(() => {
  const title = {
    update: "Cập nhật đơn hàng",
    create: "Tạo mới đơn hàng",
  };
  return title[route.params?.action] || "đơn hàng";
});

//
const handleGetOrder = async () => {
  try {
    const {data: results} = await detailOrder({id: route.params?.id});
    Object.assign(data, results);
    data.shippingAddressName = results.shippingAddress.name;
    data.shippingAddressPhone = results.shippingAddress.phone;
    data.shippingAddressProvince = results.shippingAddress.province;
    data.shippingAddressProvinceId = results.shippingAddress.provinceId;
    data.shippingAddressDistrict = results.shippingAddress.district;
    data.shippingAddressDistrictId = results.shippingAddress.districtId;
    data.shippingAddressWard = results.shippingAddress.ward;
    data.shippingAddressWardId = results.shippingAddress.wardId;
    data.shippingAddressStreet = results.shippingAddress.street;
    handleGetDistrict(data.shippingAddressDistrictId);
    handleGetWard(data.shippingAddressDistrictId);
  } catch (e) {
    if (e.response.data.status == 500) {
      message.error("Đơn hàng không tồn tại");
      router.push("/admin/order/update");
    }
  }
};
// Hanlde Click
const handleSubmitForm = async () => {

};
const handleSetCurrentData = () => {
  listOrder.value[activeKey.value] = listOrder.value[activeKey.value] || {};
  if (isEmpty(listOrder.value[activeKey.value])) {
    handleResetData(data);
  } else {
    handleSetData(data, listOrder.value[activeKey.value]);
  }
  message.success("Cập nhật đơn hàng thành công");
};
const onFinish = (values) => {
  handleErrorProduct(data.products);
  if (hasProductError.value) {
    return message.error("Vui lòng kiểm tra lại thông tin sản phẩm");
  } else {
    actionShowReceiver.value = false;
    listOrder.value[activeKey.value] = {...data, ...values};
    activeKey.value = activeKey.value + 1;
    handleSetCurrentData();
  }
};
const handleErrorProduct = (value) => {
  hasProductError.value = false;
  value.forEach((product) => {
    for (const property in product) {
      if (!product[property] && property != "specialType") {
        hasProductError.value = true;
        return;
      }
    }
  });
};
const onFinishFailed = (errorInfo) => {
  console.log("Failed:", errorInfo);
};

// Handle Coupon
const handleGetCoupon = async () => {
  const {data} = await listActive();
  coupons.value = data;
};

// Handle Address
const handleGetProvince = async () => {
  const {data} = await province();
  provinces.value = data;
};
const handleGetDistrict = async (provinceId) => {
  const {data} = await district(provinceId);
  districts.value = data;
};
const handleGetWard = async (districtId) => {
  const {data} = await ward(districtId);
  wards.value = data;
};
watch(
  () => data.shippingAddressProvinceId,
  () => {
    if (!data.shippingAddressProvinceId) {
      data.shippingAddressProvince = "";
      districts.value = [];
      return;
    }
    const province = provinces.value.find(
      (x) => x.province_id == data.shippingAddressProvinceId
    );
    if (province) {
      data.shippingAddressProvince = province.province_name;
      handleGetDistrict(data.shippingAddressProvinceId);
    }
    // if (!actionShowReceiver.value) {
    //   data.shippingAddressDistrictId = "";
    //   data.shippingAddressWardId = "";
    //   data.shippingAddressStreet = "";
    // }
  }
);
watch(
  () => data.shippingAddressDistrictId,
  () => {
    if (!data.shippingAddressDistrictId) {
      data.shippingAddressDistrict = "";
      wards.value = [];
      return;
    }
    const district = districts.value.find(
      (x) => x.district_id == data.shippingAddressDistrictId
    );
    if (district) {
      data.shippingAddressDistrict = district.district_name;
    }
    handleGetWard(data.shippingAddressDistrictId);
    // if (!actionShowReceiver.value) {
    //   data.shippingAddressWardId = "";
    //   data.shippingAddressStreet = "";
    // }
  }
);
watch(
  () => data.shippingAddressWardId,
  () => {
    if (!data.shippingAddressWardId) {
      data.shippingAddressWard = "";
      wards.value = [];
      return;
    }
    const ward = wards.value.find(
      (x) => x.ward_id == data.shippingAddressWardId
    );
    if (ward) {
      data.shippingAddressWard = ward?.ward_name;
    }
    // if (!actionShowReceiver.value) {
    //   data.shippingAddressStreet = "";
    //   actionShowReceiver.value = false;
    // }
  }
);
// Handle Action
handleGetOrder();
watch(
  () => route.fullPath,
  () => handleGetOrder()
);
handleGetCoupon();
handleGetProvince();
//

// =======================================================================

const activeKey = ref('default');

const formData = ref({
  senderInfo: {
    name: '',
    address: '',
    phone: ''
  },
  shippingInfo: {
    name: '',
    phone: '',
    province: '',
    provinceId: null,
    district: '',
    districtId: null,
    ward: '',
    wardId: null,
    street: ''
  },
  shippingType: {
    type: '',
    warehouseId: '',
    officeId: '',
    time: '',
    objPay: true
  },
  otherInfo: {
    note: '',
    coupon: '',
    returnCode: ''
  },
  productSelector: {
    id: null,
    name: '',
    qty: '',
    weight: '',
    price: '',
    long: null,
    width: null,
    height: null,
    specialType: false
  },
  productList: {}
});

const handleSenderChange = (value) => {
  formData.value.senderInfo = value;
}

const handleShippingTypeChange = (value) => {
  formData.value.shippingType = value;
}

const handleShippingInfoChange = (value) => {
  formData.value.shippingInfo = value;
}

const handleOtherInfoChange = (value) => {
  formData.value.otherInfo = value;
}

const handleValidateProduct = (event) => {
  productFormValidate().then(() => {
    event();
  }).catch(() => {
    notification['error']({
      message: "Có Lỗi Sảy Ra!",
      description: "Thông tin sản phẩm không hợp lệ!"
    });
  });
}

const handleProductSelectorChange = (value) => {
  formData.value.productSelector = value;
}

const handleProductChange = (value) => {
  formData.value.productList = value;
}

const productFormValidate = async () => {
  await form.value.validateFields([
    ['productSelector', 'name'],
    ['productSelector', 'qty'],
    ['productSelector', 'weight'],
    ['productSelector', 'price']
  ]);
}

</script>
<template>
  <div class="">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">
      {{ getTitle }}
    </div>
    <div class="px-4">
      <a-tabs v-model:activeKey="activeKey" hide-add type="editable-card" >
<!--              @tabClick="handleChangeTab" @edit="handleEditTab">-->
<!--        <a-tab-pane v-for="(order, index) in dataOrders" :key="index"-->
<!--                    :tab="`Đơn hàng - (${index + 1})`" :closable="true"/>-->
        <a-tab-pane :tab="'Thông tin đơn hàng'" :key="'default'" :closable="false"/>
      </a-tabs>
      <a-form :model="data" layout="vertical" name="basic" autocomplete="off" @finish="onFinish" ref="form" @finishFailed="onFinishFailed">
        <a-form-item>
          <div class="d-flex justify-content-between">
            <button class="btn btn-primary" @click.prevent="handleSubmitForm">
              Lưu đơn hàng
            </button>
          </div>
        </a-form-item>
        <a-row type="flex" class="my-4" :gutter="[10, 10]" align="top">
          <a-col :span="8" :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 8 }">
            <SenderInfo :senderInfo="formData.senderInfo"
                        @sender-info-change="handleSenderChange"
            />
            <ShippingType :shippingType="formData.shippingType"
                          :isCustomer="false"
                          @shipping-type-change="handleShippingTypeChange"
            />
          </a-col>
          <a-col :span="8" :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 8 }">
            <ShippingInfo :shippingInfo="formData.shippingInfo"
                          @shipping-info-change="handleShippingInfoChange"
            />
<!--            <a-button-->
<!--              type="danger"-->
<!--              class="mt-3 me-3"-->
<!--              v-if="onlyCreate && actionShowReceiver"-->
<!--              @click="handleUpdateReceiver"-->
<!--            >Chỉnh sửa người nhận-->
<!--            </a-button>-->
<!--            <a-button-->
<!--              type="danger"-->
<!--              class="mt-3"-->
<!--              v-if="onlyCreate"-->
<!--              @click="handleAddReceiver"-->
<!--            >Thêm người nhận-->
<!--            </a-button-->
<!--            >-->
<!--            <div v-if="onlyCreate">-->
<!--              <span v-for="(receiver, index) in listReceiver" :key="index">-->
<!--                <a-button-->
<!--                  type="dashed"-->
<!--                  danger-->
<!--                  v-if="receiver.inOrder == activeKey"-->
<!--                  class="my-2 me-2"-->
<!--                  @click="handleShowReceiver(receiver)"-->
<!--                >{{ receiver.shippingAddressName }}</a-button-->
<!--                >-->
<!--              </span>-->
<!--            </div>-->
          </a-col>
          <a-col :span="8" :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 8 }">
            <AdminProductSelector :productList="formData.productList"
                                  :productSelector="formData.productSelector"
                                  @validate-product="handleValidateProduct"
                                  @change-product="handleProductChange"
                                  @product-selector-change="handleProductSelectorChange" />

            <OtherInfo :otherInfo="formData.otherInfo"
                       @other-info-change="handleOtherInfoChange"
            />
          </a-col>
        </a-row>
      </a-form>
    </div>
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
