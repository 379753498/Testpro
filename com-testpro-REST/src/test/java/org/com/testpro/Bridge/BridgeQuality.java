package org.com.testpro.Bridge;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.com.testpro.Base.SpringTestngBase;
import org.com.testpro.Bean.BridgeQualityCJbean;
import org.com.testpro.DataMapper.BridgeQualityMapper;
import org.com.testpro.StringUtil.JsonUtil;
import org.com.testpro.TestngUtil.TestNgUtil;
import org.com.testpro.restassured.restAssuredUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class BridgeQuality extends SpringTestngBase {
    private static restAssuredUtil restAssuredUtil = null;
    private static String bridge_detailid = null;

    @Autowired
    Environment Environment;

    @Test(testName = "getBridgeQualityCount", dataProvider = "getBridgeQualityMapper", dataProviderClass = BridgeQualityMapper.class)
    public void getBridgeQualityCount(HashMap <String, String> parameters) {
        //        准备参数
        String gsonBeanToString = JsonUtil.GsonBeanToString(parameters);
        log.info("BridgeQualityCount请求参数" + gsonBeanToString);
        String url = "/getBridgeQualityCount.mvc";
        restAssuredUtil = new restAssuredUtil(Environment.getProperty("baseURI"));
        long before = System.currentTimeMillis();
        //       执行并获取返回值
        Response response = restAssuredUtil.PostResponse(url, parameters);
        long after = System.currentTimeMillis();
        log.info("接口请求返回总花费时间{}s", (float) (after - before) / 1000);
        log.info("BridgeQualityCount请求返回值" + response.asString());

        //      执行断言
        Assert.assertNotNull(response.jsonPath().getString("getBridgeQualityCount[0].bridge_detailid[0]"), "绝壁不能为空否则接口异常bridge_detailid未正常获取");
        // 后续操作
        bridge_detailid = response.jsonPath().getString("getBridgeQualityCount[0].bridge_detailid[0]");

    }

    @Test(priority = 1, testName = "getBridgeQualityCJMapper", dataProvider = "getBridgeQualityCJMapper", dataProviderClass = BridgeQualityMapper.class, dependsOnMethods = "getBridgeQualityCount")
    public void getBridgeQualityCJ(BridgeQualityCJbean bridgeQualityCJbean) throws JsonProcessingException {
        //        准备参数
        bridgeQualityCJbean.setBridge_detailid(bridge_detailid);
        List <BridgeQualityCJbean> list = new ArrayList <>();
        list.add(bridgeQualityCJbean);
        String toJSonString = JsonUtil.GsonBeanToString(list);
        log.info("BridgeQualityCJ请求参数" + toJSonString);
        HashMap <String, String> parameters = TestNgUtil.createParameters("message", toJSonString);
        long before = System.currentTimeMillis();
        //        执行请求
        String url = "/getBridgeQualityCJ.mvc";
        restAssuredUtil = new restAssuredUtil(Environment.getProperty("baseURI"));
        Response response = restAssuredUtil.PostResponse(url, parameters);
        long after = System.currentTimeMillis();
        //       执行获取response对象

        log.info("接口请求返回总花费时间{}s", (float) (after - before) / 1000);
        log.info("BridgeQualityCJ请求返回值" + response.asString());
        //      执行断言
        Assert.assertNotNull(response, "getBridgeQualityCJ返回结果不可为空");

    }
}
