import { sha256 } from "js-sha256";
import md5 from "md5";

const commonFunction = {
    redirect(url) { window.location.href = url; },
    reloadPage() {location.reload();},
    apiProvincesURL: 'https://vapi.vnappmob.com/api/province/',
    apiBanksURL: 'https://api.vietqr.io/v2/banks',
    encodePassword(pass) { return md5(sha256(pass)); },
    regexPassword: /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/,
    regexEmail: /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/,
    regexPhone: /[0-9]{10}/g,
    typeEmail: 'EMAIL',
    typePassword: 'PASSWORD',
    regexStringValidate(string, type) {
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
    typeShippingSTANDARD: "STANDARD",
    typeShippingFAST: "FAST",
    typeShippingSUPER: "SUPER"
}

export { commonFunction };