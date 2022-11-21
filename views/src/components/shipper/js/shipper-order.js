import NavbarClient from "@/components/common/NavbarClient";
import FooterClient from "@/components/common/FooterClient.vue";
import ListOrderConfirmedShipper from "@/components/common/ListOrderConfirmedShipper.vue";
import ListOrderTransferShipper from "@/components/common/ListOrderTransferShipper.vue";
import ListOrderCompleteShipper from "@/components/common/ListOrderCompleteShipper.vue";
import ListOrderDeliveryShipper from "@/components/common/ListOrderDeliveryShipper.vue";

import ShipperData from "@/components/models/shipping/shipper-data";
import OrderData from "@/components/models/order/order-data";
import OrderStatusService from "@/components/service/order-status-service";
import { commonFunction } from '@/scripts/ulti';
import axios from "axios";
import moment from "moment";

export default {
    components: {
        NavbarClient,
        FooterClient,
        ListOrderConfirmedShipper,
        ListOrderTransferShipper,
        ListOrderCompleteShipper,
        ListOrderDeliveryShipper
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
            tabSelected: "TRANSFER"
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
            "v1/shipper/order/assign/" +
            this.shipperModel.getData().id,
            commonFunction.configApi()
        ).then((response) => {
            // handle not found
            response.data.forEach(o => {
                if (moment(o.order.shippingTime).format("YYYY-MM-DD") == moment(new Date()).format("YYYY-MM-DD")) {
                    let order = new OrderData();
                    order.setData(o.order);
                    this.listOrder.data.push(order.getData());
                }
            });

            this.listOrder.total = this.listOrder.data.length;
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
        emitTabSelected: function (value) {
            this.tabSelected = value;
        }
    },
};