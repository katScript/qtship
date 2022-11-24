
import NavbarClient from "@/components/common/NavbarClient.vue";
import FooterClient from "@/components/common/FooterClient.vue";
import ToolbarRight from "@/components/common/ToolbarRight.vue";
import NotficationClient from "@/components/common/NotficationClient.vue";
import BillOrder from "@/components/common/BillOrder.vue";
import OrderData from "@/components/models/order/order-data";
import CustomerData from "@/components/models/customer/customer-data";
import HistoryOrderData from "@/components/models/order/history-order";

// import moment from "moment";
import { useCookies } from "vue3-cookies";
import { commonFunction } from "@/scripts/ulti";
import axios from "axios";

export default {
    setup() {
        const { cookies } = useCookies();
        const orderModel = new OrderData();
        const historyOrderModel = new HistoryOrderData();
        const customerModel = new CustomerData();
        let auth = commonFunction.getCookies(commonFunction.userCookies.username),
            role = commonFunction.getCookies(commonFunction.userCookies.roles),
            id = commonFunction.getCookies(commonFunction.userCookies.id);

        return {
            cookies,
            orderModel,
            historyOrderModel,
            customerModel,
            auth,
            role,
            id,
        };
    },
    data() {
        return {
            orderData: {
                shippingAddress: {},
            },
            isShowBill: false,
            historyOrder: []
        }
    },
    components: {
        NavbarClient,
        FooterClient,
        ToolbarRight,
        NotficationClient,
        BillOrder,
    },
    async mounted() {
        this.customerModel.setData(
            JSON.parse(commonFunction.getCustomerStorage())
        );

        // let customerId = this.customerModel.getData().id;

        // can update
        if (this.auth == null || this.role !== "customer") {
            commonFunction.redirect("/");
        }

        axios.get(
            commonFunction.DOMAIN_URL + "v1/order/customer/"
            + this.$route.params.customerId + '/detail/'
            + this.$route.params.orderId,
            commonFunction.configApi()
        )
            .then((response) => {
                this.orderModel.setData(response.data)
                this.orderData = this.orderModel.getData();
            })
            .catch((e) => {
                console.log(e);
            });

        await axios.get(
            commonFunction.DOMAIN_URL + "v1/order/history/orderId/" + this.$route.params.orderId,
            commonFunction.configApi()
        )
            .then((response) => {
                response.data.forEach(element => {
                    this.historyOrder.push(element);
                });
            })
            .catch((e) => {
                console.log(e);
            });
    },
    methods: {
        displayBill(value) {
            this.isShowBill = value;
        },
        genCodeStatusToTextDisplay(code) {
            switch (code) {
                case commonFunction.orderStatus.Pending:
                    return "Đang đờ xác nhận";
                case commonFunction.orderStatus.AdminConfirm:
                    return "Đã xác nhận";
                case commonFunction.orderStatus.TransferShipper:
                    return "Đang bàn giao cho shipper";
                case commonFunction.orderStatus.ShipperConfirm:
                    return "Shipper đã nhận giao hàng";
                case commonFunction.orderStatus.Delivery:
                    return "Đang giao hàng";
                case commonFunction.orderStatus.Delay:
                    return "Delay giao hàng";
                case commonFunction.orderStatus.Occurred:
                    return "Đơn hàng phát sinh vấn đề";
                case commonFunction.orderStatus.Return:
                    return "Người nhận không nhận - Đơn hàng hoàn trả";
                case commonFunction.orderStatus.Storage:
                    return "Đơn hàng đang lưu kho chờ kiểm tra";
                case commonFunction.orderStatus.Success:
                    return "Giao hàng thành công";
                case commonFunction.orderStatus.Payment:
                    return "Đã thanh toán";
                case commonFunction.orderStatus.Done:
                    return "Hoàn thành";

                default:
                    return "";
            }
        }
    },

}
