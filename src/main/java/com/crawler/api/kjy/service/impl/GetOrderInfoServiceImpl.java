package com.crawler.api.kjy.service.impl;

import com.alibaba.fastjson.JSON;
import com.crawler.api.kjy.service.GetOrderInfoService;
import com.crawler.api.kjy.vo.getOrderInfo.GetOrderInfoRequest;
import com.crawler.api.kjy.vo.getOrderInfo.GetOrderInfoResponse;
import com.crawler.api.utils.Constant;
import com.crawler.api.utils.HttpRequestUtil;
import org.springframework.stereotype.Service;

@Service
public class GetOrderInfoServiceImpl implements GetOrderInfoService {


    @Override
    public GetOrderInfoResponse getOrderInfo(GetOrderInfoRequest getOrderInfoRequest){
        String getOrderInfoRequestStr= JSON.toJSONString(getOrderInfoRequest);
        String data = HttpRequestUtil.post(Constant.GET_ORDER_INFO,getOrderInfoRequestStr);
        try{
            GetOrderInfoResponse getOrderInfoResponse=JSON.parseObject(data,GetOrderInfoResponse.class);
            return getOrderInfoResponse;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
