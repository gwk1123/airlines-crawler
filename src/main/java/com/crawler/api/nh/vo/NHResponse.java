/**
  * Copyright 2020 bejson.com
  */
package com.crawler.api.nh.vo;

/**
 * Auto-generated: 2020-09-27 11:33:37
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class NHResponse {

    private boolean success;
    private Data data;
    private String version;
    private String server;
    public void setSuccess(boolean success) {
         this.success = success;
     }
     public boolean getSuccess() {
         return success;
     }

    public void setData(Data data) {
         this.data = data;
     }
     public Data getData() {
         return data;
     }

    public void setVersion(String version) {
         this.version = version;
     }
     public String getVersion() {
         return version;
     }

    public void setServer(String server) {
         this.server = server;
     }
     public String getServer() {
         return server;
     }

}
