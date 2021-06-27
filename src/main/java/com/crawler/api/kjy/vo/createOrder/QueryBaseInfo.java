package com.crawler.api.kjy.vo.createOrder;

import lombok.Data;

@Data
public class QueryBaseInfo {

    /**
     * N	用户本地生成的唯一标识
     * 唯一性用户自行保证
     * 接口不做限制
     * 长度不能超过64
     */
    private String userOrderId;
    /**
     * 联系人	N	订单联系人姓名
     */
    private String orderDelayName;
    /**
     * 手机号	N	订单联系人手机号
     */
    private String orderDelayPhone;
}
