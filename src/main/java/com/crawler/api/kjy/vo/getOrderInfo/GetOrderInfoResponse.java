package com.crawler.api.kjy.vo.getOrderInfo;

import lombok.Data;

import java.util.List;

@Data
public class GetOrderInfoResponse {

    private Order order;

    private List<Journey> Journey;

    private List<Passenger> passenger;
}
