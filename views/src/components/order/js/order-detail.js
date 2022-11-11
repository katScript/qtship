
import NavbarClient from "@/components/common/NavbarClient.vue";
import FooterClient from "@/components/common/FooterClient.vue";
import ToolbarRight from "@/components/common/ToolbarRight.vue";
import NotficationClient from "@/components/common/NotficationClient.vue";
import BillOrder from "@/components/common/BillOrder.vue";
import OrderData from "@/components/models/order/order-data";

// import moment from "moment";
import { useCookies } from "vue3-cookies";
import { commonFunction } from "@/scripts/ulti";
import axios from "axios";

export default {
    setup() {
        const { cookies } = useCookies();
        const orderModel = new OrderData();

        return {
            cookies,
            orderModel
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
        let auth = commonFunction.getCookies("authenication_cookies"),
            role = commonFunction.getCookies("authenrole_cookies"),
            // id = commonFunction.getCookies("idrequest_cookies"),
            token = commonFunction.getCookies("accesstoken_cookies");

        if (auth == null && role !== "customer") {
            commonFunction.redirect("/");
        }

        this.configRequestApi = {
            headers: { Authorization: "Bearer " + token },
        };

        axios
        .get(
            commonFunction.DOMAIN_URL + "v1/order/detail/"
            //  + id + "/" 
             + this.$route.params.orderId,
            this.configRequestApi
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
