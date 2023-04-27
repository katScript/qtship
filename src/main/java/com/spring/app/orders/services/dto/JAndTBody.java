package com.spring.app.orders.services.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JAndTBody {
    private LogisticInterface logistics_interface;
    private String data_digest;
    @Builder.Default
    private String msg_type = "ORDERCREATE";
    @Builder.Default
    private String eccompanyid = "CUSMODEL";
}
