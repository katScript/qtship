
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
                    return "??ang ch??? x??c nh???n";
                case commonFunction.orderStatus.AdminConfirm:
                    return "???? x??c nh???n";
                case commonFunction.orderStatus.TransferShipper:
                    return "??ang b??n giao cho shipper";
                case commonFunction.orderStatus.ShipperConfirm:
                    return "Shipper ???? nh???n giao h??ng";
                case commonFunction.orderStatus.Delivery:
                    return "??ang giao h??ng";
                case commonFunction.orderStatus.Delay:
                    return "Delay giao h??ng";
                case commonFunction.orderStatus.Occurred:
                    return "????n h??ng ph??t sinh v???n ?????";
                case commonFunction.orderStatus.Return:
                    return "Ng?????i nh???n kh??ng nh???n - ????n h??ng ho??n tr???";
                case commonFunction.orderStatus.Storage:
                    return "????n h??ng ??ang l??u kho ch??? ki???m tra";
                case commonFunction.orderStatus.Success:
                    return "Giao h??ng th??nh c??ng";
                case commonFunction.orderStatus.Payment:
                    return "???? thanh to??n";
                case commonFunction.orderStatus.Done:
                    return "Ho??n th??nh";

                default:
                    return "";
            }
        }
    },

}
