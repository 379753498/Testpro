package org.com.testpro.Mybatis.Service;

import org.com.testpro.Annotation.ChooseDataSource;
import org.com.testpro.Bean.SensorData;
import org.com.testpro.Enum.DataEnum;
import org.com.testpro.Mybatis.Mapper.SensorDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorDataService {
    @Autowired
    SensorDataMapper sensorDataMapper;

    @ChooseDataSource(dataSourceName = DataEnum.DATA_SOURCE_15)
    public List <SensorData> getSensorData() {
        return sensorDataMapper.getSensorData();
    }

}
