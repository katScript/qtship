import {commonFunction} from "@/scripts/ulti";
import LoginService from "@/components/service/login-service";
import CustomerData from "@/components/models/customer/customer-data";

import axios from "axios";

export default {
    setup() {
        let loginService = new LoginService(),
            customerModel = new CustomerData();
        return {
            loginService,
            customerModel
        }
    },
    components: {},
    data() {
        return {
            loginData: this.loginService.getModel(),
            message: "",
            loading: false,
        };
    },
    methods: {
        login: function () {
            this.loading = true;
            this.loginService.setModel(this.loginData);

            if (this.loginService.validate()) {
                axios.post(commonFunction.DOMAIN_URL + "api/auth/login",
                    this.loginService.getModel()
                ).then(async (response) => {
                    this.loginService.setData(response.data);

                    if (this.loginService.isAuth()) {
                        commonFunction.setListCookies(
                            [
                                {
                                    key: commonFunction.userCookies.token,
                                    value: this.loginService.getData().accessToken
                                },
                                {
                                    key: commonFunction.userCookies.username,
                                    value: this.loginService.getData().username
                                },
                                {
                                    key: commonFunction.userCookies.id,
                                    value: this.loginService.getData().id
                                },
                                {
                                    key: commonFunction.userCookies.roles,
                                    value: this.loginService.getData().roles
                                }
                            ]
                        );

                        await this.setCustomerData(response.data);
                        commonFunction.redirect("/");
                    }
                }).catch((error) => {
                    alert("Thông tin đăng nhập không chính xác. Vui lòng thử lại!");
                    console.log(error);
                });

            } else {
                this.message = this.loginService.errors.toString();
                alert("Vui lòng điền đầy đủ thông tin đăng nhập!");
            }
        },

        setCustomerData: async function (data) {
            await axios.get(
                commonFunction.DOMAIN_URL +
                "v1/customer/detail/" + data.id,
                commonFunction.configApi()
            ).then((response) => {
                // handle not found
                this.customerModel.setData(response.data);
                localStorage.setItem('customer', JSON.stringify(this.customerModel.getData()));
            }).catch((e) => {
                console.log(e);
            });
        }
    },
};