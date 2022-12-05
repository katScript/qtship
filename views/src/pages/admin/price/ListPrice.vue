<script setup>
import { ref } from "vue";
import { price, priceSample, areaSample } from "@/services/office";
import { message } from "ant-design-vue";
import { UploadOutlined } from "@ant-design/icons-vue";

const filePrice = ref([]);
const fileArea = ref([]);
const name = ref("");
const description = ref("");
const uploading = ref(false);

const getAreaSample = async () => {
  const { data } = await areaSample({ responseType: "blob" });
  const href = URL.createObjectURL(data);
  const link = document.createElement("a");
  link.href = href;
  link.setAttribute("download", "AreaSample.csv");
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
  URL.revokeObjectURL(href);
};

const getPriceSample = async () => {
  const { data } = await priceSample({ responseType: "blob" });
  const href = URL.createObjectURL(data);
  const link = document.createElement("a");
  link.href = href;
  link.setAttribute("download", "PriceSample.csv");
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
  URL.revokeObjectURL(href);
};

const handleRemovePrice = () => {
  filePrice.value = [];
};

const handleRemoveArea = () => {
  fileArea.value = [];
};

const beforeUploadPrice = (file) => {
  filePrice.value = [file];
  return false;
};

const beforeUploadArea = (file) => {
  fileArea.value = [file];
  return false;
};

const handleSetPrice = async () => {
  const formData = new FormData();
  formData.append("areaConfig", fileArea.value[0]);
  formData.append("priceConfig", filePrice.value[0]);
  formData.append("name", name.value);
  formData.append("description", description.value);
  uploading.value = true;
  try {
    await price(formData);
    message.success("Thành công");
  } catch (e) {
    message.error("Thất bại");
  }
  uploading.value = false;
};
//
</script>
<template>
  <div class="px-4">
    <div class="my-2 border-bottom px-4 py-2 fs-2 text-uppercase">
      Tạo bảng giá mới
    </div>
    <a-button type="primary" class="me-3" @click="getPriceSample"
      >File giá mẫu</a-button
    >
    <a-button type="primary" @click="getAreaSample">File khu vực mẫu</a-button>
    <div class="mt-3">
      <div class="mt-2">
        <a-input style="width: 300px" v-model:value="name" placeholder="Tên" />
      </div>
      <div class="mt-2">
        <a-input
          style="width: 300px"
          v-model:value="description"
          placeholder="Mô tả"
        />
      </div>
      <div class="mt-2" style="width: 300px">
        <a-upload
          :max-count="1"
          :file-list="fileArea"
          :before-upload="beforeUploadArea"
          @remove="handleRemoveArea"
        >
          <a-button style="min-width: 300px; text-align: left">
            <UploadOutlined style="transform: translate(0px, -3px)" />
            Vui lòng chọn file khu vực (areaConfig)
          </a-button>
        </a-upload>
      </div>
      <div class="mt-2" style="width: 300px">
        <a-upload
          :max-count="1"
          :file-list="filePrice"
          :before-upload="beforeUploadPrice"
          @remove="handleRemovePrice"
        >
          <a-button style="min-width: 300px; text-align: left">
            <UploadOutlined style="transform: translate(0px, -3px)" />
            Vui lòng chọn file giá (priceConfig)
          </a-button>
        </a-upload>
        <a-button
          type="primary"
          :disabled="!filePrice.length && !fileArea.length"
          :loading="uploading"
          style="margin-top: 16px"
          @click="handleSetPrice"
        >
          {{ uploading ? "Đang cập nhật" : "Cập nhật" }}
        </a-button>
      </div>
    </div>
  </div>
</template>
