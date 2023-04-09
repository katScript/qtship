<template>
    <div class="">
        <div class="my-2 border-bottom px-4 py-2 fs-2"><h4>Tạo mới đơn hàng</h4></div>
        <div class="px-4">
            <a-tabs v-model:activeKey="activeKey" hide-add
                    type="editable-card">
<!--                <a-tab-pane v-for="(order, index) in orders" :key="index"-->
<!--                            :tab="order.shippingAddressName" :closable="true"/>-->
                <a-tab-pane :tab="'Thông tin đơn hàng'" :key="'default'" :closable="false"/>
            </a-tabs>

            <a-form :model="formData" layout="vertical" name="order" autocomplete="off" ref="form">
                <div class="w-100 m-auto">
                    <div class="row">
                        <a-col class="col-md-6">
                            <SenderInfo :name="formData.senderInfo.name"
                                        :address="formData.senderInfo.address"
                                        :phone="formData.senderInfo.phone"
                                        @sender-change="handleSenderChange"
                            />
                            <ShippingInfo/>
                        </a-col>
                        <a-col class="col-md-6">
                            <ShippingType :customer-id="customerStorage.id" />
                            <OtherInfo />
                            <div class="border mt-3">
                                <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Tóm tắt thông tin dịch vụ</div>
                                <div class="p-3">
                                    <div class="d-flex justify-content-between my-2">
                                        Phí ship : 1000 VNĐ
                                    </div>
                                </div>
                            </div>
                        </a-col>
                        <a-col class="col-md-6">
<!--                            <ProductSelect :hasError="hasProductError" :products="data.products" :endValidate="endCheck"-->
<!--                                           @on-error-product="handleErrorProduct"-->
<!--                                           @on-throw-product="handleThrowProduct"/>-->
                        </a-col>
                    </div>
                </div>
                <center>
                    <a-form-item class="m-auto">
                        <div class="">
                            <button type="submit"  class="mt-3 me-1"
                                    :class="'btn btn-outline-info'" ref="submitList">
                                {{'Thêm đơn hàng mới' }}
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
// import ProductSelect from "@/components/common/ProductSelect";
import SenderInfo from "@/components/order/component/SenderInfo";
import ShippingInfo from "@/components/order/component/ShippingInfo";
import ShippingType from "@/components/order/component/ShippingType";
import OtherInfo from "@/components/order/component/OtherInfo";
import {commonFunction} from "@/scripts/ulti";

const activeKey = ref('default');
let customerStorage = JSON.parse(commonFunction.getCustomerStorage());

const form = ref(null);
// const data = ref([]);
const formData = ref({
    senderInfo: {
        name: '',
        address: '',
        phone: ''
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
    let senderData = formData.value.senderInfo;

    senderData.name = value.name;
    senderData.address = value.address;
    senderData.phone = value.phone;
}

const handleSubmit = async () => {
    let validate = await form.value.validate();
    console.log(validate);
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