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
    import { useCookies } from "vue3-cookies";
    import { commonFunction } from '../scripts/ulti'
  
    export default {
        setup() {
            const { cookies } = useCookies();
            return { cookies };
        },
  
        // <data, methods...>
  
        mounted() {
            let authenication_cookies = this.cookies.get("authenication_cookies");
            if(authenication_cookies == null){
              commonFunction.redirect('/login-page');
            } else {
                commonFunction.redirect('/client/management');
            }
        }
    };
  </script>