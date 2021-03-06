package com.crawler.api.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gwk
 * @date 10/26/16
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GDSSearchResponseDTO implements Serializable {

    private int status;
    private String msg;
    private String uid;
    private List<Routing> routings;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public List<Routing> getRoutings() {
        if (routings == null) {
            routings = new ArrayList<Routing>();
        }
        return this.routings;
    }

    public void setRoutings(List<Routing> routings) {
        this.routings = routings;
    }
}


