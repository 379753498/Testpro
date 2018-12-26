package org.com.testpro.Bridge;

import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.com.testpro.Base.SpringJunitBase;
import org.com.testpro.restassured.restAssuredUtil;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.HashMap;

@Slf4j
public class BridgeQualitycontiperf extends SpringJunitBase {
    private static restAssuredUtil restAssuredUtil = null;
    private static String bridge_detailid = null;
    @Rule
    public ContiPerfRule i = new ContiPerfRule();
    @Autowired
    Environment Environment;

    @Test
    @PerfTest(invocations = 100, threads = 20)
    @Required(max = 8000, average = 4000, totalTime = 4000000)
    public void getBridgeQualityCount1() {
        restAssuredUtil = new restAssuredUtil(Environment.getProperty("baseURI"));
        HashMap <String, String> parameters = new HashMap <String, String>();
        String str = "[{\"bridgeids\":[\"4028844050a214390150a216354d0003\",\"4028844050a214390150a216354d0002\",\"4028844050a214390150a216354d0001\",\"4028844050a214390150a216354d0005\",\"4028844050a214390150a216354d0004\"],\"stime\":\"2018-10-11 16:12:27\",\"etime\":\"2018-10-12 16:12:27\",\"username\":\"dev\",\"syscode\":\"bridge\",\"pageSize\":\"10\",\"pageNo\":\"1\"}]";
        parameters.put("message", str);
        String url = "/getBridgeQualityCount.mvc";
        long before = System.currentTimeMillis();
        Response response = restAssuredUtil.PostResponse(url, parameters);
        long after = System.currentTimeMillis();
        log.info("接口请求返回总花费时间{}s", (float) (after - before) / 1000);

    }


}
