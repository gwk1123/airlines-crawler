package com.crawler.api.kjy.vo.search;

import lombok.Data;

import java.util.List;

@Data
public class SearchResponse {

    private String ShareaFltno;
    private String CodeshareAirline;
    private String Routno;
    private String FlightNo;
    private String Carrier;
    private String company;
    private String OffPoint;
    private String BoardPoint;
    private String fromFly;
    private String toFly;
    private String tpm;
    private String DepartureDate;
    private String Aircraft;
    private String Week;
    private String ArrivalDate;
    private String DepartureTime;
    private String ArrivalTime;
    private String YClassPrice;
    private String Meal;
    private String ViaPort;
    private String ETicket;
    private String BoardPointAT;
    private String OffPointAT;
    private String FClassPrice;
    private String CClassPrice;
    private String AirportTax;
    private String FuelSurTax;
    private String FuelSurTaxChd;
    private List<ClassAry> ClassAry;
    private DefSeat defSeat;

}
