<template>
    <div class="bank-picker">
        <div class="row">
            <div class="col-md-12">
                <small class="text-danger">{{msgValidationFor.forControl.holderName}}</small>
                <input v-model="holderName" type="text" class="form-control input-form-register" id="input-register-name-bank"
                    placeholder="Tên chủ tài khoản">
            </div>
            <div class="col-md-4">
                <small class="text-danger">{{msgValidationFor.forControl.cardNumber}}</small>
                <input v-model="cardNumber" type="text" class="form-control input-form-register" id="input-register-number-bank"
                    placeholder="Số tài khoản Ngân hàng">
            </div>
            <div class="col-md-5">
                <small class="text-danger">{{msgValidationFor.forControl.bank}}</small>
                <v-select v-model="bankSelected" :options="bankList" placeholder="Chọn Ngân hàng"
                    style="margin-bottom: 28px;"></v-select>
            </div>
            <div class="col-md-3">
                <input v-model="branchBank" type="text" class="form-control input-form-register" id="input-register-branch-bank"
                    placeholder="Chi nhánh ngân hàng" :disabled="isDisabledBranchBank">
            </div>
        </div>
    </div>
</template>

<script>
    import { commonFunction } from '../../scripts/ulti'
    export default {
        props: ['msgValidationFor'],
        components: {
        },
        data() {
            return {
                //{code: 'CA', country: 'Canada'}
                apiBanksURL: '',
                holderName: '',
                bankSelected: '',
                cardNumber: '',
                branchBank:'',
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
                    this.bankList.push({ label: dataBanks[bank].shortName + ' - ' + dataBanks[bank].name, code: dataBanks[bank].code });
                }
            })
        },
        watch: {
            bankSelected: {
                handler: function () {
                    this.$emit('updateForControl', this.bankSelected, 'BANK');
                    if (this.bankSelected.code == 'VBA') {
                        this.isDisabledBranchBank = false;
                    }
                }
            },
            holderName: {
                handler: function () {this.$emit('updateForControl', this.holderName, 'HOLDER');}},
            cardNumber: {
                handler: function () {this.$emit('updateForControl', this.cardNumber, 'CARDNUMBER');}},
            branchBank:{
                handler: function () {this.$emit('updateForControl', this.branchBank, 'ADDRESS');}},
        }
    }
</script>