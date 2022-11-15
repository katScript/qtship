import WarehouseData from "@/components/models/warehouse/warehouse-data";
import { commonFunction } from "@/scripts/ulti";

export default class OrderData {
    warehouse = new WarehouseData();

    data = {
        id: null,
        orderCode: "",
        customerId: "",
        customerCode: "",
        status: commonFunction.typeOrderWaitingConfirm,
        feedback: "",
        note: "",
        subtotal: "",
        totalWeight: "",
        senderName: "",
        senderPhone: "",
        senderAddress: "",
        notification: false,
        shippingFee: true,
        shippingType: "",
        shippingTime: "",
        coupon: "",
        warehouse: this.warehouse.getData(),
        returnCode: "",
        orderItem: [],
        createdAt: null,
        updateAt: null
    };

    errors = {
        shippingAddress: {
            phone: "",
            name: ""
        },
        shippingType: "",
        isSelectedTypeLH: "",
        warehouse: "",
        products: "",
        returnCode: ""
    }

    setData(data) {
        this.data.id = data.id;
        this.data.orderCode = data.orderCode;
        this.data.customerId = data.customerId;
        this.data.customerCode = data.customerCode;
        this.data.status = data.status;
        this.data.feedback = data.feedback;
        this.data.note = data.note;
        this.data.subtotal = data.subtotal;
        this.data.totalWeight = data.totalWeight;
        this.data.senderName = data.senderName;
        this.data.senderPhone = data.senderPhone;
        this.data.senderAddress = data.senderAddress;
        this.data.notification = data.notification;
        this.data.shippingFee = data.shippingFee;
        this.data.shippingType = data.shippingType;
        this.data.shippingTime = data.shippingTime;
        this.data.coupon = data.coupon;
        this.data.warehouse = data.warehouse;
        this.data.returnCode = data.returnCode;
        this.data.orderItem = data.orderItem;
        this.data.createdAt = data.createdAt;
        this.data.updateAt = data.updateAt;
    }

    getData() {
        return this.data;
    }

    getErrors() {
        return this.errors;
    }

    validate() {

    }

    setOrderItem(orderItem) {
        this.data.orderItem = orderItem;
    }

    setWarehouse(warehouse) {
        this.data.warehouse = warehouse;
    }
}