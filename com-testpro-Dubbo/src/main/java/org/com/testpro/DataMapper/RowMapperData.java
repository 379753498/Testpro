package org.com.testpro.DataMapper;

import org.com.testpro.Bean.SensorData;
import org.com.testpro.DataresultBean.NewSensorData;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.com.testpro.DaoMapper.SensorData15.getModularnum;
import static org.com.testpro.DaoMapper.SensorData15.getpathnum;

public class RowMapperData implements RowMapper<NewSensorData> {

    @Override
    public NewSensorData mapRow(ResultSet rs, int rowNum) throws SQLException {
        NewSensorData s = new NewSensorData();
        s.setBridgename(rs.getString("bridgename"));
        s.setEquipmentid(rs.getString("tid"));
        s.setEquipmentname(rs.getString("equipmentname"));
        s.setGatewaynum(rs.getString("parent_id"));
        s.setLeixing(rs.getString("tname"));
        String a = rs.getString("sensor_code");

        if (a.contains("_")) {
            s.setModularnum(getModularnum(a));
            s.setPathnum(getpathnum(a));
        } else {
            s.setModularnum("null");
            s.setPathnum("null");
        }
        s.setPoint_flag(rs.getString("point_flag"));
        return s;
    }
}
