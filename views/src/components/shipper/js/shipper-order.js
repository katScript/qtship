import NavbarClient from "@/components/common/NavbarClient";
import FooterClient from "@/components/common/FooterClient.vue";
import ListOrderShipper from "@/components/common/ListOrderShipper.vue";
import ListOrderTransferShipper from "@/components/common/ListOrderTransferShipper.vue";

import ShipperData from "@/components/models/shipping/shipper-data";
import OrderData from "@/components/models/order/order-data";
import OrderStatusService from "@/components/service/order-status-service";
import { commonFunction } from '@/scripts/ulti';
import axios from "axios";

export default {
    components: {
        NavbarClient,
        FooterClient,
        ListOrderShipper,
        ListOrderTransferShipper,
    },
    setup() {
        let shipperModel = new ShipperData(),
            orderStatusService = new OrderStatusService();
        return {
            shipperModel,
            orderStatusService
        }
    },
    data() {
        return {
            isShipper: true,
            currentDate: "",
            listOrder: {
                total: 0,
                data: []
            },
            listOrderTransferForShipper: [],
            listOrderByStatus: this.orderStatusService.getListOrderStatusSupport(),
            totalPrice: "0",
            totalSuccessPrice: "0",
        };
    },
    async mounted() {
        this.currentDate = commonFunction.getDateTimeFormat(null, "DD/MM/YYYY");

        this.shipperModel.setData(
            JSON.parse(commonFunction.getShipperStorage())
        );
        //
        // let startDay = commonFunction.getDateTimeFormat(null, "YYYY-MM-DD") + " 00:00:00",
        //     endDay = commonFunction.getDateTimeFormat(null, "YYYY-MM-DD") + " 23:59:59";

        await axios.get(
            commonFunction.DOMAIN_URL +
            "v1/order/all/shipper/" +
            this.shipperModel.getData().id,
            commonFunction.configApi()
        ).then((response) => {
            // handle not found
            this.listOrder.total = response.data.length;
            response.data.forEach(o => {
                let order = new OrderData();
                order.setData(o);

                this.listOrder.data.push(order.getData());
            });
        }).catch((e) => {
            console.log(e);
        });

        this.processListOrder();
        this.listOrderTransferForShipper = this.listOrder.data.filter(e => e.status == commonFunction.orderStatus.TransferShipper)
    },
    methods: {
        processListOrder: function () {
            Object.keys(
                this.orderStatusService.getListOrderStatusSupport()
            ).forEach(k => {
                let orderList = this.listOrder.data.filter(o => o.status === k);

                this.listOrderByStatus[k].total =
                    orderList.length;

                this.listOrderByStatus[k].data = orderList;
            });

            let total = 0,
                successOrderTotal = 0;

            this.listOrder.data.forEach(o => {
                total += o.subtotal;
            });

            let currencyFormat = new Intl.NumberFormat('vi-VN', {
                style: 'currency',
                currency: 'VND'
            });

            this.totalPrice = currencyFormat.format(total);

            this.listOrderByStatus.SUCCESS.data.forEach(o => {
                successOrderTotal += o.subtotal;
            });

            this.totalSuccessPrice = currencyFormat.format(successOrderTotal);
        },
    },
};