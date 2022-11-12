import OrderData from "@/components/models/order/order-data";
import NavbarLeftAdmin from "@/components/common/NavbarLeftAdmin.vue";

// import moment from "moment";
// import { commonFunction } from "@/scripts/ulti";
// import axios from "axios";
import { useCookies } from "vue3-cookies";

export default {
    components: {
        NavbarLeftAdmin,
    },
    setup() {
        const orderModel = new OrderData();
        const cookies = useCookies();
        return {
            cookies,
            orderModel
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

    },
    watch: {
        filterTime: {
            handler: function () {
                return this.filterTime == "timeCOTimeAbout"
                    ? (this.classFilterTimeAbout = "d-contents")
                    : (this.classFilterTimeAbout = "d-none");
            },
        },
    },
    methods: {
    },
};