import WarehouseData from "@/components/models/warehouse/warehouse-data";
import ShipperData from "@/components/models/shipping/shipper-data";
import ShippingData from "@/components/models/shipping/shipping-data";
import {commonFunction} from "@/scripts/ulti";

export default class OrderData {
    warehouse = new WarehouseData();
    shipper = new ShipperData();
    shippingData = new ShippingData();

    data = {
        id: null,
        orderCode: "",
        customerId: "",
        customerCode: "",
        senderName: "",
        senderPhone: "",
        senderAddress: "",
        subtotal: 0,
        totalWeight: 0,
        note: "",
        status: commonFunction.orderStatus.Pending,
        feedback: "",
        notification: false,
        shipPayer: true,
        shippingFee: 0,
        coupon: "",
        warehouse: this.warehouse.getData(),
        shipper: this.shipper.getData(),
        shippingAddress: this.shippingData.getData(),
        shippingType: "",
        shippingTime: "",
        products: [],
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
        this.data.senderName = data.senderName;
        this.data.senderPhone = data.senderPhone;
        this.data.senderAddress = data.senderAddress;
        this.data.subtotal = data.subtotal;
        this.data.totalWeight = data.totalWeight;
        this.data.note = data.note;
        this.data.status = data.status;
        this.data.feedback = data.feedback;
        this.data.notification = data.notification;
        this.data.shipPayer = data.shipPayer;
        this.data.shippingFee = data.shippingFee;
        this.data.coupon = data.coupon;
        this.data.warehouse = data.warehouse;
        this.data.shipper = data.shipper;
        this.data.shippingAddress = data.shippingAddress;
        this.data.shippingType = data.shippingType;
        this.data.shippingTime = data.shippingTime;
        this.data.products = data.products;
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

    setWarehouse(warehouse) {
        this.data.warehouse = warehouse;
    }

    setProduct(products) {
        this.data.products = products;
    }

    setShippingAddress(shippingAddress) {
        this.data.shippingAddress = shippingAddress;
    }
}