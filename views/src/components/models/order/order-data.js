import WarehouseData from "@/components/models/warehouse/warehouse-data";

export default class OrderData {
    warehouse = new WarehouseData();

    data = {
        id: null,
        orderCode: "",
        customerId: null,
        customerCode: "",
        status: "",
        feedback: "",
        note: "",
        subtotal: null,
        senderName: "",
        senderPhone: "",
        senderAddress: "",
        notification: false,
        shippingFee: false,
        shippingType: "",
        shippingTime: "",
        coupon: "",
        warehouse: this.warehouse.getData(),
        returnCode: "",
        orderItem: [],
        createdAt: null,
        updateAt: null
    };

    setData(data) {
        this.data.id = data.id;
        this.data.orderCode = data.orderCode;
        this.data.customerId = data.customerId;
        this.data.customerCode = data.customerCode;
        this.data.status = data.status;
        this.data.feedback = data.feedback;
        this.data.note = data.note;
        this.data.subtotal = data.subtotal;
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
}