import LocationPicker from '@/components/common/LocationPicker.vue';
import BankPicker from '@/components/common/BankPicker.vue';
import {commonFunction} from "@/scripts/ulti";
import axios from "axios";

export default {
    components: {
        LocationPicker,
        BankPicker
    },
    data() {
        return {
            shippingData: {
                data: {},
                errors: {}
            },
            isValid: 0,
            isAccepted: false,

            customerData: {
                data: {
                    username: "",
                    password: "",
                    confirmPassword: "",
                    customer: {
                        fullName: "",
                        phone: "",
                        companyName: "",
                        email: ""
                    },
                    customerAddress: {
                        province: "",
                        provinceId: "",
                        district: "",
                        districtId: "",
                        ward: "",
                        wardId: "",
                        street: ""
                    },
                    forControl: {
                        holderName: "",
                        cardNumber: "",
                        bank: "",
                        address: ""
                    },
                },
                errors: {
                    isAccepted: "",
                    password: "",
                    confirmPassword: "",
                    customer: {
                        phone: "",
                        companyName: "",
                        email: ""
                    },
                    customerAddress: {
                        provinceId: "",
                        districtId: "",
                        wardId: "",
                        street: ""
                    },
                    forControl: {
                        holderName: "",
                        cardNumber: "",
                        bank: "",
                        address: ""
                    },
                }
            }
        }
    },
    updated() {
        this.customerData.data.customer.fullName = this.customerData.data.customer.companyName;
        this.customerData.data.username = this.customerData.data.customer.email;
    },
    methods: {
        updateAddress(data, type) {
            switch (type) {
                case "PROVINCE":
                    this.customerData.data.customerAddress.provinceId = data?.code;
                    this.customerData.data.customerAddress.province = data?.label;
                    break;
                case "DISTRICT":
                    this.customerData.data.customerAddress.districtId = data?.code;
                    this.customerData.data.customerAddress.district = data?.label;
                    break;
                case "WARD":
                    this.customerData.data.customerAddress.wardId = data?.code;
                    this.customerData.data.customerAddress.ward = data?.label;
                    break;
                case "STREET":
                    this.customerData.data.customerAddress.street = data;
                    break;
                default:
                    break;
            }
        },
        updateForControl(data, type) {
            switch (type) {
                case 'HOLDER':
                    this.customerData.data.forControl.holderName = data;
                    break;
                case 'CARD_NUMBER':
                    this.customerData.data.forControl.cardNumber = data;
                    break;
                case 'BANK':
                    this.customerData.data.forControl.bank = data.code;
                    break;
                case 'ADDRESS':
                    this.customerData.data.forControl.address = data;
                    break;
                default:
            }
        },
        register: function () {
            if (this.validationForm()) {
                axios
                    .post(commonFunction.DOMAIN_URL + "v1/customer/auth/register", this.customerData.data)
                    .then((response) => {
                        let resData = response;
                        if (resData.status === 200) {
                            console.log(resData);
                            alert('????ng k?? th??nh c??ng! Chuy???n t???i ????ng nh???p!');
                            commonFunction.redirect("/login-page");
                        }
                    })
                    .catch((error) => {
                        alert('ERROR: Vui l??ng th??? l???i ho???c li??n h??? v???i qu???n tr??? vi??n!')
                        console.log(error);
                    });
            } else {
                console.log("Remove validate!");
            }
        },
        validationForm() {
            let isValid = true;
            this.customerData.errors = {
                isAccepted: "",
                password: "",
                confirmPassword: "",
                customer: {
                    phone: "",
                    companyName: "",
                    email: ""
                },
                customerAddress: {
                    provinceId: "",
                    districtId: "",
                    wardId: "",
                    street: ""
                },
                forControl: {
                    holderName: "",
                    cardNumber: "",
                    bank: "",
                    address: ""
                },
            }
            if (this.customerData.data.customer.companyName === "") {
                this.customerData.errors.customer.companyName = "Vui l??ng ??i???n T??n chi nh??nh/c???a h??ng/shop!";
                isValid = false;
            }

            if (this.customerData.data.customer.phone === "") {
                this.customerData.errors.customer.phone = "Vui l??ng ??i???n th??ng tin s??? ??i???n tho???i!";
                isValid = false;
            }

            if (!commonFunction.regexStringValidate(this.customerData.data.customer.phone, "PHONE")) {
                this.customerData.errors.customer.phone = "S??? ??i???n tho???i c?? t???i ??a 11 ch??? s??? v?? kh??ng t???n t???i ch??? c??i!";
                isValid = false;
            }

            if (this.customerData.data.customer.email === "") {
                this.customerData.errors.customer.email = "Vui l??ng ??i???n th??ng tin email!";
                isValid = false;
            }

            if (!commonFunction.regexStringValidate(this.customerData.data.customer.email, "EMAIL")) {
                this.customerData.errors.customer.email = "Email kh??ng h???p l???! (Ex: myemail@domain.xyz,...)";
                isValid = false;
            }

            if (this.customerData.data.password === "") {
                this.customerData.errors.password = "Vui l??ng ??i???n m???t kh???u";
                isValid = false;
            }

            if (!commonFunction.regexStringValidate(this.customerData.data.password, "PASSWORD")) {
                this.customerData.errors.password = "M???t kh???u t??? 7 ?????n 15 k?? t??? trong ???? c?? ??t nh???t m???t ch??? s??? v?? m???t k?? t??? ?????c bi???t!";
                isValid = false;
            }

            if (this.customerData.data.confirmPassword !== this.customerData.data.password) {
                this.customerData.errors.confirmPassword = "M???t kh???u nh???p l???i kh??ng kh???p!";
                isValid = false;
            }

            if (this.customerData.data.customerAddress.provinceId === "") {
                this.customerData.errors.customerAddress.provinceId = "Vui l??ng ch???n T???nh/th??nh ph???!";
                isValid = false;
            }

            if (this.customerData.data.customerAddress.districtId === "") {
                this.customerData.errors.customerAddress.districtId = "Vui l??ng ch???n Qu???n/Huy???n!";
                isValid = false;
            }

            if (this.customerData.data.customerAddress.wardId === "") {
                this.customerData.errors.customerAddress.wardId = "Vui l??ng ch???n Ph?????ng/X??!";
                isValid = false;
            }

            if (this.customerData.data.customerAddress.street === "") {
                this.customerData.errors.customerAddress.street = "Vui l??ng ??i???n ?????a ch??? chi ti???t!";
                isValid = false;
            }

            if (this.customerData.data.forControl.holderName === "") {
                this.customerData.errors.forControl.holderName = "Vui l??ng ??i???n T??n ch??? t??i kho???n!";
                isValid = false;
            }

            if (this.customerData.data.forControl.cardNumber === "") {
                this.customerData.errors.forControl.cardNumber = "Vui l??ng ??i???n s??? t??i kho???n ng??n h??ng!";
                isValid = false;
            }

            if (this.customerData.data.forControl.bank === "") {
                this.customerData.errors.forControl.bank = "Vui l??ng ch???n Ng??n h??ng!";
                isValid = false;
            }

            if (!this.isAccepted) {
                this.customerData.errors.isAccepted = "Vui l??ng ?????ng ?? v???i Ch??nh s??ch b???o m???t th??ng tin!";
                isValid = false;
            }

            return isValid;
        }
    },
}