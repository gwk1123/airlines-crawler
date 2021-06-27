package com.crawler.api.kjy.controller;


import com.alibaba.fastjson.JSON;
import com.crawler.api.kjy.service.VerifyService;
import com.crawler.api.kjy.vo.search.SearchRequest;
import com.crawler.api.kjy.vo.verify.Journey;
import com.crawler.api.kjy.vo.verify.VerifyRequest;
import com.crawler.api.request.GDSSearchRequestDTO;
import com.crawler.api.request.GDSVerifyRequestDTO;
import com.crawler.api.response.GDSSearchResponseDTO;
import com.crawler.api.response.GDSVerifyResponseDTO;
import com.crawler.api.response.Segment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/kjy")
public class VerifyController {

    @Autowired
    private VerifyService verifyService;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMDDHHmm");
    DateTimeFormatter underscore = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm");

    private Logger logger = LoggerFactory.getLogger(VerifyController.class);


    @RequestMapping(value = "/verify")
    public GDSVerifyResponseDTO verify(GDSVerifyRequestDTO gdsVerifyRequestDTO){
        logger.info("verify请求参数:{}", JSON.toJSONString(gdsVerifyRequestDTO));
        List<Journey> fromJourneys= this.transformVerifySegmentRequest( gdsVerifyRequestDTO.getRouting().getFromSegments());
        VerifyRequest fromVerify =new VerifyRequest();
        fromVerify.setJourney(fromJourneys);
        GDSVerifyResponseDTO oneWayVerify= verifyService.verify(fromVerify,gdsVerifyRequestDTO);

        //往返
        if("2".equals(gdsVerifyRequestDTO.getTripType())){
            List<Journey> retJourneys= this.transformVerifySegmentRequest( gdsVerifyRequestDTO.getRouting().getRetSegments());
            VerifyRequest retVerifyRequest =new VerifyRequest();
            retVerifyRequest.setJourney(retJourneys);
            GDSVerifyResponseDTO roundTripVerify= verifyService.verify(retVerifyRequest,gdsVerifyRequestDTO);
        }

        //对两个数据结果集处理
        GDSVerifyResponseDTO gdsVerifyResponseDTO=new GDSVerifyResponseDTO();
        return gdsVerifyResponseDTO;
    }


    /**
     * 处理验价请求前参数
     * @param segments
     * @return
     */
    public List<Journey> transformVerifySegmentRequest(List<Segment> segments){

        return segments.stream().map(m ->{
            Journey journey = new Journey();
            journey.setCarrier(m.getCarrier());
            journey.setFlightNo(m.getFlightNumber());
            journey.setBoardPoint(m.getDepAirport());
            journey.setOffPoint(m.getArrAirport());
            journey.setCode(m.getCabin());
            journey.setAircraft(m.getAircraftCode());

            LocalDateTime depLocal = LocalDateTime.parse(m.getDepTime(),formatter);
            String depDateTime = depLocal.format(underscore);
            journey.setFromDateTime(depDateTime);

            LocalDateTime arrLocal = LocalDateTime.parse(m.getArrTime(),formatter);
            String arrDateTime = arrLocal.format(underscore);
            journey.setArrivalDateTime(arrDateTime);
            return journey;
        }).collect(Collectors.toList());
    }

}
