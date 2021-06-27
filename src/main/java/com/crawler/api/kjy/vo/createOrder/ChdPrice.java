package com.crawler.api.kjy.vo.createOrder;

import lombok.Data;

@Data
public class ChdPrice {

    private String airportTax;
    private String fuelSurTax;
    private String price;
    /**
     * 成人编码 单独生成儿童编码时必须填写
     */
    private String adultPnr;
    /**
     * 儿童订单结算总价=
     * (儿童票面价格+儿童开票服务费+儿童机建+儿童燃油)* 儿童人数
     */
    private String settlementPrice;

    /**
     * 如：10，儿童开票服务费
     */
    private String serviceCharge;
}
