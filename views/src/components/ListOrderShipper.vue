<template>
  <div class="container-fluid  list-order-shipper p-0">
    <br>
    <div class="row">
      <div class="col-4">
        <label for="" class="label-control">Loại đơn hàng: </label>
      </div>
      <div class="col-8">
        <select
          class="form-select"
          aria-label="Default select example"
          v-model="typeOrderFilter"
        >
          <option value="0">Tất cả</option>
          <option value="1">Đã lấy hàng</option>
          <option value="2">Đang giao</option>
          <option value="3">Đã giao/Đã thanh toán</option>
          <option value="4">Delay giao hàng</option>
          <option value="5">Hoàn hàng</option>
          <option value="6">Khác...</option>
        </select>
      </div>
    </div>
    <!-- ALL -->
    <div class="row" v-if="typeOdersListDisplay == 'ALL'">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Đang giao
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
            <button class="btn btn-danger">
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
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">MD0001003</th>
              <td>John</td>
              <td>0987612312</td>
              <td>
                <button class="btn btn-detail-order btn-primary">Chi tiết</button>
              </td>
            </tr>
            <tr>
              <td>Địa chỉ:</td>
              <td colspan="3">Ha Noi, Ha Noi, Viet Nam</td>
            </tr>
          </tbody>
          <tbody>
            <tr>
              <th scope="row">MD0001002</th>
              <td>Mark</td>
              <td>0987654321</td>
              <td>
                <button class="btn btn-detail-order btn-primary">Chi tiết</button>
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

    <!-- TAKED -->
    <div class="row" v-else-if="typeOdersListDisplay == 'TAKED'">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Đã lấy hàng
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
            <button class="btn btn-danger">
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
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">MD0002134</th>
              <td>John</td>
              <td>0987612312</td>
              <td>
                <button class="btn btn-detail-order btn-primary">Chi tiết</button>
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

    <!-- DELIVERING -->
    <div class="row" v-else-if="typeOdersListDisplay == 'DELIVERING'">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Đang giao
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
            <button class="btn btn-danger">
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
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">MD0002134</th>
              <td>John</td>
              <td>0987612312</td>
              <td>
                <button class="btn btn-detail-order btn-primary">Chi tiết</button>
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

    <!-- DELIVERED -->
    <div class="row" v-else-if="typeOdersListDisplay == 'DELIVERED'">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Đã giao và thanh toán COD
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
            <button class="btn btn-danger">
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
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">MD0000004</th>
              <td>John</td>
              <td>0987612312</td>
              <td>
                <button class="btn btn-detail-order btn-primary">Chi tiết</button>
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

    <!-- DELAYDELIVERY -->
    <div class="row" v-else-if="typeOdersListDisplay == 'DELAYDELIVERY'">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Delay giao hàng
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
            <button class="btn btn-danger">
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
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">MD0002221</th>
              <td>John</td>
              <td>0987612312</td>
              <td>
                <button class="btn btn-detail-order btn-primary">Chi tiết</button>
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

    <!-- TORETURN -->
    <div class="row" v-else-if="typeOdersListDisplay == 'TORETURN'">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Hoàn trả
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
            <button class="btn btn-danger">
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
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">MD0001112</th>
              <td>John</td>
              <td>0987612312</td>
              <td>
                <button class="btn btn-detail-order btn-primary">Chi tiết</button>
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

    <!-- ORTHER -->
    <div class="row" v-else-if="typeOdersListDisplay == 'ORTHER'">
      <h5>
        <i class="fa-solid fa-play"></i> Đơn hàng: Khác
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
            <button class="btn btn-danger">
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
              <th scope="col"></th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">MD0004312</th>
              <td>John</td>
              <td>0987612312</td>
              <td>
                <button class="btn btn-detail-order btn-primary">Chi tiết</button>
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

<script>
export default {
  data() {
    return {
      typeOrderFilter: 0,
      typeOdersListDisplay: "ALL",
    };
  },
  watch: {
    typeOrderFilter: {
      handler: function () {
        switch (this.typeOrderFilter) {
          case "0":
            this.typeOdersListDisplay = "ALL";
            break;
          case "1":
            this.typeOdersListDisplay = "TAKED";
            break;
          case "2":
            this.typeOdersListDisplay = "DELIVERING";
            break;
          case "3":
            this.typeOdersListDisplay = "DELIVERED";
            break;
          case "4":
            this.typeOdersListDisplay = "DELAYDELIVERY";
            break;
          case "5":
            this.typeOdersListDisplay = "TORETURN";
            break;
          case "6":
            this.typeOdersListDisplay = "ORTHER";
            break;
          default:
            this.typeOdersListDisplay = "ALL";
            break;
        }
      },
    },
  },
};
</script>

<style scoped>
    @media screen and (max-width: 992px) {
        .btn-detail-order {
            font-size: 10px;
            padding-right: 1px;
            padding-left: 1px;
            width: 45px;
        }
    }
</style>