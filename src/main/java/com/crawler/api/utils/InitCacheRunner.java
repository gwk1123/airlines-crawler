package com.crawler.api.utils;

import com.crawler.api.cq.bean.CQMap;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Order(value = 1)
@Component
public class InitCacheRunner implements CommandLineRunner {
    @Autowired
    private CQMap cqMap;

    private static Logger logger = LoggerFactory.getLogger(InitCacheRunner.class);

    @Override
    public void run(String... args) throws Exception {
        this.initCityName();
        logger.info("map:{}",cqMap.getCqMap());
    }


    /**
     * 初始化春秋航空城市名称
     * @throws IOException
     * @throws InterruptedException
     */
    public void initCityName() throws IOException, InterruptedException {
        Document document= Jsoup.parse(Constant.initCQCityNameHtml);
        Elements links = document.select("a[href]");
        for (Element link : links) {
            String citycode = link.attr("data-citycode");
            String linkText = link.text();
//            logger.info("linkText:{},data-citycode:{}",linkText,citycode);
            if(StringUtils.isNotBlank(citycode)){
                cqMap.put(citycode,linkText);
            }
        }
    }
}
