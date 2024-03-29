import NavbarClient from "@/components/common/NavbarClient.vue";
import FooterClient from "@/components/common/FooterClient.vue";
import ToolbarRight from "@/components/common/ToolbarRight.vue";
import NotificationClient from "@/components/common/NotficationClient.vue";
import LocationPicker from "@/components/common/LocationPicker.vue";
import ActionLoading from "@/components/common/ActionLoading.vue";
import OrderData from "@/components/models/order/order-data";
import OrderItemData from "@/components/models/order/order-item-data";
import WarehouseData from "@/components/models/warehouse/warehouse-data";
import ProductData from "@/components/models/product/product-data";
import ShippingData from "@/components/models/shipping/shipping-data";
import CustomerData from "@/components/models/customer/customer-data";
import CouponData from "@/components/models/sales/coupon-data";
import PackageData from "@/components/models/product/package-data";
import CreateOrder from "@/pages/admin/order/CreateOrder.vue";

import moment from "moment";
import { commonFunction } from "@/scripts/ulti";
import axios from "axios";

export default {
    components: {
        NavbarClient,
        FooterClient,
        ToolbarRight,
        NotificationClient,
        LocationPicker,
        ActionLoading,
        CreateOrder
    },

    setup() {
        let orderModel = new OrderData(),
            warehouseModel = new WarehouseData(),
            orderItemModel = new OrderItemData(),
            shippingModel = new ShippingData(),
            customerModel = new CustomerData(),
            couponModel = new CouponData();

        let auth = commonFunction.getCookies(commonFunction.userCookies.username),
            role = commonFunction.getCookies(commonFunction.userCookies.roles),
            id = commonFunction.getCookies(commonFunction.userCookies.id);

        return {
            auth,
            role,
            id,
            orderModel,
            warehouseModel,
            orderItemModel,
            shippingModel,
            customerModel,
            couponModel
        };
    },
    data() {
        return {
            // inputData
            order: this.orderModel.getData(),
            orderError: this.orderModel.getErrors(),
            // input data
            orderData: {
                data: {
                    senderAddress: "",
                    senderName: "",
                    senderPhone: "",
                    coupon: "",
                    note: "",
                    shippingFee: false,
                    shippingTime: "",
                    shippingType: "",
                    returnCode: ""
                },
                errors: {}
            },
            warehouseData: {
                data: {
                    id: null,
                },
                errors: {}
            },
            shippingData: {
                data: {
                    name: null,
                    phone: null,
                    provinceId: null,
                    province: null,
                    districtId: null,
                    district: null,
                    wardId: null,
                    ward: null,
                    street: null
                },
                errors: {}
            },

            // data preset
            warehouseList: [],
            productList: [],
            packageList: {},
            listTypeReturnOrder: {},
            listTypeShipping: {},
            packageLocation: commonFunction.packageLocationConfig.POST_OFFICES.value,
            packageLocationOption: commonFunction.packageLocationConfig,
            productSelected: {},
            packageQtyInput: 0,
            couponList: [],
            isDisplayListVoucher: false,
            isLoading: false,
            isAccepted: false,
            isValid: 0,
        };
    },

    // <data, methods...>

    mounted() {
        this.listTypeShipping = commonFunction.typeShipping;
        this.listTypeReturnOrder = commonFunction.typeReturnOrder;

        this.customerModel.setData(
            JSON.parse(commonFunction.getCustomerStorage())
        );

        let customerId = this.customerModel.getData().id;

        if (this.auth == null || this.role !== "customer") {
            commonFunction.redirect("/");
        }
        this.orderData.data.shippingTime = null;
        //warehouse
        axios.get(
            commonFunction.DOMAIN_URL +
            "v1/warehouse/all/customer/" +
            customerId,
            commonFunction.configApi()
        ).then((response) => {
            // handle not found
            this.getListWarehouse(response.data);
        }).catch((e) => {
            console.log(e);
        });

        //coupon
        axios.get(
            commonFunction.DOMAIN_URL +
            "v1/coupon/active/all",
            commonFunction.configApi()
        ).then((response) => {
            // handle not found
            let newCoupon = new CouponData();
            response.data.forEach(c => {
                newCoupon.setData(c);
                this.couponList.push(newCoupon);
            });
        }).catch((e) => {
            console.log(e);
        });

        //product
        axios
            .get(
                commonFunction.DOMAIN_URL + "v1/product/customer/" + customerId,
                commonFunction.configApi()
            ).then((response) => {
                this.getListProduct(response.data);
            }).catch((e) => {
                console.log(e);
            });

        //updated order
        let orderId = this.$route.params.id;
        if (orderId) {
            axios
                .get(
                    commonFunction.DOMAIN_URL + "v1/order/detail/" + orderId,
                    commonFunction.configApi()
                )
                .then((response) => {
                    this.orderModel.setData(response.data);
                    this.processOrderData();
                })
                .catch((e) => {
                    console.log(e);
                });
        } else {
            this.orderModel.getData().customerId = customerId;
            this.orderData.data = this.orderModel.getData();
            this.setInfoSender();
        }
    },
    watch: {
        shippingDateTime: {
            handler: function () {
                this.autoSetOrderShippingTime();
            }
        },
    },
    methods: {
        processOrderData: function () {
            this.$refs.locationPicker.updateSelectData(this.order.shippingAddress);

            this.packageLocation = this.order.warehouse.id ?
                commonFunction.packageLocationConfig.HOUSE_ADDRESS.value :
                commonFunction.packageLocationConfig.POST_OFFICES.value;

            this.$refs.locationPicker.updateSelectData(this.order.shippingAddress);

            this.order.products.forEach(e => {
                let newPackage = new PackageData();
                newPackage.setData(e);

                this.packageList[e.product.sku] = newPackage.getData();
            });
        },
        getListWarehouse: function (data) {
            data.forEach(element => {
                let wm = new WarehouseData();
                wm.setData(element);
                this.warehouseList.push(wm);
            });
        },
        getListProduct: function (data) {
            data.forEach(element => {
                let pm = new ProductData();
                pm.setData(element);
                this.productList.push(pm.getData());
            });
        },

        updateAddress(data, type) {
            switch (type) {
                case "PROVINCE":
                    this.order.shippingAddress.provinceId = data?.code;
                    this.order.shippingAddress.province = data?.label;
                    break;
                case "DISTRICT":
                    this.order.shippingAddress.districtId = data?.code;
                    this.order.shippingAddress.district = data?.label;
                    break;
                case "WARD":
                    this.order.shippingAddress.wardId = data?.code;
                    this.order.shippingAddress.ward = data?.label;
                    break;
                case "STREET":
                    this.order.shippingAddress.street = data;
                    break;
                default:
                    break;
            }
        },
        getShippingType: function (key) {
            return commonFunction.shippingType[key];
        },
        addProductToPackage: function () {
            let newPackage = new PackageData();

            newPackage.setData(
                {
                    product: this.productSelected,
                    qty: this.productSelected.qty,
                    name: this.productSelected.name,
                    price: this.productSelected.publicPrice,
                    weight: this.productSelected.weight,
                    image: this.productSelected.image
                }
            );

            this.packageList[this.productSelected.sku] = newPackage.getData();
            this.productSelected = {};
        },
        removeProductInPackage: function (sku) {
            delete this.packageList[sku];
        },
        generateCodeToText: function (text) {
            return commonFunction.generateCodeToText(text);
        },
        applySaleCode: function (code) {
            this.order.coupon = code;
        },
        autoSetOrderShippingTime() {
            this.order.shippingTime = moment(this.shippingDateTime).format("YYYY-MM-DD HH:mm:ss");
        },
        saveOrder: function () {
            let orderData = [this.prepareOrderData()];
            console.log(orderData);
            // axios
            //     .post(
            //         commonFunction.DOMAIN_URL + "v1/order/save",
            //         orderData,
            //         commonFunction.configApi()
            //     )
            //     .then((response) => {
            //         if (response.status === 200) {
            //             this.isLoading = false;
            //             alert("SUCCESS: Tạo mới thành công - " + response.data.message);
            //             commonFunction.redirect("/customer/orders");
            //         } else {
            //             this.isLoading = false;
            //             alert("FAIL: Tạo mới không thành công! Vui lòng thử lại!");
            //             commonFunction.redirect("/customer/orders");
            //         }
            //     })
            //     .catch((e) => {
            //         this.isLoading = false;
            //         alert("ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!");
            //         console.log(e);
            //         commonFunction.redirect("/customer/orders");
            //     });
        },
        prepareOrderData: function () {
            let packageItems = [];
            Object.values(this.packageList).forEach(p => {
                let packageModel = new PackageData(),
                    product = new ProductData();

                product.setData(p.product);

                packageModel.setData(p);
                packageModel.setProduct(product.getData());

                packageItems.push(packageModel.getData());
            });

            let warehouse;
            if (this.packageLocation === this.packageLocationOption.HOUSE_ADDRESS.value) {
                let warehouseModel = new WarehouseData();
                warehouseModel.setData(this.order.warehouse);
                warehouse = warehouseModel.getData();
            } else {
                warehouse = {};
                this.orderModel.getData().shippingTime = "";
            }

            let shippingAddress = new ShippingData();
            shippingAddress.setData(this.order.shippingAddress);

            let orderModel = new OrderData();
            orderModel.setData(this.order);
            orderModel.setWarehouse(warehouse);
            orderModel.setProduct(packageItems);
            orderModel.setShippingAddress(shippingAddress.getData());

            console.log(orderModel.getData());

            return orderModel.getData();
        },
        setInfoSender: function () {
            this.orderData.data.senderAddress = this.customerModel.getData().addressSet[0].street
                + ", "
                + this.customerModel.getData().addressSet[0].ward
                + ", "
                + this.customerModel.getData().addressSet[0].district
                + ", "
                + this.customerModel.getData().addressSet[0].province;
            this.orderData.data.senderPhone = this.customerModel.getData().phone;
            this.orderData.data.senderName = this.customerModel.getData().fullName;
        }
    },
};