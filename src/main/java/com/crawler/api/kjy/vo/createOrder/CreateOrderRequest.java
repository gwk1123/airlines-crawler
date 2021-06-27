package com.crawler.api.kjy.vo.createOrder;


import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

    private List<JourneyRequest> journey;
    private List<PassengerRequest> passenger;
    private Price price;
    private Price chdPrice;
    private Price infPrice;
    private QueryBaseInfo queryBaseInfo;
}
