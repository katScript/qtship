<script setup>
import {ref, watch, defineEmits, defineProps, onMounted} from "vue";
import {
    ShoppingCartOutlined,
    FieldNumberOutlined,
    ExpandOutlined,
    MoneyCollectOutlined,
    DeleteOutlined,
    PlusOutlined,
} from "@ant-design/icons-vue";
import {listProduct} from "@/services/product";
import {commonFunction} from "@/scripts/ulti";

let customerStorage = JSON.parse(commonFunction.getCustomerStorage());

const productList = ref([]);
const productListData = ref([]);

const handleGetProduct = async () => {
    const {data} = await listProduct(customerStorage.id);
    let productData = [];

    Object.values(data).forEach(val => {
        productData.push({
            value: val.name,
            data: val
        });
    });

    productListData.value = productData;
}

const handleProductChange = (value, data) => {
    if (value.length > 1) {
        value.pop();
    } else {
        let currentData = {
            product: {
                id: null,
            },
            qty: "1",
            name: value[0],
            publicPrice: "0",
            weight: "0.1",
            specialType: false
        }

        if (Object.keys(data[0]).length !== 0) {
            currentData = data[0].data;
            inputAddProduct.value.product.id = currentData.id;
        }

        processInputAddProduct(currentData);
    }
}

const processInputAddProduct = (data) => {
    inputAddProduct.value.name = data.name;
    inputAddProduct.value.qty = data.qty;
    inputAddProduct.value.price = data.publicPrice ? data.publicPrice : data.price;
    inputAddProduct.value.weight = data.weight;
    inputAddProduct.value.specialType = data.specialType;
    inputAddProduct.value.productSelect = data.name ? [data.name] : [];
}

const props = defineProps({
    startValidate: {
        type: Boolean,
        default: false,
    },
    endValidate: {
        type: Boolean,
        default: false,
    },
    products: {
        type: Array,
        default: () => [],
    },
    hasError: {
        type: Boolean,
        default: false,
    },
});
const emits = defineEmits(["on-error-product", "on-throw-product"]);

const inputAddProduct = ref({
    productSelect: [],
    product: {
        id: null,
    },
    name: "",
    qty: "",
    price: "",
    weight: "",
    specialType: ""
});

//
const handleAddProduct = () => {
    productList.value.push(inputAddProduct.value);

    inputAddProduct.value = {
        productSelect: [],
        product: {
            id: null,
        },
        name: "",
        qty: "",
        price: "",
        weight: "",
        specialType: ""
    };
};

const handleDeleteProduct = (key) => {
    productList.value = productList.value.filter((x, index) => index !== key);
};

watch(
    () => props.products,
    () => {
        if (props.products.length > 0) {
            let length = props.products.length,
                currentProduct = props.products[length - 1];

            if (length > 2) {
                productList.value = props.products.slice(0, length - 1);
            }

            processInputAddProduct(currentProduct);
        } else {
            productList.value = [];
            inputAddProduct.value = {
                productSelect: [],
                product: {
                    id: null,
                },
                name: "",
                qty: "",
                price: "",
                weight: "",
                specialType: ""
            };
        }
    }
);

watch(
    () => productList.value,
    () => {
        let returnData = [...productList.value, ...[inputAddProduct.value]];
        emits("on-throw-product", returnData);
    },
    {deep: true}
);

watch(
    () => inputAddProduct.value,
    () => {
        let returnData = [...productList.value, ...[inputAddProduct.value]];
        emits("on-throw-product", returnData);
    }
);

onMounted(() => {
    let returnData = [...productList.value, ...[inputAddProduct.value]];
    emits("on-throw-product", returnData);
});
handleGetProduct();
</script>

<template>
    <div class="border">
        <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">
            Thông tin sản phẩm
        </div>
        <div class="p-3">
            <div
                v-for="(product, index) in productList"
                :key="index"
                class="my-3 border-bottom pb-3"
            >
                <a-input
                    v-model:value="product.name"
                    :class="{ 'border-danger': !product.name && hasError }"
                    placeholder="Tên sản phẩm"
                >
                    <template #prefix>
                        <ShoppingCartOutlined class="me-2" />
                    </template>
                </a-input>
                <div class="d-flex gap-2 mt-3">
                    <a-input-number
                        v-model:value="product.qty"
                        class="flex-fill"
                        :class="{ 'border-danger': !product.qty && hasError }"
                        :min="1"
                        placeholder="Số lượng"
                    >
                        <template #prefix>
                            <FieldNumberOutlined class="me-2"/>
                        </template>
                    </a-input-number>
                    <a-input-number
                        v-model:value="product.weight"
                        class="flex-fill"
                        :class="{ 'border-danger': !product.weight && hasError }"
                        :min="0"
                        placeholder="Khối lượng"
                    >
                        <template #prefix>
                            <ExpandOutlined class="me-2"/>
                        </template>
                    </a-input-number>
                    <a-input-number
                        v-model:value="product.price"
                        class="flex-fill"
                        :class="{ 'border-danger': !product.price && hasError }"
                        :min="0"
                        placeholder="Giá tiền"
                    >
                        <template #prefix>
                            <MoneyCollectOutlined class="me-2"/>
                        </template>
                    </a-input-number>
                    <DeleteOutlined
                        class="btn btn-danger text-white px-3"
                        @click.prevent="handleDeleteProduct(index)"
                    />
                </div>
            </div>

            <div class="my-3 border-bottom pb-3">
                <a-select
                    v-model:value="inputAddProduct.productSelect"
                    mode="tags"
                    style="width: 100%"
                    :class="{ 'border-danger': !inputAddProduct.name && hasError }"
                    placeholder="Tên sản phẩm"
                    :options="productListData"
                    @change="handleProductChange"
                >
                    <template #prefix>
                        <ShoppingCartOutlined class="me-2"/>
                    </template>
                </a-select>
                <div class="d-flex gap-2 mt-3">
                    <a-input-number
                        v-model:value="inputAddProduct.qty"
                        class="flex-fill"
                        :class="{ 'border-danger': !inputAddProduct.qty && hasError }"
                        :min="1"
                        placeholder="Số lượng"
                    >
                        <template #prefix>
                            <FieldNumberOutlined class="me-2"/>
                        </template>
                    </a-input-number>
                    <a-input-number
                        v-model:value="inputAddProduct.weight"
                        class="flex-fill"
                        :class="{ 'border-danger': !inputAddProduct.weight && hasError }"
                        :min="0"
                        placeholder="Khối lượng"
                    >
                        <template #prefix>
                            <ExpandOutlined class="me-2"/>
                        </template>
                    </a-input-number>
                    <a-input-number
                        v-model:value="inputAddProduct.price"
                        class="flex-fill"
                        :class="{ 'border-danger': !inputAddProduct.price && hasError }"
                        :min="0"
                        placeholder="Giá tiền"
                    >
                        <template #prefix>
                            <MoneyCollectOutlined class="me-2"/>
                        </template>
                    </a-input-number>
                    <PlusOutlined
                        class="btn btn-success text-white px-3"
                        @click.prevent="handleAddProduct"
                    />
                </div>
            </div>
        </div>
    </div>
</template>
  