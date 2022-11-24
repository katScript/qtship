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

    validate (productData, isValid, msgValidationFor, refFile) {
        msgValidationFor.product = {};
        if (productData.name == "" || productData.name == undefined) {
            isValid += 1;
            msgValidationFor.product.name = "Vui lòng nhập tên sản phẩm!";
        }

        if (productData.sku == "" || productData.sku == undefined) {
            isValid += 1;
            msgValidationFor.product.sku = "Vui lòng nhập mã SKU!";
        }

        if (productData.basePrice == "" || productData.basePrice == undefined) {
            isValid += 1;
            msgValidationFor.product.basePrice =
                "Vui lòng nhập giá gốc của sản phẩm!";
        }

        if (productData.publicPrice == "" || productData.publicPrice == undefined) {
            isValid += 1;
            msgValidationFor.product.publicPrice =
                "Vui lòng nhập giá bán của sản phẩm!";
        }

        if (productData.qty == "" || productData.qty == undefined) {
            isValid += 1;
            msgValidationFor.product.qty =
                "Vui lòng nhập số lượng sản phẩm!";
        }

        if (productData.weight == "" || productData.weight == undefined) {
            isValid += 1;
            msgValidationFor.product.weight =
                "Vui lòng nhập trọng lượng của sản phẩm!";
        }

        if (refFile == undefined && productData.image == "") {
            isValid += 1;
            msgValidationFor.product.image =
                "Vui lòng upload ảnh cho sản phẩm!";
        }
        return isValid == 0 ? true : false;
    }
}