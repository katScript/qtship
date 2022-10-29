<template>
  <div class="container-fluid list-order-transfer p-0">
    <br />
    <div class="row">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Bàn giao cho Shipper
        <span>(Tổng số: 10 ĐH)</span>
      </h5>
      <div class="col-12">
        <div class="row d-flex justify-content-end">
          <div class="col-10">
            <input
              type="text"
              class="form-control"
              placeholder="Mã ĐH hoặc Sđt người nhận"
            />
          </div>
          <div class="col-2">
            <button class="btn btn-outline-danger">
              <i class="fa-solid fa-magnifying-glass"></i>
            </button>
          </div>
        </div>
        <table class="table table-hover">
          <thead>
            <tr>
              <th scope="col">Mã ĐH</th>
              <th scope="col">Người nhận</th>
              <th scope="col">Sđt người nhận</th>
              <th scope="col">
                <button class="btn btn-danger btn-accept-order-all">
                  Chấp nhận tất cả
                </button>
              </th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">MD0001003</th>
              <td>John</td>
              <td>0987612312</td>
              <td>
                <button class="btn btn-outline-danger btn-accept-order">
                  Chấp nhận
                </button>
              </td>
            </tr>
            <tr>
              <td>Địa chỉ:</td>
              <td colspan="3">Ha Noi, Ha Noi, Viet Nam</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
@media screen and (max-width: 992px) {
  .btn-accept-order-all,
  .btn-accept-order {
    font-size: 10px;
    padding-right: 1px;
    padding-left: 1px;
  }
}
</style>