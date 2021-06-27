package com.crawler.api.kjy.vo.prepaid;

import lombok.Data;

import java.util.List;

@Data
public class RtpnrInfo {

    private String pnr;
    private String b_pnr;
    private String is_team;
    private String HasNI;
    private String HasINF;
    private String IsCHD;
    private String remark;
    private String osi;
    private String office_code;
    private String tl;
    private String passengerCount;
    private List<Passenger> passenger;
    private List<Lines> lines;
    private String linesCount;
    private List<Price> price;
    private String priceCount;
    private String rawData;
    private List<String> fn;
}
