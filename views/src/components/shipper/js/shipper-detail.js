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
            shipperData: this.shipperModel.getData()
        };
    },
    mounted() {
        let auth = commonFunction.getCookies("authenication_cookies"),
            role = commonFunction.getCookies("authenrole_cookies"),
            id = commonFunction.getCookies("idrequest_cookies"),
            token = commonFunction.getCookies("accesstoken_cookies");

        if (auth == null && role !== "shipper") {
            commonFunction.redirect("/");
        }

        this.configRequestApi = {
            headers: { Authorization: "Bearer " + token },
        };

        axios.get(
            commonFunction.DOMAIN_URL +
            "v1/shipper/detail/" + id,
            this.configRequestApi
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