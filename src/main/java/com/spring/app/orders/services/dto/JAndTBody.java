package com.spring.app.orders.services.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JAndTBody {
    private LogisticInterface logistics_interface;
    @Builder.Default
    private String data_digest = "ODUyNGFkZmY2ZWNiM2M5MjFiNTljMDU2MjI3OTEzM2E=";
    @Builder.Default
    private String msg_type = "ORDERCREATE";
    @Builder.Default
    private String eccompanyid = "CUSMODEL";
}
