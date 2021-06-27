package com.crawler.api.kjy.service.impl;


import com.alibaba.fastjson.JSON;
import com.crawler.api.kjy.vo.verify.TicketPrice;
import com.crawler.api.kjy.vo.verify.VerifyRequest;
import com.crawler.api.kjy.vo.verify.VerifyResponse;
import com.crawler.api.request.GDSVerifyRequestDTO;
import com.crawler.api.response.GDSVerifyResponseDTO;
import com.crawler.api.utils.Constant;
import com.crawler.api.utils.HttpRequestUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VerifyServiceImpl {


    public GDSVerifyResponseDTO verify(VerifyRequest verifyRequest, GDSVerifyRequestDTO gdsVerifyRequestDTO){

        String verifyRequestStr= JSON.toJSONString(verifyRequest);
        String data = HttpRequestUtil.post(Constant.VERFIY_URL,verifyRequestStr);
        try{
            GDSVerifyResponseDTO gdsVerifyResponseDTO= this.transformVerfiyResponse(data,gdsVerifyRequestDTO);
            return gdsVerifyResponseDTO;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }


    public GDSVerifyResponseDTO transformVerfiyResponse(String data , GDSVerifyRequestDTO gdsVerifyRequestDTO){
        if(StringUtils.isEmpty(data)){
            return null;
        }
        VerifyResponse verifyResponse = JSON.parseObject(data,VerifyResponse.class);
        List<TicketPrice> ticketPrice = verifyResponse.getTicketPrice();
        Double price = ticketPrice.stream().mapToDouble(TicketPrice::getPrice).sum(); //票面价格
        Double cn = ticketPrice.stream().mapToDouble(TicketPrice::getCN).sum(); //机建价格
        Double yq = ticketPrice.stream().mapToDouble(TicketPrice::getYQ).sum(); //燃油
        GDSVerifyResponseDTO gdsVerifyResponseDTO =new GDSVerifyResponseDTO();
        gdsVerifyRequestDTO.getRouting().setAdultAirportTax(cn.toString());
        gdsVerifyResponseDTO.getRouting().setAdultPrice(new BigDecimal(price));
        gdsVerifyResponseDTO.setStatus(0);
        gdsVerifyResponseDTO.setMsg("成功");
        gdsVerifyResponseDTO.setUid(gdsVerifyRequestDTO.getUid());
        return gdsVerifyResponseDTO;
    }



}
