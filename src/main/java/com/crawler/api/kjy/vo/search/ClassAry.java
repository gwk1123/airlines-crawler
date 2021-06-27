package com.crawler.api.kjy.vo.search;

import lombok.Data;

@Data
public class ClassAry {

        private String seatNum;
        private String Code;
        private String cabinClass;
        private String fullPrice;
        private String Seat;
        private String Price;
        private String tjId;
        private String teShowImage;
        private String seatDis;
        private String seatDissNew;
        private String zk;
        private String Code_Show;
        private String isTj;
        private SeatInfo seatInfo;
        private String seatCode;
        private String seatPrice;
        private String cstPolicy;
        private String policyId;
        private String policyTicketing;
        private String settlementPrice;
        private String serviceCharge;
        private String ordernowReturn;
        private String policyType;
        private String promotionType;
        private String  fareBasis;//	运价基础	运价基础代码 如:YS
        private String aircraft; //机型	如：738
        private String boardPointAT; //出发航站楼	如：T1
        private String offPointAT; //到达航站楼	如：T2
}
