package org.com.testpro.DataresultBean;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.com.testpro.Bean.SensorData;
@Data
@EqualsAndHashCode(callSuper = true)
public class NewSensorData extends SensorData {

    private  String point_flag;
}
