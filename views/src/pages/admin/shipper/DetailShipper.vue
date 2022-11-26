<script setup>
import { defineProps, defineEmits, computed, watch, reactive } from "vue";
import { save, create } from "@/services/shipper";
import { useStore } from "vuex";
import { message } from "ant-design-vue";

const store = useStore();

const props = defineProps({
  detail: {
    type: Object,
    required: true,
  },
});

const emits = defineEmits(["on-close-detail"]);

const formState = reactive({});

const isLoading = computed(() => store.getters.isLoading);
const action = computed(() => {
  const action = {
    update: 1,
    create: 2,
  };

  return action[props.detail.action] || null;
});
const title = computed(() => {
  const title = {
    update: "Cập nhật",
    create: "Tạo mới",
  };

  return title[props.detail.action] || "Chi tiết";
});
watch(
  () => props.detail.show,
  () => {
    if (props.detail.action == "update") {
      Object.assign(formState, props.detail.data);
    }

    if (props.detail.action == "create") {
      for (const key in formState) {
        delete formState[key];
      }
    }
  },
  { deep: true }
);
const handleClose = (value = false) => {
  emits("on-close-detail", value);
};

const onFinish = async (values) => {
  const data = {};
  if (props.detail.action == "update") {
    data.id = formState.id;
  }
  data.fullName = values.fullName;
  data.phone = values.phone;
  data.email = values.email;
  data.address = values.address;
  data.currentAddress = values.currentAddress;

  store.dispatch("setLoading", true);
  try {
    if (props.detail.action == "update") {
      await save(data);
    }
    if (props.detail.action == "create") {
      await create({
        username: values.username,
        password: values.password,
        data: data,
      });
    }

    message.success("Thành công");
  } catch (e) {
    console.log(e.response.data);
    message.error("Thất bại");
  }
  store.dispatch("setLoading", false);
  handleClose(true);
};

const onFinishFailed = (errorInfo) => {
  console.log("Failed:", errorInfo);
};
</script>

<template>
  <a-modal
    :visible="detail.show"
    :title="title + ' khách hàng'"
    width="40%"
    style="top: 20px"
    @cancel="handleClose"
  >
    <div class="content">
      <a-form
        :model="formState"
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        autocomplete="off"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
      >
        <a-form-item
          v-if="detail.action == 'create'"
          label="Tài khoản"
          name="username"
          :rules="[{ required: true, message: 'Vui lòng nhập tài khoản !' }]"
        >
          <a-input v-model:value="formState.username" />
        </a-form-item>

        <a-form-item
          v-if="detail.action == 'create'"
          label="Mật khẩu"
          name="password"
          :rules="[{ required: true, message: 'Vui lòng nhập mật khẩu !' }]"
        >
          <a-input-password v-model:value="formState.password" />
        </a-form-item>

        <a-form-item
          label="Tên người giao hàng"
          name="fullName"
          :rules="[
            { required: true, message: 'Vui lòng nhập tên người giao hàng !' },
          ]"
        >
          <a-input v-model:value="formState.fullName" />
        </a-form-item>

        <a-form-item
          label="Số điện thoại"
          name="phone"
          :rules="[
            { required: true, message: 'Vui lòng nhập số điện thoại !' },
          ]"
        >
          <a-input v-model:value="formState.phone" />
        </a-form-item>

        <a-form-item
          label="Địa chỉ email"
          name="email"
          :rules="[
            {
              required: true,
              type: 'email',
              message: 'Vui lòng nhập địa chỉ email !',
            },
          ]"
        >
          <a-input v-model:value="formState.email" />
        </a-form-item>

        <a-form-item
          label="Địa chỉ"
          name="address"
          :rules="[
            {
              required: true,
              message: 'Vui lòng nhập địa chỉ !',
            },
          ]"
        >
          <a-input v-model:value="formState.address" />
        </a-form-item>

        <a-form-item
          label="Địa chỉ hiện tại"
          name="currentAddress"
          :rules="[
            {
              required: true,
              message: 'Vui lòng nhập địa chỉ hiện tại !',
            },
          ]"
        >
          <a-input v-model:value="formState.currentAddress" />
        </a-form-item>

        <a-form-item v-if="action" :wrapper-col="{ offset: 8, span: 16 }">
          <a-button type="primary" html-type="submit" :loading="isLoading">{{
            title
          }}</a-button>
        </a-form-item>
      </a-form>
    </div>
    <template #footer>
      <a-button type="danger" @click="handleClose">Đóng</a-button>
    </template>
  </a-modal>
</template>
