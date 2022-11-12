<template>
    <div class="management-client-page">
        <div :class="isLoading ? 'show' : 'hide'">
            <ActionLoading/>
        </div>
        <NavbarClient/>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2">
                    <NotificationClient/>
                </div>
                <div class="col-md-8">
                    <div class="create-order-area">
                        <div class="row">
                            <div class="col-12">
                                <h4 class="site-title">
                                    <i class="fa-solid fa-circle-plus"></i> Tạo mới đơn hàng
                                </h4>
                                <br />
                            </div>

                            <hr>

                            <div class="col-md-6 left-form">
                                <div class="receiver-info">
                                    <h5 class="block-title">Thông tin người nhận</h5>
                                    <div class="row">
                                        <div class="col-1"><i class="fa-solid fa-phone"></i></div>
                                        <div class="form-group col-11">
                                            <small class="text-danger">{{
                                                    shippingData.errors.phone
                                                }}</small>
                                            <input type="text" class="form-control" id=""
                                                   placeholder="Số điện thoại người nhận"
                                                   v-model="shippingData.data.phone"/>
                                            <br/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-1"><i class="fa-solid fa-user"></i></div>
                                        <div class="form-group col-11">
                                            <small class="text-danger">{{
                                                    shippingData.errors.name
                                                }}</small>
                                            <input type="text" class="form-control" id="" placeholder="Tên người nhận"
                                                   v-model="shippingData.data.name"/>
                                            <br/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-1">
                                            <i class="fa-solid fa-location-dot"></i>
                                        </div>
                                        <div class="col-11">
                                        <span><b>Địa chỉ hiện tại: </b></span>
                                            <LocationPicker @updateData="updateAddress"
                                                            :dataValidate="shippingData.errors"
                                                            ref="locationPicker"
                                            />
                                        </div>
                                    </div>
                                </div>

                                <hr/>

                                <div class="sender-info">
                                    <h5 class="block-title">Thông tin người gửi</h5>
                                    <div class="row">
                                        <div class="col-1"><i class="fa-solid fa-phone"></i></div>
                                        <div class="form-group col-11">
                                            <input type="text" class="form-control" id=""
                                                   placeholder="Số điện thoại người gửi"
                                                   v-model="orderData.data.senderPhone"/>
                                            <br/>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-1"><i class="fa-solid fa-user"></i></div>
                                        <div class="form-group col-11">
                                            <input type="text" class="form-control" id="" placeholder="Thông tin người gửi"
                                                   v-model="orderData.data.senderName"/>
                                            <br/>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-1">
                                            <i class="fa-solid fa-location-dot"></i>
                                        </div>
                                        <div class="col-11">
                                            <input type="text" class="form-control" id="" placeholder="Địa chỉ người gửi"
                                                   v-model="orderData.data.senderAddress"/>
                                            <br/>
                                        </div>
                                    </div>
                                </div>

                                <hr/>

                                <div class="shipping-option">
                                    <h5 class="block-title">Phương thức giao & lấy hàng</h5>

                                    <div class="shipping-type">
                                        <div class="form-group row">
                                            <div class="col-1">
                                                <i class="fa-solid fa-truck-fast"></i>
                                            </div>
                                            <div class="col-11">
                                                <small class="text-danger">{{ orderData.errors.shippingType }}</small>
                                                <select class="form-select" aria-label="Default select example"
                                                        v-model="orderData.data.shippingType">
                                                    <option value="" selected>- Chọn phương thức giao hàng -</option>
                                                    <option v-for="(type, index) in listTypeShipping" :key="index">
                                                        {{ getShippingType(type) }}
                                                    </option>
                                                </select>
                                                <br/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group row">
                                        <div class="form-check" v-for="(value, key) in packageLocationOption" :key="key">
                                            <input class="form-check-input" type="radio" name="flexRadioDefault"
                                                   :id="key" v-model="packageLocation" :value="value.value">
                                            <label class="form-check-label" :for="key">
                                                {{value.label}}
                                            </label>
                                        </div>
                                    </div>

                                    <div class="warehouse-selected">
                                        <div :class="packageLocation === packageLocationOption.HOUSE_ADDRESS.value ? 'show' : 'hide'">
                                            <div class="form-group row">
                                                <div class="col-4">
                                                    <label for="" class="label-control">Địa điểm lấy hàng</label>
                                                </div>
                                                <div class="col-8">
                                                    <small class="text-danger">{{ orderData.errors.warehouse }}</small>
                                                    <select class="form-select" aria-label="Default select example"
                                                            v-model="warehouseData.data.id">
                                                        <option value="">- Địa điểm lấy hàng -</option>
                                                        <option v-for="(w, index) in warehouseList"
                                                                :value="w.data.id"
                                                                :key="index">
                                                            {{ w.data.name }}
                                                        </option>
                                                    </select>
                                                    <br/>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div class="col-4">
                                                    <label class="label-control">Thời gian lấy hàng/bưu cục nhận hàng</label>
                                                </div>
                                                <div class="col-8">
                                                    <input type="datetime-local"
                                                           class="form-control"
                                                           v-on:change="autoSetOrderShippingTime()"
                                                           v-model="orderData.data.shippingTime"/>
                                                    <br/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6 right-form">
                                <div class="order-package">
                                    <div class="row block-head">
                                        <div class="col-6 p-0">
                                            <h5>Thông tin sản phẩm</h5>
                                        </div>
                                        <div class="col-6 p-0">
                                            <a href="/customer/stock/create-product" class="btn btn-outline-success"
                                               style="float: right">Danh sách sản phẩm</a>
                                        </div>
                                    </div>

                                    <div class="package-list">
                                        <div class="list-product-in-order" v-for="(ps, index) in packageList" :key="index">
                                            <div class="row">
                                                <div class="col-11">
                                                    <div class="row">
                                                        <div class="col-sm-2 p-0">
                                                            <img :src="ps.product.image" alt="" style="width: 100%;"/>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <div class="row">
                                                                <div class="col-10 m-auto">
                                                                    <select class="form-select"
                                                                            aria-label="Default select example">
                                                                        <!-- index 0 is default -->
                                                                        <option>{{ ps.product.name }}</option>
                                                                    </select>
                                                                </div>
                                                                <div class="col-2">
                                                                    <input type="number" class="form-control mb-1" v-model="ps.qty"
                                                                           min="0" placeholder="SL"/>
                                                                </div>
                                                                <div class="col-6">
                                                                    <input type="text" class="form-control product-select-weight"
                                                                           v-model="ps.product.weight"
                                                                           placeholder="Trọng lượng (kg)" disabled readonly/>
                                                                </div>
                                                                <div class="col-6">
                                                                    <input type="text" class="form-control product-select-public-price"
                                                                           v-model="ps.product.publicPrice"
                                                                           placeholder="Giá bán (VNĐ)" disabled readonly/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-1">
                                                    <button class="btn btn-outline-danger h-100"
                                                            v-on:click="removeProductInPackage(index)">
                                                        <i class="fa-solid fa-minus minus-order-number"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="list-product-in-order">
                                            <div class="row">
                                                <div class="col-11">
                                                    <div class="row">
                                                        <div class="col-sm-2 p-0">
                                                            <img src="@/images/img-default.jpg" alt=""
                                                                 width="" height="" style="width: 100%;"/>
                                                        </div>
                                                        <div class="col-sm-10">
                                                            <div class="row">
                                                                <div class="col-10 m-auto">
                                                                    <select class="form-select"
                                                                            aria-label="Default select example" v-model="productSelected">
                                                                        <!-- index 0 is default -->
                                                                        <option v-for="(p, index) in productList"
                                                                                :key="index" :value="p"> {{ p.name }} </option>
                                                                    </select>
                                                                </div>
                                                                <div class="col-2">
                                                                    <input type="number" class="form-control mb-1" v-model="packageQtyInput"
                                                                           min="0" placeholder="SL"/>
                                                                </div>
                                                                <div class="col-6">
                                                                    <input type="number" class="form-control product-select-weight"
                                                                           placeholder="Trọng lượng (kg)"/>
                                                                </div>
                                                                <div class="col-6">
                                                                    <input type="number"
                                                                           class="form-control product-select-public-price"
                                                                           placeholder="Giá bán (VNĐ)"/>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="col-1">
                                                    <button class="btn btn-outline-success h-100"
                                                            v-on:click="addProductToPackage()">
                                                        <i class="fa-solid fa-plus plus-order-number"></i>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <hr/>

                                <div class="row">
                                    <div class="shipping-note col-sm-12">
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <!-- phai tinh gia -->
                                                Phí ship: <span class="feeShip">0 đ</span>
                                                <br/>
                                            </div>
                                            <div class="col-sm-8">
                                                <select class="form-select" aria-label="Default select example"
                                                        v-model="orderData.data.shippingFee">
                                                    <option value="true">Shop trả ship</option>
                                                    <option value="false">Người nhận trả ship</option>
                                                </select>
                                            </div>
                                            <div class="col-12">
                                                <div class="form-group">
                                                    <label for="">Ghi chú đơn hàng</label>
                                                    <textarea class="form-control" id=""
                                                              aria-describedby="emailHelp" rows="4"
                                                              v-model="orderData.data.note"
                                                              placeholder="Nhập ghi chú của đơn hàng"/>
                                                    <br/>
                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                    </div>
                                    <div class="coupon col-12">
                                        <div class="row">
                                            <div class="col-8">
                                                <input type="text" class="form-control" placeholder="Mã giảm giá"
                                                       v-model="orderData.data.coupon"/>
                                            </div>
                                            <div class="col-4">
                                                <button class="btn btn-danger w-100" v-on:click="isDisplayListVoucher = !isDisplayListVoucher">
                                                    {{ !isDisplayListVoucher ? "Thêm mã giảm giá" : "Đóng" }}
                                                </button>
                                            </div>
                                        </div>

                                        <div class="row" :class="isDisplayListVoucher ? 'show' : 'hide'">
                                            <div class="col-12">
                                                <div class="list-group">
                                                    <a class="list-group-item list-group-item-action" v-for="(coupon, index) in couponList" :key="index">
                                                        <div class="row">
                                                            <div class="col-1 mt-1">
                                                                <i class="fa-solid fa-ticket text-danger"
                                                                   style="font-size: 24px"></i>
                                                            </div>
                                                            <div class="col-3 mt-1">{{coupon.getData().code}}</div>
                                                            <div class="col-5 mt-1">
                                                                Bo xung sau
                                                            </div>
                                                            <div class="col-3">
                                                                <button class="btn btn-outline-danger w-100" v-on:click="applySaleCode(coupon.getData().code)">
                                                                    Áp dụng
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </a>

                                                </div>
                                            </div>
                                        </div>
                                        <br/>
                                    </div>
                                    <div class="col-sm-4">
                                        <label for="" class="label-control"><span style="color: red">(*)</span> TH Hoàn hàng: </label>
                                        <br/>
                                    </div>
                                    <div class="col-sm-8">
                                        <small class="text-danger">{{ orderData.errors.returnCode }}</small>
                                        <select class="form-select" aria-label="Default select example"
                                                v-model="orderData.data.returnCode">
                                            <option :value="item" v-for="(item, index) in listTypeReturnOrder"
                                                    :key="index">
                                                {{ generateCodeToText(item) }}
                                            </option>
                                        </select>
                                        <br/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr/>
                        <div class="row">
                            <div class="col-md-6"></div>
                            <div class="col-md-6">
                                <input v-model="isAccepted" type="checkbox" class="form-check-input"
                                       id="radio-accept-contract"/>
                                <label class="form-check-label" for="exampleCheck1" style="padding-left: 10px">
                                    Tôi đã đọc và đồng ý với Chính sách bảo mật thông tin</label>
                                <br/>
                                <button class="btn btn-success m-auto w-100" style="font-size: 18px" v-on:click="saveOrder()">
                                    <i class="fa-solid fa-up-right-from-square"></i> Luu đơn hàng
                                </button>
                            </div>
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

<script src="@/components/order/js/create-order"/>
<style src="./css/create-order.css"/>