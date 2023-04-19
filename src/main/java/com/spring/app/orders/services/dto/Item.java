package com.spring.app.orders.services.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private String itemname;
    private String englishName;
    private String itemvalue;
    private String desc;
}
