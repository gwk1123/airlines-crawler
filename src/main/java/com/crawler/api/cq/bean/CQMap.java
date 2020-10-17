package com.crawler.api.cq.bean;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CQMap {
    private static Map<String,String> map = new ConcurrentHashMap<>(500);

    public void put(String key,String value){
        map.put(key,value);
    }

    public String get(String key){
        return map.get(key);
    }

    public Map<String,String> getCqMap(){
        return map;
    }

}
