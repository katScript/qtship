import NavbarClient from "@/components/common/NavbarClient.vue";
import FooterClient from "@/components/common/FooterClient.vue";
import ToolbarRight from "@/components/common/ToolbarRight.vue";
import NotficationClient from "@/components/common/NotficationClient.vue";
import PopupNotify from "@/components/common/PopupNotify.vue";
import ActionLoading from "@/components/common/ActionLoading.vue";
import WarehouseData from "@/components/models/warehouse/warehouse-data";

import moment from "moment";
import { useCookies } from "vue3-cookies";
import { commonFunction } from '@/scripts/ulti'
import { debounce } from "vue-debounce";
import axios from "axios";

export default {
    components: {
        NavbarClient,
        FooterClient,
        ToolbarRight,
        NotficationClient,
        PopupNotify,
        ActionLoading,
    },
    setup() {
        const { cookies } = useCookies();
        const warehouseModel = new WarehouseData();

        return {
            cookies,
            warehouseModel
        };
    },
    data() {
        return {
            warehouseData: "  ",
            isLoading: false,
            isValid: 0,
            filterTime: "label",
            filterTimeDS: "label",
            classFilterTimeAbout: "d-none",
            classFilterTimeAboutDS: "d-none",
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
                },
            },
            headersWarehouse: [
                { text: "Tên kho", value: "data.name", sortable: true },
                { text: "Địa chỉ", value: "data.address" },
                { text: "TT liên hệ", value: "data.phone" },
                { text: "Chức năng", value: "btn-function" },
            ],
            headersProduct: [
                { text: "SKU", value: "sku", sortable: true },
                { text: "", value: "image", sortable: true },
                { text: "Tên Sản phẩm", value: "name", sortable: true },
                { text: "Số lượng", value: "qty", sortable: true },
                { text: "Trọng lượng(kg)", value: "weight", sortable: true },
                { text: "Giá gốc (VNĐ)", value: "basePrice", sortable: true },
                { text: "Giá bán (VNĐ)", value: "publicPrice", sortable: true },
                { text: "Ngày tạo", value: "createdAt", sortable: true },
                { text: "Chức năng", value: "btn-function" },
            ],
            listWarehouseByCustomer: [],
            listProductByCustomer: [],
            idRequest: 0,
            formDataProduct: {
                id: "",
                sku: "",
                qty: "",
                name: "",
                weight: "",
                basePrice: "",
                publicPrice: "",
                description: "",
                image: "",
            },
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
        };
    },

    // <data, methods...>

    mounted() {
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
                    let newarehouse = new WarehouseData();
                    newarehouse.setData(element);
                    this.listWarehouseByCustomer.push(newarehouse);
                });
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
                this.listProductByCustomer = respronseData;
            })
            .catch((e) => {
                console.log(e);
            });
    },
    watch: {
        filterTime: {
            handler: debounce(function () {
                return this.filterTime == "timeCOTimeAbout"
                    ? (this.classFilterTimeAbout = "d-contents")
                    : (this.classFilterTimeAbout = "d-none");
            }, 500),
        },
        filterTimeDS: {
            handler: function () {
                return this.filterTimeDS == "controlTimeAboutDS"
                    ? (this.classFilterTimeAboutDS = "d-contents")
                    : (this.classFilterTimeAboutDS = "d-none");
            },
        },
    },
    methods: {
        //warehouse
        createNewWarehouse: function () {
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
        updateWarehouse: function () {
            this.isValid = 0;
            this.validationFormWarehouse();
            if (this.isValid == 0) {
                this.isLoading = true;
                axios
                    .post(
                        commonFunction.DOMAIN_URL + "v1/warehouse/save",
                        {
                            id: this.formDataWarehouse.id,
                            customerId: this.idRequest,
                            name: this.formDataWarehouse.name,
                            address: this.formDataWarehouse.address,
                            phone: this.formDataWarehouse.phone,
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
            }
        },
        selectWareHouseForUpdate: function (item) {
            this.warehouseModel.setData(item);
            this.isUpdateWarehouseAction = true;
        },
        cancelUpdateWarehouse: function () {
            this.formDataWarehouse = {
                id: "",
                name: "",
                address: "",
                phone: "",
            };
            this.isUpdateWarehouseAction = false;
        },
        deleteWareHouse: function (item) {
            this.typeNotify = commonFunction.typeNotifyDelete;
            this.typeComponent = "WAREHOUSE";
            this.dataNotify = {
                name: item.name,
                idRequest: this.idRequest,
                data: {
                    id: item.id,
                },
            };
            this.isShowNotify = true;
        },
        updateTypeNotify: (type) => (this.typeNotify = type),
        closePopupNotify: function () {
            this.isShowNotify = false;
            this.typeNotify = "";
            this.typeComponent = "";
            this.dataNotify = {
                name: "",
                idRequest: 0,
                data: {},
            };
        },

        //product
        createNewProduct: function () {
            this.isValid = 0;
            this.validationFormProduct();
            if (this.isValid == 0) {
                this.isLoading = true;
                let accesstoken_cookies = this.cookies.get("accesstoken_cookies");
                let formData = new FormData();
                formData.append("file", this.productImg);
                // formData.append("id", this.formDataProduct.id);
                formData.append("customerId", this.idRequest);
                formData.append("sku", this.formDataProduct.sku);
                formData.append("qty", this.formDataProduct.qty);
                formData.append("name", this.formDataProduct.name);
                formData.append("weight", this.formDataProduct.weight);
                formData.append("basePrice", this.formDataProduct.basePrice);
                formData.append("publicPrice", this.formDataProduct.publicPrice);
                formData.append("description", this.formDataProduct.description);
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
            this.validationFormProduct();
            if (this.isValid == 0) {
                this.isLoading = true;
                let accesstoken_cookies = this.cookies.get("accesstoken_cookies");
                let formData = new FormData();
                formData.append("file", this.productImg);
                formData.append("id", this.formDataProduct.id);
                formData.append("customerId", this.idRequest);
                formData.append("sku", this.formDataProduct.sku);
                formData.append("qty", this.formDataProduct.qty);
                formData.append("name", this.formDataProduct.name);
                formData.append("weight", this.formDataProduct.weight);
                formData.append("basePrice", this.formDataProduct.basePrice);
                formData.append("publicPrice", this.formDataProduct.publicPrice);
                formData.append("description", this.formDataProduct.description);
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
            this.formDataProduct = {
                id: item.id,
                sku: item.sku,
                qty: item.qty,
                name: item.name,
                weight: item.weight,
                basePrice: item.basePrice,
                publicPrice: item.publicPrice,
                description: item.description,
                image: item.image,
            };
            this.isUpdateProductAction = true;
        },
        cancelUpdateProduct: function () {
            this.formDataProduct = {
                sku: "",
                qty: "",
                name: "",
                weight: "",
                basePrice: "",
                publicPrice: "",
                description: "",
            };
            this.isUpdateProductAction = false;
        },
        deleteProduct: function (item) {
            this.typeNotify = commonFunction.typeNotifyDelete;
            this.typeComponent = "PRODUCT";
            this.dataNotify = {
                name: item.name,
                idRequest: this.idRequest,
                data: {
                    id: item.id,
                },
            };
            this.isShowNotify = true;
        },
        handleFileUpload() {
            this.productImg = this.$refs.productImgUpload.files[0];
            this.urlImgProductUpload = URL.createObjectURL(this.productImg);
            this.loadingImgUpload = false;
        },
        validationFormProduct() {
            if (this.formDataProduct.name == "") {
                this.isValid += 1;
                this.msgValidationFor.product.name = "Vui lòng nhập tên sản phẩm!";
            }

            if (this.formDataProduct.sku == "") {
                this.isValid += 1;
                this.msgValidationFor.product.sku = "Vui lòng nhập mã SKU!";
            }

            if (this.formDataProduct.basePrice == "") {
                this.isValid += 1;
                this.msgValidationFor.product.basePrice =
                    "Vui lòng nhập giá gốc của sản phẩm!";
            }

            if (this.formDataProduct.publicPrice == "") {
                this.isValid += 1;
                this.msgValidationFor.product.publicPrice =
                    "Vui lòng nhập giá bán của sản phẩm!";
            }

            if (this.formDataProduct.weight == "") {
                this.isValid += 1;
                this.msgValidationFor.product.weight =
                    "Vui lòng nhập trọng lượng của sản phẩm!";
            }

            if (this.$refs.productImgUpload.files[0] == null && this.formDataProduct.image == "") {
                this.isValid += 1;
                this.msgValidationFor.product.image =
                    "Vui lòng upload ảnh cho sản phẩm!";
            }
        },
        formatDateYYYYMMDD(value) {
            return moment(value).format("YYYY-MM-DD");
        },
    },
};