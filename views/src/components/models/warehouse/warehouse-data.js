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

    error = {
        name: "",
        address: "",
        phone: "",
    }

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

    validate() {
        let isValid = true;

        if (this.data.name === undefined || this.data.name === "") {
            isValid = false;
            this.error.name =
                "Vui lòng nhập thông tin kho/cửa hàng!";
        }
        if (this.data.phone === undefined || this.data.phone === "") {
            isValid = false;
            this.error.phone =
                "Vui lòng nhập thông tin liên hệ!";
        }
        if (this.data.address === undefined || this.data.address === "" ) {
            isValid = false;
            this.error.address =
                "Vui lòng nhập địa chỉ cụ thể: Số nhà/thôn, xã/phường, quận/huyện, tỉnh/thành phố!";
        }

        return isValid;
    }

    getError() {
        return this.error;
    }
}