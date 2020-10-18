package com.crawler.api.aq.controller;

import com.crawler.api.aq.vo.*;
import com.crawler.api.request.GDSSearchRequestDTO;
import com.crawler.api.response.GDSSearchResponseDTO;
import com.crawler.api.response.Routing;
import com.crawler.api.response.Segment;
import com.crawler.api.utils.HttpRequestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/aq")
public class AQController {

    private Logger logger= LoggerFactory.getLogger(AQController.class);
    private String TRIP_TYPE_RT ="2";
    private String PAX_TYPE_ADT ="ADT";
    private String PAX_TYPE_INF ="INF";
    private String PAX_TYPE_CHD ="CHD";

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
    public GDSSearchResponseDTO search(@RequestBody GDSSearchRequestDTO gdsSearchRequestDTO) throws JsonProcessingException, ParseException {
        transformRequest( gdsSearchRequestDTO);
        AQResponse aqResponse= HttpRequestUtil.searchAQContent(gdsSearchRequestDTO);
        return transformResponse( aqResponse, gdsSearchRequestDTO);
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

    public void transformRequest(GDSSearchRequestDTO gdsSearchRequestDTO) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date fromDate = formatter.parse(gdsSearchRequestDTO.getFromDate());
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        String fromDateStr = ft.format(fromDate);
        if(TRIP_TYPE_RT.equals(gdsSearchRequestDTO.getTripType())) {
            Date rtDate = formatter.parse(gdsSearchRequestDTO.getRetDate());
            String rtDateStr = ft.format(rtDate);
            gdsSearchRequestDTO.setRetDate(rtDateStr);
        }
        gdsSearchRequestDTO.setFromDate(fromDateStr);
    }


    public GDSSearchResponseDTO transformResponse(AQResponse aqResponse,GDSSearchRequestDTO gdsSearchRequestDTO){
        GDSSearchResponseDTO gdsSearchResponseDTO = null;
        if(aqResponse == null || aqResponse.getStatus() !=200){
            return null;
        }
        ArrayList<Routing> routingList =new ArrayList<>();
        aqResponse.getData().getFlights().stream().forEach(e ->{
            e.forEach( f ->{
                List<Routing> routings= transformSibeRouting(f, gdsSearchRequestDTO);
                routingList.addAll(routings);
            });
        });
        gdsSearchResponseDTO =new GDSSearchResponseDTO();
        gdsSearchResponseDTO.setStatus(0);
        gdsSearchResponseDTO.setMsg("成功");
        gdsSearchResponseDTO.setUid(gdsSearchRequestDTO.getUid());
        gdsSearchResponseDTO.setRoutings(routingList);
        return gdsSearchResponseDTO;

    }


    public List<Routing> transformSibeRouting(Flights flights,GDSSearchRequestDTO gdsSearchRequestDTO){
        Map<String,List<Fares>> faresMap = flights.getFares().stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Fares::getSubClass));
        BigDecimal tax = new BigDecimal(0);
        List<Routing> routings = new ArrayList<>();
        faresMap.keySet().forEach(key ->{
            Segment segment = transformSegment(flights);
            segment.setCabin(key);
            segment.setCabinGrade(faresMap.get(key).get(0).getCabinClass());
            Routing routing =new Routing();
            faresMap.get(key).stream().forEach(e ->{
                if(PAX_TYPE_ADT.equals(e.getPaxType())){
                    routing.setAdultPrice(new BigDecimal(StringUtils.isEmpty(e.getTicketPrice())?"0":e.getTicketPrice()));
                }
                if(PAX_TYPE_CHD.equals(e.getPaxType())){
                    routing.setChildPrice(new BigDecimal(StringUtils.isEmpty(e.getTicketPrice())?"0":e.getTicketPrice()));
                }
                if(PAX_TYPE_INF.equals(e.getPaxType())){
                    routing.setInfantsPrice(new BigDecimal(StringUtils.isEmpty(e.getTicketPrice())?"0":e.getTicketPrice()));
                }
            });
            routing.setValidatingCarrier(flights.getSegments().get(0).getMarketAirlineCode());
            routing.setAdultTax(tax);
            routing.setChildTax(tax);
            routing.setInfantsTax(tax);
            routing.setProductType("PRV");
            routing.setCurrency("CNY");
            routing.setReservationType(gdsSearchRequestDTO.getReservationType());
            routing.setOfficeId(gdsSearchRequestDTO.getOfficeId());
            List<Segment> segments=new ArrayList<>();
            segments.add(segment);
            routing.setFromSegments(segments);
            routing.setRetSegments(new ArrayList<>());
            routings.add(routing);
        });
        return routings;
    }

    //目前只支持单程一段
    public Segment transformSegment(Flights flights){
        Segment segment = new Segment();
        Segments aqSegments =flights.getSegments().get(0);
        segment.setItemNumber(1);
        segment.setFlightIndicator("1");
        segment.setCarrier(aqSegments.getCarrierAirlineCode());
        segment.setFlightNumber(aqSegments.getCarrierFlightNo());
        segment.setDepAirport(aqSegments.getDepartAirportCode());
        segment.setArrAirport(aqSegments.getArrivalAirportCode());
        segment.setDepTime(transformTimestampStr(aqSegments.getDepartDate()+" " +aqSegments.getDepartTime()));
        segment.setArrTime(transformTimestampStr(aqSegments.getArrivalDate() +" "+aqSegments.getArrivalTime()));
        segment.setCodeShare(aqSegments.getCodeShare());
        segment.setDepTerminal(aqSegments.getDepartTerminal());
        segment.setArrTerminal(aqSegments.getArrivalTerminal());
        segment.setAircraftCode(aqSegments.getModel());
        return segment;
    }


    public String transformTimestampStr(String time){
        DateTimeFormatter ft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(time, ft);
        // 将时间戳转为当前时间
        return DateTimeFormatter.ofPattern("yyyyMMddHHmm").format(localDateTime);
    }

}
