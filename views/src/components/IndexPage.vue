<template>
    <div class="index-page">
        <div class="container-fluid">
            <div class="d-flex justify-content-center">
                <div class="spinner-border" role="status">
                    <span class="visually-hidden">Loading...</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {commonFunction} from '@/scripts/ulti';

export default {
    mounted() {
        let auth = commonFunction.getCookies(commonFunction.userCookies.username),
            role = commonFunction.getCookies(commonFunction.userCookies.roles);

        if (auth == null || role == null) {
            commonFunction.redirect('/login-page');
        } else if (auth) {
            if(auth && role == "admin") {
                commonFunction.redirect("/admin/order");
            } else {
                commonFunction.redirect("/" + role + "/management");
            }
        }
    }
};
</script>