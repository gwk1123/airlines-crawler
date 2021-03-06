package com.crawler.api.kjy.vo.createOrder;

import lombok.Data;

import java.util.List;

@Data
public class CHD {

    private Order order;
    private List<JourneyResponse> journey;
    private List<PassengerResponse> passenger;
}
