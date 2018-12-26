package org.com.testpro.Mybatis.Mapper;

import org.apache.ibatis.annotations.Select;
import org.com.testpro.Bean.SensorData;

import java.util.List;

public interface SensorDataMapper {

    @Select("select a.equipmentname as equipmentname ,\n" +
            "       a.parent_id as gatewaynum ,\n" +
            "       b.monitor_id as modularnum,\n" +
            "       c.bridgename as bridgename ,\n" +
            "       d.tname as leixing,\n" +
            "       b.sensor_code as filepath \n" +
            "  from PLAT_BAS_EQUIPMENT   a,\n" +
            "       PLAT_BAS_EQUIPCONFIG b,\n" +
            "       PLAT_BAS_BRIDGE      c,\n" +
            "       plat_bas_dictionary  d\n" +
            " where a.equipmentid = b.equip_id\n" +
            "   and c.bridgeid = b.build_id\n" +
            "   and d.tid = b.monitor_id\n" +
            "   and b.sensor_code is not null\n" +
            "   and d.tname != '地磅'\n" +
            " order by c.bridgename, d.tname\n")
    List <SensorData> getSensorData();


    @Select("select a.equipmentname as equipmentname ,\n" +
            "       a.parent_id as gatewaynum ,\n" +
            "       b.monitor_id as modularnum,\n" +
            "       c.bridgename as bridgename ,\n" +
            "       d.tname as leixing,\n" +
            "       b.sensor_code as filepath \n" +
            "  from PLAT_BAS_EQUIPMENT   a,\n" +
            "       PLAT_BAS_EQUIPCONFIG b,\n" +
            "       PLAT_BAS_BRIDGE      c,\n" +
            "       plat_bas_dictionary  d\n" +
            " where a.equipmentid = b.equip_id\n" +
            "   and c.bridgeid = b.build_id\n" +
            "   and d.tid = b.monitor_id\n" +
            "   and b.sensor_code is not null\n" +
            "   and d.tname != '地磅'\n" +
            " order by c.bridgename, d.tname\n")
    List<SensorData> getSensorData13();

}
