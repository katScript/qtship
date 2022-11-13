
import NavbarClient from "@/components/common/NavbarClient.vue";
import FooterClient from "@/components/common/FooterClient.vue";
import ToolbarRight from "@/components/common/ToolbarRight.vue";
import NotficationClient from "@/components/common/NotficationClient.vue";
import ActionLoading from "@/components/common/ActionLoading.vue";
import PopupNotify from "@/components/common/PopupNotify.vue";
import BillOrder from "@/components/order/OrderDetail.vue";
import OrderData from "@/components/models/order/order-data";
import CustomerData from "@/components/models/customer/customer-data";

import moment from "moment";
import { useCookies } from "vue3-cookies";
import { commonFunction } from "@/scripts/ulti";
import axios from "axios";

export default {
    components: {
        NavbarClient,
        FooterClient,
        ToolbarRight,
        NotficationClient,
        ActionLoading,
        PopupNotify,
        BillOrder,
    },
    setup() {
        const { cookies } = useCookies();
        const orderModel = new OrderData();
        const customerModel = new CustomerData();

        return {
            cookies,
            orderModel,
            customerModel
        };
    },

    data() {
        return {
            orderData: {},
            listOrderByCustomer: [],
            listOrderByCustomerBk: [],
            filterTime: "",
            // filterTimeDS: "",
            classFilterTimeAbout: "d-none",
            // classFilterTimeAboutDS: "d-none",
            typeNotify: "",
            typeComponent: "",
            isShowNotify: false,
            countOrder: 0,
            idRequest: "",
            isLoading: false,
            configRequestApi: {},
            headersOrder: [
                { text: "Mã ĐH", value: "orderCode", sortable: true },
                { text: "Tên người nhận", value: "name-receiver", sortable: true },
                { text: "SĐT nhận", value: "phone-receiver" },
                { text: "Trạng thái ĐH", value: "status", sortable: true },
                { text: "Dịch vụ giao hàng", value: "shippingType", sortable: true },
                { text: "Tổng giá trị ĐH (VNĐ)", value: "subtotal", sortable: true },
                { text: "Ngày tạo đơn", value: "createdAt", sortable: true },
                { text: "Chức năng", value: "btn-function-order" },
            ],
            dataNotify: {
                name: "",
                idRequest: 0,
                data: {},
            },
            listTypeShipping: {},
            filterOderCondition: {
                orderId: "",
                phoneReceiver: "",
                orderStatus: "",
                timeFrom: "",
                timeTo: "",
                // timeDSFrom: "",
                // timeDSTo: "",
                shippingType: "",
            },
        };
    },

    // <data, methods...>

    mounted() {
        let auth = commonFunction.getCookies(commonFunction.userCookies.username),
            role = commonFunction.getCookies(commonFunction.userCookies.roles),
            token = commonFunction.getCookies(commonFunction.userCookies.token);

        if (auth == null && role !== "customer") {
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

        axios
            .get(
                commonFunction.DOMAIN_URL + "v1/order/all/customer/" + this.idRequest,
                this.configRequestApi
            )
            .then((response) => {
                let respronseData = response.data;
                respronseData.forEach(element => {
                    let newOrderModel = new OrderData();
                    newOrderModel.setData(element);
                    this.listOrderByCustomer.push(newOrderModel.getData());
                    this.listOrderByCustomerBk.push(newOrderModel.getData());
                });
                this.countOrder = this.listOrderByCustomer.length;
            })
            .catch((e) => {
                console.log(e);
            });
    },
    watch: {
        filterTime: {
            handler: function () {
                return this.filterTime == "timeCOTimeAbout"
                    ? (this.classFilterTimeAbout = "d-contents")
                    : (this.classFilterTimeAbout = "d-none");
            },
        },
        // filterTimeDS: {
        //   handler: function () {
        //     return this.filterTimeDS == "controlTimeAboutDS"
        //       ? (this.classFilterTimeAboutDS = "d-contents")
        //       : (this.classFilterTimeAboutDS = "d-none");
        //   },
        // },
    },
    methods: {
        resetFormSearch: function () {
            this.filterTime = "";
            // this.filterTimeDS = "";
            this.classFilterTimeAbout = "d-none";
            // this.classFilterTimeAboutDS = "d-none";
            this.filterOderCondition = {
                orderId: "",
                phoneReceiver: "",
                orderStatus: "",
                timeFrom: "",
                timeTo: "",
                // timeDSFrom: "",
                // timeDSTo: "",
                shippingType: "",
            };
            this.listOrderByCustomer = this.listOrderByCustomerBk;
        },
        cancelOrder: function (item) {
            this.typeNotify = commonFunction.typeNotifyCancel;
            this.typeComponent = "ORDER";
            this.dataNotify = {
                name: item.orderCode,
                idRequest: this.idRequest,
                data: item,
            };
            this.isShowNotify = true;
            console.log(item);
        },
        genUrlUpdateOrder(item) {
            return "/customer/orders/save#" + item.id;
        },
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
        updateTypeNotify: (type) => (this.typeNotify = type),
        generateCodeToText: function (text) {
            return commonFunction.generateCodeToText(text);
        },
        filterOrder: function () {
            this.listOrderByCustomer = this.listOrderByCustomerBk;
            this.listOrderByCustomer = this.listOrderByCustomer.filter(
                (o) =>
                    o.orderCode ==
                    (this.filterOderCondition.orderId != ""
                        ? this.filterOderCondition.orderId
                        : o.orderCode) &&

                    o.orderItem[0].shippingAddress.phone ==
                    (this.filterOderCondition.phoneReceiver != ""
                        ? this.filterOderCondition.phoneReceiver
                        : o.orderItem[0].shippingAddress.phone) &&

                    o.status ==
                    (this.filterOderCondition.orderStatus != ""
                        ? this.filterOderCondition.orderStatus
                        : o.status) &&

                    o.shippingType ==
                    (this.filterOderCondition.shippingType != ""
                        ? this.filterOderCondition.shippingType
                        : o.shippingType)
            );
            this.conditionFilterByTimeCreate(this.listOrderByCustomer);
            // this.conditionFilterByTimeDS(this.listOrderByCustomer);
        },
        // moment().subtract(1, "weeks").format("YYYY-MM-DD HH:MM:SS");
        conditionFilterByTimeCreate(listOrderByCustomer) {
            if (this.filterTime == "timeCOToday") {
                this.listOrderByCustomer = listOrderByCustomer.filter(
                    (o) =>
                        this.fomartDateYYYYMMDD(o.createdAt) ==
                        this.fomartDateYYYYMMDD(new Date())
                );
            } else if (this.filterTime == "timeCO1week") {
                this.listOrderByCustomer = listOrderByCustomer.filter(
                    (o) =>
                        this.fomartDateYYYYMMDD(o.createdAt) >=
                        this.fomartDateYYYYMMDD(moment().subtract(1, "weeks"))
                );
            } else if (this.filterTime == "timeCO1month") {
                this.listOrderByCustomer = listOrderByCustomer.filter(
                    (o) =>
                        this.fomartDateYYYYMMDD(o.createdAt) >=
                        this.fomartDateYYYYMMDD(moment().subtract(1, "months"))
                );
            } else if (this.filterTime == "timeCO1year") {
                this.listOrderByCustomer = listOrderByCustomer.filter(
                    (o) =>
                        this.fomartDateYYYYMMDD(o.createdAt) >=
                        this.fomartDateYYYYMMDD(moment().subtract(1, "years"))
                );
            } else if (this.filterTime == "timeCOAll") {
                console.log(this.filterTime);
            } else if (this.filterTime == "timeCOTimeAbout") {
                this.listOrderByCustomer = listOrderByCustomer.filter(
                    (o) =>
                        this.fomartDateYYYYMMDD(o.createdAt) >=
                        this.fomartDateYYYYMMDD(this.filterOderCondition.timeFrom) &&
                        this.fomartDateYYYYMMDD(o.createdAt) <=
                        this.fomartDateYYYYMMDD(this.filterOderCondition.timeTo)
                );
            } else {
                console.log("DEFAULT");
            }
            console.log(this.listOrderByCustomer.lenth);
        },
        // conditionFilterByTimeDS(listOrderByCustomer) {
        //   if (this.filterTimeDS == "controlTimeToday") {
        //     this.listOrderByCustomer = listOrderByCustomer.filter(o => this.fomartDateYYYYMMDD(o.createdAt) == this.fomartDateYYYYMMDD(new Date()));
        //   } else if (this.filterTimeDS == "controlTime1week") {
        //     this.listOrderByCustomer.filter(o => this.fomartDateYYYYMMDD(o.createdAt) >= this.fomartDateYYYYMMDD(moment().subtract(1, "weeks")));
        //   } else if (this.filterTimeDS == "controlTime1month") {
        //     this.listOrderByCustomer = listOrderByCustomer.filter(o => this.fomartDateYYYYMMDD(o.createdAt) >= this.fomartDateYYYYMMDD(moment().subtract(1, "months")));
        //   } else if (this.filterTimeDS == "controlTime1year") {
        //     this.listOrderByCustomer = listOrderByCustomer.filter(o => this.fomartDateYYYYMMDD(o.createdAt) >= this.fomartDateYYYYMMDD(moment().subtract(1, "years")));
        //   } else if (this.filterTimeDS == "controlTimeAll") {
        //     console.log(this.filterTimeDS);
        //   } else if (this.filterTimeDS == "controlTimeAboutDS") {
        //     this.listOrderByCustomer = listOrderByCustomer.filter(o => this.fomartDateYYYYMMDD(o.createdAt) >= this.fomartDateYYYYMMDD(moment(this.filterOderCondition.timeDSFrom))
        //       && this.fomartDateYYYYMMDD(o.createdAt) <= this.fomartDateYYYYMMDD(moment(this.filterOderCondition.timeDSTo)));
        //   } else {
        //     console.log("DEFAULT");
        //   }
        //   console.log(this.listOrderByCustomer.lenth);
        // },
        fomartDateYYYYMMDD(date) {
            return moment(date).format("YYYY-MM-DD");
        },
    },
};