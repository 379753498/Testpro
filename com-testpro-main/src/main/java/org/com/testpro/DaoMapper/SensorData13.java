package org.com.testpro.DaoMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.com.testpro.Bean.SensorData;
import org.springframework.jdbc.core.RowMapper;

public class SensorData13 implements RowMapper<SensorData> {

	public SensorData mapRow(ResultSet rs, int rowNum) throws SQLException {

		SensorData s = new SensorData();
		s.setBridgename(rs.getString("bridgename"));
		s.setEquipmentid(rs.getString("equipmentid"));
		s.setEquipmentname(rs.getString("equipmentname"));
		s.setDevicePosition(rs.getString("device_position"));
		s.setGatewaynum(rs.getString("gatewaynum"));
		s.setModularnum(rs.getString("modularnum"));
		s.setPathnum(rs.getString("pathnum"));
		s.setLeixing(rs.getString("monproject"));
		return s;
	}

}
