package com.crawler.api.kjy.vo.getOrderInfo;

import lombok.Data;

@Data
public class Order {

    private String orderId;

    private String orderNumber;

    private String userOrderId;

    private String orderPNR;

    private String orderFlyer;

    private String orderManPNR;

    private String orderType;

    private String orderStatus;

    private String orderPayStatus;

    private String orderCreateTime;

    private String orderTicketPrice;

    private String orderAirportTax;

    private String orderFuelSurcharge;

    private String orderInsurance;

    private String orderTotalPrice;

    private String orderCstTotalPrice;

    private String orderIsShare;

}
