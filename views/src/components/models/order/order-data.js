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
            this.errors.shippingAddress.phone = "Vui lòng điền thông tin số điện thoại!";
            isValid = false;
        }
        if (!commonFunction.regexStringValidate(data.shippingAddress.phone, "PHONE")) {
            this.errors.shippingAddress.phone = "Số điện thoại có tối đa 11 chữ số và không tồn tại chữ cái!";
            isValid = false;
        }
        if (data.shippingAddress.name == "") {
            this.errors.shippingAddress.name = "Vui lòng điền thông tin người nhận!";
            isValid = false;
        }
        if (this.formDataOrder.shippingType == "") {
            this.errors.shippingType = "Vui lòng chọn phương thức giao hàng!";
            isValid = false;
        }
        if (this.isSelectedTypeLH == "") {
            this.errors.isSelectedTypeLH = "Vui lòng chọn hình thức lấy hàng!";
            isValid = false;
        }
        if (this.isSelectedTypeLH == 'LHTN' && this.formDataOrder.warehouseId == "") {
            this.errors.warehouseId = "Vui lòng chọn địa điểm và thời gian lấy hàng!";
            isValid = false;
        }
        if (data.products[0].productId == "" || data.products[0].qty == "") {
            this.errors.products = "Vui lòng chọn sản phẩm và điền số lượng!";
            isValid = false;
        }
        if (this.customerAddress.provinceId == "") {
            isValid = false;
            this.errors.customerAddress.provinceId =
                "Vui lòng chọn Tỉnh/Thành phố!";
        }
        if (this.customerAddress.districtId == "") {
            isValid = false;
            this.errors.customerAddress.districtId =
                "Vui lòng chọn Quận/Huyện!";
        }
        if (this.customerAddress.wardId == "") {
            if (this.customerAddress.districtId != 498 && this.customerAddress.districtId != 576)
                this.errors.customerAddress.wardId =
                    "Vui lòng chọn Phường/Xã!";
        }
        if (this.customerAddress.street == "") {
            isValid = false;
            this.errors.customerAddress.street =
                "Vui lòng điền địa chỉ chi tiết!";
        }
        if (this.formDataOrder.returnCode == "") {
            isValid = false;
            this.errors.returnCode = "Vui lòng chọn Phương thức hoàn hàng!"
        }
        if (!this.isAccepted) {
            isValid = false;
            alert("Vui lòng đọc và đồng ý với Chính sách bảo mật thông tin!");
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