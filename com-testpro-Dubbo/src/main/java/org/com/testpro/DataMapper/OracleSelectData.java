package org.com.testpro.DataMapper;

import org.com.testpro.Annotation.ChooseDataSource;
import org.com.testpro.DataresultBean.NewSensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OracleSelectData {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ChooseDataSource
    public List <NewSensorData>  selectNewSensorData() {
        String sql = "select b.point_flag,a.equipmentname,a.parent_id,b.tid,b.monitor_id,c.bridgename,d.tname,b.sensor_code from  PLAT_BAS_EQUIPMENT a,PLAT_BAS_EQUIPCONFIG b,PLAT_BAS_BRIDGE c,plat_bas_dictionary d where a.equipmentid=b.equip_id and c.bridgeid=b.build_id and d.tid=b.monitor_id and b.sensor_code is not null and d.tname!='地磅'and c.bridgename!='柘皋河桥'order by c.bridgename, d.tname ";
        List <NewSensorData> query = jdbcTemplate.query(sql, new RowMapperData());
        return query;
    }

}
