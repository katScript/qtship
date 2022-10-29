<template>
  <div class="boxs-data-overview">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-3">
          <a href="">
            <div class="box-data box-data1">
              <i class="fa-solid fa-note-sticky"></i>
              <h5>Tổng số Đơn hàng</h5>
              <h1>1000</h1>
            </div>
          </a>
        </div>
        <div class="col-md-3">
          <a href="">
            <div class="box-data box-data2">
              <i class="fa-solid fa-user-check"></i>
              <h5>Tổng số Khách hàng</h5>
              <h1>200</h1>
            </div>
          </a>
        </div>
        <div class="col-md-3">
          <a href="">
            <div class="box-data box-data3">
              <i class="fa-sharp fa-solid fa-user"></i>
              <h5>Tổng số Nhân viên</h5>
              <h1>1200</h1>
            </div>
          </a>
        </div>
        <div class="col-md-3">
          <a href="">
            <div class="box-data box-data4">
              <i class="fa-solid fa-cubes-stacked"></i>
              <h5>Tổng số Bưu cục</h5>
              <h1>20</h1>
            </div>
          </a>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
a {
  text-decoration: none;
}

a:hover .box-data {
  color: #ffffff;
}

.box-data {
  /* width: 100%; */
  margin: auto;
  height: 150px;
  color: black;
  padding-top: 10px;
  text-align: center;
}

.box-data1 {
  background-color: #ff7675;
}

.box-data2 {
  background-color: #74b9ff;
}

.box-data3 {
  background-color: #2ecc71;
}

.box-data4 {
  background-color: #95afc0;
}

.box-data i {
  position: relative;
  font-size: 30px;
  color: #ffff;
  padding-bottom: 10px;
}
</style>