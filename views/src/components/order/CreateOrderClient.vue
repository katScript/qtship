<template>
    <div class="management-client-page">
        <div :class="isLoading ? 'show' : 'hide'">
            <ActionLoading />
        </div>
        <NavbarClient />
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <NotficationClient />
                </div>
                <div class="col-md-8">
                    <div class="create-order-area">
                        <div class="row">
                            <div class="col-12">
                                <h4 class="title-management">
                                    <i class="fa-solid fa-circle-plus"></i> Tạo mới đơn hàng
                                </h4>
                                <br />
                            </div>
                            <div class="col-md-6 info-reciver-senter">
                                <h5>Thông tin người nhận</h5>
                                <div class="row">
                                    <div class="col-1"><i class="fa-solid fa-phone"></i></div>
                                    <div class="form-group col-11">
                                        <small class="text-danger">{{msgValidationFor.shippingAddress.senderPhone}}</small>
                                        <input type="text" class="form-control" id=""
                                            placeholder="Số điện thoại người nhận"
                                            v-model="shippingData.phone" />
                                        <br />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-1"><i class="fa-solid fa-user"></i></div>
                                    <div class="form-group col-11">
                                        <small class="text-danger">{{msgValidationFor.shippingAddress.phone}}</small>
                                        <input type="text" class="form-control" id="" placeholder="Tên người nhận"
                                            v-model="shippingData.senderName" />
                                        <br />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-1">
                                        <i class="fa-solid fa-location-dot"></i>
                                    </div>
                                    <div class="col-11">
                                        <span v-if="idOrderUpdateQuery"><b>Địa chỉ hiện tại: </b> {{ addressOrderUpdated
                                            }}</span>
                                        <LocationPicker @updateReceiverAddress="updateReceiverAddress" :shippingData="shippingData" :msgValidationFor="msgValidationFor" />
                                    </div>
                                </div>
                                <hr />
                                <h5>Thông tin người gửi</h5>
                                <div class="row">
                                    <div class="col-1"><i class="fa-solid fa-phone"></i></div>
                                    <div class="form-group col-11">
                                        <input type="text" class="form-control" id=""
                                            placeholder="Số điện thoại người gửi" v-model="orderData.senderPhone"/>
                                        <br />
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-1"><i class="fa-solid fa-user"></i></div>
                                    <div class="form-group col-11">
                                        <input type="text" class="form-control" id="" placeholder="Thông tin người gửi"
                                            v-model="orderData.senderName"/>
                                        <br />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <div class="col-1">
                                        <i class="fa-solid fa-location-dot"></i>
                                    </div>
                                    <div class="col-11">
                                        <input type="text" class="form-control" id="" placeholder="Địa chỉ người gửi"
                                            v-model="orderData.senderAddress"/>
                                        <br />
                                    </div>
                                </div>
                                <hr />
                                <h5>Phương thức giao & lấy hàng</h5>
                                <div class="form-group row">
                                    <div class="col-1">
                                        <i class="fa-solid fa-truck-fast"></i>
                                    </div>
                                    <div class="col-11">
                                        <small class="text-danger">{{msgValidationFor.shippingType}}</small>
                                        <select class="form-select" aria-label="Default select example"
                                            v-model="orderData.shippingType">
                                            <option value="" selected>- Chọn phương thức giao hàng -</option>
                                            <option v-for="(type, index) in listTypeShipping" :key="index">
                                                {{ generateCodeToText(type) }}
                                            </option>
                                        </select>
                                        <br />
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <small class="text-danger">{{msgValidationFor.isSelectedTypeLH}}</small>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="flexRadioDefault"
                                            id="flexRadioDefault1" v-on:click="isSelectedTypeLH = 'LHTBC'" />
                                        <label class="form-check-label" for="flexRadioDefault1">
                                            Gửi hàng tại bưu cục
                                        </label>
                                    </div>
                                    <div class="form-check">
                                        <input class="form-check-input" type="radio" name="flexRadioDefault"
                                            id="flexRadioDefault2" v-on:click="isSelectedTypeLH = 'LHTN'" />
                                        <label class="form-check-label" for="flexRadioDefault2">
                                            Lấy hàng tận nơi
                                        </label>
                                    </div>
                                </div>
                                <div :class="isSelectedTypeLH == 'LHTN' ? 'show' : 'hide'">
                                    <div class="form-group row">
                                        <div class="col-4">
                                            <label for="" class="label-control">Địa điểm lấy hàng</label>
                                        </div>
                                        <div class="col-8">
                                            <small class="text-danger">{{msgValidationFor.warehouseId}}</small>
                                            <select class="form-select" aria-label="Default select example"
                                                v-model="orderData.warehouse">
                                                <option value="">- Địa điểm lấy hàng -</option>
                                                <option :value="w.data" v-for="(w, index) in listWarehouseByCustomer"
                                                    :key="index">
                                                    {{ w.data.name }}
                                                </option>
                                            </select>
                                            <br />
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-4">
                                            <label for="" class="label-control">Thời gian lấy hàng/bưu cục nhận
                                                hàng</label>
                                        </div>
                                        <div class="col-8">
                                            <input type="datetime-local"
                                            class="form-control" name="" v-model="shippingDateTime"/>
                                            <br />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 info-order-detail">
                                <div class="row">
                                    <div class="col-6">
                                        <h5>Thông tin sản phẩm</h5>
                                    </div>
                                    <div class="col-6">
                                        <a href="/client/stock/create-product" class="btn btn-outline-success"
                                            style="float: right">Danh sách sản phẩm</a>
                                    </div>
                                </div>
                                <br />
                                <div class="list-product-in-order" v-for="(ps, index) in productSelected" :key="index">
                                    <div class="row">
                                        <div class="col-11">
                                            <small class="text-danger">{{msgValidationFor.products}}</small>
                                            <div class="row">
                                                <div class="col-sm-2">
                                                    <img v-if="ps.image" :src="ps.image" alt="" class="img-fluid mb-1"
                                                        width="" height="" />
                                                    <img v-else src="../../images/img-default.jpg" alt="" class="mb-1"
                                                        width="" height="" style="width: 90%;" />
                                                </div>
                                                <div class="col-sm-10">
                                                    <div class="row">
                                                        <div class="col-10 m-auto">
                                                            <select class="form-select"
                                                                aria-label="Default select example"
                                                                v-model="productSelected[index]">
                                                                <!-- index 0 is default -->
                                                                <option :value="p"
                                                                    v-for="(p, index) in listProductByCustomer"
                                                                    :key="index">
                                                                    {{ p.data.name }}
                                                                </option>
                                                            </select>
                                                        </div>
                                                        <div class="col-2">
                                                            <input type="number" class="form-control mb-1" min="0"
                                                                v-model="productSelected[index].data.qty"
                                                                placeholder="SL" />
                                                        </div>
                                                        <div class="col-6">
                                                            <input type="text"
                                                                class="form-control product-select-weight"
                                                                v-model="productSelected[index].data.weight"
                                                                placeholder="Trọng lượng (kg)" disabled readonly />
                                                        </div>
                                                        <div class="col-6">
                                                            <input type="text"
                                                                class="form-control product-select-public-price"
                                                                v-model="productSelected[index].data.publicPrice"
                                                                placeholder="Giá bán (VNĐ)" disabled readonly />
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-1">
                                            <button class="btn btn-outline-success"
                                                v-on:click="addItemProductList(index)">
                                                <i class="fa-solid fa-plus plus-order-number"></i>
                                            </button>
                                            <button class="btn btn-outline-danger"
                                                :class="productSelected.length > 1 ? 'show' : 'hide'"
                                                v-on:click="removeItemProductList(productSelected[index])">
                                                <i class="fa-solid fa-minus minus-order-number"></i>
                                            </button>
                                        </div>
                                    </div>
                                    <hr />
                                </div>

                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="row">
                                            <div class="col-sm-4">
                                                Phí ship: <span class="feeShip">0 đ</span>
                                                <br />
                                            </div>
                                            <div class="col-sm-8">
                                                <select class="form-select" aria-label="Default select example"
                                                    v-model="orderData.shippingFee">
                                                    <option value="true">Shop trả ship</option>
                                                    <option value="false">Người nhận trả ship</option>
                                                </select>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-group">
                                                    <label for="">Ghi chú đơn hàng</label>
                                                    <textarea type="" class="form-control" id=""
                                                        aria-describedby="emailHelp" rows="4"
                                                        v-model="orderData.note"
                                                        placeholder="Nhập ghi chú của đơn hàng" />
                                                    <br />
                                                </div>
                                            </div>
                                        </div>
                                        <br />
                                    </div>
                                    <div class="col-12">
                                        <div class="row">
                                            <div class="col-8">
                                                <input type="text" class="form-control" placeholder="Mã giảm giá"
                                                    v-model="orderData.coupon" />
                                            </div>
                                            <div class="col-4">
                                                <button class="btn btn-danger w-100" v-on:click="
                            isDisplayListVourcer = !isDisplayListVourcer
                          ">
                                                    {{
                                                    !isDisplayListVourcer ? "Thêm mã giảm giá" : "Đóng"
                                                    }}
                                                </button>
                                            </div>
                                        </div>

                                        <div class="row" :class="isDisplayListVourcer ? 'show' : 'hide'">
                                            <div class="col-12">
                                                <div class="list-group">
                                                    <a href="#" class="list-group-item list-group-item-action">
                                                        <div class="row">
                                                            <div class="col-1 mt-1">
                                                                <i class="fa-solid fa-ticket text-danger"
                                                                    style="font-size: 24px"></i>
                                                            </div>
                                                            <div class="col-3 mt-1">MPVC001-VC</div>
                                                            <div class="col-5 mt-1">
                                                                Miễn phí phí vận chuyển
                                                            </div>
                                                            <div class="col-3">
                                                                <button class="btn btn-outline-danger w-100"
                                                                    v-on:click="applySaleCode('MPVC001-VC')">
                                                                    Áp dụng
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </a>
                                                    <a href="#" class="list-group-item list-group-item-action">
                                                        <div class="row">
                                                            <div class="col-1 mt-1">
                                                                <i class="fa-solid fa-ticket text-danger"
                                                                    style="font-size: 24px"></i>
                                                            </div>
                                                            <div class="col-3 mt-1">MPVC002-VC</div>
                                                            <div class="col-5 mt-1">
                                                                Miễn phí phí vận chuyển
                                                            </div>
                                                            <div class="col-3">
                                                                <button class="btn btn-outline-danger w-100"
                                                                    v-on:click="applySaleCode('MPVC002-VC')">
                                                                    Áp dụng
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <br />
                                    </div>
                                    <div class="col-sm-4">
                                        <label for="" class="label-control"><span style="color: red">(*)</span> TH Hoàn
                                            hàng:
                                        </label>
                                        <br />
                                    </div>
                                    <div class="col-sm-8">
                                        <small class="text-danger">{{msgValidationFor.returnCode}}</small>
                                        <select class="form-select" aria-label="Default select example"
                                            v-model="orderData.returnCode">
                                            <option :value="item" v-for="(item, index) in listTypeReturnOrder"
                                                :key="index">
                                                {{ generateCodeToText(item) }}
                                            </option>
                                        </select>
                                        <br />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr />
                        <div class="row">
                            <div class="col-md-6"></div>
                            <div class="col-md-6">
                                <input v-model="isAccepted" type="checkbox" class="form-check-input"
                                    id="radio-accept-contract" />
                                <label class="form-check-label" for="exampleCheck1" style="padding-left: 10px">
                                    Tôi đã đọc và đồng ý với Chính sách bảo mật thông tin</label>
                                <br />
                                <button class="btn btn-success m-auto w-100" style="font-size: 18px"
                                    v-on:click="createOrder" v-if="!idOrderUpdateQuery">
                                    <i class="fa-solid fa-up-right-from-square"></i> Đăng đơn hàng
                                </button>
                                <button class="btn btn-success m-auto w-50" style="font-size: 18px"
                                    v-on:click="updateOrder" v-if="idOrderUpdateQuery">
                                    <i class="fa-solid fa-up-right-from-square"></i> Cập nhật đơn
                                    hàng
                                </button>
                                <a href="/client/orders" class="btn btn-dark ms-2 w-25" style="font-size: 18px"
                                    v-if="idOrderUpdateQuery">
                                    <i class="fa-solid fa-up-right-from-square"></i> Hủy bỏ
                                </a>
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

<script src="@/components/order/js/create-order.js"/>
<style scoped>
    a {
        text-decoration: none;
    }

    .hide {
        display: none;
    }

    .show {
        display: inline;
    }

    .vs__search,
    .vs__search:focus {
        margin: 7px 0px;
    }

    .vs__dropdown-toggle {
        width: 105% !important;
    }
</style>