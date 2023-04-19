package com.spring.app.orders.services.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseItem {
    private String codFee;
    private String discountFee;
    private String insurancefee;
    private String reason;
    private String success;
}
