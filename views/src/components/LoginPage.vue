<template>
    <div class="login-page">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-6 h-100">
                    <img src="../images/logo-branch.png" class="w-50 h-auto mx-auto d-block align-middle branch-logo"
                        alt="Branch logo">
                        <br>
                </div>
                <div class="col-md-6">
                    <div class="div-form-login">
                        <form class="w-75 mx-auto">
                            <div class="mb-3">
                                <label for="input-account-login" class="form-label label-qt">Email hoặc số điện thoại</label>
                                <input type="text" class="form-control" id="input-account-login">
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputPassword1" class="form-label label-qt">Password</label>
                                <input type="password" class="form-control" id="input-password-login">
                            </div>
                            <div class="mb-3 form-check">
                                <input type="checkbox" class="form-check-input" id="radio-remember-account">
                                <label class="form-check-label" for="exampleCheck1">Remember me</label>
                            </div>
                            <div class="mb-3 text-center">
                                <a href="#" class="a-forget-password">Bạn quên mật khẩu?</a>
                                <br>
                                <br>
                                <button type="submit" class="btn btn-danger btn-login-qt">Đăng nhập</button>
                                <br>
                                <br>
                                <p>Bạn chưa có tài khoản, <a href="/register-page" class="a-register-account">Đăng ký ngay!</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>