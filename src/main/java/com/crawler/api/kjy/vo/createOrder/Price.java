package com.crawler.api.kjy.vo.createOrder;

import lombok.Data;

@Data
public class Price {

    private String airportTax;
    private String fuelSurTax;
    private String price;
    private String settlementPrice;
    private String policyValue;
    private String policyId;
}
