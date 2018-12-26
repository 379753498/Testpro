package org.com.testpro.RedisTest;

import lombok.extern.slf4j.Slf4j;
import org.com.testpro.Base.SpringTestngBase;
import org.com.testpro.Bean.SensorData;
import org.com.testpro.Dao.RadisDataJQ;
import org.com.testpro.Mybatis.Service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
public class RedisDataCheck extends SpringTestngBase {
    @Autowired
    SensorDataService sensorDataService;
    @Autowired
    RadisDataJQ radisDataJQ;


    @Test
    public void testRedisDataCheck() {
        Boolean istrue = false;
        List <SensorData> sensorData = sensorDataService.getSensorData();
        int offset = ((int) (Math.random() * sensorData.size()));
        SensorData sensorData1 = sensorData.get(offset);
        String filepath = sensorData1.getFilepath();
        String[] split = filepath.split("_");
        log.info(sensorData1.toString());
        HashMap <String, Integer> equipData1000_ext = radisDataJQ.getEquipData1000_Ext(sensorData1.getGatewaynum(), split[0], split[1]);
        if (equipData1000_ext != null) {
            Iterator <Map.Entry <String, Integer>> iterator = equipData1000_ext.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry <String, Integer> next = iterator.next();
                String key = next.getKey();
                Integer value = next.getValue();
                if (value != 1) {
                    istrue = true;
                    log.error("设备信息{},sensor{}发生了上传数据时间戳重复情况,发生时间{},重复个数{}", sensorData1.getGatewaynum(), sensorData1.getFilepath(), key, value);
                }

            }
        } else {
            log.info(sensorData1.getGatewaynum() + sensorData1.getFilepath() + "没有数据");
        }

        Assert.assertEquals(true, istrue == false);
        log.info("测试通过未发现Redis中有上传重复的时间戳数据");

    }


}
