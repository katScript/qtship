<script setup>
import { computed, reactive, ref, watch } from "vue";
import { useRoute, useRouter } from "vue-router";
import {
  UserOutlined,
  PhoneOutlined,
  AimOutlined,
  AccountBookOutlined,
  CheckCircleOutlined,
} from "@ant-design/icons-vue";
import common from "@/utils/common";
import { province, district, ward } from "@/services/region";
import { saveOrder, detailOrder } from "@/services/order";
import { listActive } from "@/services/coupon";
import {
  dataSample,
  handleResetData,
  handleSetData,
  requiredData,
  omitKey,
} from "./configOrder";
import ProductOrder from "../components/ProductOrder.vue";
import { message } from "ant-design-vue";
import { isEmpty, omit } from "lodash";

const route = useRoute();
const router = useRouter();

const data = reactive({
  ...dataSample,
});
const form = ref(null);
const listReceiver = ref([]);
const submitList = ref(null);
const listOrder = ref([{}]);
const activeKey = ref(0);
const placeTake = ref("default");
const provinces = ref([]);
const districts = ref([]);
const orderCode = ref("");
const wards = ref([]);
const coupons = ref([]);
const showCoupon = ref(false);
const endCheck = ref(false);
const hasProductError = ref(false);
const actionShowReceiver = ref(false);
const getTitle = computed(() => {
  const title = {
    update: "Cập nhật đơn hàng",
    create: "Tạo mới đơn hàng",
  };
  return title[route.params?.action] || "đơn hàng";
});

const onlyCreate = computed(() => route.params?.action == "create");
const onlyUpdate = computed(
  () => route.params?.action == "update" && route.params?.id
);

const handleUpdateReceiver = async () => {
  const res = await form.value.validate();

  if (res) {
    const index = listReceiver.value.findIndex(
      (receiver) => receiver.id == actionShowReceiver.value
    );
    listReceiver.value[index] = {
      id: actionShowReceiver.value,
      inOrder: activeKey.value,
      shippingAddressName: data.shippingAddressName,
      shippingAddressPhone: data.shippingAddressPhone,
      shippingAddressProvince: data.shippingAddressProvince,
      shippingAddressProvinceId: data.shippingAddressProvinceId,
      shippingAddressDistrict: data.shippingAddressDistrict,
      shippingAddressDistrictId: data.shippingAddressDistrictId,
      shippingAddressWard: data.shippingAddressWard,
      shippingAddressWardId: data.shippingAddressWardId,
      shippingAddressStreet: data.shippingAddressStreet,
    };
    handleResetReceiver();
  }
};
//
const handleResetReceiver = () => {
  data.shippingAddressName = "";
  data.shippingAddressPhone = "";
  data.shippingAddressProvince = "";
  data.shippingAddressProvinceId = "";
  data.shippingAddressDistrict = "";
  data.shippingAddressDistrictId = "";
  data.shippingAddressWard = "";
  data.shippingAddressWardId = "";
  data.shippingAddressStreet = "";
  actionShowReceiver.value = false;
};
const handleAddReceiver = async () => {
  const res = await form.value.validate();

  if (res) {
    listReceiver.value.push({
      id: Math.random() + 1,
      inOrder: activeKey.value,
      shippingAddressName: data.shippingAddressName,
      shippingAddressPhone: data.shippingAddressPhone,
      shippingAddressProvince: data.shippingAddressProvince,
      shippingAddressProvinceId: data.shippingAddressProvinceId,
      shippingAddressDistrict: data.shippingAddressDistrict,
      shippingAddressDistrictId: data.shippingAddressDistrictId,
      shippingAddressWard: data.shippingAddressWard,
      shippingAddressWardId: data.shippingAddressWardId,
      shippingAddressStreet: data.shippingAddressStreet,
    });
    handleResetReceiver();
  }
};
const handleShowReceiver = (receiver) => {
  actionShowReceiver.value = receiver.id;
  data.shippingAddressName = receiver.shippingAddressName;
  data.shippingAddressPhone = receiver.shippingAddressPhone;
  data.shippingAddressProvince = receiver.shippingAddressProvince;
  data.shippingAddressProvinceId = receiver.shippingAddressProvinceId;
  data.shippingAddressDistrict = receiver.shippingAddressDistrict;
  data.shippingAddressDistrictId = receiver.shippingAddressDistrictId;
  data.shippingAddressWard = receiver.shippingAddressWard;
  data.shippingAddressWardId = receiver.shippingAddressWardId;
  data.shippingAddressStreet = receiver.shippingAddressStreet;
  handleGetDistrict(data.shippingAddressDistrictId);
  handleGetWard(data.shippingAddressDistrictId);
};
//
const handleGetOrder = async () => {
  if (!onlyUpdate.value) {
    return;
  }
  try {
    const { data: results } = await detailOrder({ id: route.params?.id });
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
const handlePreSaveOrder = () => {
  if (!listReceiver.value.length) {
    return;
  }
  const currentOrderReceivers = listReceiver.value.filter(
    (receiver) => receiver.inOrder == activeKey.value
  );
  if (currentOrderReceivers.length) {
    handleShowReceiver(currentOrderReceivers[currentOrderReceivers.length - 1]);
  }
};
// Hanlde Click
const handleSubmitForm = async () => {
  let indexError = null;
  const results = [];
  handlePreSaveOrder();
  const res = await form.value.validate();
  const requiredDataCP = requiredWard.value
    ? [...requiredData]
    : [...requiredData].filter((x) => x != "shippingAddressWardId");
  if (res) {
    if (onlyCreate.value) {
      if (listOrder.value.length - 1 == activeKey.value) {
        listOrder.value[activeKey.value] = data;
      }
      listOrder.value.forEach((order, index) => {
        order.products.forEach((product) => {
          for (const property in product) {
            if (!product[property] && property != "specialType") {
              indexError = index + " phần thông tin sản phẩm";
              break;
            }
          }
        });
        for (const property in order) {
          if (
            !order[property] &&
            requiredDataCP.includes(property) &&
            property != "shipPayer"
          ) {
            indexError = index;
            break;
          }
        }
      });

      listOrder.value.forEach((order, index) => {
        if (!order.warehouse) {
          order.warehouse = {
            id: null,
          };
        }
        if (listReceiver.value.length) {
          const currentOrderReceivers = listReceiver.value.filter(
            (receiver) => receiver.inOrder == index
          );
          if (currentOrderReceivers.length) {
            currentOrderReceivers.forEach((receiver) => {
              if (receiver.inOrder == index) {
                const data = {
                  ...omit(order, omitKey),
                  shippingAddress: {
                    name: receiver.shippingAddressName,
                    phone: receiver.shippingAddressPhone,
                    province: receiver.shippingAddressProvince,
                    provinceId: receiver.shippingAddressProvinceId,
                    district: receiver.shippingAddressDistrict,
                    districtId: receiver.shippingAddressDistrictId,
                    ward: receiver.shippingAddressWard,
                    wardId: receiver.shippingAddressWardId,
                    street: receiver.shippingAddressStreet,
                  },
                };
                results.push(data);
              }
            });
          } else {
            order.shippingAddress = {
              name: order.shippingAddressName,
              phone: order.shippingAddressPhone,
              province: order.shippingAddressProvince,
              provinceId: order.shippingAddressProvinceId,
              district: order.shippingAddressDistrict,
              districtId: order.shippingAddressDistrictId,
              ward: order.shippingAddressWard,
              wardId: order.shippingAddressWardId,
              street: order.shippingAddressStreet,
            };
            results.push(omit(order, omitKey));
          }
        }
      });
    }

    if (onlyUpdate.value) {
      data.products.forEach((product) => {
        for (const property in product) {
          if (!product[property] && property != "specialType") {
            indexError = 0 + " phần thông tin sản phẩm";
            break;
          }
        }
      });
      for (const property in data) {
        if (
          !data[property] &&
          requiredDataCP.includes(property) &&
          property != "shipPayer"
        ) {
          indexError = 0;
          break;
        }
      }
      data.shippingAddress = {
        name: data.shippingAddressName,
        phone: data.shippingAddressPhone,
        province: data.shippingAddressProvince,
        provinceId: data.shippingAddressProvinceId,
        district: data.shippingAddressDistrict,
        districtId: data.shippingAddressDistrictId,
        ward: data.shippingAddressWard,
        wardId: data.shippingAddressWardId,
        street: data.shippingAddressStreet,
      };

      results[0] = omit(data, omitKey);
    }
    if (indexError != null) {
      return message.error(
        "Đơn hàng thứ " + indexError + " chưa được hoàn thiện"
      );
    }
    if (results.length) {
      return console.log(results);
    }
    await saveOrder(results);
    message.success("Lưu đơn hàng thành công");
    return router.push("/admin/order");
  }
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
    listOrder.value[activeKey.value] = { ...data, ...values };
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
// Handle Tab
const handleTabClick = (key) => {
  if (activeKey.value == key) {
    return;
  }
  form.value.clearValidate();
  handlePreSaveOrder();
  listOrder.value[activeKey.value] = { ...data };
  handleSetData(data, listOrder.value[key]);
  activeKey.value = key;
};
const remove = (targetKey) => {
  listOrder.value = listOrder.value.filter((x, index) => index != targetKey);
};
const onEdit = (targetKey, action) => {
  if (targetKey == 0) {
    activeKey.value = targetKey + 1;
  } else {
    activeKey.value = targetKey - 1;
  }
  handleSetCurrentData();
  if (action === "add") {
    return;
  } else {
    remove(targetKey);
  }
};
// Handle Emit
const handleThrowProduct = (value) => {
  data.products = value;
};

// Handle Coupon
const handleGetCoupon = async () => {
  const { data } = await listActive();
  coupons.value = data;
};
const handleSetCoupon = (code) => {
  showCoupon.value = false;
  data.coupon = code;
};
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
const requiredWard = computed(
  () =>
    data.shippingAddressProvinceId &&
    data.shippingAddressDistrictId &&
    wards.value.length
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
</script>
<template>
  <div class="">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">
      {{ getTitle }}
    </div>
    <div class="d-flex p-5" v-if="!(onlyUpdate || onlyCreate)">
      <a-input-number
        style="width: 200px"
        v-model:value="orderCode"
        addon-before="ORD"
        placeholder="Mã đơn hàng"
      />
      <a-button
        type="primary"
        class="ms-2"
        @click="router.push('/admin/order/update/' + orderCode)"
        >Cập nhật</a-button
      >
    </div>
    <div class="px-4" v-else>
      <a-tabs
        v-model:activeKey="activeKey"
        hide-add
        @tabClick="handleTabClick"
        @edit="onEdit"
        type="editable-card"
      >
        <a-tab-pane
          v-for="(order, index) in listOrder"
          :key="index"
          :tab="order.shippingAddressName || 'Đơn hàng ' + index"
          :closable="index != 0"
        />
      </a-tabs>
      <a-form
        :model="data"
        layout="vertical"
        name="basic"
        autocomplete="off"
        @finish="onFinish"
        ref="form"
        @finishFailed="onFinishFailed"
      >
        <a-form-item>
          <div class="d-flex justify-content-between">
            <button
              type="submit"
              v-if="onlyCreate"
              @click="() => handlePreSaveOrder()"
              :class="
                activeKey == listOrder.length - 1
                  ? 'btn btn-outline-info'
                  : 'btn btn-info text-white'
              "
              ref="submitList"
            >
              {{
                activeKey == listOrder.length - 1
                  ? "Thêm đơn hàng mới"
                  : "Cập nhật lại đơn hàng"
              }}
            </button>
            <div v-else></div>
            <button class="btn btn-primary" @click.prevent="handleSubmitForm">
              Lưu đơn hàng
            </button>
          </div>
        </a-form-item>
        <a-row type="flex" class="my-4" :gutter="[10, 10]" align="top">
          <a-col
            :span="8"
            :xs="{ span: 24 }"
            :md="{ span: 12 }"
            :lg="{ span: 8 }"
          >
            <div class="border">
              <div
                style="background: #bf1e2d"
                class="fs-4 border-bottom px-3 py-1 text-white"
              >
                Thông tin người gửi
              </div>
              <div class="p-3">
                <a-form-item
                  label="Thông tin người gửi"
                  name="senderName"
                  :rules="[
                    {
                      required: true,
                      message: 'Vui lòng nhập thông tin người gửi!',
                    },
                  ]"
                >
                  <a-input
                    v-model:value="data.senderName"
                    placeholder="Thông tin người gửi"
                  >
                    <template #prefix>
                      <UserOutlined class="me-2" />
                    </template>
                  </a-input>
                </a-form-item>
                <a-form-item
                  label="Số điện thoại người gửi"
                  name="senderPhone"
                  :rules="[
                    {
                      required: true,
                      message: 'Vui lòng nhập số điện thoại người gửi!',
                    },
                  ]"
                >
                  <a-input
                    v-model:value="data.senderPhone"
                    placeholder="Số điện thoại người gửi"
                  >
                    <template #prefix>
                      <PhoneOutlined class="me-2" />
                    </template>
                  </a-input>
                </a-form-item>
                <a-form-item
                  label="Địa chỉ người gửi"
                  name="senderAddress"
                  :rules="[
                    {
                      required: true,
                      message: 'Vui lòng nhập địa chỉ người gửi!',
                    },
                  ]"
                >
                  <a-input
                    v-model:value="data.senderAddress"
                    placeholder="Địa chỉ người gửi"
                  >
                    <template #prefix>
                      <AimOutlined class="me-2" />
                    </template>
                  </a-input>
                </a-form-item>
              </div>
            </div>
            <div class="border mt-3">
              <div
                style="background: #bf1e2d"
                class="fs-4 border-bottom px-3 py-1 text-white"
              >
                Phương thức giao & lấy hàng
              </div>
              <div class="p-3">
                <a-form-item
                  label="Phương thức giao hàng"
                  name="shippingType"
                  :rules="[
                    {
                      required: true,
                      message: 'Vui lòng nhập phương thức giao hàng!',
                    },
                  ]"
                >
                  <a-select
                    v-model:value="data.shippingType"
                    style="width: 100%"
                  >
                    <a-select-option value=""
                      >- Phương thức giao hàng -</a-select-option
                    >
                    <a-select-option
                      v-for="(type, index) in common.TYPE_SHIPPING"
                      :value="type.value"
                      :key="index"
                      >{{ type.name }}</a-select-option
                    >
                  </a-select>
                </a-form-item>
                <a-radio-group v-model:value="placeTake">
                  <a-radio class="d-block mb-2" :value="'default'"
                    >Gửi hàng tại bưu cục</a-radio
                  >
                  <a-radio style="d-block my-2" :value="'option'"
                    >Lấy hàng tận nơi</a-radio
                  >
                </a-radio-group>
                <div v-if="placeTake == 'option'" class="my-2">
                  <a-form-item
                    label="Địa điểm lấy hàng"
                    name="warehouse"
                    :rules="[
                      {
                        required: true,
                        message: 'Vui lòng nhập địa điểm lấy hàng!',
                      },
                    ]"
                  >
                    <a-select
                      v-model:value="data.warehouse"
                      style="width: 100%"
                    >
                      <a-select-option class="my-2" value=""
                        >- Địa điểm lấy hàng -</a-select-option
                      >
                    </a-select>
                  </a-form-item>
                  <a-form-item
                    label="Thời gian lấy hàng"
                    name="shippingTime"
                    :rules="[
                      {
                        required: true,
                        message: 'Vui lòng nhập thời gian lấy hàng!',
                      },
                    ]"
                  >
                    <a-date-picker
                      v-model:value="data.shippingTime"
                      class="my-2"
                      style="width: 100%"
                      show-time
                      placeholder="Thời gian lấy hàng"
                    />
                  </a-form-item>
                </div>
                <div class="border-top mt-2 pt-2">
                  <a-form-item
                    label="Trả phí ship"
                    name="shipPayer"
                    :rules="[
                      {
                        required: true,
                        message: 'Vui lòng nhập trả phí ship!',
                      },
                    ]"
                  >
                    <a-radio-group v-model:value="data.shipPayer">
                      <a-radio class="d-block mb-2" :value="true"
                        >Shop trả phí ship</a-radio
                      >
                      <a-radio style="d-block my-2" :value="false"
                        >Người nhận trả phí ship</a-radio
                      >
                    </a-radio-group>
                  </a-form-item>
                </div>
              </div>
            </div>
          </a-col>
          <a-col
            :span="8"
            :xs="{ span: 24 }"
            :md="{ span: 12 }"
            :lg="{ span: 8 }"
          >
            <div class="border">
              <div
                style="background: #bf1e2d"
                class="fs-4 border-bottom px-3 py-1 text-white"
              >
                Thông tin người nhận
              </div>
              <div class="p-3">
                <a-form-item
                  label="Tên người nhận"
                  name="shippingAddressName"
                  :rules="[
                    {
                      required: true,
                      message: 'Vui lòng nhập tên người nhận!',
                    },
                  ]"
                >
                  <a-input
                    v-model:value="data.shippingAddressName"
                    placeholder="Tên người nhận"
                  >
                    <template #prefix>
                      <UserOutlined class="me-2" />
                    </template>
                  </a-input>
                </a-form-item>
                <a-form-item
                  label="Số điện thoại người nhận"
                  name="shippingAddressPhone"
                  :rules="[
                    {
                      required: true,
                      message: 'Vui lòng nhập số điện thoại người nhận!',
                    },
                  ]"
                >
                  <a-input
                    v-model:value="data.shippingAddressPhone"
                    placeholder="Số điện thoại người nhận"
                  >
                    <template #prefix>
                      <PhoneOutlined class="me-2" />
                    </template>
                  </a-input>
                </a-form-item>

                <a-form-item
                  label="Tỉnh / thành phố"
                  name="shippingAddressProvinceId"
                  :rules="[
                    {
                      required: true,
                      message: 'Vui lòng nhập tỉnh / thành phố người nhận!',
                    },
                  ]"
                >
                  <a-select
                    v-model:value="data.shippingAddressProvinceId"
                    style="width: 100%"
                  >
                    <a-select-option value=""
                      >- Tỉnh / thành phố -</a-select-option
                    >
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
                  name="shippingAddressDistrictId"
                  :rules="[
                    {
                      required: true,
                      message: 'Vui lòng nhập quận / huyện người nhận!',
                    },
                  ]"
                >
                  <a-select
                    v-model:value="data.shippingAddressDistrictId"
                    style="width: 100%"
                  >
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
                  name="shippingAddressWardId"
                  :rules="[
                    {
                      required: requiredWard,
                      message: 'Vui lòng nhập xã / phường người nhận!',
                    },
                  ]"
                >
                  <a-select
                    v-model:value="data.shippingAddressWardId"
                    style="width: 100%"
                  >
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
                  name="shippingAddressStreet"
                  :rules="[
                    {
                      required: true,
                      message: 'Vui lòng nhập Địa chỉ cụ thể người nhận!',
                    },
                  ]"
                >
                  <a-input
                    v-model:value="data.shippingAddressStreet"
                    placeholder="Địa chỉ cụ thể: Số nhà, Tên đường..."
                  />
                </a-form-item>
              </div>
            </div>

            <a-button
              type="danger"
              class="mt-3 me-3"
              v-if="onlyCreate && actionShowReceiver"
              @click="handleUpdateReceiver"
              >Chỉnh sửa người nhận</a-button
            >
            <a-button
              type="danger"
              class="mt-3"
              v-if="onlyCreate"
              @click="handleAddReceiver"
              >Thêm người nhận</a-button
            >
            <div v-if="onlyCreate">
              <span v-for="(receiver, index) in listReceiver" :key="index">
                <a-button
                  type="dashed"
                  danger
                  v-if="receiver.inOrder == activeKey"
                  class="my-2 me-2"
                  @click="handleShowReceiver(receiver)"
                  >{{ receiver.shippingAddressName }}</a-button
                >
              </span>
            </div>
          </a-col>
          <a-col
            :span="8"
            :xs="{ span: 24 }"
            :md="{ span: 12 }"
            :lg="{ span: 8 }"
          >
            <ProductOrder
              :hasError="hasProductError"
              :products="data.products"
              :endValidate="endCheck"
              @on-error-product="handleErrorProduct"
              @on-throw-product="handleThrowProduct"
            />

            <div class="border mt-3">
              <div
                style="background: #bf1e2d"
                class="fs-4 border-bottom px-3 py-1 text-white"
              >
                Thông tin thêm
              </div>
              <div class="p-3">
                <a-textarea
                  v-model:value="data.note"
                  class="my-2"
                  style="resize: none"
                  placeholder="Ghi chú đơn hàng"
                  :rows="6"
                />
                <div class="d-flex justify-content-between my-2">
                  <a-input
                    v-model:value="data.coupon"
                    class="me-2"
                    placeholder="Mã giảm giá"
                  >
                    <template #prefix>
                      <AccountBookOutlined class="me-2" />
                    </template>
                  </a-input>
                  <a-button type="primary">Áp dụng</a-button>
                </div>
                <a-popover
                  title="Danh sách mã giảm giá"
                  trigger="click"
                  v-model:visible="showCoupon"
                >
                  <template #content>
                    <div class="content">
                      <div
                        class="d-flex p-2 border-top justify-content-between align-items-center"
                        v-for="(coupon, index) in coupons"
                        :key="index"
                      >
                        <div>
                          <div>
                            <b>{{ coupon.code }}</b>
                          </div>
                          <small
                            >Giảm giá {{ coupon.value }}
                            {{ coupon.rule == "base" ? "VND" : "%" }}</small
                          >
                        </div>
                        <CheckCircleOutlined
                          style="color: green; cursor: pointer"
                          @click="handleSetCoupon(coupon.code)"
                        />
                      </div>
                    </div>
                  </template>
                  <a-button>Danh sách mã giảm giá</a-button>
                </a-popover>
                <a-form-item
                  label="Trường hợp hoàn hàng"
                  name="returnCode"
                  class="my-2"
                  :rules="[
                    {
                      required: true,
                      message: 'Vui lòng nhập trường hợp hoàn hàng!',
                    },
                  ]"
                >
                  <a-select v-model:value="data.returnCode" style="width: 100%">
                    <a-select-option value=""
                      >- Trường hợp hoàn hàng -</a-select-option
                    >
                    <a-select-option
                      v-for="(type, index) in common.RETURN_TYPE"
                      :value="type.value"
                      :key="index"
                      >{{ type.name }}</a-select-option
                    >
                  </a-select>
                </a-form-item>
              </div>
            </div>
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
