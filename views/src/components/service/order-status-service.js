export default class OrderStatusService {
    listOrderStatusSupport = {
        SUCCESS: {
            total: 0,
            data: {}
        },
        DELIVERY: {
            total: 0,
            data: {}
        },
        DELAY: {
            total: 0,
            data: {}
        },
        RETURN: {
            total: 0,
            data: {}
        }
    };

    getListOrderStatusSupport() {
        return this.listOrderStatusSupport;
    }
}