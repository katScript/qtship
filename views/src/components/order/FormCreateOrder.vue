<template>
    <div class="">
        <div class="my-2 border-bottom px-4 py-2 fs-2"><h4>{{ getTitle }}</h4></div>
        <div class="d-flex p-5" v-if="!(onlyUpdate || onlyCreate)">
            <a-input-number style="width: 200px;" v-model:value="orderCode" addon-before="ORD"
                            placeholder="Mã đơn hàng"/>
            <a-button type="primary" class="ms-2" @click="router.push('/customer/orders/update/' + orderCode)">Cập
                nhật
            </a-button>
        </div>
        <div class="px-4" v-else>
            <a-tabs v-model:activeKey="activeKey" hide-add @tabClick="handleTabClick" @edit="onEdit"
                    type="editable-card">
                <a-tab-pane v-for="(order, index) in orders" :key="index"
                            :tab="order.shippingAddressName" :closable="true"/>
                <a-tab-pane :tab="'Thông tin đơn hàng'" :key="'default'" :closable="false"/>
            </a-tabs>

            <a-form :model="data" layout="vertical" name="basic" autocomplete="off" @finish="onFinish" ref="form"
                    @finishFailed="onFinishFailed">
                <div class="w-100 m-auto">
                    <div class="row">
                        <a-col class="col-md-6">
                            <div class="border">
                                <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Thông tin người gửi</div>
                                <div class="p-3">
                                    <a-form-item label="Thông tin người gửi" name="senderName"
                                                 :rules="[{ required: true, message: 'Vui lòng nhập thông tin người gửi!' }]">
                                        <a-input v-model:value="data.senderName" placeholder="Thông tin người gửi">
                                            <template #prefix>
                                                <UserOutlined class="me-2"/>
                                            </template>
                                        </a-input>
                                    </a-form-item>
                                    <a-form-item label="Số điện thoại người gửi" name="senderPhone"
                                                 :rules="[{ required: true, message: 'Vui lòng nhập số điện thoại người gửi!' }]">
                                        <a-input v-model:value="data.senderPhone" placeholder="Số điện thoại người gửi">
                                            <template #prefix>
                                                <PhoneOutlined class="me-2"/>
                                            </template>
                                        </a-input>
                                    </a-form-item>
                                    <a-form-item label="Địa chỉ người gửi" name="senderAddress"
                                                 :rules="[{ required: true, message: 'Vui lòng nhập địa chỉ người gửi!' }]">
                                        <a-input v-model:value="data.senderAddress" placeholder="Địa chỉ người gửi">
                                            <template #prefix>
                                                <AimOutlined class="me-2"/>
                                            </template>
                                        </a-input>
                                    </a-form-item>
                                </div>
                            </div>

                            <div class="border mt-3">
                                <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Thông tin người nhận
                                </div>
                                <div class="p-3">
                                    <a-form-item label="Tên người nhận" name="shippingAddressName"
                                                 :rules="[{ required: true, message: 'Vui lòng nhập tên người nhận!' }]">
                                        <a-input v-model:value="data.shippingAddressName" placeholder="Tên người nhận">
                                            <template #prefix>
                                                <UserOutlined class="me-2"/>
                                            </template>
                                        </a-input>
                                    </a-form-item>
                                    <a-form-item label="Số điện thoại người nhận" name="shippingAddressPhone"
                                                 :rules="[{ required: true, message: 'Vui lòng nhập số điện thoại người nhận!' }]">
                                        <a-input v-model:value="data.shippingAddressPhone"
                                                 placeholder="Số điện thoại người nhận">
                                            <template #prefix>
                                                <PhoneOutlined class="me-2"/>
                                            </template>
                                        </a-input>
                                    </a-form-item>
                                    <a-form-item label="Tỉnh / thành phố" name="shippingAddressProvinceId"
                                                 :rules="[{ required: true, message: 'Vui lòng nhập tỉnh / thành phố người nhận!' }]">
                                        <a-select v-model:value="data.shippingAddressProvinceId" style="width: 100%" @change="provinceProcess">
                                            <a-select-option value="">- Tỉnh / thành phố -</a-select-option>
                                            <a-select-option v-for="(province, index) in provinces"
                                                             :value="province.province_id" :key="index">
                                                {{
                                                    province.province_name
                                                }}
                                            </a-select-option>
                                        </a-select>
                                    </a-form-item>
                                    <a-form-item label="Quận / huyện " name="shippingAddressDistrictId"
                                                 :rules="[{ required: true, message: 'Vui lòng nhập quận / huyện người nhận!' }]">
                                        <a-select v-model:value="data.shippingAddressDistrictId" style="width: 100%" @change="districtProcess">
                                            <a-select-option value="">- Quận / huyện -</a-select-option>
                                            <a-select-option v-for="(district, index) in districts"
                                                             :value="district.district_id" :key="index">
                                                {{
                                                    district.district_name
                                                }}
                                            </a-select-option>
                                        </a-select>
                                    </a-form-item>
                                    <a-form-item label="Xã / phường" name="shippingAddressWardId"
                                                 :rules="[{ required: requiredWard, message: 'Vui lòng nhập xã / phường người nhận!' }]">
                                        <a-select v-model:value="data.shippingAddressWardId" style="width: 100%" @change="wardProcess">
                                            <a-select-option value="">- Xã / phường -</a-select-option>
                                            <a-select-option v-for="(ward, index) in wards" :value="ward.ward_id"
                                                             :key="index">
                                                {{
                                                    ward.ward_name
                                                }}
                                            </a-select-option>
                                        </a-select>
                                    </a-form-item>
                                    <a-form-item label="Địa chỉ cụ thể" name="shippingAddressStreet"
                                                 :rules="[{ required: true, message: 'Vui lòng nhập Địa chỉ cụ thể người nhận!' }]">
                                        <a-input v-model:value="data.shippingAddressStreet"
                                                 placeholder="Địa chỉ cụ thể: Số nhà, Tên đường..."/>
                                    </a-form-item>
                                </div>
                            </div>
                        </a-col>
                        <a-col class="col-md-6">
                            <div class="border">
                                <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Phương thức giao & lấy
                                    hàng
                                </div>
                                <div class="p-3">
                                    <a-form-item label="Phương thức giao hàng" name="shippingType"
                                                 :rules="[{ required: true, message: 'Vui lòng nhập phương thức giao hàng!' }]">
                                        <a-select v-model:value="data.shippingType" style="width: 100%">
                                            <a-select-option value="">- Phương thức giao hàng -</a-select-option>
                                            <a-select-option v-for="(type, index) in common.TYPE_SHIPPING"
                                                             :value="type.value" :key="index">{{
                                                    type.name
                                                }}
                                            </a-select-option>
                                        </a-select>
                                    </a-form-item>
                                    <a-radio-group v-model:value="placeTake">
                                        <a-radio class="d-block mb-2" :value="'default'">Gửi hàng tại bưu cục</a-radio>
                                        <a-radio class="d-block my-2" :value="'option'">Lấy hàng tận nơi</a-radio>
                                    </a-radio-group>
                                    <div v-if="placeTake == 'option'" class="my-2">
                                        <a-form-item label="Địa điểm lấy hàng" name="warehouse"
                                                     :rules="[{ required: true, message: 'Vui lòng chọn địa điểm lấy hàng!' }]">
                                            <a-select v-model:value="data.warehouse.id" style="width: 100%">
                                                <a-select-option class="my-2" value="">- Địa điểm lấy hàng -
                                                </a-select-option>
                                                <a-select-option v-for="(type, index) in warehouses" :value="type.id"
                                                                 :key="index">{{
                                                        type.name
                                                    }}
                                                </a-select-option>
                                            </a-select>
                                        </a-form-item>
                                        <a-form-item label="Thời gian lấy hàng" name="takenTime"
                                                     :rules="[{ required: true, message: 'Vui lòng nhập thời gian lấy hàng!' }]">
                                            <a-date-picker v-model:value="data.takenTime" class="my-2"
                                                           style="width: 100%" show-time
                                                           placeholder="Thời gian lấy hàng"/>
                                        </a-form-item>
                                    </div>
                                    <div class="border-top mt-2 pt-2">
                                        <a-form-item label="Trả phí ship" name="shipPayer"
                                                     :rules="[{ required: true, message: 'Vui lòng nhập trả phí ship!' }]">
                                            <a-radio-group v-model:value="data.shipPayer">
                                                <a-radio class="d-block mb-2" :value="true">Shop trả phí ship</a-radio>
                                                <a-radio class="d-block my-2" :value="false">Người nhận trả phí ship
                                                </a-radio>
                                            </a-radio-group>
                                        </a-form-item>
                                    </div>
                                </div>
                            </div>

                            <div class="border mt-3">
                                <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Thông tin thêm</div>
                                <div class="p-3">
                                    <a-textarea v-model:value="data.note" class="my-2" style="resize: none;"
                                                placeholder="Ghi chú đơn hàng"
                                                :rows="6"/>
                                    <div class="d-flex justify-content-between my-2">
                                        <a-input v-model:value="data.coupon" class="me-2" placeholder="Mã giảm giá">
                                            <template #prefix>
                                                <AccountBookOutlined class="me-2"/>
                                            </template>
                                        </a-input>
                                        <a-button type="primary">Áp dụng</a-button>
                                    </div>
                                    <a-popover title="Danh sách mã giảm giá" trigger="click"
                                               v-model:visible="showCoupon">
                                        <template #content>
                                            <div class="content">
                                                <div
                                                    class="d-flex p-2 border-top justify-content-between align-items-center"
                                                    v-for="(coupon, index) in coupons" :key="index">
                                                    <div>
                                                        <div>
                                                            <b>{{ coupon.code }}</b>
                                                        </div>
                                                        <small>Giảm giá {{ coupon.value }}
                                                            {{ coupon.rule == 'base' ? "VND" : "%" }}</small>
                                                    </div>
                                                    <CheckCircleOutlined style="color: green; cursor: pointer;"
                                                                         @click="handleSetCoupon(coupon.code)"/>
                                                </div>
                                            </div>
                                        </template>
                                        <a-button>Danh sách mã giảm giá</a-button>
                                    </a-popover>
                                    <a-form-item label="Trường hợp hoàn hàng" name="returnCode" class="my-2"
                                                 :rules="[{ required: true, message: 'Vui lòng nhập trường hợp hoàn hàng!' }]">
                                        <a-select v-model:value="data.returnCode" style="width: 100%">
                                            <a-select-option value="">- Trường hợp hoàn hàng -</a-select-option>
                                            <a-select-option v-for="(type, index) in common.RETURN_TYPE"
                                                             :value="type.value" :key="index">{{
                                                    type.name
                                                }}
                                            </a-select-option>
                                        </a-select>
                                    </a-form-item>
                                </div>
                            </div>
                            <div class="border mt-3">
                                <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Tóm tắt thông tin dịch vụ</div>
                                <div class="p-3">
                                    <div class="d-flex justify-content-between my-2">
                                        Phí ship : {{shippingFee}} VNĐ
                                    </div>
                                </div>
                            </div>
                        </a-col>
                        <a-col class="col-md-6">
                            <ProductSelect :hasError="hasProductError" :products="data.products" :endValidate="endCheck"
                                           @on-error-product="handleErrorProduct"
                                           @on-throw-product="handleThrowProduct"/>
                        </a-col>
                    </div>
                </div>
                <center>
                    <a-form-item class="m-auto">
                        <div class="">
                            <button type="submit" v-if="onlyCreate" class="mt-3 me-1"
                                    :class="'btn btn-outline-info'" ref="submitList">
                                {{'Thêm đơn hàng mới' }}
                            </button>
                            <div v-else></div>
                            <button class="btn btn-success mt-3" @click.prevent="handleSubmitForm">Lưu đơn hàng</button>
                        </div>
                    </a-form-item>
                </center>
            </a-form>
            <hr>
        </div>
    </div>
</template>
<script setup>
import {computed, reactive, ref, watch} from "vue";
import {useRoute, useRouter} from "vue-router";
import {
    UserOutlined,
    PhoneOutlined,
    AimOutlined,
    AccountBookOutlined,
    CheckCircleOutlined
} from "@ant-design/icons-vue";
import common from "@/utils/common";
import {province, district, ward} from "@/services/region";
import {saveOrder, detailOrder} from "@/services/order";
import {listActive} from "@/services/coupon";
import {listWarehouse} from "@/services/warehouse";
import {
    dataSample,
    handleSetData,
    requiredData,
    omitKey,
    processCustomerDataSample
} from "@/pages/admin/order/configOrder";
import ProductSelect from "@/components/common/ProductSelect";
import {message} from "ant-design-vue";
import {omit} from "lodash"
import {commonFunction} from "@/scripts/ulti";
import {getShippingFee} from "@/services/shipping"

const route = useRoute();
const router = useRouter();

let customerStorage = JSON.parse(commonFunction.getCustomerStorage());
processCustomerDataSample(dataSample, customerStorage);

const data = reactive({...dataSample});
const currentTabData = reactive({...dataSample});

const form = ref(null);
const orders = ref([]);
const submitList = ref(null);
const activeKey = ref('default');
const hasProductError = ref(false);
const placeTake = ref('default');
const provinces = ref([]);
const districts = ref([]);
const orderCode = ref('');
const wards = ref([]);
const coupons = ref([]);
const warehouses = ref([]);
const showCoupon = ref(false);
const endCheck = ref(false);
const getTitle = computed(() => {
    const title = {
        update: 'Cập nhật đơn hàng',
        create: 'Tạo mới đơn hàng',
    }
    return title[route.params?.action] || 'đơn hàng';
});

const shippingDetail = ref({
    shippingType: null,
    warehouseId: null,
    officeId: null,
    provinceId: null,
    weight: 0,
    couponCode: null
});

const shippingFee = ref(0);

const onlyCreate = computed(() => route.params?.action === 'create');
const onlyUpdate = computed(() => route.params?.action === 'update' && route.params?.id);

//=================================== Prepare form data =============================

/**
 * Get coupon list
 * @returns {Promise<void>}
 */
const handleGetCoupon = async () => {
    const {data} = await listActive();
    coupons.value = data;
}

/**
 * Get warehouse list
 * @returns {Promise<void>}
 */
const handleGetWarehouse = async () => {
    const {data} = await listWarehouse(customerStorage.id);
    warehouses.value = data;
}

/**
 * Region address data
 */
const handleGetProvince = async () => {
    const {data} = await province();
    provinces.value = data;
}

const handleGetDistrict = async (provinceId) => {
    resetDistrictData();
    const {data} = await district(provinceId);
    districts.value = data;
}

const handleGetWard = async (districtId) => {
    resetWardData();
    const {data} = await ward(districtId);
    wards.value = data;
}

const resetDistrictData = () => {
    districts.value = [];
    data.shippingAddressDistrict = '';
    data.shippingAddressDistrictId = '';
}

const resetWardData = () => {
    wards.value = [];
    data.shippingAddressWard = '';
    data.shippingAddressWardId = '';
}

// ========================= Order data function ====================
/**
 * set form data for current tab
 */
const handleSetCurrentData = () => {
    if (isDefaultTab()) {
        handleSetData(data, currentTabData);
    } else {
        handleSetData(data, orders.value[activeKey.value]);
    }

    message.success('Cập nhật đơn hàng thành công');
}

/**
 * get Order data
 * @returns {Promise<void>}
 */
const handleGetOrder = async () => {
    if (onlyUpdate.value) {
        try {
            const {data: results} = await detailOrder({id: route.params?.id});
            Object.assign(data, results);
            data.shippingAddressName = results.shippingAddress.name
            data.shippingAddressPhone = results.shippingAddress.phone
            handleGetDistrict(results.shippingAddress.provinceId);
            handleGetWard(results.shippingAddress.districtId);
            data.shippingAddressProvince = results.shippingAddress.province
            data.shippingAddressProvinceId = results.shippingAddress.provinceId
            data.shippingAddressDistrict = results.shippingAddress.district
            data.shippingAddressDistrictId = results.shippingAddress.districtId
            data.shippingAddressWard = results.shippingAddress.ward
            data.shippingAddressWardId = results.shippingAddress.wardId
            data.shippingAddressStreet = results.shippingAddress.street
        } catch (e) {
            if (e.response.data.status === 500) {
                message.error('Đơn hàng không tồn tại');
                router.push('/customer/order')
            }
        }
    }
}

// ========================= Tab function =========================
const isDefaultTab = () => {
    return activeKey.value === 'default';
}

const setToDefaultTab = () => {
    handleTabClick('default');
}

const handleTabClick = async (key) => {
    let currentTab = activeKey.value;
    if (!isDefaultTab()) {
        let valid = await form.value.validate();
        if (!valid) return;
    }

    if (currentTab !== key) {
        if (key === 'default') {
            orders.value[currentTab] = {...data};
            handleSetData(data, currentTabData);
        } else {
            form.value.clearValidate();

            if (isDefaultTab()) {
                handleSetData(currentTabData, data);
            } else {
                orders.value[currentTab] = {...data};
            }

            handleSetData(data, orders.value[key]);
        }

        activeKey.value = key;
    }
}

const onEdit = (targetKey, action) => {
    if (action !== 'add') {
        remove(targetKey);
    }

    handleSetCurrentData();
};

const remove = targetKey => {
    if (orders.value.length > 1) {
        activeKey.value = targetKey === 0 ? targetKey + 1 : targetKey - 1;
    } else {
        setToDefaultTab();
    }

    orders.value = orders.value.filter((x, index) => index !== targetKey);
};

// ======================== Add new order ==========================
const onFinish = () => {
    handleErrorProduct(data.products);

    if (hasProductError.value) {
        return message.error('Vui lòng kiểm tra lại thông tin sản phẩm');
    } else {
        if (isDefaultTab()) {
            orders.value.push({...data});
        } else {
            orders.value[activeKey.value] = {...data};
        }

        processCustomerDataSample(dataSample, null);
        handleSetData(currentTabData, {...dataSample});
        handleSetCurrentData();
    }
};

const handleErrorProduct = (value) => {
    hasProductError.value = false;

    value.forEach(product => {
        for (const property in product) {
            if (!product[property] && property !== 'specialType') {
                hasProductError.value = true;
            }
        }
    })
};

const onFinishFailed = (errorInfo) => {
    console.log('Failed:', errorInfo);
};

//=================================== Process order input data =======================
/**
 * Set coupon
 * @param code
 */
const handleSetCoupon = (code) => {
    showCoupon.value = false;
    data.coupon = code;
}

/**
 * Set product data
 * @param value
 */
const handleThrowProduct = (value) => {
    data.products = value;

    let weight = 0.0;
    value.forEach((e) => {
        weight += e.weight;
    });

    shippingDetail.value.weight = weight;
    calculateShippingFee(shippingDetail.value);
}
// ========================== Save order ===========================

const handleSubmitForm = async () => {
    let currentValidate = await form.value.validate();
    if (currentValidate) {
        if (!isDefaultTab())
            setToDefaultTab();

        let validate = await form.value.validate();

        if (validate) {
            let indexError = null;
            const results = [];
            const requiredDataCP = requiredWard.value ? [...requiredData] : [...requiredData].filter(x => x !== 'shippingAddressWardId');

            if (onlyUpdate.value) {
                // data.products.forEach(product => {
                //     for (const property in product) {
                //         if (!product[property] && property !== 'specialType') {
                //             indexError = property + ' phần thông tin sản phẩm';
                //             break;
                //         }
                //     }
                // });

                for (const property in data) {
                    if (!data[property] && requiredDataCP.includes(property) && property !== 'shipPayer') {
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
                }

                results[0] = omit(data, omitKey);
            } else if (onlyCreate.value) {
                orders.value.push(data);

                orders.value.forEach((order, index) => {
                    order.products.forEach(product => {
                        for (const property in product) {
                            if (!product[property] && property !== 'specialType') {
                                indexError = index + ' phần thông tin sản phẩm';
                                break;
                            }
                        }
                    })
                    for (const property in order) {
                        if (!order[property] && requiredDataCP.includes(property) && property !== 'shipPayer') {
                            indexError = index;
                            break;
                        }
                    }
                });

                orders.value.forEach((order, index) => {
                    order.customerId = customerStorage.id;
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
                    }
                    order.takenTime = commonFunction.getDateTimeFormat(order.takenTime);
                    results[index] = omit(order, omitKey);
                });
            }

            if (indexError != null) {
                return message.error('Đơn hàng thứ ' + indexError + ' chưa được hoàn thiện');
            }

            await saveOrder(results);
            message.success('Lưu đơn hàng thành công');
            return router.push('/customer/orders');
        }
    }
};

// =================== Calculate shipping fee ======================
watch(
    () => data.shippingType,
    () => {
        shippingDetail.value.shippingType = data.shippingType;
    }
);

watch(
    () => data.shippingAddressProvinceId,
    () => {
        if (data.shippingAddressProvinceId) {
            shippingDetail.value.provinceId = data.shippingAddressProvinceId;
            calculateShippingFee(shippingDetail.value);
        }
    }
);


watch(
    () => data.warehouse.id,
    () => {
        if (data.warehouse) {
            shippingDetail.value.warehouseId = data.warehouse.id;
            calculateShippingFee(shippingDetail.value);
        }
    }
);

const calculateShippingFee = async (shippingDetailData) => {
    const {data} = await getShippingFee(shippingDetailData);
    shippingFee.value = data.shippingFee;
}

// =================== Process address data ========================
const provinceProcess = () => {
    const province = provinces.value.find(x => x.province_id === data.shippingAddressProvinceId);
    if (province) {
        data.shippingAddressProvince = province.province_name;
        handleGetDistrict(data.shippingAddressProvinceId).then(() => {
            districtProcess();
        });
    } else {
        resetDistrictData();
    }
}

const districtProcess = () => {
    const district = districts.value.find(x => x.district_id === data.shippingAddressDistrictId);
    if (district) {
        data.shippingAddressDistrict = district.district_name;
        handleGetWard(data.shippingAddressDistrictId).then(() => {
            wardProcess();
        });
    } else {
        resetWardData();
    }
}

const wardProcess = () => {
    const ward = wards.value.find(x => x.ward_id === data.shippingAddressWardId);
    if (ward) {
        data.shippingAddressWard = ward?.ward_name;
    }
}

const requiredWard = computed(() => data.shippingAddressProvinceId && data.shippingAddressDistrictId && wards.value.length);
// Handle Action
handleGetOrder();
watch(
    () => route.fullPath,
    () => handleGetOrder()
);
handleGetCoupon();
handleGetProvince();
handleGetWarehouse();
//
</script>
<style scoped>
.content {
    height: 250px;
    width: 200px;
    overflow-y: scroll;
    overflow-x: hidden;
}
</style>