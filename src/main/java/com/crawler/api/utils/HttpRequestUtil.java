package com.crawler.api.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crawler.api.aq.vo.AQRequest;
import com.crawler.api.aq.vo.AQResponse;
import com.crawler.api.cq.vo.CQRequest;
import com.crawler.api.cq.vo.CQResponse;
import com.crawler.api.nh.vo.NHRequest;
import com.crawler.api.nh.vo.NHResponse;
import com.crawler.api.request.GDSSearchRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by guoyanan on 2018/8/7 0007.
 * 接口调用工具类
 */
public class HttpRequestUtil {
    private static final String TRIP_TYPE_OW = "1";
    private static CloseableHttpClient httpClient;
    private static Logger logger = LoggerFactory.getLogger(HttpRequestUtil.class);

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(1000);
        cm.setDefaultMaxPerRoute(200);
        cm.setDefaultMaxPerRoute(500);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    public static String get(String url) {
        CloseableHttpResponse response = null;
        BufferedReader in = null;
        String result = "";
        try {
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(20000).setSocketTimeout(20000).build();
            httpGet.setConfig(requestConfig);
            httpGet.setConfig(requestConfig);
            httpGet.addHeader("Content-type", "application/json; charset=utf-8");
            httpGet.setHeader("Accept", "application/json");
            response = httpClient.execute(httpGet);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String post(String url, String jsonString) {
        CloseableHttpResponse response = null;
        BufferedReader in = null;
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(200000).setSocketTimeout(200000).
                    setRedirectsEnabled(false).build();
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:5.0) Gecko/20100101 Firefox/5.0)");

            httpPost.addHeader("Cookie", "_os=ff08ef99dd88107fb19f42ac0fe919bd; PcPopAd_VisitWebSite=2020-09-21 17:54:28; c3=www.baidu.com; c4=; s7=https://www.baidu.com/link?url=KjGEgYUkaoqgNXdoyKL2ceXwLPsQpyJDKOQ54nf0wn3&wd=&eqid=a2a04c290002d758000000065f687853; RiskifiedToken=16997fc9859f4bd5a137e092c6b6d035; g_refresh=0; cookie_policy=1; smidV2=20200921175429c4f8ac93dec16990ceaa3e0df94e8e3b001eb6e65dbba4c20; PcPopAd_GQF1599448430705=; rskxRunCookie=0; rCookie=5kjkk1fzrvypeg80be9fy9kfccryrb; Qs_lvt_100645=1600682071; s1=Win7; s2=WEB; s3=zh-cn; s4=ab4eb62abe6b4b72b4deb2cbde932dc0; s6=6fcede43fc034a1e9eba4fded52c608b; preloadJs=.js%3Fvs%3Dv2020091601; hasProcessIP=1; Hm_lvt_f6066c7da67b0ae5719a9fa8c6e004a8=1600682073; gr_user_id=c200c7cc-c2ce-49e4-bd5b-9e5f95380764; 9683d26dac59f3e8_gr_session_id=e4376a58-35f7-4368-b1d0-88210b7ff856; 9683d26dac59f3e8_gr_session_id_e4376a58-35f7-4368-b1d0-88210b7ff856=true; grwng_uid=1a029e90-48ef-4bad-bc23-f849082e083f; acw_tc=2f624a2a16006820892294088e3c5b3ef96cfbdaa25112bae0d8f8a9ca5268; IsShowTaxprice=false; mediav=%7B%22eid%22%3A%22194066%22%2C%22ep%22%3A%22%22%2C%22vid%22%3A%22%22%2C%22ctn%22%3A%22%22%2C%22vvid%22%3A%22%22%2C%22_mvnf%22%3A1%2C%22_mvctn%22%3A0%2C%22_mvck%22%3A1%2C%22_refnf%22%3A0%7D; SearchHis=zh-cn%26SZX%26%u6DF1%u5733%26SIA%26%u897F%u5B89%262020-10-21%26%26SZX%26SIA%26false%26false; Hm_lpvt_f6066c7da67b0ae5719a9fa8c6e004a8=1600682182; Qs_pv_100645=3452390885972331000%2C4002146624442403300%2C2279809347355212500%2C1777163299099279000%2C3899374334191683600; shumei_device_id=WHJMrwNw1k%2FEx1XMOL3qsQDgtn5tgkWbTjrmEJh61fb%2BxPVKP%2FTCSVs5GIv2yvpotMUVD7t03AothXAhXXhoNKFfJVsuKWKogvZwXnA6wGGcTv06okQ1koYYHqxuZPWulBJ7HANruhDjgssVEtOEyiJGebD4P9188MdzVDzvFXj%2FC2CT6C1Ms%2BDlzR%2BLE4%2FRNKei9dcuNh8gYjh0R4kbUwMc116kP%2BBdVLmmGufafk2I2kNF0emrW7G7v4vlHZoQZ1487582755342; s5=5; lastRskxRun=1600682208535");

            httpPost.setEntity(new StringEntity(jsonString, Charset.forName("UTF-8")));

            response = httpClient.execute(httpPost);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 南航(直飞)
     *
     * @param nhRequest
     * @return
     * @throws JsonProcessingException
     */
    public static String searchNHContent(NHRequest nhRequest, String url) {
        CloseableHttpResponse response = null;
        BufferedReader in = null;
        String result = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(nhRequest);
            //该接口是直飞接口  "https://b2c.csair.com/portal/flight/direct/query"
            //中转接口 "https://b2c.csair.com/portal/flight/czaddon/query"
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            String cookie = "JSESSIONID=470F1A18642190550BD713DE0D39A511; sid=6d1ef81ae4c643b1a56e15668b11cff6; jumpPage=https%3A%2F%2Fb2c.csair.com%2Fportal%2Fflight%2F%3F_%3D1623489514595; _gscu_422057653=23489514ny7c6442; _gscbrs_422057653=1; captchaKey=1623489513888-120.229.4.58; isXionganFlag=false; language=zh_CN; likev_user_id=f0a47d0c-bca6-4eea-fea4-1e391897bf63; _gcl_au=1.1.1904406545.1623489608; last_session_stm_8mrmut7r76ntg21b=1623581003368; likev_session_id_8mrmut7r76ntg21b=49706ab5-bfa9-4f83-f422-f1e7cabf69de; last_session_id_8mrmut7r76ntg21b=49706ab5-bfa9-4f83-f422-f1e7cabf69de; ticketBoolingSearch=%7B%22segType%22%3A%22S%22%2C%22adultNum%22%3A%221%22%2C%22childNum%22%3A%220%22%2C%22infantNum%22%3A%220%22%2C%22citys%22%3A%5B%7B%22id%22%3A%22single-formCity%22%2C%22value%22%3A%22%E6%B7%B1%E5%9C%B3%22%7D%2C%7B%22id%22%3A%22single-formCityCode%22%2C%22value%22%3A%22SZX%22%7D%2C%7B%22id%22%3A%22isdepair%22%2C%22value%22%3Afalse%7D%2C%7B%22id%22%3A%22single-toCity%22%2C%22value%22%3A%22%E5%B9%BF%E5%B7%9E%22%7D%2C%7B%22id%22%3A%22single-toCityCode%22%2C%22value%22%3A%22CAN%22%7D%2C%7B%22id%22%3A%22isarrair%22%2C%22value%22%3Afalse%7D%5D%2C%22dates%22%3A%5B%7B%22id%22%3A%22single-formCalender%22%2C%22value%22%3A%222021-09-28%22%2C%22minDate%22%3A%22%2B0d%22%7D%5D%7D; WT.al_flight=WT.al_hctype(S)%3AWT.al_adultnum(1)%3AWT.al_childnum(0)%3AWT.al_infantnum(0)%3AWT.al_orgcity1(SZX)%3AWT.al_dstcity1(CAN)%3AWT.al_orgdate1(2021-09-28); acw_tc=2f6a1f9c16235829113707393e5de2c48a3ab17563b888140f59ff3b5c31a0; temp_zh=cou%3D0%3Bsegt%3D%E5%8D%95%E7%A8%8B%3Btime%3D2021-09-22%3B%E6%B7%B1%E5%9C%B3-%E5%B9%BF%E5%B7%9E%3B1%2C0%2C0%3B00%3B%26cou%3D1%3Bsegt%3D%E5%8D%95%E7%A8%8B%3Btime%3D2021-09-24%3B%E6%B7%B1%E5%9C%B3-%E5%B9%BF%E5%B7%9E%3B1%2C0%2C0%3B00%3B%26cou%3D2%3Bsegt%3D%E5%8D%95%E7%A8%8B%3Btime%3D2021-09-28%3B%E6%B7%B1%E5%9C%B3-%E5%B9%BF%E5%B7%9E%3B1%2C0%2C0%3B00%3B%26; _gscs_422057653=t23581003nmh8c836|pv:9; WT-FPC=id=223.74.102.45-3937471632.30891883:lv=1623582928500:ss=1623581002793:fs=1623489514293:pn=9:vn=5; acw_sc__v3=60c5e8d279d6986f4ff5a51ed32cbc6fa86ec819; acw_sc__v2=60c5e8d3dae451571d1d26e7b9c90159b0a7c008; likev_session_etm_8mrmut7r76ntg21b=1623582940972; ssxmod_itna=eqfxyDRDBiY7q+Dl4iuitGGOY3eY543vhNE5QD/3mDnqD=GFDK40oE73PD7tyw9E0i34MnGhGK5EWBG4T3NTGYcUfvmDB3DEx0==Y0eKiicDCeDIDWeDiDG4GmS4GtDpxG=Dj0FUZMUxi3Dbh=Df4DmDGY9QqDgDYQDGMp6D7QDIw6eyf2SfCt=bCGx7qDMUeGXYDc17dRWH1vZbQnrwxxqD0Pe9oyx9EHPA0D5eQPuDB=mxBjMfwXUfQyySqNxNgoeAi+zFi4=bxhxQBvNWG5PmYhr3fGU=bkNWRxQBU/xDDfYjsDxD; ssxmod_itna2=eqfxyDRDBiY7q+Dl4iuitGGOY3eY543vhNE5G9irBDBqhRD7pxOF+ySxD7mc85GiKxsXCYMTr34uYu0dh33+Y8r=Zb/qK6zWzrxgWElIRjLr84ZR3j98bZtDljsmxXUvW56tfrIH+eSvwWqYOSrd8IS0V8xGFlLdWD7j18xGcDij1eD=";
            httpPost.setHeader("Cookie", cookie);
            StringEntity stringEntity = new StringEntity(jsonStr, "utf-8");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(200000).setSocketTimeout(200000).
                    setRedirectsEnabled(false).build();
            httpPost.setConfig(requestConfig);
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        NHResponse nhResponse = JSONObject.parseObject(result, NHResponse.class);
        System.out.println("----->" + result);
        return result;

    }



    /**
     * 春秋航空
     *
     * @param cqRequest
     * @return
     * @throws JsonProcessingException
     */
    public static CQResponse searchCQContent(CQRequest cqRequest) throws JsonProcessingException {
        CloseableHttpResponse response = null;
        BufferedReader in = null;
        String result = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(cqRequest);
            HttpPost httpPost = new HttpPost("https://flights.ch.com/Flights/SearchByTime");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(200000).setSocketTimeout(200000).
                    setRedirectsEnabled(false).build();
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(jsonStr, "utf-8");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        CQResponse cqResponse = JSONObject.parseObject(result, CQResponse.class);
        System.out.println("----->" + objectMapper.writeValueAsString(cqResponse));
        return cqResponse;

    }


    /**
     * 九元航空
     *
     * @param gdsSearchRequestDTO
     * @return
     * @throws JsonProcessingException
     */
    public static AQResponse searchAQContent(GDSSearchRequestDTO gdsSearchRequestDTO) {
        CloseableHttpResponse response = null;
        String result = "";
        //可以测通
        String urlTest = "http://www.9air.com/shop/api/shopping/b2c/searchflight?language=zh_CN&currency=CNY&" +
                "flightCondition=index:0;depCode:CAN;arrCode:KWE;depDate:2021-02-16;depCodeType:CITY;arrCodeType:CITY;" +
                "&channelNo=B2C&tripType=OW&groupIndicator=I&adultCount=1&childCount=0&infantCount=0&airlineCode=&directType=&cabinClass=&taxFee=&taxCurrency=&promotionCode=";

        String url ="";
        if(TRIP_TYPE_OW.equals(gdsSearchRequestDTO.getTripType())){
            url = "http://www.9air.com/shop/api/shopping/b2c/searchflight?language=zh_CN&currency=CNY&" +
                    "flightCondition=index:0;depCode:" + gdsSearchRequestDTO.getFromCity() + ";arrCode:" + gdsSearchRequestDTO.getToCity() + ";depDate:" + gdsSearchRequestDTO.getFromDate() + ";depCodeType:CITY;arrCodeType:CITY;" +
                    "&channelNo=B2C&tripType=OW&groupIndicator=I&adultCount=" + gdsSearchRequestDTO.getAdultNumber() + "&childCount=" + gdsSearchRequestDTO.getChildNumber() + "&infantCount=" + gdsSearchRequestDTO.getInfantNumber() + "&airlineCode=&directType=&cabinClass=&taxFee=&taxCurrency=&promotionCode=";
        }else {
            //返往需要另做处理
            url = "http://www.9air.com/shop/api/shopping/b2c/searchflight?" +
                    "language=zh_CN&currency=CNY&" +
                    "flightCondition=index:1;depCode:"+gdsSearchRequestDTO.getToCity()+";arrCode:"+gdsSearchRequestDTO.getFromCity()+";depDate:"+gdsSearchRequestDTO.getRetDate()+";depCodeType:CITY;arrCodeType:CITY;&" +
                    "flightCondition=index:0;depCode:"+gdsSearchRequestDTO.getFromCity()+";arrCode:"+gdsSearchRequestDTO.getToCity()+";depDate:"+gdsSearchRequestDTO.getFromDate()+";depCodeType:CITY;arrCodeType:CITY;" +
                    "&channelNo=B2C&tripType=RT&groupIndicator=I&adultCount=1&childCount=0&infantCount=0&airlineCode=&directType=&cabinClass=&taxFee=&taxCurrency=&promotionCode=";
        }

        AQResponse aqResponse = null;
        try {
            logger.info("---->{}",url);
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(20000).setSocketTimeout(20000).build();
            httpGet.setConfig(requestConfig);
            httpGet.addHeader("Content-type", "application/json;charset=UTF-8");
            httpGet.setHeader("Accept", "application/json, text/plain, */*");
            httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity);
                logger.info("--->{}",result);
                aqResponse = JSON.parseObject(result,AQResponse.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return aqResponse;
    }


    /**
     * 九元航空
     *
     * @param aqRequest
     * @return
     * @throws JsonProcessingException
     */
    public static String searchAQContentTest(AQRequest aqRequest) throws JsonProcessingException {
        CloseableHttpResponse response = null;
        BufferedReader in = null;
        String result = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonStr = objectMapper.writeValueAsString(aqRequest);
            HttpPost httpPost = new HttpPost("http://www.9air.com/shop/api/shopping/b2c/searchflight");
            httpPost.setHeader("Accept", "application/json");
//            httpPost.addHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(20000).setConnectionRequestTimeout(200000).setSocketTimeout(200000).
                    setRedirectsEnabled(false).build();
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(jsonStr, "utf-8");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        CQResponse cqResponse = JSONObject.parseObject(result, CQResponse.class);
        System.out.println("----->" + objectMapper.writeValueAsString(result));
        return result;

    }


}
