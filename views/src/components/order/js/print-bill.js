import GenBarCode from "@/components/common/GenBarCode.vue";
import QRCodeVue3 from "qrcode-vue3";

export default {
    props: ['isDisplayBill'],
    data() {
        return {
        }
    },
    components: {
        GenBarCode,
        QRCodeVue3
    },
    mounted() {
    },
    methods: {
        closePopUpBillOrder(value) {
            this.$emit("closePopUpBillOrder", value)
        }
    }
}
