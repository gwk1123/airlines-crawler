package com.crawler.api.kjy.vo.createOrder;

import lombok.Data;

@Data
public class PassengerRequest {

    private String passengerName;
    private String cardId;
    private String phone;
    private String passengerType;
    private String cardTypeId;
}
