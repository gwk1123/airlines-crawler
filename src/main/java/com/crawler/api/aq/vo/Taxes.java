/**
  * Copyright 2020 bejson.com
  */
package com.crawler.api.aq.vo;

/**
 * Auto-generated: 2020-09-29 14:0:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Taxes {

    private String code;
    private int amount;
    private String currency;
    private String passengerType;
    private String country;
    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setAmount(int amount) {
         this.amount = amount;
     }
     public int getAmount() {
         return amount;
     }

    public void setCurrency(String currency) {
         this.currency = currency;
     }
     public String getCurrency() {
         return currency;
     }

    public void setPassengerType(String passengerType) {
         this.passengerType = passengerType;
     }
     public String getPassengerType() {
         return passengerType;
     }

    public void setCountry(String country) {
         this.country = country;
     }
     public String getCountry() {
         return country;
     }

}
