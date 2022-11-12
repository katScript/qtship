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
        const { cookies } = useCookies();
        const orderModel = new OrderData();
        return {
            cookies,
            orderModel
        };
    },

    data() {
        return {
        };
    },

    // <data, methods...>

    mounted() {

    },
    watch: {
    },
};