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
public class Data {

    private String id;
    private String createTime;
    private List<Segment> segment;
//    private List<Citys> citys;
//    private List<Airports> airports;
//    private List<Planes> planes;
//    private ProductRules productRules;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setCreateTime(String createTime) {
         this.createTime = createTime;
     }
     public String getCreateTime() {
         return createTime;
     }

    public void setSegment(List<Segment> segment) {
         this.segment = segment;
     }
     public List<Segment> getSegment() {
         return segment;
     }

//    public void setCitys(List<Citys> citys) {
//         this.citys = citys;
//     }
//     public List<Citys> getCitys() {
//         return citys;
//     }
//
//    public void setAirports(List<Airports> airports) {
//         this.airports = airports;
//     }
//     public List<Airports> getAirports() {
//         return airports;
//     }
//
//    public void setPlanes(List<Planes> planes) {
//         this.planes = planes;
//     }
//     public List<Planes> getPlanes() {
//         return planes;
//     }
//
//    public void setProductRules(ProductRules productRules) {
//         this.productRules = productRules;
//     }
//     public ProductRules getProductRules() {
//         return productRules;
//     }

}
