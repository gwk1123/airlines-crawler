package com.crawler.api.kjy.vo.createOrder;

import lombok.Data;

@Data
public class JourneyRequest {

    private String boardPoint;
    private String offPoint;
    private String flightNo;
    private String fromDate;
    private String fromTime;
    private String todate;
    private String totime;
    private String carrier;
    private String shareCarrier;
    private String shareFlightNo;
    private String code;
    private String aircraft;
    private String launchTerminal;
    private String arrivedTerminal;
}
