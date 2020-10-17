package com.crawler.api.aq.controller;

import com.crawler.api.aq.vo.AQRequest;
import com.crawler.api.aq.vo.FlightCondition;
import com.crawler.api.request.GDSSearchRequestDTO;
import com.crawler.api.utils.HttpRequestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/aq")
public class AQController {

    private Logger logger= LoggerFactory.getLogger(AQController.class);

    /**
     * 测试目前该接口不通
     * @param aqRequest
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/search/test")
    public String searchTest(@RequestBody AQRequest aqRequest) throws JsonProcessingException {
        if(aqRequest == null){
            aqRequest =new AQRequest();
//            FlightCondition flightCondition=new FlightCondition();
//            aqRequest.setFlightCondition(flightCondition);
        }
        FlightCondition flightCondition=new FlightCondition();
        List<FlightCondition> flightConditions=new ArrayList<>();
        flightConditions.add(flightCondition);
        aqRequest.setFlightCondition(flightConditions);
        return HttpRequestUtil.searchAQContentTest(aqRequest);
    }

    @RequestMapping(value = "/search")
    public String search(@RequestBody GDSSearchRequestDTO gdsSearchRequestDTO) throws JsonProcessingException {
        return HttpRequestUtil.searchAQContent(gdsSearchRequestDTO);
    }

    public AQRequest transformSearch(GDSSearchRequestDTO gdsSearchRequestDTO){
        AQRequest aqRequest =new AQRequest();
        aqRequest.setAdultCount(String.valueOf(gdsSearchRequestDTO.getAdultNumber()==0?1:gdsSearchRequestDTO.getAdultNumber()));
        aqRequest.setChildCount(String.valueOf(gdsSearchRequestDTO.getChildNumber()));
        aqRequest.setInfantCount(String.valueOf(gdsSearchRequestDTO.getInfantNumber()));
        aqRequest.setTripType("1".equals(gdsSearchRequestDTO.getTripType())?"OW":"2".equals(gdsSearchRequestDTO.getTripType())?"RT":"RM");
        List<FlightCondition> flightConditions=new ArrayList<>();
        FlightCondition flightCondition=new FlightCondition();
        flightCondition.setDepDate(gdsSearchRequestDTO.getFromDate());
        flightCondition.setDepCode(gdsSearchRequestDTO.getFromCity());
        flightCondition.setArrCode(gdsSearchRequestDTO.getToCity());
        flightConditions.add(flightCondition);
        aqRequest.setFlightCondition(flightConditions);
        return aqRequest;
    }

}
