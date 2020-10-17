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
public class Flights {

    private String flightId;
    private boolean salesClosed;
    private List<Segments> segments;
    private List<Fares> fares;
    public void setFlightId(String flightId) {
         this.flightId = flightId;
     }
     public String getFlightId() {
         return flightId;
     }

    public void setSalesClosed(boolean salesClosed) {
         this.salesClosed = salesClosed;
     }
     public boolean getSalesClosed() {
         return salesClosed;
     }

    public void setSegments(List<Segments> segments) {
         this.segments = segments;
     }
     public List<Segments> getSegments() {
         return segments;
     }

    public void setFares(List<Fares> fares) {
         this.fares = fares;
     }
     public List<Fares> getFares() {
         return fares;
     }

}
