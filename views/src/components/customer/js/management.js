import NavbarClient from "@/components/common/NavbarClient.vue";
import FooterClient from "@/components/common/FooterClient.vue";
import VueChartPie from "@/components/common/VueChartPie.vue";
import VueChartColumn from "@/components/common/VueChartColumn.vue";
import ToolbarRight from "@/components/common/ToolbarRight.vue";
import NotificationClient from "@/components/common/NotficationClient.vue";
import ActionLoading from "@/components/common/ActionLoading.vue";
import CustomerData from "@/components/models/customer/customer-data";

import axios from "axios";
import moment from "moment";
import { useCookies } from "vue3-cookies";
import { commonFunction } from "@/scripts/ulti";

export default {
    components: {
        NavbarClient,
        FooterClient,
        VueChartPie,
        VueChartColumn,
        ToolbarRight,
        NotificationClient,
        ActionLoading,
    },
    setup() {
        const { cookies } = useCookies();
        let customerModel = new CustomerData();

        return {
            customerModel,
            cookies
        };
    },
    data() {
        return {
            filterTime: "all",
            classFilterTimeAbout: "d-none",
            customerInfo: null,
            filterTimeFrom: "",
            filterTimeTo: "",
            msgValidationTimeFromTo: "",
            numberOrderSuccess: 0,
            numberOrderDelivery: 0,
            numberOrderOccurred: 0,
            numberOrderCancel: 0,
            totalPriceOrderSuccess: 0,
            totalPriceOrderDelivery: 0,
            totalPriceOrderOccurred: 0,
            totalPriceOrderCancel: 0,
            idRequest: "",
            listOrderByCustomer: [],
            listOrderByCustomerBk: [],
            listProductByCustomer: [],
            totalOrderAll: 0,
            totalOrderByDate: 0,
            totalProductByDate: 0,
            totalProductAll: 0,
            dateFilter: "",
            isLoading: false,
        };
    },
    // <data, methods...>

    mounted() {
        const self = this;
        let auth = commonFunction.getCookies(commonFunction.userCookies.username);

        this.customerModel.setData(
            JSON.parse(commonFunction.getCustomerStorage())
        );

        self.dateFilter = moment().format("YYYY-MM-DD HH:MM:SS");

        if (auth == null) {
            commonFunction.redirect("/");
        }

        //product
        axios
            .get(
                commonFunction.DOMAIN_URL + "v1/product/customer/" + this.customerModel.getData().id,
                commonFunction.configApi()
            )
            .then((response) => {
                self.listProductByCustomer = response.data;
                self.totalProductByDate = self.listProductByCustomer.filter(
                    (e) =>
                        moment(e.createdAt).format("YYYY-MM-DD") === moment(self.dateFilter).format("YYYY-MM-DD")
                ).length;
                self.totalProductAll = self.listProductByCustomer.length;
            })
            .catch((e) => {
                console.log(e);
            });

        //orders
        axios
            .get(
                commonFunction.DOMAIN_URL + "v1/order/all/customer/" + this.customerModel.getData().id,
                commonFunction.configApi()
            )
            .then((response) => {
                self.listOrderByCustomer = response.data;
                self.listOrderByCustomerBk = response.data;
                self.totalOrderAll = response.data.length;
                self.totalOrderByDate = self.listOrderByCustomer.length;

                self.numberOrderSuccess = self.listOrderByCustomer.filter(
                    (e) => e.status === commonFunction.typeOrderSuccess
                ).length;
                self.numberOrderDelivery = self.listOrderByCustomer.filter(
                    (e) => e.status === commonFunction.typeOrderDelivery
                ).length;
                self.numberOrderOccurred = self.listOrderByCustomer.filter(
                    (e) => e.status === commonFunction.typeOrderOccurred
                ).length;
                self.numberOrderCancel = self.listOrderByCustomer.filter(
                    (e) => e.status === commonFunction.typeOrderCancel
                ).length;

                //price
                self.listOrderByCustomer.forEach((element) => {
                    if (element.status === commonFunction.typeOrderSuccess) {
                        self.totalPriceOrderSuccess += element.subtotal;
                    }
                });
                self.listOrderByCustomer.forEach((element) => {
                    if (element.status === commonFunction.typeOrderDelivery) {
                        self.totalPriceOrderDelivery += element.subtotal;
                    }
                });
                self.listOrderByCustomer.forEach((element) => {
                    if (element.status === commonFunction.typeOrderOccurred) {
                        self.totalPriceOrderOccurred += element.subtotal;
                    }
                });
                self.listOrderByCustomer.forEach((element) => {
                    if (element.status === commonFunction.typeOrderCancel) {
                        self.totalPriceOrderCancel += element.subtotal;
                    }
                });
            })
            .catch((e) => {
                console.log(e);
            });
    },
    watch: {
        filterTime: {
            handler: function () {
                const self = this;
                self.filterTime === "timeAbout"
                    ? (self.classFilterTimeAbout = "d-contents")
                    : (self.classFilterTimeAbout = "d-none");

                if (self.filterTime === "today") {
                    let timeFrom = new Date(),
                        timeTo = new Date();
                    self.axiosGetOrdersByTime(timeFrom, timeTo);
                } else if (self.filterTime === "1week") {
                    let timeFrom = moment(new Date()).subtract(1, "weeks"),
                        timeTo = new Date();
                    self.axiosGetOrdersByTime(timeFrom, timeTo);
                } else if (self.filterTime === "1month") {
                    let timeFrom = moment(new Date()).subtract(1, "months"),
                        timeTo = new Date();
                    self.axiosGetOrdersByTime(timeFrom, timeTo);
                } else if (self.filterTime === "1year") {
                    let timeFrom = moment(new Date()).subtract(1, "years"),
                        timeTo = new Date();
                    self.axiosGetOrdersByTime(timeFrom, timeTo);
                } else if (self.filterTime === "all") {
                    self.listOrderByCustomer = self.listOrderByCustomerBk;
                    self.totalOrderByDate = self.listOrderByCustomer.length;

                    self.numberOrderSuccess = self.listOrderByCustomer.filter(
                        (e) => e.status === commonFunction.typeOrderSuccess
                    ).length;
                    self.numberOrderDelivery = self.listOrderByCustomer.filter(
                        (e) => e.status === commonFunction.typeOrderDelivery
                    ).length;
                    self.numberOrderOccurred = self.listOrderByCustomer.filter(
                        (e) => e.status === commonFunction.typeOrderOccurred
                    ).length;
                    self.numberOrderCancel = self.listOrderByCustomer.filter(
                        (e) => e.status === commonFunction.typeOrderCancel
                    ).length;
                } else if (self.filterTime === "timeAbout") {
                    let timeFrom = moment(self.filterTimeFrom),
                        timeTo = moment(self.filterTimeTo);
                    self.axiosGetOrdersByTime(timeFrom, timeTo);
                }
            },
        },
        filterTimeFrom: {
            handler: function () {
                const self = this;
                if (
                    self.filterTime === "timeAbout" &&
                    (self.filterTimeFrom === "" || self.timeTo === "")
                ) {
                    self.msgValidationTimeFromTo = "Vui lòng chọn từ ngày - đến ngày!";
                }

                let timeFrom = moment(self.filterTimeFrom),
                    timeTo = moment(self.filterTimeTo);
                self.axiosGetOrdersByTime(timeFrom, timeTo);
            },
        },
        filterTimeTo: {
            handler: function () {
                const self = this;
                if (
                    self.filterTime === "timeAbout" &&
                    (self.filterTimeFrom === "" || self.timeTo === "")
                ) {
                    self.msgValidationTimeFromTo = "Vui lòng chọn từ ngày - đến ngày!";
                }

                let timeFrom = moment(self.filterTimeFrom),
                    timeTo = moment(self.filterTimeTo);
                self.axiosGetOrdersByTime(timeFrom, timeTo);
            },
        },
    },
    methods: {
        debouncer(fn, delay) {
            var timeoutID = null;
            return function () {
                clearTimeout(timeoutID);
                var args = arguments;
                var that = this;
                timeoutID = setTimeout(function () {
                    fn.apply(that, args);
                }, delay);
            };
        },
        axiosGetOrdersByTime(timeFrom, timeTo) {
            const self = this;
            self.isLoading = true;
            self.listOrderByCustomer = self.listOrderByCustomerBk;
            self.resetTotalPrice();
            axios
                .get(
                    commonFunction.DOMAIN_URL +
                    "v1/order/all/customer/" + this.customerModel.getData().id +
                    "?from=" +
                    moment(timeFrom).format("YYYY-MM-DD HH:MM:SS") +
                    "&to=" +
                    moment(timeTo).format("YYYY-MM-DD HH:MM:SS"),
                    commonFunction.configApi()
                )
                .then((response) => {
                    let respronseData = response.data;
                    self.listOrderByCustomer = respronseData;
                    console.log(self.listOrderByCustomer);

                    //count
                    self.totalOrderByDate = self.listOrderByCustomer.length;
                    self.numberOrderSuccess = self.listOrderByCustomer.filter(
                        (e) => e.status === commonFunction.typeOrderSuccess
                    ).length;
                    self.numberOrderDelivery = self.listOrderByCustomer.filter(
                        (e) => e.status === commonFunction.typeOrderDelivery
                    ).length;
                    self.numberOrderOccurred = self.listOrderByCustomer.filter(
                        (e) => e.status === commonFunction.typeOrderOccurred
                    ).length;
                    self.numberOrderCancel = self.listOrderByCustomer.filter(
                        (e) => e.status === commonFunction.typeOrderCancel
                    ).length;

                    //price
                    self.listOrderByCustomer.forEach((element) => {
                        if (element.status === commonFunction.typeOrderSuccess) {
                            self.totalPriceOrderSuccess += element.subtotal;
                        }
                    });
                    self.listOrderByCustomer.forEach((element) => {
                        if (element.status === commonFunction.typeOrderDelivery) {
                            self.totalPriceOrderDelivery += element.subtotal;
                        }
                    });
                    self.listOrderByCustomer.forEach((element) => {
                        if (element.status === commonFunction.typeOrderOccurred) {
                            self.totalPriceOrderOccurred += element.subtotal;
                        }
                    });
                    self.listOrderByCustomer.forEach((element) => {
                        if (element.status === commonFunction.typeOrderCancel) {
                            self.totalPriceOrderCancel += element.subtotal;
                        }
                    });

                    self.isLoading = false;
                })
                .catch((e) => {
                    console.log(e);
                    self.isLoading = false;
                });
        },
        resetTotalPrice() {
            this.totalPriceOrderSuccess = 0;
            this.totalPriceOrderDelivery = 0;
            this.totalPriceOrderOccurred = 0;
            this.totalPriceOrderCancel = 0;
        },
    },
};