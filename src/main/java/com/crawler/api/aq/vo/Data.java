/**
  * Copyright 2020 bejson.com
  */
package com.crawler.api.aq.vo;
import java.util.List;

/**
 * Auto-generated: 2020-09-29 14:0:38
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private String sessionId;
    private String tripType;
    private List<List<Flights>> flights;
    private List<Products> products;
    private List<Services> services;
    public void setSessionId(String sessionId) {
         this.sessionId = sessionId;
     }
     public String getSessionId() {
         return sessionId;
     }

    public void setTripType(String tripType) {
         this.tripType = tripType;
     }
     public String getTripType() {
         return tripType;
     }

    public void setFlights(List<List<Flights>> flights) {
         this.flights = flights;
     }
     public List<List<Flights>> getFlights() {
         return flights;
     }

    public void setProducts(List<Products> products) {
         this.products = products;
     }
     public List<Products> getProducts() {
         return products;
     }

    public void setServices(List<Services> services) {
         this.services = services;
     }
     public List<Services> getServices() {
         return services;
     }

}
