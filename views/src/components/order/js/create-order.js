import NavbarClient from "@/components/common/NavbarClient.vue";
import FooterClient from "@/components/common/FooterClient.vue";
import ToolbarRight from "@/components/common/ToolbarRight.vue";
import NotficationClient from "@/components/common/NotficationClient.vue";
import LocationPicker from "@/components/common/LocationPicker.vue";
import ActionLoading from "@/components/common/ActionLoading.vue";
import OrderData from "@/components/models/order/order-data";
import OrderItemData from "@/components/models/order/order-item-data";
import WarehouseData from "@/components/models/warehouse/warehouse-data";
import ProductData from "@/components/models/product/product-data";
import ShippingData from "@/components/models/shipping/shipping-data";
import CustomerData from "@/components/models/customer/customer-data";

import moment from "moment";
import { commonFunction } from "@/scripts/ulti";
import { useCookies } from "vue3-cookies";
import axios from "axios";
// import $ from "jquery"

export default {
    components: {
        NavbarClient,
        FooterClient,
        ToolbarRight,
        NotficationClient,
        LocationPicker,
        ActionLoading,
    },

    setup() {
        const { cookies } = useCookies();
        const orderModel = new OrderData();
        const warehouseModel = new WarehouseData();
        const productModel = new ProductData();
        const orderItemModel = new OrderItemData();
        const shippingModel = new ShippingData();
        const customerModel = new CustomerData();
        return {
            cookies,
            orderModel,
            warehouseModel,
            productModel,
            orderItemModel,
            shippingModel,
            customerModel
        };
    },
    data() {
        return {
            orderData: {},
            warehouseData: {},
            productData: {},
            orderItemModel: {},
            shippingData: {},
            customerData: {},

            isLoading: false,
            isAccepted: false,
            isValid: 0,
            isDisplayListVourcer: false,
            idRequest: "",
            idOrderUpdateQuery: "",
            addressOrderUpdated: "",
            numberProduct: 3,
            productSelected: [
                {data: { sku: "default", name: "- Chọn sản phẩm -", weight: "", publicPrice: "", qty: "" }},
            ],
            listProducts: [],
            customerAddress: {
                province: "",
                provinceId: "",
                district: "",
                districtId: "",
                ward: "",
                wardId: "",
                street: "",
            },
            msgValidationFor: {
                shippingAddress: {
                    phone: "",
                    name: ""
                },
                shippingType: "",
                isSelectedTypeLH: "",
                warehouseId: "",
                products: "",
                returnCode: "",
                customerAddress: {
                    provinceId: "",
                    districtId: "",
                    wardId: "",
                    street: "",
                },
            },
            isSelectedTypeLH: "",
            configRequestApi: {},
            listWarehouseByCustomer: [],
            listProductByCustomer: [],
            listOrderByCustomer: [],
            shippingDateTime: "",
            formDataOrder: {
                id: "",
                customerId: "",
                senderName: "",
                senderPhone: "",
                senderAddress: "",
                note: "",
                feedback: "",
                status: "",
                notification: "",
                shippingFee: true,
                coupon: "",
                warehouseId: "",
                shippingType: "",
                shippingTime: "",
                returnCode: "",
                orderItem: [
                    {
                        shippingAddress: {
                            name: "",
                            phone: "",
                            province: "",
                            district: "",
                            ward: "",
                            street: "",
                            provinceId: "",
                            districtId: "",
                            wardId: "",
                        },
                        products: [{ productId: "", qty: "" }],
                    },
                ],
            },
            listTypeReturnOrder: {},
            listTypeShipping: {},
        };
    },

    // <data, methods...>

    mounted() {
        this.orderData = this.orderModel.getData();
        this.listTypeShipping = commonFunction.typeShipping;
        this.listTypeReturnOrder = commonFunction.typeReturnOrder;

        let auth = commonFunction.getCookies("authenication_cookies"),
            role = commonFunction.getCookies("authenrole_cookies"),
            id = commonFunction.getCookies("idrequest_cookies"),
            token = commonFunction.getCookies("accesstoken_cookies");

        if (auth == null && role !== "shipper") {
            commonFunction.redirect("/");
        }

        this.configRequestApi = {
            headers: { Authorization: "Bearer " + token },
        };
        this.idRequest = id;


        commonFunction.disableInput(".product-select-weight");
        commonFunction.disableInput(".product-select-public-price");

        //warehouse
        axios
            .get(
                commonFunction.DOMAIN_URL +
                "v1/warehouse/all/customer/" +
                this.idRequest,
                this.configRequestApi
            )
            .then((response) => {
                let respronseData = response.data;
                respronseData.forEach(element => {
                    let newWarehouseModel = new WarehouseData();
                    newWarehouseModel.setData(element);
                    this.listWarehouseByCustomer.push(newWarehouseModel);
                });
            })
            .catch((e) => {
                console.log(e);
            });

        //customer detail
        axios
            .get(
                commonFunction.DOMAIN_URL + "v1/customer/detail/" + this.idRequest,
                this.configRequestApi
            )
            .then((response) => {
                let respronseData = response.data;
                this.customerModel.setData(respronseData);
                this.customerData = this.customerModel.getData();
            })
            .catch((e) => {
                console.log(e);
            });

        //product
        axios
            .get(
                commonFunction.DOMAIN_URL + "v1/product/customer/" + this.idRequest,
                this.configRequestApi
            )
            .then((response) => {
                let respronseData = response.data;
                respronseData.forEach(element => {
                    let product = new ProductData();
                    product.setData(element);
                    this.listProductByCustomer.push(product);
                });
            })
            .catch((e) => {
                console.log(e);
            });

        //updated order
        this.idOrderUpdateQuery = commonFunction.currentURL().split("#_")[1];
        if (this.idOrderUpdateQuery) {
            axios
                .get(
                    commonFunction.DOMAIN_URL + "v1/order/all/customer/" + this.idRequest,
                    this.configRequestApi
                )
                .then((response) => {
                    let respronseData = response.data;
                    this.listOrderByCustomer = respronseData;
                    this.formDataOrder = this.listOrderByCustomer.filter(
                        (item) => item.id == this.idOrderUpdateQuery
                    )[0];
                    this.productSelected = this.formDataOrder.orderItem[0].products;

                    this.addressOrderUpdated =
                        this.formDataOrder.orderItem[0].shippingAddress.street +
                        ", " +
                        this.formDataOrder.orderItem[0].shippingAddress.ward +
                        ", " +
                        this.formDataOrder.orderItem[0].shippingAddress.district +
                        ", " +
                        this.formDataOrder.orderItem[0].shippingAddress.province;
                })
                .catch((e) => {
                    console.log(e);
                });
        }
    },
    updated() {
        // this.formDataOrder.priceTotal = this.productSelected.reduce(function (sum, p) {
        //     return sum + p.publicPrice;
        // }, 0);
        // this.formDataOrder.weightTotal = this.productSelected.reduce(function (sum, p) {
        //     return sum + p.weight;
        // }, 0);
    },
    watch: {
        shippingDateTime: {
            handler: function () {
                this.autoSetOrderShippingTime();
            }
        }
    },
    methods: {
        updateReceiverAddress(data, type) {
            switch (type) {
                case "PROVINCE":
                    this.shippingData.provinceId = data.code;
                    this.shippingData.province = data.label;
                    break;
                case "DISTRICT":
                    this.shippingData.districtId = data.code;
                    this.shippingData.district = data.label;
                    break;
                case "WARD":
                    this.shippingData.wardId = data.code;
                    this.shippingData.ward = data.label;
                    break;
                case "STREET":
                    this.shippingData.street = data;
                    break;
                default:
                    break;
            }
        },

        addItemProductList: function (index) {
            console.log(index);

            if (this.productSelected[index].data.sku != "default") {
                this.productSelected.push({
                    data: {
                        sku: "default",
                        weight: "",
                        publicPrice: "",
                        qty: "",
                    }
                });
            }
        },
        removeItemProductList: function (product) {
            if (product) {
                this.productSelected = this.productSelected.filter(
                    (p) => p.data.sku !== product.data.sku
                );
            }
        },
        generateCodeToText: function (text) {
            return commonFunction.generateCodeToText(text);
        },
        applySaleCode: function (code) {
            this.formDataOrder.coupon = code;
        },
        //order
        createOrder: function () {
            let objOrderItem = [{
                shippingAddress: this.shippingData,
                products: []
            }]

            //mapping productSelected to products
            this.productSelected.forEach((element, index) => {
                let objProduct = {
                    qty: element[index].data.qty,
                    product: {
                        id: element[index].data.id
                    }
                }
                objOrderItem.products.push(objProduct);
             //mapping shippingData to product.orderItem
            
            });

            this.order.orderItem[0].push(objOrderItem)

            //validation
            this.isValid = 0;
            this.validationForm();
            if (this.isValid == 0) {
                this.isLoading = true;

                let bodyRequest = {
                    customerId: this.idRequest,
                    senderName: this.formDataOrder.senderName,
                    senderPhone: this.formDataOrder.senderPhone,
                    senderAddress: this.formDataOrder.senderAddress,
                    note: this.formDataOrder.note,
                    status: "default",
                    feedback: this.formDataOrder.feedback,
                    notification: false,
                    shippingFee: this.formDataOrder.shippingFee,
                    coupon: this.formDataOrder.coupon,
                    warehouseId: 1,
                    shippingType: this.formDataOrder.shippingType,
                    shippingDate: moment(new Date()).format("YYYY-MM-DD HH:MM:SS"),
                    returnCode: this.formDataOrder.returnCode,
                    orderItem: [
                        {
                            shippingAddress: this.formDataOrder.orderItem[0].shippingAddress,
                            products: this.formDataOrder.orderItem[0].products,
                        },
                    ],
                };

                if (this.isSelectedTypeLH == "GHTBC") {
                    this.orderData.shippingTime = "";
                }

                console.log(bodyRequest);
                axios
                    .post(
                        commonFunction.DOMAIN_URL + "v1/order/save",
                        bodyRequest,
                        this.configRequestApi
                    )
                    .then((response) => {
                        if (response.status == 200) {
                            this.isLoading = false;
                            alert("SUCCESS: Tạo mới thành công - " + response.data.message);
                            commonFunction.reloadPage();
                        } else {
                            this.isLoading = false;
                            alert("FAIL: Tạo mới không thành công! Vui lòng thử lại!");
                            commonFunction.reloadPage();
                        }
                    })
                    .catch((e) => {
                        this.isLoading = false;
                        alert("ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!");
                        console.log(e);
                        commonFunction.reloadPage();
                    });
            }
        },

        //update
        updateOrder: function () {
            this.isLoading = true;

            //mapping id product
            this.productSelected.forEach((element, index) => {
                this.formDataOrder.orderItem[0].products[index].productId = element.id;
            });
            let bodyRequestproducts = [];
            this.formDataOrder.orderItem[0].products.forEach((element, index) => {
                if (element.productId && element.qty) {
                    bodyRequestproducts.push({
                        productId:
                            this.formDataOrder.orderItem[0].products[index].productId,
                        qty: this.formDataOrder.orderItem[0].products[index].qty,
                    });
                }
            });
            let bodyRequest = {
                id: this.formDataOrder.id.replaceAll("0", ""),
                customerId: this.formDataOrder.customerId
                    .replace(/[^0-9\\.]+/g, "")
                    .replaceAll("0", ""),
                senderName: this.formDataOrder.senderName,
                senderPhone: this.formDataOrder.senderPhone,
                senderAddress: this.formDataOrder.senderAddress,
                note: this.formDataOrder.note,
                status: "default",
                feedback: this.formDataOrder.feedback,
                notification: false,
                shippingFee: this.formDataOrder.shippingFee,
                coupon: this.formDataOrder.coupon,
                warehouseId: 1,
                shippingType: this.formDataOrder.shippingType,
                shippingDate: moment(new Date()).format("YYYY-MM-DD HH:MM:SS"),
                returnCode: this.formDataOrder.returnCode,
                orderItem: [
                    {
                        shippingAddress: this.formDataOrder.orderItem[0].shippingAddress,
                        products: bodyRequestproducts,
                    },
                ],
            };

            if (this.isSelectedTypeLH == "GHTBC") {
                this.orderData.shippingTime = "";
            }
            console.log(bodyRequest);
            axios
                .post(
                    commonFunction.DOMAIN_URL + "v1/order/save",
                    bodyRequest,
                    this.configRequestApi
                )
                .then((response) => {
                    if (response.status == 200) {
                        this.isLoading = false;
                        alert("SUCCESS: Cập nhật thành công - " + response.data.message);
                        commonFunction.redirect("/client/orders");
                    } else {
                        this.isLoading = false;
                        alert("FAIL: Cập nhật không thành công! Vui lòng thử lại!");
                        commonFunction.redirect("/client/orders");
                    }
                })
                .catch((e) => {
                    this.isLoading = false;
                    alert("ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!");
                    console.log(e);
                    commonFunction.redirect("/client/orders");
                });
        },
        validationForm() {
            if (this.formDataOrder.orderItem[0].shippingAddress.phone == "") {
                this.msgValidationFor.shippingAddress.phone = "Vui lòng điền thông tin số điện thoại!";
                this.isValid += 1;
            }

            if (!commonFunction.regexStringValidate(this.formDataOrder.orderItem[0].shippingAddress.phone, "PHONE")) {
                this.msgValidationFor.shippingAddress.phone = "Số điện thoại có tối đa 11 chữ số và không tồn tại chữ cái!";
                this.isValid += 1;
            }

            if (this.formDataOrder.orderItem[0].shippingAddress.name == "") {
                this.msgValidationFor.shippingAddress.name = "Vui lòng điền thông tin người nhận!";
                this.isValid += 1;
            }

            if (this.formDataOrder.shippingType == "") {
                this.msgValidationFor.shippingType = "Vui lòng chọn phương thức giao hàng!";
                this.isValid += 1;
            }

            if (this.isSelectedTypeLH == "") {
                this.msgValidationFor.isSelectedTypeLH = "Vui lòng chọn hình thức lấy hàng!";
                this.isValid += 1;
            }

            if (this.isSelectedTypeLH == 'LHTN' && this.formDataOrder.warehouseId == "") {
                this.msgValidationFor.warehouseId = "Vui lòng chọn địa điểm và thời gian lấy hàng!";
                this.isValid += 1;
            }

            if (this.formDataOrder.orderItem[0].products[0].productId == "" || this.formDataOrder.orderItem[0].products[0].qty == "") {
                this.msgValidationFor.products = "Vui lòng chọn sản phẩm và điền số lượng!";
                this.isValid += 1;
            }

            if (this.customerAddress.provinceId == "") {
                this.isValid += 1;
                this.msgValidationFor.customerAddress.provinceId =
                    "Vui lòng chọn Tỉnh/Thành phố!";
            }
            if (this.customerAddress.districtId == "") {
                this.isValid += 1;
                this.msgValidationFor.customerAddress.districtId =
                    "Vui lòng chọn Quận/Huyện!";
            }
            if (this.customerAddress.wardId == "") {
                if (this.customerAddress.districtId != 498 && this.customerAddress.districtId != 576)
                    this.msgValidationFor.customerAddress.wardId =
                        "Vui lòng chọn Phường/Xã!";
            }
            if (this.customerAddress.street == "") {
                this.isValid += 1;
                this.msgValidationFor.customerAddress.street =
                    "Vui lòng điền địa chỉ chi tiết!";
            }

            if (this.formDataOrder.returnCode == "") {
                this.isValid += 1;
                this.msgValidationFor.returnCode = "Vui lòng chọn Phương thức hoàn hàng!"
            }
            if (!this.isAccepted) {
                this.isValid += 1;
                alert("Vui lòng đọc và đồng ý với Chính sách bảo mật thông tin!");
            }
        },
        autoSetOrderShippingTime() {
            this.orderData.shippingTime = moment(this.shippingDateTime).format("YYYY-MM-DD HH:mm:ss");
        }
    },
};