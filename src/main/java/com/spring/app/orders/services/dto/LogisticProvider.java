package com.spring.app.orders.services.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogisticProvider {
    private String logisticproviderid;
    private List<ResponseItem> responseitems;
}
