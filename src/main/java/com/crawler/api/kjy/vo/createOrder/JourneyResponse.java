package com.crawler.api.kjy.vo.createOrder;


import lombok.Data;

@Data
public class JourneyResponse {

    private String journeyId;
    private String boardPoint;
    private String boardPointName;
    private String offPoint;
    private String offPointName;
    private String flightNo;
    private String fromDateTime;
    private String todateTime;
    private String carrier;
    private String carrierName;
    private String shareCarrier;
    private String shareFlightNo;
    private String code;
    private String aircraft;
    private String launchTerminal;
    private String arrivedTerminal;
    private String journeyWeight;
}
