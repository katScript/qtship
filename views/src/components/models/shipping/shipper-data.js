export default class ShipperData {
    data = {
        id: null,
        shipperCode: "",
        username: "",
        fullName: "",
        email: "",
        phone: "",
        address: "",
        currentAddress: "",
        createdAt: null,
        updatedAt: null
    };

    setData (data) {
        this.data.id = data.id;
        this.data.shipperCode = data.shipperCode;
        this.data.username = data.username;
        this.data.email = data.email;
        this.data.fullName = data.fullName;
        this.data.phone = data.phone;
        this.data.address = data.address;
        this.data.currentAddress = data.currentAddress;
        this.data.createdAt = data.createdAt;
        this.data.updatedAt = data.updatedAt;
    }

    getData () {
        return this.data;
    }
}