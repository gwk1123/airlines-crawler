package com.crawler.api.request;

import com.crawler.api.response.Routing;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangdehua on 10/26/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GDSOrderRequestDTO {


    private String apiKey;
    private String tripType;
    private Routing routing ;
    private List<Passenger> passengers ;
    private Contact contact ;
    private String reservationType;
    private String officeId;
    private String uid;
    /*订单请求方式手工，或者系统*/
    private String sourceType;
    private String requestSource;
    private PayInfo payInfo;

    public List<Passenger> getPassengers() {
        if (passengers == null) {
            passengers = new ArrayList<Passenger>();
        }
        return this.passengers;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
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

    public Routing getRouting() {
        return routing;
    }

    public void setRouting(Routing routing) {
        this.routing = routing;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
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

    public PayInfo getPayInfo() {
        return payInfo;
    }

    public void setPayInfo(PayInfo payInfo) {
        this.payInfo = payInfo;
    }
}


