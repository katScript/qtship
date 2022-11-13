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
                            alert('Đăng kí thành công! Chuyển tới đăng nhập!');
                            commonFunction.redirect("/login-page");
                        }
                    })
                    .catch((error) => {
                        alert('ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!')
                        console.log(error);
                    });
            } else {
                console.log("Remove validate!");
            }
        },
        validationForm() {
            let isValid = true;

            if (this.customerData.data.customer.companyName === "") {
                this.customerData.errors.customer.companyName = "Vui lòng điền Tên chi nhánh/cửa hàng/shop!";
                isValid = false;
            }

            if (this.customerData.data.customer.phone === "") {
                this.customerData.errors.customer.phone = "Vui lòng điền thông tin số điện thoại!";
                isValid = false;
            }

            if (!commonFunction.regexStringValidate(this.customerData.data.customer.phone, "PHONE")) {
                this.customerData.errors.customer.phone = "Số điện thoại có tối đa 11 chữ số và không tồn tại chữ cái!";
                isValid = false;
            }

            if (this.customerData.data.customer.email === "") {
                this.customerData.errors.customer.email = "Vui lòng điền thông tin email!";
                isValid = false;
            }

            if (!commonFunction.regexStringValidate(this.customerData.data.customer.email, "EMAIL")) {
                this.customerData.errors.customer.email = "Email không hợp lệ! (Ex: myemail@domain.xyz,...)";
                isValid = false;
            }

            if (this.customerData.data.password === "") {
                this.customerData.errors.password = "Vui lòng điền mật khẩu";
                isValid = false;
            }

            if (!commonFunction.regexStringValidate(this.customerData.data.password, "PASSWORD")) {
                this.customerData.errors.password = "Mật khẩu từ 7 đến 15 ký tự trong đó có ít nhất một chữ số và một ký tự đặc biệt!";
                isValid = false;
            }

            if (this.customerData.data.confirmPassword !== this.customerData.data.password) {
                this.customerData.errors.confirmPassword = "Mật khẩu nhập lại không khớp!";
                isValid = false;
            }

            if (this.customerData.data.customerAddress.provinceId === "") {
                this.customerData.errors.customerAddress.provinceId = "Vui lòng chọn Tỉnh/thành phố!";
                isValid = false;
            }

            if (this.customerData.data.customerAddress.districtId === "") {
                this.customerData.errors.customerAddress.districtId = "Vui lòng chọn Quận/Huyện!";
                isValid = false;
            }

            if (this.customerData.data.customerAddress.wardId === "") {
                this.customerData.errors.customerAddress.wardId = "Vui lòng chọn Phường/Xã!";
                isValid = false;
            }

            if (this.customerData.data.customerAddress.street === "") {
                this.customerData.errors.customerAddress.street = "Vui lòng điền địa chỉ chi tiết!";
                isValid = false;
            }

            if (this.customerData.data.forControl.holderName === "") {
                this.customerData.errors.forControl.holderName = "Vui lòng điền Tên chủ tài khoản!";
                isValid = false;
            }

            if (this.customerData.data.forControl.cardNumber === "") {
                this.customerData.errors.forControl.cardNumber = "Vui lòng điền số tài khoản ngân hàng!";
                isValid = false;
            }

            if (this.customerData.data.forControl.bank === "") {
                this.customerData.errors.forControl.bank = "Vui lòng chọn Ngân hàng!";
                isValid = false;
            }

            if (!this.isAccepted) {
                this.customerData.errors.isAccepted = "Vui lòng đồng ý với Chính sách bảo mật thông tin!";
                isValid = false;
            }

            return isValid;
        }
    },
}