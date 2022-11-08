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
}