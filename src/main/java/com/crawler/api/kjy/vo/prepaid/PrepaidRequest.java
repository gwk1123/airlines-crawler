package com.crawler.api.kjy.vo.prepaid;


import lombok.Data;

@Data
public class PrepaidRequest {

    /**
     * 是	String	PNR编码
     */
    private String pnr;
    /**
     * 否	Integer	是否为预订编码,0否，1是 默认0
     */
    private String hk;
    /**
     * 否	Integer	是否获取票号信息（只有hk=0时此项才有效）0否，1是 默认0
     */
    private String getTicket;
    /**
     * 否	Integer	是否获取该订单的票面、机建、燃油价格信息 0 或1,PAT价格 默认0
     */
    private String getPrice;
    /**
     * 	否	Integer	是否获取RT的原始信息，0否，1是 默认0
     */
    private String getRawdata;
    /**
     * 否	String	大客户号,政采用gp/cc
     */
    private String customerCode;
    /**
     * 否	Integer	是否获取乘机人的手机号码，0否，1是 默认0
     */
    private String getTel;
    /**
     * 否	String	航空公司二字码
     */
    private String carrierCode;



}
