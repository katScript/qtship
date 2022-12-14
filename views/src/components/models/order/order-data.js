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
        returnCode: "",
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

    validate(data) {
        let isValid = true;
        if (data.shippingAddress.phone == "") {
            this.errors.shippingAddress.phone = "Vui l??ng ??i???n th??ng tin s??? ??i???n tho???i!";
            isValid = false;
        }
        if (!commonFunction.regexStringValidate(data.shippingAddress.phone, "PHONE")) {
            this.errors.shippingAddress.phone = "S??? ??i???n tho???i c?? t???i ??a 11 ch??? s??? v?? kh??ng t???n t???i ch??? c??i!";
            isValid = false;
        }
        if (data.shippingAddress.name == "") {
            this.errors.shippingAddress.name = "Vui l??ng ??i???n th??ng tin ng?????i nh???n!";
            isValid = false;
        }
        if (this.formDataOrder.shippingType == "") {
            this.errors.shippingType = "Vui l??ng ch???n ph????ng th???c giao h??ng!";
            isValid = false;
        }
        if (this.isSelectedTypeLH == "") {
            this.errors.isSelectedTypeLH = "Vui l??ng ch???n h??nh th???c l???y h??ng!";
            isValid = false;
        }
        if (this.isSelectedTypeLH == 'LHTN' && this.formDataOrder.warehouseId == "") {
            this.errors.warehouseId = "Vui l??ng ch???n ?????a ??i???m v?? th???i gian l???y h??ng!";
            isValid = false;
        }
        if (data.products[0].productId == "" || data.products[0].qty == "") {
            this.errors.products = "Vui l??ng ch???n s???n ph???m v?? ??i???n s??? l?????ng!";
            isValid = false;
        }
        if (this.customerAddress.provinceId == "") {
            isValid = false;
            this.errors.customerAddress.provinceId =
                "Vui l??ng ch???n T???nh/Th??nh ph???!";
        }
        if (this.customerAddress.districtId == "") {
            isValid = false;
            this.errors.customerAddress.districtId =
                "Vui l??ng ch???n Qu???n/Huy???n!";
        }
        if (this.customerAddress.wardId == "") {
            if (this.customerAddress.districtId != 498 && this.customerAddress.districtId != 576)
                this.errors.customerAddress.wardId =
                    "Vui l??ng ch???n Ph?????ng/X??!";
        }
        if (this.customerAddress.street == "") {
            isValid = false;
            this.errors.customerAddress.street =
                "Vui l??ng ??i???n ?????a ch??? chi ti???t!";
        }
        if (this.formDataOrder.returnCode == "") {
            isValid = false;
            this.errors.returnCode = "Vui l??ng ch???n Ph????ng th???c ho??n h??ng!"
        }
        if (!this.isAccepted) {
            isValid = false;
            alert("Vui l??ng ?????c v?? ?????ng ?? v???i Ch??nh s??ch b???o m???t th??ng tin!");
        }

        return isValid;
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