package com.crawler.api.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Passenger {

    private String name ;
    //乘客类型，0 成人/1 儿童/2 婴儿
    private String passengerType ;
    private String birthday ;
    private String gender ;
    private String cardNum ;
    private String cardType ;
    private String cardIssuePlace ;
    private String cardExpired ;
    private String nationality ;
    private FFPNo ffpNo;
    private int number;
    //发证日期，格式：YYYYMMDD
    private String issueDate;
    //乘机人手机号
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardIssuePlace() {
        return cardIssuePlace;
    }

    public void setCardIssuePlace(String cardIssuePlace) {
        this.cardIssuePlace = cardIssuePlace;
    }

    public String getCardExpired() {
        return cardExpired;
    }

    public void setCardExpired(String cardExpired) {
        this.cardExpired = cardExpired;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public FFPNo getFfpNo() {
        return ffpNo;
    }

    public void setFfpNo(FFPNo ffpNo) {
        this.ffpNo = ffpNo;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }
}
