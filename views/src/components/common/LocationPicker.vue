<template>
    <div class="row location-picker">
        <div class="col-md-4">
            <small class="text-danger">{{ dataValidate.provinceId }}</small>
            <v-select v-model="province" :options="provinceList" placeholder="Chọn Tỉnh/Thành phố"
                      class="form-control-location"></v-select>
            <!-- binding data to param . Example : ttpSelected : {label: '', code: ''} -->
        </div>
        <div class="col-md-4">
            <small class="text-danger">{{ dataValidate.districtId }}</small>
            <v-select v-model="district" :options="districtList" placeholder="Chọn Quận/Huyện"
                      class="form-control-location"></v-select>
        </div>
        <div class="col-md-4">
            <small class="text-danger">{{ dataValidate.wardId }}</small>
            <v-select v-model="ward" :options="wardList" placeholder="Chọn Phường/Xã"
                      class="form-control-location"></v-select>
        </div>

        <div class="col-md-12">
            <br/>
            <small class="text-danger">{{ dataValidate.street }}</small>
            <input type="text" class="form-control input-form-register" id="input-address-detail-shop"
                   placeholder="Địa chỉ cụ thể: Số nhà, tên Đường,..." v-model="street">
        </div>
    </div>
</template>

<script>
import {commonFunction} from '@/scripts/ulti'
import axios from "axios";

export default {
    props: ['dataValidate','warehouseData'],
    components: {},
    data() {
        return {
            province: {
                label: "Chọn Tỉnh/Thành phố",
                code: ""
            },
            district: {
                label: "Chọn Quận/Huyện",
                code: "",
                provinceId: ""
            },
            ward: {
                label: "Chọn Phường/Xã",
                code: "",
                districtId: ""
            },
            provinceList: [],
            districtList: [],
            wardList: [],
            street: ""
        }
    },
    async mounted() {
        if(this.warehouseData != undefined) {
            this.province.label = this.warehouseData?.province == "" ? "Chọn Tỉnh/Thành phố" : this.warehouseData.province;
            this.province.code = this.warehouseData?.provinceId;
            this.district.label = this.warehouseData?.district == "" ? "Chọn Quận/Huyện" : this.warehouseData.district;
            this.district.code = this.warehouseData?.districtId;
            this.ward.label = this.warehouseData?.ward == "" ? "Chọn Phường/Xã" : this.warehouseData.ward;
            this.ward.code = this.warehouseData?.wardId;
            this.street = this.warehouseData?.street == "" ? "Địa chỉ cụ thể: Số nhà, tên Đường,..." : this.warehouseData.street;
        }
        await axios.get(commonFunction.apiProvincesURL).then((response) => {
            let dataProvince = response.data;
            for (let province in dataProvince) {
                this.provinceList.push({
                    label: dataProvince[province].province_name,
                    code: dataProvince[province].province_id
                });
            }
        });
    },
    updated() {

    },
    watch: {
        province: {
            handler: function () {
                this.$emit('updateAddress', this.province, 'PROVINCE');

                let districtList = [],
                    district = {};

                axios.get(
                    commonFunction.apiProvincesURL + '/district/' + this.province?.code
                ).then((response) => {
                    let dataDistricts = response.data;
                    for (let district in dataDistricts) {
                        districtList.push({
                            label: dataDistricts[district].district_name,
                            code: dataDistricts[district].district_id
                        });
                    }

                    if (!this.province || !this.district || this.province.code !== this.district.provinceId) {
                        this.district = district;
                    }

                    this.districtList = districtList;
                });
            }
        },
        district: {
            handler: function () {
                this.$emit('updateAddress', this.district, 'DISTRICT');

                let wardList = [],
                    ward = {};

                axios.get(
                    commonFunction.apiProvincesURL + '/ward/' + this.district?.code
                ).then((response) => {
                    let dataWards = response.data;
                    for (let ward in dataWards) {
                        wardList.push({
                            label: dataWards[ward].ward_name,
                            code: dataWards[ward].ward_id
                        });
                    }

                    if (!this.district || !this.ward || !this.district.code || this.district.code !== this.ward.districtId) {
                        this.ward = ward;
                    }

                    this.wardList = wardList;
                });
            }
        },
        ward: {
            handler: function () {
                this.$emit('updateAddress', this.ward, 'WARD');
            }
        },
        street: {
            handler: function () {
                this.$emit('updateAddress', this.street, 'STREET');
            }
        }
    },
    methods: {
        updateSelectData(data) {
            this.province = {
                label: data.province,
                code: data.provinceId
            };

            this.district = {
                label: data.district,
                code: data.districtId,
                provinceId: data.provinceId
            }

            this.ward = {
                label: data.ward,
                code: data.wardId,
                districtId: data.districtId
            };

            this.street = data.street;
        }
    }
}
</script>

<style scoped>
@media (max-width: 768px) {
    .form-control-location {
        margin-bottom: 28px;
    }
}

.form-control{
    /* border: none; */
}
</style>