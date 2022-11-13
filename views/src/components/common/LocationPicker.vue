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

export default {
    props: ['dataValidate'],
    components: {},
    data() {
        return {
            province: {
                label: "",
                code: ""
            },
            district: {
                label: "",
                code: "",
                provinceId: ""
            },
            ward: {
                label: "",
                code: "",
                districtId: ""
            },
            provinceList: [],
            districtList: [],
            wardList: [],
            street: ""
        }
    },
    mounted() {
        this.axios.get(commonFunction.apiProvincesURL).then((response) => {
            let dataProvince = response.data.results;
            for (let province in dataProvince) {
                this.provinceList.push({
                    label: dataProvince[province].province_name,
                    code: dataProvince[province].province_id
                });
            }
        });
    },
    watch: {
        province: {
            handler: function () {
                this.$emit('updateAddress', this.province, 'PROVINCE');

                let districtList = [],
                    district = {};

                this.axios.get(
                    commonFunction.apiProvincesURL + 'district/' + this.province?.code
                ).then((response) => {
                    let dataDistricts = response.data.results;
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

                this.axios.get(
                    commonFunction.apiProvincesURL + 'ward/' + this.district?.code
                ).then((response) => {
                    let dataWards = response.data.results;
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