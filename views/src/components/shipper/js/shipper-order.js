import NavbarClient from "@/components/common/NavbarClient";
import FooterClient from "@/components/common/FooterClient.vue";
import ListOrderShipper from "@/components/common/ListOrderShipper.vue";
import ListOrderTransferShipper from "@/components/common/ListOrderTransferShipper.vue";

import { useCookies } from "vue3-cookies";
// import { commonFunction } from '@/scripts/ulti';
// import axios from "axios";

export default {
    components: {
        NavbarClient,
        FooterClient,
        ListOrderShipper,
        ListOrderTransferShipper,
    },
    data() {
        return {
            isShipper: true,
            currentDate: "",
            configRequestApi: {},
            idRequest: 0,
        };
    },

    setup() {
        const { cookies } = useCookies();
        return {
            cookies,
        };
    },
    mounted() {
        let today = new Date();
        this.currentDate =
            String(today.getDate()).padStart(2, "0") +
            "/" +
            String(today.getMonth() + 1).padStart(2, "0") +
            "/" +
            today.getFullYear();
    },

    method: {},
};