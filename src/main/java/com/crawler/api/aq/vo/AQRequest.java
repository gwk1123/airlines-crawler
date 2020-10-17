package com.crawler.api.aq.vo;

import java.util.List;

public class AQRequest {

    private String language= "zh_CN";
    private String currency= "CNY";
    private List<FlightCondition> flightCondition;
    private String channelNo= "B2C";
    private String tripType= "OW";
    private String groupIndicator= "I";
    private String adultCount= "1";
    private String childCount= "0";
    private String infantCount= "0";
    private String airlineCode= null;
    private String directType = null;
    private String cabinClass= null;
    private String taxFee= null;
    private String taxCurrency= null;
    private String promotionCode= null;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<FlightCondition> getFlightCondition() {
        return flightCondition;
    }

    public void setFlightCondition(List<FlightCondition> flightCondition) {
        this.flightCondition = flightCondition;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public String getGroupIndicator() {
        return groupIndicator;
    }

    public void setGroupIndicator(String groupIndicator) {
        this.groupIndicator = groupIndicator;
    }

    public String getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(String adultCount) {
        this.adultCount = adultCount;
    }

    public String getChildCount() {
        return childCount;
    }

    public void setChildCount(String childCount) {
        this.childCount = childCount;
    }

    public String getInfantCount() {
        return infantCount;
    }

    public void setInfantCount(String infantCount) {
        this.infantCount = infantCount;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getDirectType() {
        return directType;
    }

    public void setDirectType(String directType) {
        this.directType = directType;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public String getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(String taxFee) {
        this.taxFee = taxFee;
    }

    public String getTaxCurrency() {
        return taxCurrency;
    }

    public void setTaxCurrency(String taxCurrency) {
        this.taxCurrency = taxCurrency;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }
}
