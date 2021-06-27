package com.crawler.api.kjy.controller;


import com.alibaba.fastjson.JSON;
import com.crawler.api.kjy.service.SearchService;
import com.crawler.api.kjy.vo.search.SearchRequest;
import com.crawler.api.request.GDSSearchRequestDTO;
import com.crawler.api.response.GDSSearchResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 查询
 */
@RestController(value = "/kjy")
public class SearchController {

    @Autowired
    private SearchService searchService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMDD");
    DateTimeFormatter underscore = DateTimeFormatter.ofPattern("yyyy-MM-DD");

    private Logger logger = LoggerFactory.getLogger(SearchController.class);

    @RequestMapping(value = "/search")
    public GDSSearchResponseDTO search(GDSSearchRequestDTO gdsSearchRequestDTO){
        logger.info("search请求参数:{}", JSON.toJSONString(gdsSearchRequestDTO));
        SearchRequest searchRequest= transformSearchRequest( gdsSearchRequestDTO);
        GDSSearchResponseDTO gdsSearchResponseDTO=searchService.search(searchRequest,gdsSearchRequestDTO);
        return gdsSearchResponseDTO;
    }


    /**
     * 处理请求前参数
     * @param gdsSearchRequestDTO
     * @return
     */
    public SearchRequest transformSearchRequest(GDSSearchRequestDTO gdsSearchRequestDTO){
        SearchRequest searchRequest=new SearchRequest();
        searchRequest.setFromcity(gdsSearchRequestDTO.getFromCity());
        searchRequest.setTocity(gdsSearchRequestDTO.getToCity());
        LocalDate localDate = LocalDate.parse(gdsSearchRequestDTO.getFromDate(),formatter);
        String fromDateStr = localDate.format(underscore);
        searchRequest.setFromdate(fromDateStr);
        searchRequest.setIsPriceAll("1");
        searchRequest.setSearchType(gdsSearchRequestDTO.getTripType());
        if(StringUtils.isEmpty(gdsSearchRequestDTO.getAirline())){
            searchRequest.setCarrierCode(gdsSearchRequestDTO.getAirline());
        }
        searchRequest.setShareaFlights("1");
        return searchRequest;
    }

}
