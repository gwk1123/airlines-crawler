package com.crawler.api.kjy.controller;

import com.alibaba.fastjson.JSON;
import com.crawler.api.kjy.vo.createOrder.*;
import com.crawler.api.kjy.vo.search.SearchRequest;
import com.crawler.api.kjy.vo.verify.Journey;
import com.crawler.api.request.GDSOrderRequestDTO;
import com.crawler.api.request.GDSSearchRequestDTO;
import com.crawler.api.request.Passenger;
import com.crawler.api.response.GDSSearchResponseDTO;
import com.crawler.api.response.Routing;
import com.crawler.api.response.Segment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController(value = "/kjy")
public class OrderController {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMDDHHmm");
    DateTimeFormatter underscore = DateTimeFormatter.ofPattern("yyyy-MM-DD");

    DateTimeFormatter localdate = DateTimeFormatter.ofPattern("HH:mm");

    private Logger logger = LoggerFactory.getLogger(OrderController.class);


    @RequestMapping(value = "/order")
    public GDSSearchResponseDTO order(GDSOrderRequestDTO gdsOrderRequestDTO){
        logger.info("order请求参数:{}", JSON.toJSONString(gdsOrderRequestDTO));
        CreateOrderRequest searchRequest= transformOrderRequest( gdsOrderRequestDTO);
        return null;
    }

    public CreateOrderRequest transformOrderRequest(GDSOrderRequestDTO gdsOrderRequestDTO){

        QueryBaseInfo queryBaseInfo=new QueryBaseInfo();
        queryBaseInfo.setUserOrderId(gdsOrderRequestDTO.getUid());
        if(gdsOrderRequestDTO.getContact() != null) {
            queryBaseInfo.setOrderDelayName(gdsOrderRequestDTO.getContact().getPhoneNumber());
            queryBaseInfo.setOrderDelayName(gdsOrderRequestDTO.getContact().getName());
        }

        List<Segment> segments =new ArrayList<>();
        segments.addAll(gdsOrderRequestDTO.getRouting().getFromSegments());
        if("2".equals(gdsOrderRequestDTO.getTripType())){
            segments.addAll(gdsOrderRequestDTO.getRouting().getRetSegments());
        }

        List<JourneyRequest> journeyRequests= transformOrderSegmentRequest(segments);
        List<PassengerRequest> passengerRequests = transformOrderPassengerRequest(gdsOrderRequestDTO.getPassengers());

        CreateOrderRequest createOrderRequest=new CreateOrderRequest();
        createOrderRequest.setQueryBaseInfo(queryBaseInfo);
        createOrderRequest.setJourney(journeyRequests);
        createOrderRequest.setPassenger(passengerRequests);
        this.transformOrderPriceRequest(gdsOrderRequestDTO.getRouting(), createOrderRequest);//设置价格信息
        return  createOrderRequest;
    }



    public List<PassengerRequest> transformOrderPassengerRequest(List<Passenger> passengers){

        return passengers.stream().filter(Objects::nonNull).map(m ->{
            PassengerRequest passengerRequest=new PassengerRequest();
            passengerRequest.setPassengerName(m.getName());
            passengerRequest.setCardId(m.getCardNum());
            passengerRequest.setPhone(m.getMobile());
            passengerRequest.setPassengerType(m.getPassengerType());
            passengerRequest.setCardTypeId(m.getCardType());
            return passengerRequest;
        }).collect(Collectors.toList());
    }

    /**
     * 处理验价请求前参数
     * @param segments
     * @return
     */
    public List<JourneyRequest> transformOrderSegmentRequest(List<Segment> segments){

        return segments.stream().map(m ->{
            JourneyRequest journey = new JourneyRequest();
            journey.setCarrier(m.getCarrier());
            journey.setFlightNo(m.getFlightNumber());
            journey.setBoardPoint(m.getDepAirport());
            journey.setOffPoint(m.getArrAirport());
            journey.setCode(m.getCabin());
            journey.setAircraft(m.getAircraftCode());

            LocalDateTime depLocal = LocalDateTime.parse(m.getDepTime(),formatter);
            String depDate = depLocal.format(underscore);
            journey.setFromDate(depDate);
            String depTime = depLocal.format(localdate);
            journey.setFromTime(depTime);

            LocalDateTime arrLocal = LocalDateTime.parse(m.getArrTime(),formatter);
            String arrDate = arrLocal.format(underscore);
            journey.setTodate(arrDate);
            String arrTime = arrLocal.format(localdate);
            journey.setFromTime(arrTime);

            return journey;
        }).collect(Collectors.toList());
    }


    public void transformOrderPriceRequest(Routing routing,CreateOrderRequest createOrderRequest){

        Price price=new Price();
        price.setAirportTax(routing.getAdultAirportTax());
        price.setFuelSurTax(routing.getAdultFuelCosts()); //燃油
        price.setPrice(routing.getAdultPrice().toString());
        //ordernowReturn  代理费总金额
        //serviceCharge	开票服务费
        //settlementPrice 成人结算总价 成人订单结算总价= / （票面价格-代理费金额+开票服务费+机建+燃油）*成人人数
        Price chdPrice=new Price();
        chdPrice.setAirportTax(routing.getChildAirportTax());
        chdPrice.setFuelSurTax(routing.getChildFuelCosts()); //燃油
        chdPrice.setPrice(routing.getChildPrice().toString());
        //ordernowReturn  代理费总金额
        //serviceCharge	开票服务费
        //settlementPrice 成人结算总价 成人订单结算总价= / （票面价格-代理费金额+开票服务费+机建+燃油）*成人人数
        createOrderRequest.setPrice(price);
        createOrderRequest.setChdPrice(chdPrice);
    }


}

