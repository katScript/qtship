<script setup>
import { ref } from "vue";
import { price } from "@/services/office";
import { message } from "ant-design-vue";
import { UploadOutlined } from "@ant-design/icons-vue";

const fileList = ref([]);
const name = ref("");
const description = ref("");
const uploading = ref(false);
const handleRemove = (file) => {
  const index = fileList.value.indexOf(file);
  const newFileList = fileList.value.slice();
  newFileList.splice(index, 1);
  fileList.value = newFileList;
};
const beforeUpload = (file) => {
  fileList.value = [...fileList.value, file];
  return false;
};

const handleSetPrice = async () => {
  const formData = new FormData();
  formData.append("areaConfig", fileList.value[0]);
  formData.append("priceConfig", fileList.value[1]);
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
    <div class="">
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
      <div class="mt-2">
        <a-upload
          :file-list="fileList"
          :before-upload="beforeUpload"
          @remove="handleRemove"
        >
          <a-button>
            <UploadOutlined style="transform: translate(0px, -3px)" />
            Vui lòng chọn 2 file
          </a-button>
        </a-upload>
        <div class="text-danger mt-2">
          Vui lòng chọn file khu vực (areaConfig) trước rồi sau đó chọn file giá
          (priceConfig).
        </div>
        <a-button
          type="primary"
          :disabled="fileList.length < 2"
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
