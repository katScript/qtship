import axios from "axios";
import {commonFunction} from "@/scripts/ulti";
import CustomerData from "@/components/models/customer/customer-data";
import ShipperData from "@/components/models/shipping/shipper-data";

export default class LoginService {
    model = {
        username: "",
        password: ""
    }

    data = {
        id: null,
        username: "",
        email: "",
        roles: [],
        accessToken: "",
        tokenType: ""
    }

    errors = []

    storeUserData = {
        customer: async function (data) {
            await axios.get(
                commonFunction.DOMAIN_URL +
                "v1/customer/detail/" + data.id,
                commonFunction.configApi()
            ).then((response) => {
                // handle not found
                let customerModel = new CustomerData();

                customerModel.setData(response.data);
                commonFunction.setCustomerStorage(customerModel.getData());
            }).catch((e) => {
                console.log(e);
            });
        },
        shipper: async function (data) {
            await axios.get(
                commonFunction.DOMAIN_URL +
                "v1/shipper/detail/" + data.id,
                commonFunction.configApi()
            ).then((response) => {
                // handle not found
                let shipperModel = new ShipperData();

                shipperModel.setData(response.data);
                commonFunction.setShipperStorage(shipperModel.getData());
            }).catch((e) => {
                console.log(e);
            });
        }
    }

    setModel(data) {
        this.model.username = data.username;
        this.model.password = data.password;
    }

    getModel() {
        return this.model;
    }

    setData(data) {
        this.data.id = data.id;
        this.data.username = data.username;
        this.data.email = data.email;
        this.data.roles = data.roles;
        this.data.accessToken = data.accessToken;
        this.data.tokenType = data.tokenType;
    }

    getData() {
        return this.data;
    }

    isAuth() {
        return this.getData().accessToken != null;
    }

    validate() {
        let valid = true;

        if (!this.getModel().username || this.getModel().username === "") {

            valid = false;
        }

        if (!this.getModel().password || this.getModel().password === "") {

            valid = false;
        }

        return valid;
    }

    storeUserDataFunction() {
        return this.storeUserData;
    }
}