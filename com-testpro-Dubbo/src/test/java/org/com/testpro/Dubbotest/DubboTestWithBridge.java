package org.com.testpro.Dubbotest;

import com.gsafety.bigdata.lifeline.pojo.BridgeParam;
import com.gsafety.bigdata.lifeline.service.BridgeQueryService;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.com.testpro.Base.SpringTestngBase;
import org.com.testpro.DataMapper.DubboTestDataMap;
import org.com.testpro.DataresultBean.BridgeDataResult;
import org.com.testpro.DataresultBean.DynamicResult;
import org.com.testpro.DataresultBean.NewBridgeParam;
import org.com.testpro.StringUtil.Datautil;
import org.com.testpro.StringUtil.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
public class DubboTestWithBridge extends SpringTestngBase {


    @Autowired
    BridgeQueryService BridgeQueryService;


    /**
     * 从指定的类中加载测试数据
     * 该类需满足2个条件 1、注解 2、方法是static
     *
     * @param BridgeParam
     */
    @Test(testName = "测试Dubbo数据查询接口", dataProvider = "BridgeQueryServiceinterface", dataProviderClass = DubboTestDataMap.class)
    @Description("测试Dubbo数据查询接口")
    @Step("terninal: +{BridgeParam.terninal}+ sensor: +{BridgeParam.sensor}+ starTime:+{BridgeParam.starTime} +endTime:+{BridgeParam.endTime}")
    public void testBridgeQueryServiceinterface(NewBridgeParam BridgeParam) throws IOException {
        log.info("BridgeQueryService接口参数" + BridgeParam.toString());
        String queryBridgeresult = null;
        try {
            queryBridgeresult = BridgeQueryService.queryBridge(BridgeParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(queryBridgeresult, "绝壁不能为空");
        List <BridgeDataResult> list = JsonUtil.FastJsonStringToBeanList(queryBridgeresult, BridgeDataResult.class);
        int size = list.size();
        log.info(BridgeParam.getEquipmentname() + "传感器查询数据量" + String.valueOf(size));
        Assert.assertNotNull(list, "List 不可以为空");
        Assert.assertEquals(true, size > 1);
    }


    @Test(priority = 1, testName = "测试Dubbo数据查询半年以上接口", dataProvider = "BridgeQueryServiceinterface", dataProviderClass = DubboTestDataMap.class)
    @Description("测试Dubbo数据查询接口")
    @Step("terninal: +{BridgeParam.terninal}+ sensor: +{BridgeParam.sensor}+ starTime:+{BridgeParam.starTime} +endTime:+{BridgeParam.endTime}")
    public void testBridgeQueryServiceinterfacesixMonth(NewBridgeParam BridgeParam) throws IOException {
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.MONTH, -12);
        Date time = instance.getTime();
        long time1 = time.getTime();
        String start = Datautil.LongTimeStampTODate(time1, "yyyy-MM-dd HH:mm:ss");
        BridgeParam.setDataType("");
        BridgeParam.setStarTime(start);
        log.info("BridgeQueryService接口参数" + BridgeParam.toString());
        String queryBridgeresult = null;
        try {
            queryBridgeresult = BridgeQueryService.queryBridge(BridgeParam);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Assert.assertNotNull(queryBridgeresult, "绝壁不能为空");
        List <BridgeDataResult> list = JsonUtil.FastJsonStringToBeanList(queryBridgeresult, BridgeDataResult.class);
        String s = JsonUtil.FastObjectToJsonString(list);
        List <BridgeDataResult> list1 = JsonUtil.FastJsonStringToBeanList(s, BridgeDataResult.class);
        int size = list.size();
        log.info(BridgeParam.getEquipmentname() + "传感器查询数据量" + String.valueOf(size));
        Assert.assertNotNull(list, "List 不可以为空");
        Assert.assertEquals(true, size > 1);
    }

    @Test(priority = 2, testName = "批量测试dubbo服务查询动态阈值接口", dataProvider = "CreateDataqueryDynamic", dataProviderClass = DubboTestDataMap.class)
    @Description("批量测试dubbo服务查询动态阈值接口")
    @Step("terninal: +{BridgeParam.terninal}+ sensor: +{BridgeParam.sensor}+ starTime:+{BridgeParam.starTime} +endTime:+{BridgeParam.endTime}")
    public void testBridgeQueryServiceinterfacequeryDynamic(BridgeParam BridgeParam) throws IOException {
        log.info("testBridgeQueryServiceinterfacequeryDynamic接口参数" + BridgeParam.toString());
        String queryBridgeresult = null;
        try {
            queryBridgeresult = BridgeQueryService.queryDynamic(BridgeParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(queryBridgeresult, "绝壁不能为空");
        List <DynamicResult> list = JsonUtil.FastJsonStringToBeanList(queryBridgeresult, DynamicResult.class);
        int size = list.size();
        System.out.println(list.toString());
        Assert.assertEquals(true, size > 10);

    }


}
