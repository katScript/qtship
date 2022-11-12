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
        this.data.product = data.product;
        this.data.qty = data.qty;
        this.data.createdAt = data.createdAt;
        this.data.updatedAt = data.updatedAt;
    }

    getData () {
        return this.data;
    }

    setProduct(product) {
        this.data.product = product;
    }
}