package com.crawler.api.request;

import com.crawler.api.response.Routing;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GDSVerifyRequestDTO {

    private String apiKey;
    private String tripType;
    private int adultNumber;
    private int childNumber;
    private int infantNumber;
    private Routing routing;
    private String officeId;
    private String uid;
    private String requestSource;
    private Integer verifyType;
    private String currency;
    private int lowestPriceFlag;
    private String requestType;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getLowestPriceFlag() {
        return lowestPriceFlag;
    }

    public void setLowestPriceFlag(int lowestPriceFlag) {
        this.lowestPriceFlag = lowestPriceFlag;
    }

    public Integer getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(Integer verifyType) {
        this.verifyType = verifyType;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    public String getTripType() {
        return tripType;
    }

    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    public int getAdultNumber() {
        return adultNumber;
    }

    public void setAdultNumber(int adultNumber) {
        this.adultNumber = adultNumber;
    }

    public int getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(int childNumber) {
        this.childNumber = childNumber;
    }

    public int getInfantNumber() {
        return infantNumber;
    }

    public void setInfantNumber(int infantNumber) {
        this.infantNumber = infantNumber;
    }

    public Routing getRouting() {
        return routing;
    }

    public void setRouting(Routing routing) {
        this.routing = routing;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRequestSource() {
        return requestSource;
    }

    public void setRequestSource(String requestSource) {
        this.requestSource = requestSource;
    }
}


