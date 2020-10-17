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
public class Products {

    private String code;
    private String name;
    private String salePrice;
    private String text;
    private List<ProductAncillaryServices> productAncillaryServices;
    private String labels;
    private String maxAge;
    private String minAge;
    private String gender;
    private String member;
    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setSalePrice(String salePrice) {
         this.salePrice = salePrice;
     }
     public String getSalePrice() {
         return salePrice;
     }

    public void setText(String text) {
         this.text = text;
     }
     public String getText() {
         return text;
     }

    public void setProductAncillaryServices(List<ProductAncillaryServices> productAncillaryServices) {
         this.productAncillaryServices = productAncillaryServices;
     }
     public List<ProductAncillaryServices> getProductAncillaryServices() {
         return productAncillaryServices;
     }

    public void setLabels(String labels) {
         this.labels = labels;
     }
     public String getLabels() {
         return labels;
     }

    public void setMaxAge(String maxAge) {
         this.maxAge = maxAge;
     }
     public String getMaxAge() {
         return maxAge;
     }

    public void setMinAge(String minAge) {
         this.minAge = minAge;
     }
     public String getMinAge() {
         return minAge;
     }

    public void setGender(String gender) {
         this.gender = gender;
     }
     public String getGender() {
         return gender;
     }

    public void setMember(String member) {
         this.member = member;
     }
     public String getMember() {
         return member;
     }

}
