<script setup>
import {ref, defineProps, defineEmits} from "vue";
import {
  ShoppingCartOutlined,
  FieldNumberOutlined,
  ExpandOutlined,
  MoneyCollectOutlined,
  DeleteOutlined,
  PlusOutlined,
} from "@ant-design/icons-vue";
import {listProduct} from "@/services/product";

const props = defineProps({
  refForm: {
    type: Object
  },
  customerId: {
    type: Number,
    default: null
  },
  productList: {
    type: Object,
    default: () => {
      return {};
    }
  },
  productSelector: {
    type: Object,
    default: () => {
      return {
        id: null,
        name: '',
        qty: null,
        weight: null,
        price: null
      }
    }
  }
});

const emits = defineEmits(['validate-product', 'change-product', 'product-selector-change']);

const productList = ref([{
  options: []
}]);
const selectedList = ref(props.productList);
const productForm = ref({
  id: props.productSelector.id,
  name: props.productSelector.name,
  qty: props.productSelector.qty,
  weight: props.productSelector.weight,
  price: props.productSelector.price
});

const selectedProduct = ref({});

const onProductSelect = (input, option) => {
  let productFormData = productForm.value;
  productFormData.id = option.data.id;
  productFormData.name = option.data.name;
  productFormData.qty = productFormData.qty === null || productFormData.qty === '' ? 1 : productFormData.qty;
  productFormData.weight = option.data.weight;
  productFormData.price = option.data.publicPrice;
  selectedProduct.value = option.data;

  emits('product-selector-change', productForm);
}

const onProductFilter = (input, option) => {
  return option.value.toLowerCase().indexOf(input.toLowerCase()) >= 0;
}

const handleRemoveProduct = (key) => {
  delete selectedList.value[key];

  emits('change-product', selectedList);
}

const handleAddProduct = () => {
  emits('validate-product', addProduct);
}

const addProduct = () => {
  if (Object.keys(selectedProduct.value).length !== 0) {
    selectedList.value[selectedProduct.value.sku] = productForm.value;
  } else {
    let size = Object.keys(selectedList).length;
    selectedList.value[size] = productForm.value;
  }

  emits('change-product', selectedList);

  selectedProduct.value = {};
  productForm.value = {
    id: null,
    name: ' ',
    qty: null,
    weight: null,
    price: null
  }

  emits('product-selector-change', productForm);
}

const handGetListProduct = async () => {
  const {data} = await listProduct(props.customerId);

  Object.values(data).forEach(val => {
    productList.value[0].options.push({
      data: val,
      value: val.name + ` (${val.sku})`
    });
  });
}

handGetListProduct();
</script>

<template>
  <div class="border mt-3">
    <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Thông tin sản phẩm</div>
    <div class="p-3">
      <div
          v-for="(product, key) in selectedList"
          :key="key"
          class="my-3 border-bottom pb-3"
      >
        <a-input
            v-model:value="product.name"
            :disabled="true"
            placeholder="Tên sản phẩm"
        >
          <template #prefix>
            <ShoppingCartOutlined class="me-2"/>
          </template>
        </a-input>
        <div class="d-flex gap-2 mt-3">
          <a-input-number
              v-model:value="product.qty"
              class="flex-fill"
              :disabled="true"
              placeholder="Số lượng"
          >
            <template #prefix>
              <FieldNumberOutlined class="me-2"/>
            </template>
          </a-input-number>
          <a-input-number
              v-model:value="product.weight"
              class="flex-fill"
              :disabled="true"
              placeholder="Khối lượng"
          >
            <template #prefix>
              <ExpandOutlined class="me-2"/>
            </template>
          </a-input-number>
          <a-input-number
              v-model:value="product.price"
              class="flex-fill"
              :disabled="true"
              placeholder="Giá tiền"
          >
            <template #prefix>
              <MoneyCollectOutlined class="me-2"/>
            </template>
          </a-input-number>
          <DeleteOutlined
              class="btn btn-danger text-white px-3"
              @click="handleRemoveProduct(key)"
          />
        </div>
      </div>

      <div class="my-3 border-bottom pb-3">
        <div class="certain-category-search-wrapper" style="width: 100%">
          <a-form-item :name="['productSelector', 'name']" class="my-2">
            <a-auto-complete
                v-model:value="productForm.name"
                style="width: 100%"
                placeholder="Tên sản phẩm"
                :options="productList[0].options"
                :filter-option="onProductFilter"
                @select="onProductSelect"
                @change="emits('product-selector-change', productForm)"
            >
              <template #option="item">
                <div style="display: flex; justify-content: space-between">
                  {{ item.value }}
                </div>
              </template>
              <template #prefix>
                <ShoppingCartOutlined class="me-2"/>
              </template>
            </a-auto-complete>
          </a-form-item>
        </div>
        <div class="d-flex gap-2 mt-3">
          <a-form-item :name="['productSelector', 'qty']"
                       class="flex-fill">
            <a-input v-model:value="productForm.qty"
                     :min="1"
                     style="width: 100%"
                     placeholder="Số lượng"
                     @change="emits('product-selector-change', productForm)"
            >
              <template #prefix>
                <FieldNumberOutlined class="me-2"/>
              </template>
            </a-input>
          </a-form-item>
          <a-form-item :name="['productSelector', 'weight']"
                       class="flex-fill">
            <a-input v-model:value="productForm.weight"
                     :min="0"
                     style="width: 100%"
                     placeholder="Khối lượng"
                     @change="emits('product-selector-change', productForm)"
            >
              <template #prefix>
                <ExpandOutlined class="me-2"/>
              </template>
            </a-input>
          </a-form-item>
          <a-form-item :name="['productSelector', 'price']"
                       class="flex-fill">
            <a-input v-model:value="productForm.price"
                     :min="0"
                     style="width: 100%"
                     placeholder="Giá tiền"
                     @change="emits('product-selector-change', productForm)"
            >
              <template #prefix>
                <MoneyCollectOutlined class="me-2"/>
              </template>
            </a-input>
          </a-form-item>
          <a-form-item>
            <PlusOutlined
                class="btn btn-success text-white px-3"
                @click.prevent="handleAddProduct"
            />
          </a-form-item>
        </div>
      </div>
    </div>
  </div>
</template>
