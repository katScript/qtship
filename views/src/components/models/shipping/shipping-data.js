export default class ShippingData {
    data = {
        id: null,
        name: "",
        phone: "",
        province: "",
        provinceId: "",
        district: "",
        districtId: "",
        ward: "",
        wardId: "",
        street: "",
        createdAt: null,
        updatedAt: null
    };

    errors = {
        phone: "",
        name: "",
        provinceId: "",
        districtId: "",
        wardId: "",
        street: ""
    }

    setData (data) {
        this.data.id = data.id;
        this.data.name = data.name;
        this.data.phone = data.phone;
        this.data.province = data.province;
        this.data.provinceId = data.provinceId;
        this.data.district = data.district;
        this.data.districtId = data.districtId;
        this.data.ward = data.ward;
        this.data.wardId = data.wardId;
        this.data.street = data.street;
        this.data.createdAt = data.createdAt;
        this.data.updatedAt = data.updatedAt;
    }

    getData () {
        return this.data;
    }

    getErrors () {
        return this.errors;
    }

    validate() {
        let valid = true;

        if (this.getData().provinceId === "") {
            valid = false;
            this.errors.provinceId = "Vui lòng chọn Tỉnh/Thành phố!";
        }
        if (this.getData().districtId === "") {
            valid = false;
            this.errors.districtId = "Vui lòng chọn Quận/Huyện!";
        }

        if (this.getData().wardId === "" && this.getData().districtId !== 498 && this.getData().districtId !== 576) {
            valid = false;
            this.errors.wardId = "Vui lòng chọn Phường/Xã!";
        }

        if (this.getData().street === "") {
            valid = false;
            this.errors.street = "Vui lòng điền địa chỉ chi tiết!";
        }

        return valid;
    }
}