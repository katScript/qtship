import ProductData from "@/components/models/product/product-data";

export default class PackageData {
    product = new ProductData();

    data = {
        id : null,
        product : this.product.getData(),
        qty : 0,
        createdAt : null,
        updatedAt : null
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
}