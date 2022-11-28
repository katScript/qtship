export default class Liquidity {
    data = {
        id: null,
        customerId: null,
        moneyReceivable: 0.0,
        ppPm: 0.0,
        ccCash: 0.0,
        ppCash: 0.0,
        forControl: 0.0,
        receivable: 0.0,
        couponCash: 0.0,
        createdAt: "",
        updatedAt: ""
    };

    getData () {
        return this.data;
    }

    setData(data) {
        this.id = data.id;
        this.customerId = data.customerId;
        this.moneyReceivable = data.moneyReceivable;
        this.ppPm = data.ppPm;
        this.ccCash = data.ccCash;
        this.ppCash = data.ppCash;
        this.forControl = data.forControl;
        this.receivable = data.receivable;
        this.couponCash = data.couponCash;
        this.createdAt = data.createdAt;
        this.updatedAt = data.updatedAt;
    }
}