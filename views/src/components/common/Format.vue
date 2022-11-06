<template>
    <div>

    </div>
</template>

<script>
export default {
    data() {
        return {

        }
    },
    mounted() {
        let bodyRequest = {
            "id": 23,
            "customerId": "1",
            "senderName": "companyName - fullName",
            "senderPhone": "0123456789",
            "senderAddress": "street, ward, district, province",
            "note": "Test",
            "status": "default",
            "feedback": "",
            "notification": false,
            "shippingFee": "false",
            "coupon": "MPVC001-VC",
            "warehouseId": 1,
            "returnCode": "qwe",
            "shippingType": "Standard",
            "shippingDate": "2022-11-02 14:11:51",
            "orderItem": [
                {
                    "shippingAddress": {
                        "name": "Tu QUang",
                        "phone": "0987654567",
                        "province": "Thành phố Cần Thơ",
                        "district": "Huyện Cờ Đỏ",
                        "ward": "Thị trấn Cờ Đỏ",
                        "street": "Ton Duc Thang",
                        "provinceId": "92",
                        "districtId": "925",
                        "wardId": "31261"
                    },
                    "products": [
                        {
                            "productId": 15,
                            "qty": 3
                        }
                    ]
                }
            ]
        }
    },
}
</script>