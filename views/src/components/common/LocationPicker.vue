<template>
    <div class="row location-picker">
        <div class="col-md-4">
            <v-select v-model="ttpSelected" :options="ttpList"></v-select>
            <!-- binding data to param . Example : ttpSelected : {label: '', code: ''} -->
        </div>
        <div class="col-md-4">
            <v-select :options="qhList"> </v-select>
        </div>
        <div class="col-md-4">
            <v-select :options="pxList"></v-select>
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
                ttpSelected:'',
                ttpList: [],
                qhList: [],
                pxList: []
            }
        },
        mounted() {
            this.axios.get(commonFunction.getAllProvinces()).then((response) => {
                let dataProvince = response.data.data.data;
                for (let province in dataProvince) {
                    this.ttpList.push({ label: dataProvince[province].name, code: dataProvince[province].code });
                }
            })
        },
        methods: {

        }
    }
</script>

<style>

</style>