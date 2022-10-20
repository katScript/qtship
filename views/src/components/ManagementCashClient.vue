<template>
    <div class="management-client-page">
        <NavbarClient />
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <NotficationClient />
                </div>
                <div class="col-md-8">
                    <div class="management-cash">
                        <div class="row">
                            <h4><i class="fa-solid fa-money-bill-transfer"></i> Quản lí dòng tiền</h4>
                            <div class="col-12">
                                Tổng số tiền shop sẽ nhận được trong lần đối soát tới là: 0 đ (Số dư chốt)
                                <ul>
                                    <li>(2) Tiền cộng: Khuyến mãi, Bồi hoàn</li>
                                    <li>(3) Phí dịch vụ: Phí giao hàng, Phí DV phát sinh, Phí hoàn hàng, Nợ tồn, Phí chuyển
                                        khoản</li>
                                    <li>(4) Số dư hiện tại là tiền về tính đến thời điểm hiện tại, sau khi trừ các khoản phí
                                        phát sinh dự kiến của tất cả các đơn (chưa bao gồm phí chuyển khoản)</li>
                                    <li>(5) Số dư chốt là tiền về tính đến thời điểm hiện tại, sau khi trừ các khoản phí phát
                                        sinh dự kiến của tất cả các đơn đã chốt trạng thái</li>
                                    <li>(6) Tiền đang luân chuyển: Số tiền đối soát dự kiến mà Shop sẽ nhận được của các đơn
                                        hàng GHTK đã lấy thành công nhưng chưa hoàn thành việc giao hàng</li>
                                </ul>
                            </div>
                            <div class="col-12">
                                <table class="table table-hover">
                                    <thead>
                                      <tr>
                                        <th scope="col">Tiền thu hộ (1)</th>
                                        <th scope="col">Tiền cộng (2)</th>
                                        <th scope="col">Phí dịch vụ (3)</th>
                                        <th scope="col">Số dư hiện tại (4)</th>
                                        <th scope="col">Số dư chốt (5)</th>
                                        <th scope="col">Tiền đang luân chuyển (6)</th>
                                        <th scope="col">Chức năng</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                      </tr>
                                    </tbody>
                                  </table>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-md-3"><h5>Lịch sử đối soát</h5></div>
                            <div class="col-md-9">
                                <div class="row">
                                    <div class="col-md-4"><input type="text" class="form-control" placeholder="Mã đơn hàng"></div>
                                    <div class="col-md-4"><input type="date" name="" id="" class="form-control w-100"></div>
                                    <div class="col-md-4"><button class="btn btn-success w-100"><i class="fa-solid fa-magnifying-glass"></i> Tìm kiếm</button></div>
                                </div>
                            </div>
                            <div class="col-12">
                                Tiền đối soát = (1) + (2) - (3)
                                <ul>
                                    <li>(2) Tiền cộng: Khuyến mãi, Bồi hoàn, Trả trước, Phí dịch vụ hoàn lại, Phí dịch vụ đã thanh toán</li>
                                    <li>(3) Tiền trừ: Phí giao hàng, Phí bảo hiểm, Phí hoàn hàng, Phí thay đổi địa chỉ giao, Phí lưu kho, Tiền Tip CoD, Phí gia cố, Phí dịch vụ cấn trừ, Nợ tồn, Phí chuyển khoản</li>
                                </ul>
                            </div>
                            <div class="col-12">
                                <table class="table table-hover">
                                    <thead>
                                      <tr>
                                        <th scope="col">Thông tin hóa đơn</th>
                                        <th scope="col">Tiền thu hộ (1)</th>
                                        <th scope="col">Tiền cộng (2)</th>
                                        <th scope="col">Phí dịch vụ (3)</th>
                                        <th scope="col">Tiền đối soát</th>
                                        <th scope="col">Chức năng</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                      <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                      </tr>
                                    </tbody>
                                  </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <ToolbarRight />
                </div>
            </div>
        </div>

        <br />
        <br /><br />
        <FooterClient />
    </div>
</template>

<script>
    import NavbarClient from "./common/NavbarClient.vue";
    import FooterClient from "./common/FooterClient.vue";
    import ToolbarRight from "./common/ToolbarRight.vue";
    import NotficationClient from "./common/NotficationClient.vue";

    import { useCookies } from "vue3-cookies";
    // import { commonFunction } from '../scripts/ulti'

    export default {
        components: {
            NavbarClient,
            FooterClient,
            ToolbarRight,
            NotficationClient
        },
        data() {
            return {
                orderCode: "",
                receiverPhonenumber: "",
                filterTime: "label",
                filterTimeDS: "label",
                classFilterTimeAbout: "d-none",
                classFilterTimeAboutDS: "d-none",
                countOrder: 3,
            };
        },

        setup() {
            const { cookies } = useCookies();
            return {
                cookies,
            };
        },

        // <data, methods...>

        mounted() {
            // let authenication_cookies = this.cookies.get("authenication_cookies");
            // if(authenication_cookies == null){
            //   commonFunction.redirect('/');
            // } else {
            //     commonFunction.redirect('/client/management');
            // }
        },
        watch: {

        },
        method: {

        },
    };
</script>

<style scoped>
    ul li {
        list-style: none;
    }
</style>