<template>
  <div class="">
    <div class="my-2 border-bottom px-4 py-2 fs-2"><h4>Tạo mới đơn hàng</h4></div>
    <div class="px-4">
      <a-tabs v-model:activeKey="activeKey" hide-add type="editable-card" @tabClick="handleChangeTab" @edit="handleEditTab">
        <a-tab-pane v-for="(order, index) in dataOrders" :key="index"
                    :tab="`Đơn hàng - (${index + 1})`" :closable="true"/>
        <a-tab-pane :tab="'Thông tin đơn hàng'" :key="'default'" :closable="false"/>
      </a-tabs>

      <a-form :model="formData" :rules="formDataRules" layout="vertical" name="order" autocomplete="off" ref="form">
        <div class="w-100 m-auto">
          <div class="row">
            <a-col class="col-md-6">
              <SenderInfo :senderInfo="formData.senderInfo"
                          @sender-info-change="handleSenderChange"
              />
              <ShippingInfo :shippingInfo="formData.shippingInfo"
                            @shipping-info-change="handleShippingInfoChange"
              />
              <ProductSelector :customerId="customerStorage.id"
                               :productList="formData.productList"
                               :productSelector="formData.productSelector"
                               @validate-product="handleValidateProduct"
                               @change-product="handleProductChange"
                               @product-selector-change="handleProductSelectorChange"
              />
            </a-col>
            <a-col class="col-md-6">
              <ShippingType :customerId="customerStorage.id"
                            :shippingType="formData.shippingType"
                            @shipping-type-change="handleShippingTypeChange"
              />
              <OtherInfo :otherInfo="formData.otherInfo"
                         @other-info-change="handleOtherInfoChange"
              />
<!--              <div class="border mt-3">-->
<!--                <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Tổng quan</div>-->
<!--                <div class="p-3">-->
<!--                  <div class="d-flex justify-content-between my-2">-->
<!--                    Phí ship : 1000 VNĐ-->
<!--                  </div>-->
<!--                </div>-->
<!--              </div>-->
            </a-col>
          </div>
        </div>
        <center>
          <a-form-item class="m-auto">
            <div class="">
              <button v-if="activeKey === 'default'" :class="'mt-3 me-1 btn btn-outline-info'" @click.prevent="handleAddOrders">
                {{ 'Thêm đơn hàng mới' }}
              </button>
              <button class="btn btn-success mt-3" @click.prevent="handleSubmit">Lưu đơn hàng</button>
            </div>
          </a-form-item>
        </center>
      </a-form>
      <hr>
    </div>
  </div>
</template>
<script setup>
import {ref} from "vue";
import SenderInfo from "@/components/order/component/SenderInfo";
import ShippingInfo from "@/components/order/component/ShippingInfo";
import ShippingType from "@/components/order/component/ShippingType";
import OtherInfo from "@/components/order/component/OtherInfo";
import ProductSelector from "@/components/order/component/ProductSelector";
import {commonFunction} from "@/scripts/ulti";
import {setOrderFormData, resetOrderFormData, getOrderFormData} from "@/pages/admin/order/configOrder";

const activeKey = ref('default');

const customerStorage = JSON.parse(commonFunction.getCustomerStorage());

const form = ref(null);
const dataOrders = ref([]);
const defaultTabData = ref(getOrderFormData());

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
    time: '',
    objPay: true
  },
  otherInfo: {
    note: '',
    coupon: '',
    returnCode: ''
  },
  productSelector: {
    name: '',
    qty: '',
    weight: '',
    price: ''
  },
  productList: {}
});

const formDataRules = ref({
  senderInfo: {
    name: [{required: true, message: 'Vui lòng nhập thông tin người gửi!'}],
    phone: [{required: true, message: 'Vui lòng nhập số điện thoại người gửi!'}, {
      pattern: /\d{9,11}/,
      message: 'Nhập số điện thoại phù hợp'
    }],
    address: [{required: true, message: 'Vui lòng nhập địa chỉ người gửi!'}]
  },
  shippingInfo: {
    name: [{required: true, message: 'Vui lòng nhập tên người nhận!'}],
    phone: [{required: true, message: 'Vui lòng nhập số điện thoại người nhận!'}, {
      pattern: /\d{9,11}/,
      message: 'Nhập số điện thoại phù hợp'
    }],
    provinceId: [{required: true, message: 'Vui lòng nhập tỉnh / thành phố người nhận!'}],
    districtId: [{required: true, message: 'Vui lòng nhập quận / huyện người nhận!'}],
    wardId: [{required: true, message: 'Vui lòng nhập xã / phường người nhận!'}],
    street: [{required: true, message: 'Vui lòng nhập Địa chỉ cụ thể người nhận!'}]
  },
  shippingType: {
    type: [{ required: true, message: 'Vui lòng nhập phương thức giao hàng!' }],
    warehouseId: [{ required: true, message: 'Vui lòng chọn địa điểm lấy hàng!' }],
    time: [{ required: true, message: 'Vui lòng nhập thời gian lấy hàng!' }],
    objPay: [{ required: true, message: 'Vui lòng nhập trả phí ship!' }]
  },
  otherInfo: {
    returnCode: [{ required: true, message: 'Vui lòng nhập trường hợp hoàn hàng!' }]
  },
  productSelector: {
    name: [{ required: true, message: 'Vui lòng nhập tên sản phẩm!' }],
    qty: [{ required: true, message: 'Vui lòng nhập số lượng sản phẩm!' }],
    weight: [{ required: true, message: 'Vui lòng nhập khối lượng sản phẩm!' }],
    price: [{ required: true, message: 'Vui lòng nhập giá sản phẩm!' }]
  }
});

// const orderData = ref({
//     id: null,
//     customerId: null,
//     senderName: null,
//     senderPhone: null,
//     senderAddress: null,
//     note: null,
//     status: null,
//     feedback: null,
//     notification: false,
//     shipPayer: false,
//     coupon: null,
//     officeId: null,
//     warehouse: {
//         id: null
//     },
//     shippingAddress: {
//         id: null,
//         name: null,
//         phone: null,
//         province: null,
//         provinceId: null,
//         district: null,
//         districtId: null,
//         ward: null,
//         wardId: null,
//         street: null
//     },
//     shippingType: null,
//     takenTime: null,
//     returnCode: null,
//     products: [
//         {
//             id: null,
//             product: null,
//             name: null,
//             qty: null,
//             price: null,
//             weight: null,
//             longPackage: null,
//             widthPackage: null,
//             heightPackage: null,
//             specialType: false
//         }
//     ]
// });

const handleSenderChange = (value) => {
  formData.value.senderInfo = value;
}

const handleShippingInfoChange = (value) => {
  formData.value.shippingInfo = value;
}

const handleShippingTypeChange = (value) => {
  formData.value.shippingType = value;
}

const handleOtherInfoChange = (value) => {
  formData.value.otherInfo = value;
}

const handleProductSelectorChange = (value) => {
  formData.value.productSelector = value;
}

const handleProductChange = (value) => {
  formData.value.productList = value;
}

const handleValidateProduct = async (event) => {
  let validate = await form.value.validateFields([
    ['productSelector', 'name'],
    ['productSelector', 'qty'],
    ['productSelector', 'weight'],
    ['productSelector', 'price']
  ]);

  if (validate) {
    event();
  }
}

const handleSubmit = async () => {
  let validate = await form.value.validate();
  console.log(validate);
}

const handleAddOrders = async () => {
  let validate = await form.value.validate();

  if (validate) {
    let length = dataOrders.value.length;
    dataOrders.value.push(getOrderFormData());

    setOrderFormData(dataOrders.value[length], {...formData.value});
    resetOrderFormData(formData.value);

    console.log('handleAddOrders', dataOrders.value[length].shippingType.time);
  }
}

const handleChangeTab = async (key) => {
  let currentTab = activeKey.value;

  if (currentTab === 'default') {
    setOrderFormData(defaultTabData.value, {...formData.value});
    setOrderFormData(formData.value, {...dataOrders.value[key]});

    activeKey.value = key;
  } else {
    let validate = await form.value.validate();

    if (!validate) {
      setOrderFormData(dataOrders.value[currentTab], {...formData.value});

      if (key === 'default') {
        setOrderFormData(formData.value, {...defaultTabData.value});
        resetOrderFormData(defaultTabData.value);
      } else {
        setOrderFormData(formData.value, {...dataOrders.value[key]});
      }

      activeKey.value = key;
    }
  }

  console.log(activeKey.value);
}

const handleEditTab = (key, action) => {
  if (action !== 'add') {
    handleChangeTab('default');
    dataOrders.value = dataOrders.value.filter((x, index) => index !== key);
  }
}

</script>
<style scoped>
.content {
  height: 250px;
  width: 200px;
  overflow-y: scroll;
  overflow-x: hidden;
}
</style>