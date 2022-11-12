import NavbarLeftAdmin from "@/components/common/NavbarLeftAdmin.vue";
import BoxDataOverview from "@/components/common/BoxDataOverview.vue";
import VueDeliveryPerformanceChart from "@/components/common/VueDeliveryPerformanceChart.vue";
import VueOrderTypeAdminChart from "@/components/common/VueOrderTypeAdminChart.vue";
import VueAreaCashCharts from "@/components/common/VueAreaCashCharts.vue";

import { useCookies } from "vue3-cookies";
// import { commonFunction } from '@/scripts/ulti'

export default {
    setup() {
        const { cookies } = useCookies();
        return {
            cookies,
        };
    },
    data() {
        return {           
        };
    },
    components: {
        NavbarLeftAdmin,
        BoxDataOverview,
        VueDeliveryPerformanceChart,
        VueOrderTypeAdminChart,
        VueAreaCashCharts
    },
    mounted() {
        // let authenication_cookies = this.cookies.get("authenication_cookies");
        // if (authenication_cookies == null) {
        //   commonFunction.redirect('/');
        // }
    },
    methods: {
    },
};