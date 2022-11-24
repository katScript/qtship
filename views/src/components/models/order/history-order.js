export default class HistoryOrder {

    data = {
        id: null,
        orderId: null,
        detail: "",
        status: "",
        createdAt: "",
        updatedAt: ""
    };

    setData (data) {
        this.data.id = data.id;
        this.data.orderId = data.orderId;
        this.data.detail = data.detail;
        this.data.status = data.status;
        this.data.createdAt = data.createdAt;
        this.data.updatedAt = data.updatedAt;
    }

    getData () {
        return this.data;
    }
}