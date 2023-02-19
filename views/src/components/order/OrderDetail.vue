<template>
    <div class="management-client-page">
        <div class="bill-detail" :class="isShowBill ? 'show' : 'hide'">
            <BillOrder @displayBill="displayBill" :orderData="orderData"/>
        </div>
        <NavbarClient/>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <NotficationClient/>
                </div>
                <div class="col-md-8">
                    <div class="row">
                        <div class="title text-center">
                            <h4>Chi tiết đơn hàng {{ orderData.orderCode }}</h4>
                            <label for="">Ngày tạo: </label> <small> {{ orderData.createdAt }}</small>
                            <hr/>
                        </div>
                        <div class="col-md-8">
                            <!-- detail order -->
                            <div class="order-detail-area m-auto p-2">
                                <div class="content-oder-detail">
                                    <h6>Thông tin người gửi:</h6>
                                    <div class="ps-3">
                                        <small><label for="">Người gửi: </label> {{ orderData.senderName }}</small>
                                        <br/>
                                        <small><label for="">Địa chỉ: </label> {{ orderData.senderAddress }}</small>
                                        <br/>
                                        <small><label for="">Số điện thoại: </label> {{ orderData.senderPhone }}</small>
                                        <br/>
                                    </div>
                                    <br/>

                                    <h6>Thông tin người nhận:</h6>
                                    <div class="ps-3">
                                        <small><label for="">Người nhận: </label> {{
                                                orderData.shippingAddress.name
                                            }}</small>
                                        <br/>
                                        <small>
                                            <label for="">Địa chỉ: </label> {{
                                                orderData.shippingAddress.street
                                                + ', '
                                                + orderData.shippingAddress.ward
                                                + ', '
                                                + orderData.shippingAddress.district
                                                + ', '
                                                + orderData.shippingAddress.province
                                            }}
                                        </small>
                                        <br/>
                                        <small><label for="">Số điện thoại: </label> {{
                                                orderData.shippingAddress.phone
                                            }}</small>
                                        <br/>
                                    </div>
                                    <br/>

                                    <h6>Thông tin đơn hàng:</h6>
                                    <div class="ps-3">
                                        <label for="">Sản phẩm: </label>
                                        <div class="products ps-2">
                                            <small v-for="(item, index) in orderData.products" :key="index"><label
                                                for="">{{ index + 1 }}. </label> {{ item.name }} - SL: {{ item.qty }}
                                                <br></small>
                                        </div>
                                        <br>
                                        <small><label for="">Tiền ship: </label> {{ orderData.shippingFee }}</small>
                                        <br/>
                                        <small><label for="">Khối lượng đơn hàng: </label> {{
                                                orderData.totalWeight
                                            }}</small>
                                        <br>
                                        <small><label for="">Ghi chú: </label> {{ orderData.note }}</small>
                                        <br/>
                                        <small><label for="">Mã giảm giá đã áp dụng: </label> {{
                                                orderData.coupon
                                            }}</small>
                                        <hr/>
                                        <p>
                                            <label for="">Tổng giá trị đơn hàng: </label> {{ orderData.subtotal }} VNĐ
                                        </p>
                                        <br/>
                                    </div>
                                    <button v-if="orderData.status !== 'PENDING'" class="btn btn-primary"
                                            v-on:click="isShowBill = true">Xuất hóa đơn
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <h5>Tình trạng đơn hàng</h5>
                            <a-timeline>
                                <a-timeline-item v-for="(item, index) in historyOrder" :key="index">
                                    <b>{{ item.createdAt }}</b>:
                                    <p>{{genCodeStatusToTextDisplay(item.status)}}</p>
                                </a-timeline-item>
                            </a-timeline>
                        </div>
                    </div>
                </div>
                <div class="col-md-2">
                    <ToolbarRight/>
                </div>
            </div>
        </div>

        <br/>
        <br/><br/>
        <FooterClient/>
    </div>
</template>

<script src="@/components/order/js/order-detail.js"/>

<style scoped>
.bill-detail {
    width: 150vw;
    height: 150vh;
    position: fixed;
    background-color: rgba(0, 0, 0, 0.356);
    z-index: 100;
}

.timeline-item {
    padding-bottom: 1.5em
}

.timeline-item i {
    font-size: 30px;
    padding-top: 2px;
    padding-bottom: 2px;
    color: #7f8c8d;
}

i.actioned {
    color: #2ecc71;
}

.ul-detail-timeline-item li {
    list-style: circle;
}

.show {
    display: block;
}

.hide {
    display: none;
}
</style>