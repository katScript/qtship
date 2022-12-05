import NavbarClient from "@/components/common/NavbarClient.vue";
import LocationPicker from "@/components/common/LocationPicker.vue";
import FooterClient from "@/components/common/FooterClient.vue";
import ToolbarRight from "@/components/common/ToolbarRight.vue";
import NotificationClient from "@/components/common/NotficationClient.vue";
import PopupNotify from "@/components/common/PopupNotify.vue";
import ActionLoading from "@/components/common/ActionLoading.vue";
import WarehouseData from "@/components/models/warehouse/warehouse-data";
import ProductData from "@/components/models/product/product-data";
import CustomerData from "@/components/models/customer/customer-data";

import moment from "moment";
import { commonFunction } from '@/scripts/ulti'
import axios from "axios";

export default {
    components: {
        NavbarClient,
        FooterClient,
        ToolbarRight,
        NotificationClient,
        PopupNotify,
        ActionLoading,
        LocationPicker
    },
    setup() {
        const warehouseModel = new WarehouseData();
        const productModel = new ProductData();
        const customerModel = new CustomerData();

        return {
            warehouseModel,
            productModel,
            customerModel
        };
    },
    data() {
        return {
            warehouseData: {
                name: "",
                customerCode: "",
                address: "",
                provinceId: "",
                province: "",
                districtId: "",
                district: "",
                wardId: "",
                ward: "",
                street: "",
                phone: "",
            },
            productData: {},
            listWarehouseByCustomer: [],
            listProductByCustomer: [],
            listProductByCustomerBk: [],
            conditionFilter: "",
            isLoading: false,
            isValid: 0,
            msgValidationFor: {
                warehouse: {
                    name: "",
                    address: "",
                    phone: "",
                },
                product: {
                    name: "",
                    sku: "",
                    basePrice: "",
                    publicPrice: "",
                    weight: "",
                    qty: "",
                    image: "",
                    description: "",
                }
            },
            headersWarehouse: [
                { text: "Tên kho", value: "name", sortable: true },
                { text: "Địa chỉ", value: "address" },
                { text: "TT liên hệ", value: "phone" },
                { text: "Chức năng", value: "btn-function" },
            ],
            headersProduct: [
                { text: "SKU", value: "data.sku", sortable: true },
                { text: "", value: "image", sortable: true },
                { text: "Tên Sản phẩm", value: "data.name", sortable: true },
                { text: "Số lượng", value: "data.qty", sortable: true },
                { text: "Trọng lượng(kg)", value: "data.weight", sortable: true },
                { text: "Giá gốc (VNĐ)", value: "data.basePrice", sortable: true },
                { text: "Giá bán (VNĐ)", value: "data.publicPrice", sortable: true },
                { text: "Ngày tạo", value: "data.createdAt", sortable: true },
                { text: "Chức năng", value: "btn-function" },
            ],
            idRequest: 0,
            productImg: "",
            urlImgProductUpload: "",
            loadingImgUpload: false,
            configRequestApi: {},
            isUpdateWarehouseAction: false,
            isUpdateProductAction: false,
            isShowNotify: false,
            typeNotify: "",
            typeComponent: "",
            dataNotify: {
                name: "",
                idRequest: 0,
                data: {},
            },
            customerData: {
                data: {
                    username: "",
                    password: "",
                    confirmPassword: "",
                    customer: {
                        fullName: "",
                        phone: "",
                        companyName: "",
                        email: ""
                    },
                    customerAddress: {
                        province: "",
                        provinceId: "",
                        district: "",
                        districtId: "",
                        ward: "",
                        wardId: "",
                        street: ""
                    },
                    forControl: {
                        holderName: "",
                        cardNumber: "",
                        bank: "",
                        address: ""
                    },
                },
                errors: {
                    isAccepted: "",
                    password: "",
                    confirmPassword: "",
                    customer: {
                        phone: "",
                        companyName: "",
                        email: ""
                    },
                    customerAddress: {
                        provinceId: "",
                        districtId: "",
                        wardId: "",
                        street: ""
                    },
                    forControl: {
                        holderName: "",
                        cardNumber: "",
                        bank: "",
                        address: ""
                    },
                }
            }
        };
    },

    // <data, methods...>

    mounted() {
        let auth = commonFunction.getCookies(commonFunction.userCookies.username),
            role = commonFunction.getCookies(commonFunction.userCookies.roles),
            token = commonFunction.getCookies(commonFunction.userCookies.token);

        if (auth == null && role !== "shipper") {
            commonFunction.redirect("/");
        }

        this.configRequestApi = {
            headers: { Authorization: "Bearer " + token },
        };

        this.customerModel.setData(
            JSON.parse(commonFunction.getCustomerStorage())
        );

        let customerId = this.customerModel.getData().id;

        this.idRequest = customerId;

        //warehouse
        axios.get(
                commonFunction.DOMAIN_URL +
                "v1/warehouse/all/customer/" +
                this.idRequest,
                this.configRequestApi
            )
            .then((response) => {
                this.listWarehouseByCustomer = response.data;
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
                    this.listProductByCustomerBk.push(product);
                });
            })
            .catch((e) => {
                console.log(e);
            });
    },
    watch: {
    },
    methods: {
        //warehouse
        createNewWarehouse: function () {
            this.warehouseModel.setData(this.warehouseData);
            if (this.warehouseModel.validate()) {
                this.isLoading = true;
                axios
                    .post(
                        commonFunction.DOMAIN_URL + "v1/warehouse/save",
                        {
                            customerId: this.idRequest,
                            data: this.warehouseData
                        },
                        this.configRequestApi
                    )
                    .then((response) => {
                        if (response.status === 200) {
                            this.isLoading = false;
                            alert("SUCCESS: Tạo mới thành công - " + response.data.message);
                            commonFunction.reloadPage();
                        } else {
                            this.isLoading = false;
                            alert("FAIL: Đăng kí không thành công! Vui lòng thử lại!");
                        }
                    })
                    .catch((e) => {
                        this.isLoading = false;
                        alert("ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!");
                        console.log(e);
                    });
            } else {
                this.msgValidationFor.warehouse = this.warehouseModel.error;
            }
        },
        updateWarehouse: function () {
            this.warehouseModel.setData(this.warehouseData);
            this.isValid = 0;
            if (this.warehouseModel.validate(this.warehouseData, this.isValid, this.msgValidationFor)) {
                this.isLoading = true;
                axios
                    .post(
                        commonFunction.DOMAIN_URL + "v1/warehouse/save",
                        {
                            customerId: this.idRequest,
                            data: this.warehouseData
                        },
                        this.configRequestApi
                    )
                    .then((response) => {
                        if (response.status == 200) {
                            this.isLoading = false;
                            alert("SUCCESS: Cập nhật thành công - " + response.data.message);
                            commonFunction.reloadPage();
                        } else {
                            this.isLoading = false;
                            alert("FAIL: Cập nhật không thành công! Vui lòng thử lại!");
                        }
                    })
                    .catch((e) => {
                        this.isLoading = false;
                        alert("ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!");
                        console.log(e);
                    });
            } else {
                this.msgValidationFor.product = this.warehouseModel.error;
            }
        },
        selectWareHouseForUpdate: function (item) {
            this.warehouseData = item;
            this.isUpdateWarehouseAction = true;
        },
        cancelUpdateWarehouse: function () {
            this.warehouseData = new WarehouseData().getData();
            this.isUpdateWarehouseAction = false;
        },
        deleteWareHouse: function (item) {
            this.typeNotify = commonFunction.typeNotify.Delete;
            this.typeComponent = "WAREHOUSE";
            this.dataNotify = {
                idRequest: this.idRequest,
                name: item.name,
                data: item,
            };
            this.isShowNotify = true;
        },
        updateTypeNotify: (type) => (this.typeNotify = type),
        closePopupNotify: function () {
            this.isShowNotify = false;
            this.typeNotify = "";
            this.typeComponent = "";
            this.dataNotify = {
                idRequest: 0,
                data: {},
            };
        },

        //product
        createNewProduct: function () {
            this.isValid = 0;
            if (this.productModel.validate(this.productData, this.isValid, this.msgValidationFor, this.$refs.productImgUpload.files[0])) {
                this.isLoading = true;
                let accesstoken_cookies = commonFunction.getCookies(commonFunction.userCookies.token);
                let formData = new FormData();
                if(this.productImg !== "") {
                    formData.append("file", this.productImg);
                }
                // formData.append("id", this.formDataProduct.id);
                formData.append("customerId", this.idRequest);
                formData.append("sku", this.productData.sku);
                formData.append("qty", this.productData.qty);
                formData.append("name", this.productData.name);
                formData.append("weight", this.productData.weight);
                formData.append("basePrice", this.productData.basePrice);
                formData.append("publicPrice", this.productData.publicPrice);
                formData.append("description", this.productData.description);
                axios
                    .post(commonFunction.DOMAIN_URL + "v1/product/save", formData, {
                        headers: {
                            Authorization: "Bearer " + accesstoken_cookies,
                            "Content-Type": "multipart/form-data",
                        },
                    })
                    .then((response) => {
                        if (response.status == 200) {
                            this.isLoading = false;
                            alert("SUCCESS: Tạo mới thành công - " + response.data.message);
                            commonFunction.reloadPage();
                        } else {
                            this.isLoading = false;
                            alert("FAIL: Đăng kí không thành công! Vui lòng thử lại!");
                        }
                    })
                    .catch((e) => {
                        this.isLoading = false;
                        alert("ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!");
                        console.log(e);
                    });
            }
        },
        updateProduct: function () {
            this.isValid = 0;
            if (this.productModel.validate(this.productData, this.isValid, this.msgValidationFor, this.$refs.productImgUpload.files[0])) {
                this.isLoading = true;
                let accesstoken_cookies = commonFunction.getCookies(commonFunction.userCookies.token);
                let formData = new FormData();
                if(this.productImg !== "") {
                    formData.append("file", this.productImg);
                }
                formData.append("id", this.productData.id);
                formData.append("customerId", this.idRequest);
                formData.append("sku", this.productData.sku);
                formData.append("qty", this.productData.qty);
                formData.append("name", this.productData.name);
                formData.append("weight", this.productData.weight);
                formData.append("basePrice", this.productData.basePrice);
                formData.append("publicPrice", this.productData.publicPrice);
                formData.append("description", this.productData.description);
                axios
                    .post(commonFunction.DOMAIN_URL + "v1/product/save", formData, {
                        headers: {
                            Authorization: "Bearer " + accesstoken_cookies,
                            "Content-Type": "multipart/form-data",
                        },
                    })
                    .then((response) => {
                        if (response.status == 200) {
                            this.isLoading = false;
                            alert("SUCCESS: Cập nhật thành công - " + response.data.message);
                            commonFunction.reloadPage();
                        } else {
                            this.isLoading = false;
                            alert("FAIL: Cập nhật không thành công! Vui lòng thử lại!");
                        }
                    })
                    .catch((e) => {
                        this.isLoading = false;
                        alert("ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!");
                        console.log(e);
                    });
            }
        },
        selectProductForUpdate: function (item) {
            let modelUpdate = new ProductData();
            modelUpdate.setData(item.data);
            this.productData = modelUpdate.getData();
            this.isUpdateProductAction = true;
        },
        cancelUpdateProduct: function () {
            this.productData = new ProductData().getData();
            this.isUpdateProductAction = false;
        },
        deleteProduct: function (item) {
            this.typeNotify = commonFunction.typeNotifyDelete;
            this.typeComponent = "PRODUCT";
            this.dataNotify = {
                name: item.data.name,
                idRequest: this.idRequest,
                data: item,
            };
            this.isShowNotify = true;
        },
        handleFileUpload() {
            this.productImg = this.$refs.productImgUpload.files[0];
            this.urlImgProductUpload = URL.createObjectURL(this.productImg);
            this.loadingImgUpload = false;
        },
        formatDateYYYYMMDD(value) {
            return moment(value).format("YYYY-MM-DD");
        },
        filterProduct() {
            let conditionString = this.conditionFilter.toString().toLowerCase();
            this.listProductByCustomer = this.listProductByCustomerBk;
            this.listProductByCustomer = this.listProductByCustomer.filter(
                p => p.data.sku.toLowerCase().includes(conditionString) || p.data.name.toLowerCase().includes(conditionString)
            )
        },
        updateAddress(data, type) {
            switch (type) {
                case "PROVINCE":
                    this.warehouseData.provinceId = data?.code;
                    this.warehouseData.province = data?.label;
                    break;
                case "DISTRICT":
                    this.warehouseData.districtId = data?.code;
                    this.warehouseData.district = data?.label;
                    break;
                case "WARD":
                    this.warehouseData.wardId = data?.code;
                    this.warehouseData.ward = data?.label;
                    break;
                case "STREET":
                    this.warehouseData.street = data;
                    break;
                default:
                    break;
            }
            this.warehouseData.address = this.warehouseData.street +", " + this.warehouseData.ward +", " + this.warehouseData.district +", " + this.warehouseData.province;
        },
    },
};