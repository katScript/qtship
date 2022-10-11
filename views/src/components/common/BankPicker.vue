<template>
    <div class="bank-picker">
        <div class="row">
            <div class="col-md-12">
                <input type="text" class="form-control input-form-register" id="input-register-name-bank"
                    placeholder="Tên chủ tài khoản">
            </div>
            <div class="col-md-4">
                <input type="text" class="form-control input-form-register"
                    id="input-register-number-bank" placeholder="Số tài khoản Ngân hàng">
            </div>
            <div class="col-md-5">
                <v-select v-model="bankSelected" :options="bankList" placeholder="Chọn Ngân hàng"></v-select>
            </div>
            <div class="col-md-3">
                <input type="text" class="form-control input-form-register" id="input-register-branch-bank"
                    placeholder="Chi nhánh ngân hàng" :disabled="isDisabledBranchBank">
            </div>
        </div>
    </div>
</template>

<script>
import { commonFunction } from '../../scripts/ulti'
    export default {
        components: {
        },
        data() {
            return {
                //{code: 'CA', country: 'Canada'}
                apiBanksURL: '',
                bankSelected:'',
                bankList: [],
                isDisabledBranchBank: true
            }
        },
        mounted() {
            const self = this;
            // Banks
            self.apiBanksURL = commonFunction.apiBanksURL;
            self.axios.get(self.apiBanksURL).then((response) => {
                let dataBanks = response.data.data;
                for (let bank in dataBanks) {
                    this.bankList.push({ label: dataBanks[bank].shortName + ' - '+ dataBanks[bank].name, code: dataBanks[bank].code });
                }
            })
        },
        watch: {
            bankSelected: {
                handler: function () {
                    if(this.bankSelected.code == 'VBA') {
                        this.isDisabledBranchBank = false;
                    }
                }
            }
        }
    }
</script>