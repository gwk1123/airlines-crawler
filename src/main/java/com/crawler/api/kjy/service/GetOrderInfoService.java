package com.crawler.api.kjy.service;

import com.crawler.api.kjy.vo.getOrderInfo.GetOrderInfoRequest;
import com.crawler.api.kjy.vo.getOrderInfo.GetOrderInfoResponse;

public interface GetOrderInfoService {
    public GetOrderInfoResponse getOrderInfo(GetOrderInfoRequest getOrderInfoRequest);
}
