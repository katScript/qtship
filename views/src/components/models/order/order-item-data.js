import ShippingData from "@/components/models/shipping/shipping-data";

export default class OrderItemData {
    shippingData = new ShippingData();

    data = {
        id: null,
        price: 0.0,
        shippingAddress: this.shippingData.getData(),
        products: []
    };

    setData (data) {
        this.data.id = data.id;
        this.data.price = data.price;
        this.data.shippingAddress = data.shippingAddress;
        this.data.products = data.products;
    }

    getData () {
        return this.data;
    }

    setShippingAddress(shippingAddress) {
        this.data.shippingAddress = shippingAddress;
    }

    setProduct(products) {
        this.data.products = products;
    }
}