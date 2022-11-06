<template>
    <div class="pop-up-notify">
        <table class="h-100">
            <tr>
                <td>
                    <div class="content-notify">
                        <div class="row">
                            <div class="col-12 d-flex justify-content-end">
                                <button class="btn" v-on:click="closePopupNotify"><i
                                        class="fa-solid fa-rectangle-xmark"></i></button>
                            </div>
                        </div>
                        <div class="row">

                            <!-- <div class="col-12 text-center" v-if="typeNotify == 'SUCCESS'">
                                <br>
                                <img src="../../images/success-svg.svg" width="80" alt="Action successfully">
                                <br><br>
                                <h4>Thành công!</h4>
                                <hr>
                                <button class="btn btn-success" v-on:click="reloadPage()">Hoàn thành</button>
                            </div>

                            <div class="col-12 text-center" v-else-if="typeNotify == 'FAIL'">
                                <br>
                                <img src="../../images/fail-svg.svg" width="80" alt="Action fail!">
                                <br><br>
                                <h4>Đã có lỗi xảy ra!</h4>
                                <hr>
                                <button class="btn btn-danger" v-on:click="reloadPage()">Chấp nhận</button>
                            </div> -->

                            <div class="col-12 text-center" v-if="typeNotify == 'DELETE'">
                                <br>
                                <img src="../../images/warning-svg.svg" class="text-danger" width="80"
                                    alt="Warning Action">
                                <br><br>
                                <h5>Bạn có chắc chắn muốn xóa {{dataNotify.name}} ?</h5>
                                <hr>
                                <button class="btn btn-warning"
                                    v-on:click="confirmDelete(typeComponent, dataNotify)">Xác nhận xóa</button>
                            </div>
                            <div class="col-12 text-center" v-else-if="typeNotify == 'CANCEL'">
                                <br>
                                <img src="../../images/warning-svg.svg" class="text-danger" width="80"
                                    alt="Warning Action">
                                <br><br>
                                <h5>Bạn có chắc chắn muốn hủy {{dataNotify.name}} ?</h5>
                                <hr>
                                <button class="btn btn-warning"
                                    v-on:click="confirmDelete(typeComponent, dataNotify)">Xác nhận hủy</button>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>
</template>

<script>
    import axios from "axios";
    import { commonFunction } from '../../scripts/ulti'

    export default {
        props: ['typeNotify', 'dataNotify', 'typeComponent', 'configRequestApi'],
        data() {
            return {
            }
        },
        methods: {
            closePopupNotify() {
                this.$emit('closePopupNotify');
            },
            confirmDelete(typeComponent, dataNotify) {
                if (typeComponent == 'WAREHOUSE') {
                    axios
                        .post(commonFunction.DOMAIN_URL + "v1/warehouse/delete",
                            {
                                customerId: dataNotify.idRequest,
                                id: dataNotify.data.id
                            }, this.configRequestApi)
                        .then((response) => {
                            if (response.status == 200) {
                                alert(`"SUCCESS: Xóa ${dataNotify.name} thành công!"`);
                                commonFunction.reloadPage();
                            } else {
                                alert(`"FAIL: Xóa ${dataNotify.name} không thành công!"`);
                                commonFunction.reloadPage();
                            }
                        }).catch((e) => {
                            alert('ERROR: Có lỗi xảy ra! Vui lòng thử lại hoặc liên hệ với quản trị viên!');
                            console.log(e);
                            commonFunction.reloadPage();
                        })
                } else if (typeComponent == 'PRODUCT') {
                    axios
                        .post(commonFunction.DOMAIN_URL + "v1/product/delete",
                            {
                                customerId: dataNotify.idRequest,
                                id: dataNotify.data.id
                            }, this.configRequestApi)
                        .then((response) => {
                            if (response.status == 200) {
                                alert(`"SUCCESS: Xóa ${dataNotify.name} thành công!"`);
                                commonFunction.reloadPage();
                            } else {
                                alert(`"FAIL: Xóa ${dataNotify.name} không thành công!"`);
                                commonFunction.reloadPage();
                            }
                        }).catch((e) => {
                            alert('ERROR: Có lỗi xảy ra! Vui lòng thử lại hoặc liên hệ với quản trị viên!');
                            console.log(e);
                            commonFunction.reloadPage();
                        })
                } else if (typeComponent == 'ORDER') {
                    let bodyRequestproducts = [];
                    dataNotify.data.orderSelected.orderItem[0].products.forEach((element, index) => {
                        bodyRequestproducts.push({
                            productId:
                                dataNotify.data.orderSelected.orderItem[0].products[index].id,
                            qty: dataNotify.data.orderSelected.orderItem[0].products[index].qty,
                        });
                    });
                    console.log(bodyRequestproducts)
                    let bodyRequest = {
                        id: dataNotify.data.orderSelected.id.replaceAll("0", ""),
                        customerId: dataNotify.data.orderSelected.customerId
                            .replace(/[^0-9\\.]+/g, "")
                            .replaceAll("0", ""),
                        senderName: dataNotify.data.orderSelected.senderName,
                        senderPhone: dataNotify.data.orderSelected.senderPhone,
                        senderAddress: dataNotify.data.orderSelected.senderAddress,
                        note: dataNotify.data.orderSelected.note,
                        status: commonFunction.typeOrderCancel,
                        feedback: dataNotify.data.orderSelected.feedback,
                        notification: false,
                        shippingFee: dataNotify.data.orderSelected.shippingFee,
                        coupon: dataNotify.data.orderSelected.coupon,
                        warehouseId: dataNotify.data.orderSelected.warehouse.id,
                        shippingType: dataNotify.data.orderSelected.shippingType,
                        shippingDate: dataNotify.data.orderSelected.shippingTime,
                        returnCode: dataNotify.data.orderSelected.returnCode,
                        orderItem: [{
                            shippingAddress: dataNotify.data.orderSelected.orderItem[0].shippingAddress,
                            products: bodyRequestproducts,
                        }],
                    };
                    console.log(bodyRequest);
                    axios
                        .post(
                            commonFunction.DOMAIN_URL + "v1/order/save", bodyRequest,
                            this.configRequestApi
                        )
                        .then((response) => {
                            if (response.status == 200) {
                                this.isLoading = false;
                                alert("SUCCESS: Cập nhật thành công - " + response.data.message);
                                commonFunction.redirect("/client/orders");
                            } else {
                                this.isLoading = false;
                                alert("FAIL: Cập nhật không thành công! Vui lòng thử lại!");
                                commonFunction.redirect("/client/orders");
                            }
                        })
                        .catch((e) => {
                            this.isLoading = false;
                            alert("ERROR: Vui lòng thử lại hoặc liên hệ với quản trị viên!");
                            console.log(e);
                            commonFunction.redirect("/client/orders");
                        });
                }
            },
            reloadPage() {
                commonFunction.reloadPage();
            }
        },
    }
</script>