package com.crawler.api.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

/**
 * Created by yangdehua on 10/26/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GDSVerifyResponseDTO {

    private int status;
    private String msg;
    private  String uid;
    private int maxSeats;
    private String relatesText;

    private Routing routing;

    public Routing getRouting() {
        if(routing == null){
            return new Routing();
        }
        return routing;
    }

    public void setRouting(Routing routing) {
        this.routing = routing;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public void setMaxSeats(int maxSeats) {
        this.maxSeats = maxSeats;
    }

    public String getRelatesText() {
        return relatesText;
    }

    public void setRelatesText(String relatesText) {
        this.relatesText = relatesText;
    }
}


