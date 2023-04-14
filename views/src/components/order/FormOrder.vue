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
              <button v-if="activeKey === 'default'" :class="'mt-3 me-1 btn btn-outline-info'" @click.prevent="handleAddOrder">
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
import {ref, createVNode} from "vue";
import SenderInfo from "@/components/order/component/SenderInfo";
import ShippingInfo from "@/components/order/component/ShippingInfo";
import ShippingType from "@/components/order/component/ShippingType";
import OtherInfo from "@/components/order/component/OtherInfo";
import ProductSelector from "@/components/order/component/ProductSelector";
import {commonFunction} from "@/scripts/ulti";
import { notification, Modal } from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import {saveOrder} from "@/services/order";
import {
  setOrderFormData,
  resetOrderFormData,
  getOrderFormData,
  getOrderPayload,
  getOrderProductPayload,
  setOrderProductData
} from "@/pages/admin/order/configOrder";

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
    id: null,
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

const handleChangeTab = (key) => {
  let currentKey = activeKey.value;

  if (currentKey === key) return;

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

const changeToDefaultTab = () => {
  if (activeKey.value === 'default')
    return;

  setOrderFormData(dataOrders.value[activeKey.value], {...formData.value});
  setOrderFormData(formData.value, {...defaultTabData.value});
  resetOrderFormData(defaultTabData.value);

  activeKey.value = 'default';
}

const handleEditTab = (key, action) => {
  if (action !== 'add') {
    handleChangeTab('default');
    dataOrders.value = dataOrders.value.filter((x, index) => index !== key);
  }
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

const formValidate = async () => {
  await form.value.validate();
}

const productFormValidate = async () => {
  await form.value.validateFields([
    ['productSelector', 'name'],
    ['productSelector', 'qty'],
    ['productSelector', 'weight'],
    ['productSelector', 'price']
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
    }).catch(() => {
      notification['error']({
        message: "Có Lỗi Sảy Ra!",
        description: "Có lỗi sảy ra trong quá trình tạo đơn hàng!"
      });
  });
}

const processingOrderData = (order, formOrder) => {
  order.customerId = customerStorage.id;
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
  formOrder.productList[size] = formOrder.productSelector;

  for (const p of Object.values(formOrder.productList)) {
    const product = getOrderProductPayload();
    setOrderProductData(formOrder.productSelector, p);
    const productFormData = JSON.parse(JSON.stringify(formOrder.productSelector));

    product.product.id = productFormData.id;
    product.name = productFormData.name;
    product.qty = productFormData.qty;
    product.price = productFormData.price;
    product.weight = productFormData.weight;

    order.products.push(product);
  }
}

const handleSaveOrder = async (data) => {
  await saveOrder(data);
}

</script>
<style scoped>
.content {
  height: 250px;
  width: 200px;
  overflow-y: scroll;
  overflow-x: hidden;
}

.ant-tabs-tab-btn:focus, .ant-tabs-tab-remove:focus, .ant-tabs-tab-btn:active, .ant-tabs-tab-remove:active {
  color: red!important;
}
</style>