<script setup>
import {createVNode, onMounted, ref} from "vue";
import {Modal, notification} from "ant-design-vue";

import SenderInfo from "@/components/order/component/SenderInfo";
import ShippingType from "@/components/order/component/ShippingType";
import ShippingInfo from "@/components/order/component/ShippingInfo";
import AdminProductSelector from "@/pages/admin/order/AdminProductSelector";
import OtherInfo from "@/components/order/component/OtherInfo";
import {
  getOrderFormData,
  getOrderPayload,
  getOrderProductPayload,
  resetOrderFormData,
  setOrderFormData
} from "@/pages/admin/order/configOrder";
import {ExclamationCircleOutlined} from "@ant-design/icons-vue";
import router from "@/router/router";
import {saveOrder, detailOrder} from "@/services/order";
import {useRoute} from "vue-router";
import dayjs from "dayjs";

const route = useRoute();

const activeKey = ref('default');
const form = ref(null);
const dataOrders = ref([]);
const defaultTabData = ref(getOrderFormData());
const customerId = ref(null);
const orderId = route.params !== null ? route.params.id : null;

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
    packageId: null,
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
    price: [{ required: true, message: 'Vui lòng nhập giá sản phẩm!' }],
    long: [{ required: true, message: 'Vui lòng nhập chiều dài sản phẩm!' }],
    width: [{ required: true, message: 'Vui lòng nhập chiều rộng sản phẩm!' }],
    height: [{ required: true, message: 'Vui lòng nhập chiều cao sản phẩm!' }]
  }
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
    ['productSelector', 'price'],
    ['productSelector', 'long'],
    ['productSelector', 'width'],
    ['productSelector', 'height']
  ]);
}

const handleAddOrder = () => {
  formValidate().then(() => {
    let length = dataOrders.value.length;
    dataOrders.value.push(getOrderFormData());

    setOrderFormData(dataOrders.value[length], {...formData.value});
    resetOrderFormData(formData.value);
  }).catch(() => {
    notification['error']({
      message: "Có Lỗi Sảy Ra!",
      description: "Vui lòng hoàn thành thông tin bị thiếu!"
    });
  });
}

const formValidate = async () => {
  await form.value.validate();
}

const changeToDefaultTab = () => {
  if (activeKey.value === 'default')
    return;

  setOrderFormData(dataOrders.value[activeKey.value], {...formData.value});
  setOrderFormData(formData.value, {...defaultTabData.value});
  resetOrderFormData(defaultTabData.value);

  activeKey.value = 'default';
}

const handleSubmit = () => {
  formValidate().then(() => {
    changeToDefaultTab();

    formValidate().then(() => {
      createOrders();
    }).catch(() => {
      Modal.confirm({
        title: 'Bạn có đơn hàng chưa hoàn thành!',
        icon: createVNode(ExclamationCircleOutlined),
        content: 'Đơn hàng mới tạo của bạn chưa hoàn thành, bạn muốn tiếp tục tạo những đơn hàng đã hoàn thành trước đó',
        okText: 'Tạo đơn hàng',
        cancelText: 'Hoàn thành đơn hàng mới',
        onOk: () => {
          createOrders(false);
        }
      });
    });
  }).catch((e) => {
    notification['error']({
      message: "Có Lỗi Sảy Ra!",
      description: "Vui lòng hoàn thành thông tin bị thiếu!"
    });

    throw e;
  });
}

const createOrders = (isUseDefault = true) => {
  let listOrders = [];
  if (isUseDefault) {
    const orderForm = getOrderPayload();
    processingOrderData(orderForm, JSON.parse(JSON.stringify(formData.value)));
    listOrders.push(orderForm);
  }

  for (const e of dataOrders.value) {
    const order = getOrderPayload();
    processingOrderData(order, JSON.parse(JSON.stringify(e)));
    listOrders.push(order);
  }

  handleSaveOrder(listOrders).then(() => {
    notification['success']({
      message: "Tạo đơn hàng thành công!",
      description: "Đơn hàng của bạn đã được chuyển tới trung tâm tiếp nhận"
    });


    router.push({ path: '/admin/order/list' });
  }).catch(() => {
    notification['error']({
      message: "Có Lỗi Sảy Ra!",
      description: "Có lỗi sảy ra trong quá trình tạo đơn hàng!"
    });
  });
}

const processingOrderData = (order, formOrder) => {
  order.customerId = customerId.value;
  order.senderName = formOrder.senderInfo.name;
  order.senderPhone = formOrder.senderInfo.phone;
  order.senderAddress = formOrder.senderInfo.address;
  order.shippingType = formOrder.shippingType.type;
  order.takenTime = formOrder.shippingType.time;
  order.shipPayer = formOrder.shippingType.objPay;
  order.note = formOrder.otherInfo.note;
  order.coupon = formOrder.otherInfo.coupon;
  order.returnCode = formOrder.otherInfo.returnCode;
  order.warehouse.id = formOrder.shippingType.warehouseId;
  order.shippingAddress.name = formOrder.shippingInfo.name;
  order.shippingAddress.phone = formOrder.shippingInfo.phone;
  order.shippingAddress.province = formOrder.shippingInfo.province;
  order.shippingAddress.provinceId = formOrder.shippingInfo.provinceId;
  order.shippingAddress.district = formOrder.shippingInfo.district;
  order.shippingAddress.districtId = formOrder.shippingInfo.districtId;
  order.shippingAddress.ward = formOrder.shippingInfo.ward;
  order.shippingAddress.wardId = formOrder.shippingInfo.wardId;
  order.shippingAddress.street = formOrder.shippingInfo.street;

  let size = Object.keys(formOrder.productList).length;
  let productList = {...formOrder.productList}

  productList[size] = formOrder.productSelector;

  for (const p of Object.values(productList)) {
    const product = getOrderProductPayload();
    const productFormData = JSON.parse(JSON.stringify(p));

    product.id = productFormData.packageId;
    product.product.id = productFormData.id;
    product.name = productFormData.name;
    product.qty = productFormData.qty;
    product.price = productFormData.price;
    product.weight = productFormData.weight;
    product.longPackage = productFormData.long;
    product.widthPackage = productFormData.width;
    product.heightPackage = productFormData.height;
    product.specialType = productFormData.special;

    order.products.push(product);
  }
}

const handleSaveOrder = async (data) => {
  await saveOrder(data);
}


const handleChangeTab = (key) => {
  let currentKey = activeKey.value;

  if (currentKey === key) return;

  form.value.clearValidate();
  if (currentKey === 'default') {
    setOrderFormData(defaultTabData.value, {...formData.value});
    setOrderFormData(formData.value, {...dataOrders.value[key]});

    activeKey.value = key;
  } else {
    formValidate().then(() => {
      setOrderFormData(dataOrders.value[currentKey], {...formData.value});

      if (key === 'default') {
        setOrderFormData(formData.value, {...defaultTabData.value});
        resetOrderFormData(defaultTabData.value);
      } else {
        setOrderFormData(formData.value, {...dataOrders.value[key]});
      }

      activeKey.value = key;
    }).catch(() => {
      activeKey.value = currentKey;
      notification['error']({
        message: "Có Lỗi Sảy Ra!",
        description: "Vui lòng hoàn thành thông tin bị thiếu!"
      });
    });
  }
}

const handleEditTab = (key, action) => {
  if (action !== 'add') {
    handleChangeTab('default');
    dataOrders.value = dataOrders.value.filter((x, index) => index !== key);
  }
}

const handleGetOrder = async () => {
  const {data} = await detailOrder({id: orderId});
  return data;
}

const loadOrderData = async () => {
  if (orderId !== null && orderId !== '') {
    handleGetOrder().then((r) => {
      const orderData = formData.value;
      orderData.senderInfo.name = r.senderName;
      orderData.senderInfo.address = r.senderAddress;
      orderData.senderInfo.phone = r.senderPhone;

      orderData.shippingInfo.name = r.shippingAddress.name;
      orderData.shippingInfo.phone = r.shippingAddress.phone;
      orderData.shippingInfo.province = r.shippingAddress.province;
      orderData.shippingInfo.provinceId = r.shippingAddress.provinceId;
      orderData.shippingInfo.district = r.shippingAddress.district;
      orderData.shippingInfo.districtId = r.shippingAddress.districtId;
      orderData.shippingInfo.ward = r.shippingAddress.ward;
      orderData.shippingInfo.wardId = r.shippingAddress.wardId;
      orderData.shippingInfo.street = r.shippingAddress.street;

      orderData.shippingType.type = r.shippingType;
      orderData.shippingType.warehouseId = r.warehouse.id;
      orderData.shippingType.time = r.takenTime === null || r.takenTime === '' ? '' : dayjs(r.takenTime);
      orderData.shippingType.objPay = r.shipPayer;
      orderData.shippingType.officeId = r.officeId;

      if (r.products.length > 0) {
        orderData.productSelector.packageId = r.products[0].id;
        orderData.productSelector.id = r.products[0].product.id;
        orderData.productSelector.name = r.products[0].name;
        orderData.productSelector.qty = r.products[0].qty;
        orderData.productSelector.weight = r.products[0].weight;
        orderData.productSelector.price = r.products[0].price;
        orderData.productSelector.long = r.products[0].long;
        orderData.productSelector.width = r.products[0].width;
        orderData.productSelector.height = r.products[0].height;
        orderData.productSelector.specialType = r.products[0].specialType;
      }

      let i = 1;
      for (; i < r.products.length; i++) {
        let prodData = {...orderData.productSelector};
        let key = i;
        if (r.products[i].id !== undefined && r.products[i].id !== null) {
          key = r.products[i].product.sku;
        }

        prodData.packageId = r.products[i].id;
        prodData.id = r.products[i].product.id;
        prodData.name = r.products[i].name;
        prodData.qty = r.products[i].qty;
        prodData.weight = r.products[i].weight;
        prodData.price = r.products[i].price;
        prodData.long = r.products[i].long;
        prodData.width = r.products[i].width;
        prodData.height = r.products[i].height;
        prodData.specialType = r.products[i].specialType;

        orderData.productList[key] = {...prodData};
      }

      formData.value = orderData;
    }).catch(() => {
      notification['error']({
        message: "Có Lỗi Sảy Ra!",
        description: "Không thể lấy thông tin đơn hàng"
      });
    });
  }
}

onMounted(() => {
  loadOrderData();
});

</script>
<template>
  <div class="">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">
    </div>
    <div class="px-4">
      <a-tabs v-model:activeKey="activeKey" hide-add type="editable-card" @tabClick="handleChangeTab" @edit="handleEditTab">
        <a-tab-pane v-for="(order, index) in dataOrders" :key="index"
                    :tab="`Đơn hàng - (${index + 1})`" :closable="true"/>
        <a-tab-pane :tab="'Thông tin đơn hàng'" :key="'default'" :closable="false"/>
      </a-tabs>
      <a-form :model="formData" :rules="formDataRules" layout="vertical" name="basic" autocomplete="off" ref="form">
        <a-form-item>
          <div class="d-flex justify-content-between">
            <button v-if="activeKey === 'default'" :class="'mt-3 me-1 btn btn-outline-info'" @click.prevent="handleAddOrder">
              {{ 'Thêm đơn hàng mới' }}
            </button>
            <button class="btn btn-success mt-3" @click.prevent="handleSubmit">Lưu đơn hàng</button>
          </div>
        </a-form-item>
        <a-row type="flex" class="my-4" :gutter="[10, 10]" align="top">
          <a-col :span="8" :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 8 }">
            <SenderInfo :senderInfo="formData.senderInfo"
                        @sender-info-change="handleSenderChange"
            />
            <ShippingType :shippingType="formData.shippingType"
                          :customerId="customerId"
                          :isCustomer="false"
                          @shipping-type-change="handleShippingTypeChange"
            />
          </a-col>
          <a-col :span="8" :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 8 }">
            <ShippingInfo :shippingInfo="formData.shippingInfo"
                          @shipping-info-change="handleShippingInfoChange"
            />
            <OtherInfo :otherInfo="formData.otherInfo"
                       @other-info-change="handleOtherInfoChange"
            />
          </a-col>
          <a-col :span="8" :xs="{ span: 24 }" :md="{ span: 12 }" :lg="{ span: 8 }">
            <AdminProductSelector :productList="formData.productList"
                                  :productSelector="formData.productSelector"
                                  @validate-product="handleValidateProduct"
                                  @change-product="handleProductChange"
                                  @product-selector-change="handleProductSelectorChange" />
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
