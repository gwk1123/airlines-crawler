/**
  * Copyright 2020 bejson.com
  */
package com.crawler.api.nh.vo;

import java.util.List;

/**
 * Auto-generated: 2020-09-27 11:33:37
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DateFlight {

    private Fbasic fbasic;
    private Jbasic jbasic;
    private Wbasic wbasic;
    private Ybasic ybasic;
    private List<Flight> flight;
    private MileageStandard mileageStandard;

    private List<TransitFlight> transitFlight;
    public void setTransitFlight(List<TransitFlight> transitFlight) {
        this.transitFlight = transitFlight;
    }
    public List<TransitFlight> getTransitFlight() {
        return transitFlight;
    }

    public void setFbasic(Fbasic fbasic) {
         this.fbasic = fbasic;
     }
     public Fbasic getFbasic() {
         return fbasic;
     }

    public void setJbasic(Jbasic jbasic) {
         this.jbasic = jbasic;
     }
     public Jbasic getJbasic() {
         return jbasic;
     }

    public void setWbasic(Wbasic wbasic) {
         this.wbasic = wbasic;
     }
     public Wbasic getWbasic() {
         return wbasic;
     }

    public void setYbasic(Ybasic ybasic) {
         this.ybasic = ybasic;
     }
     public Ybasic getYbasic() {
         return ybasic;
     }

    public void setFlight(List<Flight> flight) {
         this.flight = flight;
     }
     public List<Flight> getFlight() {
         return flight;
     }

    public void setMileageStandard(MileageStandard mileageStandard) {
         this.mileageStandard = mileageStandard;
     }
     public MileageStandard getMileageStandard() {
         return mileageStandard;
     }

}
