import ShippingData from "@/components/models/shipping/shipping-data";
import BankInfoData from "@/components/models/customer/bank-info-data";

export default class CustomerData {
    shippingData = new ShippingData();
    bankInfoData = new BankInfoData();

    data = {
        id: null,
        userName: "",
        fullName: "",
        customerId: "",
        gender: null,
        dob: "",
        phone: "",
        companyName: "",
        email: "",
        cidFront: null,
        cidBack: null,
        subscription: "",
        createdAt: "",
        updatedAt: "",
        addressSet: [this.shippingData.getData()],
        forControls: [this.bankInfoData.getData()]
    };

    setData (data) {
        this.data.id = data.id;
        this.data.userName = data.userName;
        this.data.fullName = data.fullName;
        this.data.customerId = data.customerId;
        this.data.gender = data.gender;
        this.data.dob = data.dob;
        this.data.phone = data.phone;
        this.data.companyName = data.companyName;
        this.data.email = data.email;
        this.data.cidFront = data.cidFront;
        this.data.cidBack = data.cidBack;
        this.data.subscription = data.subscription;
        this.data.addressSet = data.addressSet;
        this.data.forControls = data.forControls;
        this.data.createdAt = data.createdAt;
        this.data.updateAt = data.updateAt;
    }

    getData () {
        return this.data;
    }
}