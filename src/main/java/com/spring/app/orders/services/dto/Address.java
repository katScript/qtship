package com.spring.app.orders.services.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String name;
    private String phone;
    private String mobile;
    private String prov;
    private String city;
    private String area;
    private String address;
}
