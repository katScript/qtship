export default class WarehouseData {
    data = {
        id: null,
        name: "",
        customerCode: "",
        address: "",
        phone: "",
        createdAt: null,
        updatedAt: null
    };

    setData (data) {
        this.data.id = data.id;
        this.data.name = data.name;
        this.data.customerCode = data.customerCode;
        this.data.address = data.address;
        this.data.phone = data.phone;
        this.data.createdAt = data.createdAt;
        this.data.updatedAt = data.updatedAt;
    }

    getData () {
        return this.data;
    }

    validate(data, isValid, msgValidationFor) {
        msgValidationFor.warehouse = {};
        if (data.name == "" || data.name == undefined) {
            isValid += 1;
            msgValidationFor.warehouse.name =
                "Vui lòng nhập thông tin kho/cửa hàng!";
        }
        if (data.phone == "" || data.phone == undefined) {
            isValid += 1;
            msgValidationFor.warehouse.phone =
                "Vui lòng nhập thông tin liên hệ!";
        }
        if (data.address == "" || data.address == undefined) {
            isValid += 1;
            msgValidationFor.warehouse.address =
                "Vui lòng nhập địa chỉ cụ thể: Số nhà/thôn, xã/phường, quận/huyện, tỉnh/thành phố!";
        }
        return isValid == 0 ? true : false;
    }
}