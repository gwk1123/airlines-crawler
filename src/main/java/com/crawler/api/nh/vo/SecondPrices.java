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
public class SecondPrices {

    private String code;
    private List<Price> price;
    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setPrice(List<Price> price) {
         this.price = price;
     }
     public List<Price> getPrice() {
         return price;
     }

}
