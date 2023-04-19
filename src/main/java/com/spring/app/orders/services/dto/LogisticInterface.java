package com.spring.app.orders.services.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogisticInterface {
    @Builder.Default
    private String eccompanyid = "CUSMODEL";
    @Builder.Default
    private String customerid = "084LC012345";
    private String txlogisticid;
    @Builder.Default
    private Integer ordertype = 1;
    @Builder.Default
    private Integer servicetype = 1;
    @Builder.Default
    private Integer selfAddress = 1;
    @Builder.Default
    private String partsign = "0";
    private Address sender;
    private Address receiver;
    private String createordertime;
    private String sendstarttime;
    private String sendendtime;
    @Builder.Default
    private String paytype = "PP_PM";
    private String itemsvalue;
    private String goodsvalue;
    @Builder.Default
    private String isInsured = "1";
    private List<Item> items;
    private String weight;
    private String volume;
    private String remark;
}
