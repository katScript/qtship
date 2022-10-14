<template>
  <div class="subtab-area">
    <div class="subtab-area-group">
      <ul class="list-group">
        <a href="#" class="
            list-group-item list-group-item-action list-group-page-qt
            title-group-item-qt
          "><i class="fa-solid fa-user" style="float: left; padding-right: 10px"></i>
          duongph <i class="fa-solid fa-circle" style="color: #4cd137; font-size: 10px;padding-top: 3px;"></i></a>
      </ul>
      <div class="list-group">
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Quản lí thông tin tài khoản <i class="fa-solid fa-chevron-right"></i>
        </a>
      </div>
    </div>
    <div class="subtab-area-group">
      <ul class="list-group">
        <a href="#" class="
            list-group-item list-group-item-action list-group-page-qt
            title-group-item-qt
          ">Quản lí đơn hàng</a>
      </ul>
      <div class="list-group">
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Giao thành công <span class="number-item-span">(10ĐH)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Đã lấy/Đang giao <span class="number-item-span">(10ĐH)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Delay & Hủy lấy <span class="number-item-span">(10ĐH | 10ĐH)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Không giao được/Lưu kho <span class="number-item-span">(10ĐH)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Đơn đổi trả (Đơn hoàn) <span class="number-item-span">(10ĐH)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Delay do sai thông tin ĐH & chờ kiểm tra
          <span class="number-item-span">(10ĐH)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Đã chỉnh sửa thông tin <span class="number-item-span">(10ĐH)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Chưa xử lý <span class="number-item-span">(10ĐH)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
      </div>
    </div>
    <div class="subtab-area-group">
      <ul class="list-group">
        <a href="#" class="
            list-group-item list-group-item-action list-group-page-qt
            title-group-item-qt
          ">Quản lí kho và sản phẩm</a>
        <li class="list-group-item">
          Kho:
          <select class="form-select" aria-label="Default select example" style="width: 200px; display: inline-block">
            <option selected>Tất cả</option>
            <option value="1">Kho Thanh Xuân</option>
            <option value="2">Kho Mỹ Đình</option>
          </select>
        </li>
      </ul>
      <div class="list-group">
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Nhập <span class="number-item-span">(+ 10SP)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Xuất <span class="number-item-span">(+ 10SP)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Tồn <span class="number-item-span">(+ 10SP)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Số lượng đơn hàng/Sản phẩm
          <span class="number-item-span">(10ĐH | 12SP)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt">
          Đơn hàng chưa xử lí/Sản phẩm
          <span class="number-item-span">(10ĐH | 12SP)</span>
          <i class="fa-solid fa-chevron-right"></i>
        </a>
      </div>
    </div>
    <div class="subtab-area-group">
      <ul class="list-group">
        <a class="
            list-group-item list-group-item-action list-group-page-qt
            title-group-item-qt
          ">Quản lí dòng tiền</a>
      </ul>
      <div class="list-group">
        <a class="list-group-item list-group-item-action list-group-page-qt">
          Tiền đang luân chuyển (0 VNĐ)
        </a>
        <a class="list-group-item list-group-item-action list-group-page-qt">
          Tổng thu (0 VNĐ) 
        </a>
        <a class="list-group-item list-group-item-action list-group-page-qt">
          Tiền thu hộ (0 VNĐ) 
        </a>
        <a class="list-group-item list-group-item-action list-group-page-qt">
          Phí hoàn hàng 
        </a>
      </div>
    </div>
    <div class="subtab-area-group">
      <ul class="list-group"></ul>
      <div class="list-group">
        <a href="#" class="list-group-item list-group-item-action list-group-page-qt"
          style="color: red; font-weight: bold">
          Đăng xuất <i class="fa-solid fa-chevron-right"></i>
        </a>
      </div>
    </div>
  </div>
</template>