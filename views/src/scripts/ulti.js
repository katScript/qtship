import { sha256 } from "js-sha256";
import md5 from "md5";
import $ from "jquery";
import { useCookies } from "vue3-cookies";
import moment from "moment/moment";

const APIUrl = "http://localhost:8091/";
const commonFunction = {
    DOMAIN_URL: 'http://localhost:8091/',
    ORIGIN_URL: window.location.origin,
    EXPIRED_TIME_COOKIES: "1d",
    // cookies manager
    userCookies: {
        token: "gEZkA7zyDqT7ZcMVLVhA2dYdiNHmLQD4",
        username: "jgT7Un46hLk4jBzyTGEq6j634H34jmU8",
        id: "4qYbrSdRZSb2uYS4f58iBKmjRHFFaWmy",
        roles: "4DFkgFzNb6eHkWw7SLzGCGvTwNN49pk3"
    },
    cookiesManager: useCookies(),
    setCookies: function (key, value) {
        this.cookiesManager.cookies.set(key, value, commonFunction.EXPIRED_TIME_COOKIES);
    },
    setListCookies: function (array) {
        array.forEach(elm => this.setCookies(elm.key, elm.value));
    },
    getCookies: function (key) {
        return this.cookiesManager.cookies.get(key);
    },
    removeCookies: function (key) {
        this.cookiesManager.cookies.remove(key);
    },
    removeUserCookies: function () {
        Object.values(this.userCookies).forEach(val => {
            this.removeCookies(val);
        });
    },

    // shipping type
    shippingType: {
        STANDARD: "Giao hàng thường",
        FAST: "Giao 24h",
        SUPER: "Giao hàng 1h"
    },

    // config api
    configApi: function () {
        return {
            headers: { Authorization: "Bearer " + this.getCookies(this.userCookies.token) },
        };
    },

    // option shipping type
    typeShipping: [
        "STANDARD",
        "FAST",
        "SUPER"
    ],

    // option return type
    typeReturnOrder: [
        "SAVE_WARE_WAITING_CHECK",
        "CAN_NOT_RETURN",
        "DON_NOT_SAVE_RETURN_NOW"
    ],

    // package location
    packageLocationConfig: {
        POST_OFFICES: {
            label: "Gửi hàng tại bưu cục",
            value: "POST_OFFICES"
        },
        HOUSE_ADDRESS: {
            label: "Lấy hàng tận nơi",
            value: "HOUSE_ADDRESS"
        }
    },

    // user function
    signOut: function () {
        Object.values(this.userCookies).forEach(key => {
            this.removeCookies(key);
        });

        this.removeCustomerStorage();
        this.removeShipperStorage();
        this.redirect("/");
    },

    // customer data
    setCustomerStorage: function (object) {
        localStorage.setItem('customer', JSON.stringify(object));
    },
    getCustomerStorage: function () {
        return localStorage.getItem('customer');
    },
    removeCustomerStorage: function () {
        localStorage.removeItem('customer');
    },

    // shipper data
    setShipperStorage: function (object) {
        localStorage.setItem('shipper', JSON.stringify(object));
    },
    getShipperStorage: function () {
        return localStorage.getItem('shipper');
    },
    removeShipperStorage: function () {
        localStorage.removeItem('shipper');
    },

    // datetime
    getDateTimeFormat: function (date = null, format = "YYYY-MM-DD HH:mm:ss") {
        if (date != null) {
            return moment(date).format(format);
        }

        return moment().format(format);
    },
    redirect: function(url) { window.location.href = url; },
    reloadPage: function() {location.reload(true);},
    apiProvincesURL: APIUrl+"v1/region/province",
    apiBanksURL: 'https://api.vietqr.io/v2/banks',
    encodePassword: function(pass) { return md5(sha256(pass)); },
    regexPassword: /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/,
    regexEmail: /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/,
    regexPhone: /[0-9]{10}/g,
    typeEmail: 'EMAIL',
    typePassword: 'PASSWORD',
    regexStringValidate: function(string, type) {
        let valid = false;
        switch (type) {
            case "EMAIL":
                if (string.match(commonFunction.regexEmail)) {
                    valid = true;
                }
                break;
            case "PASSWORD":
                if (string.match(commonFunction.regexPassword)) {
                    valid = true;
                }
                break;
            case "PHONE":
                if (string.match(commonFunction.regexPhone)) {
                    valid = true;
                }
                break;
            default:
                valid = false;
        }
        return valid;
    },
    removeAllCookiesClient: function(cookies){
        cookies.remove("authenication_cookies");
        cookies.remove("accesstoken_cookies");
        cookies.remove("authenrole_cookies");
        cookies.remove("idrequest_cookies");
    },
    typeNotifyDelete: 'DELETE',
    typeNotifyCreate: 'CREATE',
    typeNotifySuccess: 'SUCCESS',
    typeNotifyFail: 'FAIL',
    typeNotifyCancel: 'CANCEL',
    typeNotify: {
        Delete: 'DELETE',
        Create: 'CREATE',
        Success: 'SUCCESS',
        Fail: 'FAIL',
        Cancel: 'CANCEL',
    },
    generateCodeToText(text) {
        switch(text){
            case "SAVE_WARE_WAITING_CHECK": return "Lưu kho chờ check";
            case "CAN_NOT_RETURN": return "Không thể hoàn trả";
            case "DON_NOT_SAVE_RETURN_NOW": return "Không lưu kho hoàn ngay";
            case "STANDARD": return "Standard";
            case "FAST": return "Fast";
            case "SUPER": return "Super";
            default: return "";
        }
    },
    disableInput: function(className, id){
        if(className) {
            $(className).prop( "disabled", true )
        }
        if(id){
            $(id).prop( "disabled", true )
        }
    },
    currentURL: function(){ return location.href;},
    orderStatus: {
        Pending: "PENDING",
        Success : "SUCCESS",
        Payment: "PAYMENTED",
        Delivery: "DELIVERY",
        Occurred: "OCCURRED",
        Cancel: "CANCEL",
        Delay: "DELAY",
        Updated: "UPDATED",
        ReChecking: "RECHECKING",
        Storage: "STORAGE",
        TransferShipper: "TRANSFER_SHIPPER",
        ShipperConfirm: "SHIPPER_CONFIRMED",
        Done: "DONE",
        Return: "RETURN",  // bổ sung
        AdminConfirm: "ADMIN_CONFIRMED",
    },
}

export { commonFunction };