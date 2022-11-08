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
}