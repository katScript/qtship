<script setup>
import { ref, watch, defineEmits, defineProps } from "vue";
import { ShoppingCartOutlined, FieldNumberOutlined, ExpandOutlined, MoneyCollectOutlined, DeleteOutlined, PlusOutlined } from "@ant-design/icons-vue";

const props = defineProps({
  startValidate: {
    type: Boolean,
    default: false
  },
  endValidate: {
    type: Boolean,
    default: false
  }
});
const hasError = ref(false);
const emits = defineEmits(['on-error-product', 'on-throw-product'])
const listProduct = ref([
  {
    name: '',
    qty: '',
    price: '',
    weight: '',
  }
]);
const handleAddProduct = () => {
  listProduct.value.push({
    name: '',
    qty: '',
    price: '',
    weight: '',
  });
}
const handleDeleteProduct = (key) => {
  listProduct.value = listProduct.value.filter((x, index) => index != key);
}

watch(
  () => listProduct.value,
  () => {
    emits('on-throw-product', listProduct.value);
  },
  { deep: true }
);

watch(
  () => props.startValidate,
  () => {
    if (props.startValidate) {
      hasError.value = false;
      listProduct.value.forEach(product => {
        for (const property in product) {
          if (product[property] == '') {
            hasError.value = true;
            break;
          }
        }
      })
    }
    emits('on-error-product', hasError.value);
  },
);

watch(
  () => props.endValidate,
  () => {
    if (props.endValidate) {
      listProduct.value = [{
        name: '',
        qty: '',
        price: '',
        weight: '',
      }]
    }
  },
)
</script>

<template>
  <div class="border">
    <div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin sản phẩm</div>
    <div class="p-3">
      <div v-for="(product, index) in listProduct" :key="index" class="my-3 border-bottom pb-3">
        <a-input v-model:value="product.name" :class="{ 'border-danger': product.name == '' && hasError }"
          placeholder="Tên sản phẩm">
          <template #prefix>
            <ShoppingCartOutlined class="me-2" />
          </template>
        </a-input>
        <div class="d-flex gap-2 mt-3">
          <a-input-number v-model:value="product.qty" class="flex-fill"
            :class="{ 'border-danger': product.qty == '' && hasError }" :min="1" placeholder="Số lượng">
            <template #prefix>
              <FieldNumberOutlined class="me-2" />
            </template>
          </a-input-number>
          <a-input-number v-model:value="product.weight" class="flex-fill"
            :class="{ 'border-danger': product.weight == '' && hasError }" :min="0" placeholder="Khối lượng">
            <template #prefix>
              <ExpandOutlined class="me-2" />
            </template>
          </a-input-number>
          <a-input-number v-model:value="product.price" class="flex-fill"
            :class="{ 'border-danger': product.price == '' && hasError }" :min="0" placeholder="Giá tiền">
            <template #prefix>
              <MoneyCollectOutlined class="me-2" />
            </template>
          </a-input-number>
          <PlusOutlined class="btn btn-success text-white px-3" v-if="index == listProduct.length - 1"
            @click.prevent="handleAddProduct" />
          <DeleteOutlined class="btn btn-danger text-white px-3" v-else @click.prevent="handleDeleteProduct(index)" />
        </div>
      </div>
    </div>
  </div>
</template>