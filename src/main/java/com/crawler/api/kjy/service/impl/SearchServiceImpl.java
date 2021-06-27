package com.crawler.api.kjy.service.impl;


import com.alibaba.fastjson.JSON;
import com.crawler.api.kjy.service.SearchService;
import com.crawler.api.kjy.vo.search.SearchRequest;
import com.crawler.api.kjy.vo.search.SearchResponse;
import com.crawler.api.request.GDSSearchRequestDTO;
import com.crawler.api.response.GDSSearchResponseDTO;
import com.crawler.api.response.Routing;
import com.crawler.api.response.Segment;
import com.crawler.api.utils.Constant;
import com.crawler.api.utils.HttpRequestUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SearchServiceImpl implements SearchService {

    @Override
    public GDSSearchResponseDTO search(SearchRequest searchRequest,GDSSearchRequestDTO gdsSearchRequestDTO){
        String searchRequestStr= JSON.toJSONString(searchRequest);
        String data = HttpRequestUtil.post(Constant.SEARCH_URL,searchRequestStr);
        try{
            GDSSearchResponseDTO gdsSearchResponseDTO= this.transformSearchResponse(data,gdsSearchRequestDTO);
            return gdsSearchResponseDTO;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 处理请求后返回报文(单程，且只能返回一个航段)
     * @param data
     * @return
     */
    public GDSSearchResponseDTO transformSearchResponse(String data,GDSSearchRequestDTO gdsSearchRequestDTO){
        if(StringUtils.isEmpty(data)){
            return null;
        }
        List<SearchResponse> searchResponses =JSON.parseArray(data,SearchResponse.class);
        List<Routing> routings =new ArrayList<>();
        String gdsType= gdsSearchRequestDTO.getReservationType();
        String officeId = gdsSearchRequestDTO.getOfficeId();
        String uid = gdsSearchRequestDTO.getUid();

        searchResponses.parallelStream().filter(Objects::nonNull).forEach(m ->{
            m.getClassAry().stream().forEach(c -> {

                Routing routing = new Routing();
                routing.setValidatingCarrier(m.getCarrier());
                routing.setReservationType(gdsType);
                routing.setOfficeId(officeId);
                routing.setAdultPrice(new BigDecimal(c.getPrice()));
                routing.setAdultTax(new BigDecimal(0));
                routing.setChildPrice(new BigDecimal(c.getPrice()));
                routing.setChildTax(new BigDecimal(0));
                routing.setPriceType("PUB");
                routing.setMeal(m.getMeal());
                routing.setAdultAirportTax(m.getAirportTax());
                routing.setEligibility(m.getETicket());
                routing.setAdultFuelCosts(m.getFuelSurTax());
                routing.setChildFuelCosts(m.getFuelSurTaxChd());
                routing.setKilometers(m.getTpm());//公里数
                routing.setEconomyStandardPrice(m.getYClassPrice());//经济舱标准价

                Segment segment=new Segment();
                segment.setCarrier(m.getCarrier());
                segment.setFlightNumber(m.getFlightNo());
                segment.setDepAirport(m.getBoardPoint());
                segment.setArrAirport(m.getOffPoint());
                segment.setDepTime((m.getDepartureDate()+m.getDepartureTime())
                        .replaceAll("-","").replaceAll(":",""));

                segment.setArrTime((m.getArrivalDate()+m.getArrivalTime())
                        .replaceAll("-","").replaceAll(":",""));

                segment.setCabinGrade(this.transformCabinGrade(c.getCabinClass()));
                segment.setCabin(c.getCode());
                segment.setAircraftCode(c.getAircraft());
                segment.setDepTerminal(c.getBoardPointAT());
                segment.setArrTerminal(c.getOffPointAT());
                if(StringUtils.isEmpty(m.getCodeshareAirline()) && StringUtils.isEmpty(m.getShareaFltno())){
                    segment.setCodeShare(false);
                }else {
                    segment.setCodeShare(true);
                    segment.setOperatingCarrier(m.getCodeshareAirline());
                    segment.setOperatingFlightNo(m.getShareaFltno());
                }
                segment.setFareBasis(c.getFareBasis());
                List<Segment> fromSegments=new ArrayList<>();
                routing.setFromSegments(fromSegments);
                routings.add(routing);
            });
        });

        GDSSearchResponseDTO gdsSearchResponseDTO=new GDSSearchResponseDTO();
        gdsSearchResponseDTO.setStatus(0);
        gdsSearchResponseDTO.setMsg("成功");
        gdsSearchResponseDTO.setUid(uid);
        gdsSearchResponseDTO.setRoutings(routings);
        return gdsSearchResponseDTO;
    }


    /**
     * 1.经济舱，2.头等舱，3.公务舱
     * @param cabinClass
     * @return
     */
    public String transformCabinGrade(String cabinClass){
        if("1".equals(cabinClass)){
            return "Y";
        }else if("2".equals(cabinClass)){
            return "F";
        }else if("3".equals(cabinClass)){
            return "C";
        }else {
            return cabinClass;
        }
    }

}
