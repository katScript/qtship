<script setup>
import {ref, defineProps, defineEmits} from "vue";
import {
  ShoppingCartOutlined,
  FieldNumberOutlined,
  ColumnHeightOutlined,
  ColumnWidthOutlined,
  DragOutlined,
  ExpandOutlined,
  MoneyCollectOutlined,
  DeleteOutlined,
  PlusOutlined,
} from "@ant-design/icons-vue";

const props = defineProps({
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
        price: null,
        long: null,
        width: null,
        height: null,
        specialType: false
      }
    }
  }
});

const emits = defineEmits(['validate-product', 'change-product', 'product-selector-change']);

const selectedList = ref(props.productList);
const productForm = ref({
  id: props.productSelector.id,
  name: props.productSelector.name,
  qty: props.productSelector.qty,
  weight: props.productSelector.weight,
  price: props.productSelector.price,
  long: props.productSelector.long,
  width: props.productSelector.width,
  height: props.productSelector.height,
  specialType: props.productSelector.specialType
});

const handleRemoveProduct = (key) => {
  delete selectedList.value[key];

  emits('change-product', selectedList);
}

const handleAddProduct = () => {
  emits('validate-product', addProduct);
}

const addProduct = () => {
  let size = Object.keys(selectedList).length;
  selectedList.value[size] = productForm.value;

  productForm.value = {
    id: null,
    name: ' ',
    qty: null,
    weight: null,
    price: null,
    long: null,
    width: null,
    height: null,
    specialType: false
  }

  emits('product-selector-change', productForm);
}
</script>

<template>
  <div class="border mt-3">
    <div class="fs-5 border-bottom bg-danger px-3 py-1 text-white">Thông tin sản phẩm</div>
    <div class="p-3">
      <div v-for="(product, key) in selectedList"
          :key="key"
          class="my-3 border-bottom pb-3">
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
        <div class="d-flex gap-2 mt-3">
          <a-form-item :name="['productSelector', 'name']" class="flex-fill">
            <a-input v-model:value="productForm.name" placeholder="Tên sản phẩm">
              <template #prefix>
                <ShoppingCartOutlined class="me-2"/>
              </template>
            </a-input>
          </a-form-item>
        </div>
        <div class="d-flex gap-2 mt-3">
          <a-form-item :name="['productSelector', 'long']" class="flex-fill">
            <a-input v-model:value="productForm.long"
                     :min="1"
                     style="width: 100%"
                     placeholder="Dài"
                     @change="emits('product-selector-change', productForm)"
            >
              <template #prefix>
                <DragOutlined class="me-2" />
              </template>
            </a-input>
          </a-form-item>
          <a-form-item :name="['productSelector', 'width']"
                       class="flex-fill">
            <a-input v-model:value="productForm.width"
                     :min="0"
                     style="width: 100%"
                     placeholder="Rộng"
                     @change="emits('product-selector-change', productForm)"
            >
              <template #prefix>
                <ColumnWidthOutlined class="me-2" />
              </template>
            </a-input>
          </a-form-item>
          <a-form-item :name="['productSelector', 'height']"
                       class="flex-fill">
            <a-input v-model:value="productForm.height"
                     :min="0"
                     style="width: 100%"
                     placeholder="Cao"
                     @change="emits('product-selector-change', productForm)"
            >
              <template #prefix>
                <ColumnHeightOutlined class="me-2" />
              </template>
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-switch
              v-model:checked="productForm.specialType"
            />
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
