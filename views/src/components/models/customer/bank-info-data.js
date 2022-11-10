export default class BankInfoData {
    data = {
        id: null,
        holderName: "",
        cardNumber: "",
        bank: "",
        address: null,
        createdAt: "",
        updatedAt: ""
    };

    setData (data) {
        this.data.id = data.id;
        this.data.holderName = data.name;
        this.data.cardNumber = data.phone;
        this.data.bank = data.province;
        this.data.address = data.provinceId;
    }

    getData () {
        return this.data;
    }
}