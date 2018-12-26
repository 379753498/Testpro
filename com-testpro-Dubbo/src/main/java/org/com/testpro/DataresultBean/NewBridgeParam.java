package org.com.testpro.DataresultBean;

import com.gsafety.bigdata.lifeline.pojo.BridgeParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
//这里出现的警告可能会对EqualsAndHashCode方法有影响 所以增加注解 解决警告的提示 具体含义可能是会调用父类的方法
public class NewBridgeParam extends BridgeParam {


    private String equipmentname;

    @Override
    public String toString() {

        return new StringBuilder().append("Terninal  is").append(this.getTerninal()).append(" Sensor is").append(this.getSensor()).append(" StarTime is").append(this.getStarTime()).append(" EndTime is").append(this.getEndTime()).append(" equipmentname is").append(this.getEquipmentname()).toString();
    }
}
