package org.com.testpro.Demo;

import org.com.testpro.Aop.CustomContextHolder;
import org.com.testpro.Bean.SensorData;
import org.com.testpro.Enum.DataEnum;
import org.com.testpro.Mybatis.Mapper.SensorDataMapper;
import org.com.testpro.base.BaseapplicationTestng;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;


public class TestNGFirst extends BaseapplicationTestng {


    @Autowired
    SensorDataMapper sensorDataMapper;


    @Test
    public void TestsensorDataMapper() {
        CustomContextHolder.setCustomerType(DataEnum.DATA_SOURCE_15.getSpringDatasourceBeanName());
//        CustomContextHolder.setCustomerType(DataEnum.DATA_SOURCE_HBase.getSpringDatasourceBeanName());
        List <SensorData> sensorData = sensorDataMapper.getSensorData();
        for (SensorData s : sensorData
                ) {

            System.out.println(s);
        }
    }


}
