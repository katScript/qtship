export default class CouponData {
    data = {
        id: null,
        code: "",
        enable: false,
        rule: "",
        value: "",
        validFrom: "",
        validTo: "",
        validRoles: null,
        createdAt: null,
        updateAt: null
    };

    errors = {
    }

    setData (data) {
        this.data.id = data.id;
        this.data.code = data.code;
        this.data.enable = data.enable;
        this.data.rule = data.rule;
        this.data.value = data.value;
        this.data.validFrom = data.validFrom;
        this.data.validTo = data.validTo;
        this.data.validRoles = data.validRoles;
        this.data.createdAt = data.createdAt;
        this.data.updateAt = data.updateAt;
    }

    getData () {
        return this.data;
    }

    getErrors () {
        return this.errors;
    }

    validate() {
        let valid = true;

        return valid;
    }
}