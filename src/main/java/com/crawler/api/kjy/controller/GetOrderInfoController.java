package com.crawler.api.kjy.controller;

import com.crawler.api.kjy.service.GetOrderInfoService;
import com.crawler.api.kjy.vo.getOrderInfo.GetOrderInfoRequest;
import com.crawler.api.kjy.vo.getOrderInfo.GetOrderInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取订单详情
 */
@RestController(value = "/get_order_info")
public class GetOrderInfoController {

    @Autowired
    private GetOrderInfoService getOrderInfoService;

    private Logger logger = LoggerFactory.getLogger(GetOrderInfoController.class);

    public GetOrderInfoResponse getOrderInfo(GetOrderInfoRequest getOrderInfoRequest){
        logger.info("获取订单详情参数:{}");
        //将订单对象转化成请求GDS数据对象
        return getOrderInfoService.getOrderInfo(getOrderInfoRequest);
    }

}
