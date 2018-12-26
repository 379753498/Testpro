package org.com.testpro.DataMapper;

import com.gsafety.bigdata.lifeline.pojo.BridgeParam;
import lombok.Data;
import org.com.testpro.Bean.SensorData;
import org.com.testpro.Dao.SensorService;
import org.com.testpro.DataresultBean.NewBridgeParam;
import org.com.testpro.DataresultBean.NewSensorData;
import org.com.testpro.StringUtil.Datautil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.testng.annotations.DataProvider;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Data
public class DubboTestDataMap implements InitializingBean {


    private static List <Object> BridgeQueryServiceinterface = new ArrayList <Object>();
    private static List <Object> BridgeQueryServicequeryDynamic = new ArrayList <Object>();

    @Autowired
    OracleSelectData OracleSelectData;
    @Autowired
    Environment environment;
    private String start;
    private String end;

    @DataProvider(name = "BridgeQueryServiceinterface")
    public static Iterator <Object[]> CreateData() {
        List <Object[]> personlistobj = new ArrayList <Object[]>();
        for (Object object : BridgeQueryServiceinterface) {
            personlistobj.add(new Object[]{object});
        }
        return personlistobj.iterator();

    }


    @DataProvider(name = "CreateDataqueryDynamic")
    public static Iterator <Object[]> CreateDataqueryDynamic() {
        List <Object[]> personlistobj = new ArrayList <Object[]>();
        for (Object object : BridgeQueryServicequeryDynamic) {
            personlistobj.add(new Object[]{object});
        }
        return personlistobj.iterator();

    }

    public BridgeParam CreateBridgeParam() {
        start = Datautil.LongTimeStampTODate(System.currentTimeMillis() - 1000 * 60 * 60 * 24, "yyyy-MM-dd HH:mm:ss");
        end = Datautil.LongTimeStampTODate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
//        List <SensorData> allSensordata15 = sensorService.getAllSensordata15();
        List <NewSensorData> allSensordata15 = OracleSelectData.selectNewSensorData();
        int offset = ((int) (Math.random() * allSensordata15.size()));
        SensorData sensorData = allSensordata15.get(offset);
        NewBridgeParam bridgeParam = new NewBridgeParam();
        bridgeParam.setDataType(environment.getProperty("queryservice.time.datatype"));
        bridgeParam.setTerninal(sensorData.getGatewaynum());
        bridgeParam.setSensor(sensorData.getModularnum() + "_" + sensorData.getPathnum());
        bridgeParam.setStarTime(start);
        bridgeParam.setEndTime(end);
        bridgeParam.setEquipmentname(sensorData.getEquipmentname());
        return bridgeParam;
    }


    public BridgeParam CreateBridgeParamqueryDynamic() {
        start = Datautil.LongTimeStampTODate(System.currentTimeMillis() - 1000 * 60 * 60 * 24, "yyyy-MM-dd HH:mm:ss");
        end = Datautil.LongTimeStampTODate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
        List <NewSensorData> newSensorData = OracleSelectData.selectNewSensorData();
        String s = environment.getProperty("queryservice.time.sensor");
        NewBridgeParam bridgeParam = new NewBridgeParam();
        String[] s1 = s.split("_");
        String Modularnum = s1[0];
        String Pathnum = s1[1];
        for (NewSensorData SensorData : newSensorData) {
            if (SensorData.getGatewaynum().equals(environment.getProperty("queryservice.time.terninal")) && SensorData.getModularnum().equals(Modularnum) && SensorData.getPathnum().equals(Pathnum)) {
                bridgeParam.setLocations(SensorData.getPoint_flag());
                break;
            }
        }
        bridgeParam.setTerninal(environment.getProperty("queryservice.time.terninal"));
        bridgeParam.setSensor(environment.getProperty("queryservice.time.sensor"));
        bridgeParam.setStarTime(start);
        bridgeParam.setEndTime(end);
        bridgeParam.setDataType("");
        return bridgeParam;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BridgeQueryServiceinterface.add(CreateBridgeParam());
        BridgeQueryServicequeryDynamic.add(CreateBridgeParamqueryDynamic());
    }

    @PostConstruct
    public void init() {
        System.out.println("init方法已被调用");
    }

    @PreDestroy
    public void destory() {
        System.out.println("destory方法已被调用");

    }
}
