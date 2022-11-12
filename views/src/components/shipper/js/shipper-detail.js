import NavbarClient from "@/components/common/NavbarClient.vue";

import { useCookies } from "vue3-cookies";
import { commonFunction } from '@/scripts/ulti'
import axios from "axios";
import ShipperData from "@/components/models/shipping/shipper-data";

export default {
    // working like constructor
    setup() {
        const { cookies } = useCookies();
        const shipperModel = new ShipperData();

        return {
            cookies,
            shipperModel
        };
    },
    components: {
        NavbarClient,
    },
    data() {
        return {
            isShipper: true,
            shipperData: {}
        };
    },
    mounted() {
        let auth = commonFunction.getCookies(commonFunction.userCookies.username),
            role = commonFunction.getCookies(commonFunction.userCookies.roles),
            id = commonFunction.getCookies(commonFunction.userCookies.id);

        if (auth == null || role !== "shipper") {
            commonFunction.redirect("/");
        }

        axios.get(
            commonFunction.DOMAIN_URL +
            "v1/shipper/detail/" + id,
            commonFunction.configApi()
        )
            .then((response) => {
                this.shipperModel.setData(response.data);

                this.shipperData = this.shipperModel.getData();
            })
            .catch((e) => {
                console.log(e);
            });
    },
};