package com.crawler.api.cq.controller;

import com.alibaba.fastjson.JSON;
import com.crawler.api.cq.bean.CQMap;
import com.crawler.api.cq.vo.CQRequest;
import com.crawler.api.cq.vo.CQResponse;
import com.crawler.api.cq.vo.Route;
import com.crawler.api.request.GDSSearchRequestDTO;
import com.crawler.api.response.GDSSearchResponseDTO;
import com.crawler.api.response.Routing;
import com.crawler.api.response.Segment;
import com.crawler.api.utils.HttpRequestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RequestMapping("/api/cq")
@RestController
public class CQController {

    private Logger logger = LoggerFactory.getLogger(CQController.class);
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private CQMap cqMap;

    /**
     * @return
     */
    @RequestMapping("/search")
    public GDSSearchResponseDTO searchPost(@RequestBody GDSSearchRequestDTO gdsSearchRequestDTO) throws JsonProcessingException, ParseException {
        CQRequest cqRequest = transformSrarchRequest(gdsSearchRequestDTO);
        cqRequest.setDeparture(cqMap.get(cqRequest.getDepCityCode()));
        cqRequest.setArrival(cqMap.get(cqRequest.getArrCityCode()));
        logger.info("cqRequest:{}", JSON.toJSONString(cqRequest));
        CQResponse cqResponse = HttpRequestUtil.searchCQContent(cqRequest);
        return transformSrarchResponse(cqResponse, gdsSearchRequestDTO);
    }

    public CQRequest transformSrarchRequest(GDSSearchRequestDTO gdsSearchRequestDTO) throws ParseException {
        CQRequest cqRequest = new CQRequest();
        cqRequest.setSType(gdsSearchRequestDTO.getTripType());
        cqRequest.setDepCityCode(gdsSearchRequestDTO.getFromCity());
        cqRequest.setArrCityCode(gdsSearchRequestDTO.getToCity());
        Date date = formatter.parse(gdsSearchRequestDTO.getFromDate());
        String formDate = sdf.format(date);
        cqRequest.setDepartureDate(formDate);
        return cqRequest;
    }

    public GDSSearchResponseDTO transformSrarchResponse(CQResponse cqResponse,GDSSearchRequestDTO gdsSearchRequestDTO){
        GDSSearchResponseDTO gdsSearchResponseDTO = null;
        if(cqResponse == null || CollectionUtils.isEmpty(cqResponse.getRoute())){
            return gdsSearchResponseDTO;
        }
        ArrayList<Routing> routingList = new ArrayList<>();
        cqResponse.getRoute().stream().filter(Objects::nonNull).forEach(e ->{
            List<Routing> routings = this.transformRouting(e, gdsSearchRequestDTO);
            routingList.addAll(routings);
        });
        gdsSearchResponseDTO =new GDSSearchResponseDTO();
        gdsSearchResponseDTO.setStatus(0);
        gdsSearchResponseDTO.setMsg("成功");
        gdsSearchResponseDTO.setUid(gdsSearchRequestDTO.getUid());
        gdsSearchResponseDTO.setRoutings(routingList);
        return gdsSearchResponseDTO;
    }

    public List<Routing> transformRouting(List<Route> routes, GDSSearchRequestDTO gdsSearchRequestDTO){
        List<Routing> routings = new ArrayList<>();
        if(routes.size()==1){
            Routing routing =new Routing();
            Segment segment = this.transformFlightNumber(routes.get(0),"1",1);
            routing.setValidatingCarrier(segment.getCarrier());
            routing.setFromSegments(new ArrayList<Segment>(){{add(segment);}});
            routing.setRetSegments(new ArrayList<>());
            routing.setCurrency("CNY");
            routing.setAdultPrice(new BigDecimal(routes.get(0).getMinCabinPriceForDisplay()));
            routing.setAdultTax(new BigDecimal("0"));
            routing.setChildPrice(routing.getAdultPrice());
            routing.setChildTax(routing.getAdultTax());
            routing.setProductType("PRV");
            routing.setReservationType(gdsSearchRequestDTO.getReservationType());
            routing.setOfficeId(gdsSearchRequestDTO.getOfficeId());
            routings.add(routing);
        }else {
            for(int i=1;i<routes.size();i++){
                Routing routing =new Routing();
                Segment segment = this.transformFlightNumber(routes.get(0),"1",1);
                Segment segment1 = this.transformFlightNumber(routes.get(i),String.valueOf("2"),1);
                routing.setFromSegments(new ArrayList<Segment>(){{add(segment);add(segment1);}});
                routing.setRetSegments(new ArrayList<>());
                routing.setCurrency("CNY");
                routing.setAdultPrice(new BigDecimal(routes.get(0).getMinCabinPriceForDisplay()));
                routing.setProductType("PRV");
                routing.setReservationType(gdsSearchRequestDTO.getReservationType());
                routing.setOfficeId(gdsSearchRequestDTO.getOfficeId());

                routing.setAdultPrice((new BigDecimal(routes.get(0).getMinCabinPriceForDisplay())).add(new BigDecimal(routes.get(i).getMinCabinPriceForDisplay())));
                routing.setAdultTax(new BigDecimal("0"));
                routing.setChildPrice(routing.getAdultPrice());
                routing.setChildTax(routing.getAdultTax());
                routings.add(routing);
            }
        }
        return routings;
    }


    public Segment transformFlightNumber(Route route,String flightIndicator,int itemNumber){
        Segment segment =new Segment();
        segment.setFlightIndicator(flightIndicator);
        segment.setItemNumber(itemNumber);
        segment.setCarrier(route.getNo().substring(0,2));
        segment.setFlightNumber(route.getNo().substring(2));
        segment.setDepAirport(route.getDepartureAirportCode());
        segment.setArrAirport(route.getArrivalAirportCode());
        segment.setDepTime(this.transformTimestampStr(route.getDepartureTime()));
        segment.setArrTime(this.transformTimestampStr(route.getArrivalTime()));
        segment.setCodeShare(false);
        segment.setBookingClassAvail("9");
        segment.setAircraftCode("320");
        segment.setCabinGrade("Y");
        //舱位的解析
        segment.setCabin(route.getAircraftCabins().get(0).getAircraftCabinInfos().get(0).getName());
        return segment;
    }

    public String transformTimestampStr(String time){
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(time, ft);
        // 将时间戳转为当前时间
        return DateTimeFormatter.ofPattern("yyyyMMddHHmm").format(localDateTime);
    }

}
