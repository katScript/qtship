export default class ProductData {
    data = {
        id : null,
        customerId : null,
        customerCode : "",
        customerName : "",
        sku : "",
        qty : 0,
        name : "",
        weight : 0.0,
        basePrice : 0.0,
        publicPrice : 0.0,
        description : "",
        image : "",
        createdAt : null,
        updatedAt : null
    };

    setData (data) {
        this.data.id = data.id;
        this.data.customerId = data.customerId;
        this.data.customerCode = data.customerCode;
        this.data.customerName = data.customerName;
        this.data.sku = data.sku;
        this.data.qty = data.qty;
        this.data.name = data.name;
        this.data.weight = data.weight;
        this.data.basePrice = data.basePrice;
        this.data.publicPrice = data.publicPrice;
        this.data.description = data.description;
        this.data.image = data.image;
        this.data.createdAt = data.createdAt;
        this.data.updatedAt = data.updatedAt;
    }

    getData () {
        return this.data;
    }
}