import ProductData from "@/components/models/product/product-data";

export default class PackageData {
    product = new ProductData();

    data = {
        id : null,
        product : this.product.getData(),
        name: "",
        qty : 0,
        price: 0,
        weight: 0,
        image: "",
        createdAt : null,
        updatedAt : null
    };

    setData (data) {
        this.data.id = data.id;
        this.data.product = data.product;
        this.data.qty = data.qty;
        this.data.price = data.price;
        this.data.name = data.name;
        this.data.weight = data.weight;
        this.data.image = data.image;
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