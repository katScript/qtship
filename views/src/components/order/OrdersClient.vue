<template>
  <div class="management-client-page">
    <div>
      <PopupNotify :class="isShowNotify ? 'show' : 'hide'" @closePopupNotify="closePopupNotify"
        @updateTypeNotify="updateTypeNotify" :typeNotify="typeNotify" :dataNotify="dataNotify"
        :typeComponent="typeComponent" :configRequestApi="configRequestApi" />
    </div>
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
          <div class="orders-overview-area">
            <div class="orders-filter-form">
              <div class="row">
                <div class="col-12">
                  <h4 class="title-management">
                    <i class="fa-solid fa-clipboard-list"></i> Quản lí đơn hàng
                  </h4>
                  <br />
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <input v-model="filterOderCondition.orderId" type="text" class="form-control form-search-control"
                    id="" placeholder="Mã đơn hàng" />
                </div>
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <input v-model="filterOderCondition.phoneReceiver" type="text"
                    class="form-control form-search-control" id="" placeholder="Số điện thoại người nhận" />
                </div>
                <div class="col-12 col-sm-6 col-md-2 form-group">
                  <select class="form-select" aria-label="Default select example"
                    v-model="filterOderCondition.orderStatus">
                    <option value="" disabled hidden selected>
                      Trạng thái đơn hàng
                    </option>
                    <option value="WAITING_CONFIRM">Chờ xác nhận</option>
                    <option value="DELIVERING">Đang giao</option>
                    <option value="DELAY">Delay giao hàng</option>
                    <option value="SUCCESS">Giao thành công</option>
                    <option value="PAYMENTED">Đã thanh toán</option>
                    <option value="RETURN">Đơn hoàn</option>

                    <!-- <option value="DOISOAT">Đã đối soát</option> -->
                    <option value="CANCEL">Đã hủy</option>
                  </select>
                </div>
                <div class="col-12 col-sm-6 col-md-4 form-group">
                  <table class="w-100">
                    <tr>
                      <td>
                        <select class="form-select" id="select-box-filter-time" v-model="filterTime">
                          <option value="" hidden>Thời gian tạo đơn</option>
                          <option value="timeCOToday">Hôm nay</option>
                          <option value="timeCO1week">1 tuần</option>
                          <option value="timeCO1month">1 tháng</option>
                          <option value="timeCO1year">1 năm</option>
                          <option value="timeCOAll">Tất cả</option>
                          <option value="timeCOTimeAbout">Tùy chỉnh</option>
                        </select>
                    <tr :class="classFilterTimeAbout">
                      <td>
                        <label for="start">Từ ngày:</label>
                        <input type="date" id="from-time-filter" class="form-control"
                          v-model="filterOderCondition.timeFrom" />
                      </td>
                      <td>
                        <label for="start">Đến ngày ngày:</label>
                        <input type="date" id="to-time-filter" class="form-control"
                          v-model="filterOderCondition.timeTo" />
                      </td>
                    </tr>
                    </td>
                    </tr>
                  </table>
                </div>
                <!-- <div class="col-12 col-sm-6 col-md-3 form-group">
                  <table class="w-100">
                    <tr>
                      <td>
                        <select class="form-select" id="select-box-filter-time" v-model="filterTimeDS">
                          <option value="" hidden>
                            Thời gian đối soát
                          </option>
                          <option value="controlTimeToday">Hôm nay</option>
                          <option value="controlTime1week">1 tuần</option>
                          <option value="controlTime1month">1 tháng</option>
                          <option value="controlTime1year">1 năm</option>
                          <option value="controlTimeAll">Tất cả</option>
                          <option value="controlTimeAboutDS">
                            Tùy chỉnh
                          </option>
                        </select>
                    <tr :class="classFilterTimeAboutDS">
                      <td>
                        <label for="start">Từ ngày:</label>
                        <input type="date" id="from-time-filter" class="form-control" name=""
                          v-model="filterOderCondition.timeDSFrom" />
                      </td>
                      <td>
                        <label for="start">Đến ngày ngày:</label>
                        <input type="date" id="to-time-filter" class="form-control" name=""
                          v-model="filterOderCondition.timeDSTo" />
                      </td>
                    </tr>
                    </td>
                    </tr>
                  </table>
                </div> -->
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <select class="form-select" aria-label="Default select example"
                    v-model="filterOderCondition.shippingType">
                    <option value="" disabled hidden selected>
                      Dịch vụ giao hàng
                    </option>
                    <option v-for="(type, index) in listTypeShipping" :key="index">
                      {{ generateCodeToText(type) }}
                    </option>
                  </select>
                </div>
                <!-- <div class="col-12 col-sm-6 col-md-3 form-group">
                  <select class="form-select" aria-label="Default select example">
                    <option value="label" disabled hidden selected>
                      Trạng thái hóa đơn
                    </option>
                    <option value="invoiceIssued">Đã xuất hóa đơn</option>
                    <option value="invoiceUnissued">Chưa xuất hóa đơn</option>
                    <option value="invoiceUnissued">
                      Không xuất hóa đơn
                    </option>
                  </select>
                </div> -->
                <div class="col-12 col-sm-6 col-md-3 form-group">
                  <div class="row">
                    <div class="col-8">
                      <button class="btn btn-danger" v-on:click="filterOrder">
                        Tìm kiếm đơn hàng
                      </button>
                    </div>
                    <div class="col-4">
                      <button class="btn btn-secondary" v-on:click="resetFormSearch()" style="margin: 0px -5px">
                        Bỏ lọc
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="order-list-data">
              <br />
              <h5>
                Số đơn hàng:
                <span class="count-orders-filtered">{{ countOrder }}</span> ĐH
              </h5>
              <easy-data-table :headers="headersOrder" :items="listOrderByCustomer"
                table-class-name="easy-data-table-customize">
                <!-- #item-btn-function="item"  item: valua of row-->
                <template #item-name-receiver="item">
                  {{ item.orderItem[0].shippingAddress.name }}
                </template>
                <template #item-phone-receiver="item">
                  {{ item.orderItem[0].shippingAddress.phone }}
                </template>
                <template #item-btn-function-order="item">
                  <table class="w-100" v-if="item.status != 'CANCEL'">
                    <tr>
                      <td>
                        <!-- + idRequest +'/' add to url detail whent api updated -->
                        <a :href="'/client/orders/detail/' +item.id" class="btn btn-primary a-function a-detail" data-bs-toggle="tooltip" data-bs-placement="top"
                          title="Chi tiết đơn hàng"><i
                            class="fa-solid fa-circle-info"></i></a>
                      </td>
                      <td>
                        <a :href="'/client/orders/create#_' + item.id" class="btn btn-success a-function a-detail"
                          data-bs-toggle="tooltip" data-bs-placement="top" title="Cập nhật đơn hàng"><i
                            class="fa-solid fa-file-pen"></i></a>
                      </td>
                      <td>
                        <a href="" class="btn btn-dark a-function a-detail" data-bs-toggle="tooltip"
                          data-bs-placement="top" title="Lịch sử cập nhật"><i
                            class="fa-solid fa-clock-rotate-left"></i></a>
                      </td>
                      <td>
                        <a v-on:click="cancelOrder(item)" class="btn btn-danger a-function a-detail"
                          title="Hủy đơn hàng">
                          <i class="fa-solid fa-trash"></i></a>
                      </td>
                    </tr>
                  </table>
                  <span class="bg-danger" v-if="item.status == 'CANCEL'" disabled>CANCLED</span>
                </template>
              </easy-data-table>
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

<script src="@/components/order/js/order-management.js" />

<style scoped>
  .show {
    display: block;
  }

  .hide {
    display: none;
  }

  .form-search-control {
    margin-bottom: 10px;
  }

  a {
    text-decoration: none;
  }

  table tr td {}

  table tr td a {
    /* font-size: 5px; */
    padding: 2px;
  }

  table tr td a i {
    font-size: 18px;
    padding: 2px 2px 2px 2px;
  }
</style>