package com.crawler.api.kjy.vo.search;

import lombok.Data;

@Data
public class SearchRequest {

    /**
     * 出发三字码	Y	如：PEK
     */
    private String fromcity;

    /**
     * 到达三字码	Y	如：SHA
     */
    private String tocity;
    /**
     * 出发日期	Y	如：2017-05-05
     */
    private String fromdate;
    /**
     * 舱位价格	Y	是否返回全部舱位价格 0.返回最低价格 1.返回所有舱位价格
     */
    private String isPriceAll;
    /**
     * 行程类型	N	1.单程（默认） 2.往返（分多次单程查询，多程验价获取优惠）　　3.联程（分多次单程查询，多程验价获取优惠）
     */
    private String searchType;
    /**
     * 共享航班	N	1.返回共享航班 0.不返回共享航班
     */
    private String shareaFlights;
    /**
     * 不返回退改签文本数据	N	不返回退改签文本数据 1不返回，0返回，默认0
     */
    private String notReturnChangeRuleTxt;
    /**
     * 不返回退改签规则详细说明	N	不返回退改签规则详细说明 1不返回，0返回，默认0
     */
    private String notReturnChangeRuleDetail;
    /**
     * 舱位等级	N	F/C/Y 可选，空为不限，头等舱和公务舱两舱查询为 F-C-J，单公务舱查询为C-J，经济舱查询为Y.
     */
    private String cabinClass;
    /**
     * 航空公司	N	航司二字码，改签查询时用，如：MU-FM； 注：国内航司中，东上航包括MU、FM2个航司代码，
     * 改签查询时如是东上航航班，请求需提交2个航司 carrierCode和accountCodeInfo，2个参数二选一
     */
    private String carrierCode;
    /**
     * 是否返回所有航空公司	N	是否返回 accountCodeInfo
     * 参数指定航空公司外的其他航空公司 1是 0否
     */
    private String backAccountCodeFlight;

    private String uid;

}
