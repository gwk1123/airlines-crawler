package com.crawler.api.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangdehua on 10/26/16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Routing implements Serializable {

    private static final long serialVersionUID = -7132780016171046894L;
    private int id;
    private BigDecimal adultPrice;
    private BigDecimal adultTax;
    private String adultAgeRestriction;
    private BigDecimal childPrice;
    private BigDecimal childTax;
    private BigDecimal infantsPrice;
    private BigDecimal infantsTax;

    private String eligibility;
    private String nationality;
    private String forbiddenNationality;
    private String priceType; //运价类型: pub pri
    private int applyType;
    private int adultTaxType;
    private int childTaxType;
    private String reservationType;
    private String productType; //普通 k位
    private String officeId;

    private String minStay;
    private String maxStay;
    private int minPassengerCount;
    private int maxPassengerCount;
    private String fareBasis;
    private String validatingCarrier;
    private AirlineAncillaries airlineAncillaries;
    private Rule rule;
    private List<Segment> fromSegments;
    private List<Segment> retSegments;
    private String pricingMethod;

    private String adultAirportTax; //成人机场建设费
    private String childAirportTax; //儿童机场建设费
    private String eTicket; //电子客票标示
    private String meal; //餐食
    private String adultFuelCosts; //燃油费
    private String childFuelCosts; //儿童燃油费
    private String kilometers; //公里数
    private String economyStandardPrice; //经济舱标准价

    public String getAdultAirportTax() {
        return adultAirportTax;
    }

    public void setAdultAirportTax(String adultAirportTax) {
        this.adultAirportTax = adultAirportTax;
    }

    public String getChildAirportTax() {
        return childAirportTax;
    }

    public void setChildAirportTax(String childAirportTax) {
        this.childAirportTax = childAirportTax;
    }

    public String geteTicket() {
        return eTicket;
    }

    public void seteTicket(String eTicket) {
        this.eTicket = eTicket;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getAdultFuelCosts() {
        return adultFuelCosts;
    }

    public void setAdultFuelCosts(String adultFuelCosts) {
        this.adultFuelCosts = adultFuelCosts;
    }

    public String getChildFuelCosts() {
        return childFuelCosts;
    }

    public void setChildFuelCosts(String childFuelCosts) {
        this.childFuelCosts = childFuelCosts;
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getEconomyStandardPrice() {
        return economyStandardPrice;
    }

    public void setEconomyStandardPrice(String economyStandardPrice) {
        this.economyStandardPrice = economyStandardPrice;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }


    public List<Segment> getFromSegments() {

        if (fromSegments == null) {
            fromSegments = new ArrayList<Segment>();
        }
        return this.fromSegments;
    }


    public List<Segment> getRetSegments() {
        if (retSegments == null) {
            retSegments = new ArrayList<Segment>();
        }
        return this.retSegments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getAdultAgeRestriction() {
        return adultAgeRestriction;
    }

    public void setAdultAgeRestriction(String adultAgeRestriction) {
        this.adultAgeRestriction = adultAgeRestriction;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getForbiddenNationality() {
        return forbiddenNationality;
    }

    public void setForbiddenNationality(String forbiddenNationality) {
        this.forbiddenNationality = forbiddenNationality;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public int getApplyType() {
        return applyType;
    }

    public void setApplyType(int applyType) {
        this.applyType = applyType;
    }

    public int getAdultTaxType() {
        return adultTaxType;
    }

    public void setAdultTaxType(int adultTaxType) {
        this.adultTaxType = adultTaxType;
    }

    public int getChildTaxType() {
        return childTaxType;
    }

    public void setChildTaxType(int childTaxType) {
        this.childTaxType = childTaxType;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getMinStay() {
        return minStay;
    }

    public void setMinStay(String minStay) {
        this.minStay = minStay;
    }

    public String getMaxStay() {
        return maxStay;
    }

    public void setMaxStay(String maxStay) {
        this.maxStay = maxStay;
    }

    public int getMinPassengerCount() {
        return minPassengerCount;
    }

    public void setMinPassengerCount(int minPassengerCount) {
        this.minPassengerCount = minPassengerCount;
    }

    public int getMaxPassengerCount() {
        return maxPassengerCount;
    }

    public void setMaxPassengerCount(int maxPassengerCount) {
        this.maxPassengerCount = maxPassengerCount;
    }

    public String getFareBasis() {
        return fareBasis;
    }

    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }

    public String getValidatingCarrier() {
        return validatingCarrier;
    }

    public void setValidatingCarrier(String validatingCarrier) {
        this.validatingCarrier = validatingCarrier;
    }

    public AirlineAncillaries getAirlineAncillaries() {
        return airlineAncillaries;
    }

    public void setAirlineAncillaries(AirlineAncillaries airlineAncillaries) {
        this.airlineAncillaries = airlineAncillaries;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public void setFromSegments(List<Segment> fromSegments) {
        this.fromSegments = fromSegments;
    }

    public void setRetSegments(List<Segment> retSegments) {
        this.retSegments = retSegments;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPricingMethod() {
        return pricingMethod;
    }

    public void setPricingMethod(String pricingMethod) {
        this.pricingMethod = pricingMethod;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getAdultTax() {
        return adultTax;
    }

    public void setAdultTax(BigDecimal adultTax) {
        this.adultTax = adultTax;
    }

    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    public BigDecimal getChildTax() {
        return childTax;
    }

    public void setChildTax(BigDecimal childTax) {
        this.childTax = childTax;
    }

    public BigDecimal getInfantsPrice() {
        return infantsPrice;
    }

    public void setInfantsPrice(BigDecimal infantsPrice) {
        this.infantsPrice = infantsPrice;
    }

    public BigDecimal getInfantsTax() {
        return infantsTax;
    }

    public void setInfantsTax(BigDecimal infantsTax) {
        this.infantsTax = infantsTax;
    }

}
