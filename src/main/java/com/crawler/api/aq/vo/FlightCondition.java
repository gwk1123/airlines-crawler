package com.crawler.api.aq.vo;

public class FlightCondition {

    private String index="0";
    private String depCode="CAN";
    private String arrCode="NKG";
    private String depDate="2020-10-10";
    private String depCodeType="CITY";
    private String arrCodeType="CITY";

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getArrCode() {
        return arrCode;
    }

    public void setArrCode(String arrCode) {
        this.arrCode = arrCode;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getDepCodeType() {
        return depCodeType;
    }

    public void setDepCodeType(String depCodeType) {
        this.depCodeType = depCodeType;
    }

    public String getArrCodeType() {
        return arrCodeType;
    }

    public void setArrCodeType(String arrCodeType) {
        this.arrCodeType = arrCodeType;
    }
}
