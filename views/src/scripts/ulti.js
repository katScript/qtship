import { sha256 } from "js-sha256";
import md5 from "md5";
import $ from "jquery"

const commonFunction = {
    redirect: function(url) { window.location.href = url; },
    reloadPage: function() {location.reload(true);},
    apiProvincesURL: 'https://vapi.vnappmob.com/api/province/',
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
    DOMAIN_URL: 'https://api.dcodetest.com/',
    EXPIRED_TIME_COOKIES: "1d",
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
    typeShipping: [
        "STANDARD",
        "FAST",
        "SUPER"
    ],
    typeReturnOrder: [
        "SAVE_WARE_WAITING_CHECK",
        "CAN_NOT_RETURN",
        "DON_NOT_SAVE_RETURN_NOW"
    ],
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
    typeOrderSuccess: "DONE",
    typeOrderDelivery: "DELiVERY",
    typeOrderOccurred: "OCCURRED",
    typeOrderCancel: "CANCEL",
}

export { commonFunction };