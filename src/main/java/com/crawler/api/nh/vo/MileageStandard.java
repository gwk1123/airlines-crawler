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
public class MileageStandard {

    private String oriAirportCode;
    private String desAirportCode;
    private List<ClassAccrualDtos> classAccrualDtos;
    public void setOriAirportCode(String oriAirportCode) {
         this.oriAirportCode = oriAirportCode;
     }
     public String getOriAirportCode() {
         return oriAirportCode;
     }

    public void setDesAirportCode(String desAirportCode) {
         this.desAirportCode = desAirportCode;
     }
     public String getDesAirportCode() {
         return desAirportCode;
     }

    public void setClassAccrualDtos(List<ClassAccrualDtos> classAccrualDtos) {
         this.classAccrualDtos = classAccrualDtos;
     }
     public List<ClassAccrualDtos> getClassAccrualDtos() {
         return classAccrualDtos;
     }

}
