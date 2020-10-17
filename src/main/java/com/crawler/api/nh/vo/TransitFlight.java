/**
  * Copyright 2020 bejson.com
  */
package com.crawler.api.nh.vo;



import java.util.List;

/**
 * Auto-generated: 2020-09-30 13:18:16
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class TransitFlight {

    private String depCity;
    private String arrCity;
    private List<Solutions> solutions;
    private List<Segments> segments;
    public void setDepCity(String depCity) {
         this.depCity = depCity;
     }
     public String getDepCity() {
         return depCity;
     }

    public void setArrCity(String arrCity) {
         this.arrCity = arrCity;
     }
     public String getArrCity() {
         return arrCity;
     }

    public void setSolutions(List<Solutions> solutions) {
         this.solutions = solutions;
     }
     public List<Solutions> getSolutions() {
         return solutions;
     }

    public void setSegments(List<Segments> segments) {
         this.segments = segments;
     }
     public List<Segments> getSegments() {
         return segments;
     }

}
