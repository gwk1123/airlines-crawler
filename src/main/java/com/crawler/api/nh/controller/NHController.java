package com.crawler.api.nh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crawler.api.aysc.CompletableFutureCollector;
import com.crawler.api.nh.vo.*;
import com.crawler.api.request.GDSSearchRequestDTO;
import com.crawler.api.response.GDSSearchResponseDTO;
import com.crawler.api.response.Routing;
import com.crawler.api.response.Segment;
import com.crawler.api.utils.HttpRequestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/nh")
public class NHController {

    @Autowired
    @Qualifier("taskHttpExecutor")
    private Executor taskExecutor;
    private Logger logger = LoggerFactory.getLogger(NHController.class);

    @RequestMapping(value = "/search")
    public GDSSearchResponseDTO searchNH(@RequestBody GDSSearchRequestDTO gdsSearchRequestDTO) throws JsonProcessingException {

        NHRequest nhRequest=transformSrarchRequest(gdsSearchRequestDTO);
        logger.info("nhRequest:{}", JSON.toJSONString(nhRequest));
        List<CompletableFuture<GDSSearchResponseDTO>> requestGDSByOfficeIdFutures = new ArrayList<>();
        requestGDSByOfficeIdFutures.add(CompletableFuture.supplyAsync(() -> {
            return searchNHDirect(nhRequest);
        }, taskExecutor));
        requestGDSByOfficeIdFutures.add(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return searchNHCzaddon(nhRequest);
        }, taskExecutor));

        CompletableFuture<Void> allFutures = requestGDSByOfficeIdFutures.stream().collect(CompletableFutureCollector.allComplete());
        CompletableFuture<Void> responseFuture = CompletableFutureCollector.within(allFutures, Duration.ofSeconds(25));
        responseFuture
                .exceptionally(throwable -> {
                    logger.error("南航请求超时异常," + 25 + "秒都没有返回值", throwable);
                    return null;
                }).join();
        List<GDSSearchResponseDTO> sibeSearchResponseList = requestGDSByOfficeIdFutures
                .stream()
                .filter(CompletableFuture::isDone)
                .filter(Objects::nonNull)
                .collect(CompletableFutureCollector.collectResult())
                .join();
        sibeSearchResponseList = sibeSearchResponseList.stream().filter(Objects::nonNull).collect(Collectors.toList());

        GDSSearchResponseDTO gdsSearchResponseDTO = new GDSSearchResponseDTO();
        if (sibeSearchResponseList == null || sibeSearchResponseList.size() == 0) {
            gdsSearchResponseDTO.setStatus(1);
            gdsSearchResponseDTO.setRoutings(new ArrayList<>());
            gdsSearchResponseDTO.setMsg("南航查询无数据");
            return gdsSearchResponseDTO;
        }

        for (int i = 0; i < sibeSearchResponseList.size(); i++) {
            GDSSearchResponseDTO resp = sibeSearchResponseList.get(i);
            if (null != resp && resp.getStatus() == 0 && resp.getRoutings().size() > 0) {
                gdsSearchResponseDTO.getRoutings().addAll(resp.getRoutings());
            }
        }
        if (gdsSearchResponseDTO.getRoutings().size() > 0) {
            gdsSearchResponseDTO.setStatus(0);
            gdsSearchResponseDTO.setMsg("成功");
        } else {
            gdsSearchResponseDTO.setStatus(1);
            gdsSearchResponseDTO.setMsg("南航查询无数据");
        }
        this.transformFlightNumber(gdsSearchResponseDTO);
        return gdsSearchResponseDTO;
    }


    /**
     * 转换数据
     * @param gdsSearchRequestDTO
     * @return
     * @throws JsonProcessingException
     */
    public NHRequest transformSrarchRequest(GDSSearchRequestDTO gdsSearchRequestDTO) throws JsonProcessingException {
        NHRequest nhRequest=new NHRequest();
        nhRequest.setAdultNum(gdsSearchRequestDTO.getAdultNumber() == 0?"1":String.valueOf(gdsSearchRequestDTO.getAdultNumber()));
        nhRequest.setArrCity(gdsSearchRequestDTO.getToCity());
        nhRequest.setDepCity(gdsSearchRequestDTO.getFromCity());
        nhRequest.setChildNum(String.valueOf(gdsSearchRequestDTO.getChildNumber()));
        nhRequest.setInfantNum(String.valueOf((gdsSearchRequestDTO.getInfantNumber())));
        nhRequest.setFlightDate(gdsSearchRequestDTO.getFromDate());
        nhRequest.setFlyType(Integer.valueOf("1".equals(gdsSearchRequestDTO.getTripType())?"0":
                gdsSearchRequestDTO.getTripType()));

        nhRequest.setAction("0");
        nhRequest.setAirLine(1);
        nhRequest.setCabinOrder("0");
        nhRequest.setSegType("1");
        nhRequest.setCache(0);
        nhRequest.setInternational("0");
        return nhRequest;
    }

    /**
     * 直飞数据
     *
     * @param nhRequest
     * @return
     * @throws JsonProcessingException
     */
    public GDSSearchResponseDTO searchNHDirect(NHRequest nhRequest) {
        GDSSearchResponseDTO gdsSearchResponseDTO = null;
        try {
            String directUrl = "https://b2c.csair.com/portal/flight/direct/query";
            String nhStr = HttpRequestUtil.searchNHContent(nhRequest, directUrl);
            gdsSearchResponseDTO = this.transformSearchDirectResponse(nhStr);
        } catch (Exception e) {
            logger.error("南航直飞search请求异常:", e);
            return null;
        }
        if (gdsSearchResponseDTO == null || gdsSearchResponseDTO.getStatus() != 0 || gdsSearchResponseDTO.getRoutings().size() == 0) {
            return null;
        }
        return gdsSearchResponseDTO;
    }

    /**
     * 中转数据
     *
     * @param nhRequest
     * @return
     */
    public GDSSearchResponseDTO searchNHCzaddon(NHRequest nhRequest) {
        GDSSearchResponseDTO gdsSearchResponseDTO = null;
        try {
            String czaddonUrl = "https://b2c.csair.com/portal/flight/czaddon/query";
            nhRequest.setFlyType(2);
            String nhStr = HttpRequestUtil.searchNHContent(nhRequest, czaddonUrl);
            gdsSearchResponseDTO = this.transformSearchCzaddonResponse(nhStr);
        } catch (Exception e) {
            logger.error("南航中转search请求异常:", e);
            return null;
        }
        if (gdsSearchResponseDTO == null || gdsSearchResponseDTO.getStatus() != 0 || gdsSearchResponseDTO.getRoutings().size() == 0) {
            return null;
        }
        return gdsSearchResponseDTO;
    }


    public GDSSearchResponseDTO transformSearchDirectResponse(String nhStr) {
        GDSSearchResponseDTO gdsSearchResponseDTO = new GDSSearchResponseDTO();
        gdsSearchResponseDTO.setStatus(0);
        gdsSearchResponseDTO.setMsg("succes");
        gdsSearchResponseDTO.setUid("123456789");
        ArrayList<Routing> routings = new ArrayList<>();
        try {
            NHResponse nhResponse = JSONObject.parseObject(nhStr, NHResponse.class);
            //目前不支持往返

            if (!nhResponse.getSuccess() || CollectionUtils.isEmpty(nhResponse.getData().getSegment().get(0).getDateFlight().getFlight())) {
                gdsSearchResponseDTO.setRoutings(routings);
                return gdsSearchResponseDTO;
            }
            nhResponse.getData().getSegment().get(0).getDateFlight().getFlight()
                    .stream().filter(Objects::nonNull).forEach(e -> {

                List<Routing> routings1 = this.transformSearchDirectRouting(e);
                routings.addAll(routings1);
            });

        } catch (Exception e) {
            e.printStackTrace();
            gdsSearchResponseDTO.setStatus(1);
            gdsSearchResponseDTO.setMsg(e.getMessage());
        }
        gdsSearchResponseDTO.setRoutings(routings);
        return gdsSearchResponseDTO;
    }

    public List<Routing> transformSearchDirectRouting(Flight flight) {
        List<Routing> routings = new ArrayList<>();
        flight.getCabin().stream().filter(Objects::nonNull).forEach(e -> {
            Routing routing = new Routing();
//            routing.setProductType();
            routing.setPriceType("PRV");
            routing.setAdultPrice(new BigDecimal(e.getAdultPrice()));
            routing.setAdultTax(new BigDecimal(0));
            routing.setChildPrice(new BigDecimal(e.getChildPrice()));
            routing.setChildTax(new BigDecimal(0));
            List<Segment> segments = new ArrayList<>();
            Segment routingSegment = this.transformSearchDirectSegment(flight, e);
            segments.add(routingSegment);
            routing.setFromSegments(segments);
            routing.setValidatingCarrier(routing.getFromSegments().get(0).getCarrier());
            routings.add(routing);
        });

        return routings;
    }

    public Segment transformSearchDirectSegment(Flight flight, Cabin e) {
        Segment routingSegment = new Segment();
        routingSegment.setCarrier(flight.getAirLine());
        routingSegment.setFlightNumber(flight.getFlightNo().substring(2));
        routingSegment.setDepAirport(flight.getDepPort());
        routingSegment.setArrAirport(flight.getArrPort());
        routingSegment.setDepTime(flight.getDepDate() + flight.getDepTime());
        routingSegment.setArrTime(flight.getArrDate() + flight.getArrTime());
        routingSegment.setCodeShare(Boolean.valueOf(flight.getCodeShare()));
        if (routingSegment.getCodeShare()) {
            routingSegment.setOperatingFlightNo(flight.getCodeShareInfo());
        }
        routingSegment.setDepTerminal(flight.getDepartureTerminal());
        routingSegment.setArrTerminal(flight.getArrivalTerminal());
        routingSegment.setCabin(e.getName());
        routingSegment.setCabinGrade("Y");
        routingSegment.setAircraftCode(flight.getPlane());
        return routingSegment;
    }

    /**
     * 中转的转换
     *
     * @param nhStr
     * @return
     */
    public GDSSearchResponseDTO transformSearchCzaddonResponse(String nhStr) {
        GDSSearchResponseDTO gdsSearchResponseDTO = new GDSSearchResponseDTO();
        gdsSearchResponseDTO.setStatus(0);
        gdsSearchResponseDTO.setMsg("succes");
        gdsSearchResponseDTO.setUid("123456789");
        ArrayList<Routing> routings = new ArrayList<>();
        try {
            NHResponse nhResponse = JSONObject.parseObject(nhStr, NHResponse.class);
            //目前不支持往返

            if (!nhResponse.getSuccess() || CollectionUtils.isEmpty(nhResponse.getData().getSegment().get(0).getDateFlight().getTransitFlight())) {
                gdsSearchResponseDTO.setRoutings(routings);
                return gdsSearchResponseDTO;
            }
            nhResponse.getData().getSegment().get(0).getDateFlight().getTransitFlight()
                    .stream().filter(Objects::nonNull).forEach(e -> {
                List<Routing> routings1 = this.transformSearchCzaddonRouting(e);
                routings.addAll(routings1);
            });

        } catch (Exception e) {
            e.printStackTrace();
            gdsSearchResponseDTO.setStatus(1);
            gdsSearchResponseDTO.setMsg(e.getMessage());
        }
        gdsSearchResponseDTO.setRoutings(routings);
        return gdsSearchResponseDTO;
    }

    public List<Routing> transformSearchCzaddonRouting(TransitFlight transitFlight) {
        List<Routing> routings = new ArrayList<>();

        for (int i = 0; i < transitFlight.getSolutions().size(); i++) {
            BigDecimal adultPrice = new BigDecimal(transitFlight.getSolutions().get(i).getAdultPriceTotal())
                    .subtract(new BigDecimal((StringUtils.isEmpty(transitFlight.getSolutions().get(i).getAdultTaxTotal()) ? "0" :
                            transitFlight.getSolutions().get(i).getAdultTaxTotal())));

            BigDecimal adultTax = new BigDecimal((StringUtils.isEmpty(transitFlight.getSolutions().get(i).getAdultTaxTotal()) ? "0" :
                    transitFlight.getSolutions().get(i).getAdultTaxTotal()));

            BigDecimal childPrice = new BigDecimal(transitFlight.getSolutions().get(i).getChildPriceTotal())
                    .subtract(new BigDecimal((StringUtils.isEmpty(transitFlight.getSolutions().get(i).getChildTaxTotal()) ? "0" :
                            transitFlight.getSolutions().get(i).getChildTaxTotal())));

            BigDecimal childTax = new BigDecimal((StringUtils.isEmpty(transitFlight.getSolutions().get(i).getChildTaxTotal()) ? "0" :
                    transitFlight.getSolutions().get(i).getChildTaxTotal()));

            Routing routing = new Routing();
            routing.setProductType("PRV");
            routing.setAdultPrice(adultPrice);
            routing.setAdultTax(adultTax);
            routing.setChildPrice(childPrice);
            routing.setChildTax(childTax);

            List<Segment> segments = new ArrayList<>();
            for (int j = 0; j < transitFlight.getSegments().size(); j++) {
                Segments s = transitFlight.getSegments().get(j);
                String cabin = transitFlight.getSolutions().get(i).getFares().get(j).getSegments().get(0).getName();
                String seatsNumber = transitFlight.getSolutions().get(i).getFares().get(j).getSegments().get(0).getInfo();
                Segment routingSegment = this.transformSearchCzaddonSegment(s, cabin, seatsNumber);
                segments.add(routingSegment);
            }
            routing.setFromSegments(segments);
            routings.add(routing);
        }
        return routings;
    }


    public Segment transformSearchCzaddonSegment(Segments segments, String cabin, String seatsNumber) {
        Segment routingSegment = new Segment();
        routingSegment.setCarrier(segments.getAirLine());
        routingSegment.setFlightNumber(segments.getFlightNo().substring(2));
        routingSegment.setDepAirport(segments.getDepPort());
        routingSegment.setArrAirport(segments.getArrPort());
        routingSegment.setDepTime(segments.getDepDate() + segments.getDepTime());
        routingSegment.setArrTime(segments.getArrDate() + segments.getArrTime());
        routingSegment.setCodeShare(Boolean.valueOf(segments.getCodeShare()));
        if (routingSegment.getCodeShare()) {
            routingSegment.setOperatingFlightNo(segments.getCodeShareInfo());
        }
        routingSegment.setDepTerminal(segments.getDepartureTerminal());
        routingSegment.setArrTerminal(segments.getArrivalTerminal());
        routingSegment.setCabin(cabin);
        routingSegment.setCabinGrade("Y");
        routingSegment.setBookingClassAvail(StringUtils.isEmpty(seatsNumber) ? "9" :
                seatsNumber.contains("gt") ? "9" : seatsNumber);
        routingSegment.setAircraftCode(segments.getPlane());
        return routingSegment;
    }


    public void transformFlightNumber( GDSSearchResponseDTO gdsSearchResponseDTO){
        gdsSearchResponseDTO.getRoutings().stream().filter(Objects::nonNull).forEach(e ->{
            int num = 1;
            if(!CollectionUtils.isEmpty(e.getFromSegments())){
                for(int i = 0;i<e.getFromSegments().size();i++){
                    e.getFromSegments().get(i).setFlightIndicator("1");
                    e.getFromSegments().get(i).setItemNumber(num);
                    num++;
                }
            }

            if(!CollectionUtils.isEmpty(e.getRetSegments())){
                for(int i = 0;i<e.getRetSegments().size();i++){
                    e.getRetSegments().get(i).setFlightIndicator("2");
                    e.getRetSegments().get(i).setItemNumber(num);
                    num++;
                }
            }
        });
    }

}
