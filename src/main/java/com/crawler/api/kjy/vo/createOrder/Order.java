package com.crawler.api.kjy.vo.createOrder;

import lombok.Data;

@Data
public class Order {

    private long orderId;
    private String orderNumber;
    private String orderPNR;
    private String orderCstTotalPrice;

}
