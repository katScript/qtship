<script setup>
import { defineProps, defineEmits } from 'vue';

defineProps({
	openDetail: {
		type: Boolean,
		default: false
	},
	detail: {
		type: Object,
		required: true
	}
})

const emits = defineEmits(['on-close-detail'])

const handleClose = () => {
	emits('on-close-detail')
}
</script>
<template>
	<a-modal :visible="openDetail" title="Chi tiết khách hàng" width="40%" style="top: 20px;" @cancel="handleClose">
		<div class="content">
			<div class="border">
				<div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin khách hàng</div>
				<div class="p-3">
					<div><span class="font-weight-bold">Mã khách hàng: </span>{{ detail?.customerId }}</div>
					<div><span class="font-weight-bold">Tên khách hàng: </span>{{ detail?.fullName }}</div>
					<div><span class="font-weight-bold">Số điện thoại: </span>{{ detail?.phone }}</div>
					<div><span class="font-weight-bold">Địa chỉ email: </span>{{ detail?.email }}</div>
				</div>
			</div>
			<a-row type="flex" class="my-4" :gutter="[10, 10]" align="top">
				<a-col :span="12">
					<div class="border">
						<div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin địa chỉ</div>
						<div class="p-3">
							<div><span class="font-weight-bold">Tên cửa hàng / công ty : </span>{{ detail?.companyName }}</div>
							<div><span class="font-weight-bold">Tỉnh / thành phố: </span>{{ (detail && detail?.addressSet?.length) && detail?.addressSet[0]?.province }}</div>
							<div><span class="font-weight-bold">Quận / huyện: </span>{{ (detail && detail?.addressSet?.length) && detail?.addressSet[0]?.district }}</div>
							<div><span class="font-weight-bold">Xã / phường </span>{{ (detail && detail?.addressSet?.length) && detail?.addressSet[0]?.ward }}</div>
							<div><span class="font-weight-bold">Số nhà - Đường: </span>{{ detail?.addressSet?.length && detail?.addressSet[0]?.street }}</div>
						</div>
					</div>
				</a-col>
				<a-col :span="12">
					<div class="border">
						<div class="fs-4 border-bottom bg-secondary px-3 py-1 text-white">Thông tin sở hữu</div>
						<div class="p-3">
							<div><span class="font-weight-bold">Tên chủ sở hữu: </span>{{ (detail && detail?.forControls?.length) && detail?.forControls[0]?.holderName }}</div>
							<div><span class="font-weight-bold">Số thẻ: </span>{{ (detail && detail?.forControls?.length) && detail?.forControls[0]?.cardNumber }}</div>
							<div><span class="font-weight-bold">Ngân hàng: </span>{{ (detail && detail?.forControls?.length) && detail?.forControls[0]?.bank }}</div>
						</div>
					</div>
				</a-col>
			</a-row>
		</div>
		<template #footer>
			<a-button type="primary" @click="handleClose">Đóng</a-button>
		</template>
	</a-modal>
</template>

<style scoped>
</style>