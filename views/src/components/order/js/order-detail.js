
import NavbarClient from "@/components/common/NavbarClient.vue";
import FooterClient from "@/components/common/FooterClient.vue";
import ToolbarRight from "@/components/common/ToolbarRight.vue";
import NotficationClient from "@/components/common/NotficationClient.vue";
import BillOrder from "@/components/common/BillOrder.vue";
import OrderData from "@/components/models/order/order-data";
import CustomerData from "@/components/models/customer/customer-data";

// import moment from "moment";
import { useCookies } from "vue3-cookies";
import { commonFunction } from "@/scripts/ulti";
import axios from "axios";

export default {
    setup() {
        const { cookies } = useCookies();
        const orderModel = new OrderData();
        const customerModel = new CustomerData();
        let auth = commonFunction.getCookies(commonFunction.userCookies.username),
            role = commonFunction.getCookies(commonFunction.userCookies.roles),
            id = commonFunction.getCookies(commonFunction.userCookies.id);

        return {
            cookies,
            orderModel,
            customerModel,
            auth,
            role,
            id,
        };
    },
    data() {
        return {
            orderData: {},
            isShowBill: false,
        }
    },
    components: {        
        NavbarClient,
        FooterClient,
        ToolbarRight,
        NotficationClient,
        BillOrder,
    },
    created() {
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
    },
    methods: {
        displayBill(value) {
            this.isShowBill = value;
        }
    },

}
