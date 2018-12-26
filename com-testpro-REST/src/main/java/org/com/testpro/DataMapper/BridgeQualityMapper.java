package org.com.testpro.DataMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import org.com.testpro.Bean.BridgeQualityBean;
import org.com.testpro.Bean.BridgeQualityCJbean;
import org.com.testpro.StringUtil.Datautil;
import org.com.testpro.StringUtil.JsonUtil;
import org.com.testpro.TestngUtil.TestNgUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.testng.annotations.DataProvider;

import javax.annotation.Resource;
import java.util.*;

@Component
@Data
public class BridgeQualityMapper implements InitializingBean {

    /**
     * parametersList 最终返回参数集合List
     */
    private static List<Object> parametersList = new ArrayList<Object>();
    private static List<String> BridgeId = new ArrayList<String>();
    private static String[] BridgeIds = null;
    /**
     * maps接受来自数据查询的结果
     */
    private static List<Map<String, Object>> maps = null;
    final String sql = "select  b.build_id  from PLAT_BAS_EQUIPMENT  a,PLAT_BAS_EQUIPCONFIG b,PLAT_BAS_BRIDGE  c, plat_bas_dictionary  d where a.equipmentid = b.equip_id   and c.bridgeid = b.build_id   and d.tid = b.monitor_id   and b.sensor_code is not null   and d.tname != '地磅'  and c.bridgename!='柘皋河桥' group by   b.build_id";
    @Resource
    private JdbcTemplate jdbcTemplate;

    @DataProvider(name = "getBridgeQualityMapper")
    public static Iterator<Object[]> getBridgeQualityMapper() throws JsonProcessingException {
        HashMap<String, String> parameters = new HashMap<String, String>();
        ArrayList<BridgeQualityBean> list = new ArrayList<BridgeQualityBean>();
        list.add(createBridgeQualityBean());
        String valueAsString = JsonUtil.FastObjectToJsonString(list);
        parameters.put("message", valueAsString);
        parametersList.add(parameters);
        return TestNgUtil.createObjectList(parametersList);

    }


    @DataProvider(name = "getBridgeQualityCJMapper")
    public static Iterator<Object[]> getBridgeQualityCJMapper() throws JsonProcessingException {
        List<Object> BridgeQualityCJbeanList = new ArrayList<Object>();
        BridgeQualityCJbean bridgeQualityCJbean = new BridgeQualityCJbean();
        bridgeQualityCJbean.setBridge_id(BridgeIds);
        bridgeQualityCJbean.setStime(Datautil.LongTimeStampTODate((System.currentTimeMillis() - 24 * 60 * 60 * 1000), "yyyy-MM-dd HH:mm:ss"));
        bridgeQualityCJbean.setEtime(Datautil.LongTimeStampTODate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        bridgeQualityCJbean.setUsername("bridge");
        bridgeQualityCJbean.setSyscode("bridge");
        BridgeQualityCJbeanList.add(bridgeQualityCJbean);
        return TestNgUtil.createObjectList(BridgeQualityCJbeanList);

    }

    private static BridgeQualityBean createBridgeQualityBean() {
        BridgeQualityBean bridgeQualityBean = new BridgeQualityBean();
        bridgeQualityBean.setStime(Datautil.LongTimeStampTODate((System.currentTimeMillis() - 24 * 60 * 60 * 1000), "yyyy-MM-dd HH:mm:ss"));
        bridgeQualityBean.setEtime(Datautil.LongTimeStampTODate(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss"));
        bridgeQualityBean.setSyscode("bridge");
        bridgeQualityBean.setUsername("dev");
        bridgeQualityBean.setBridgeids(BridgeIds);
        bridgeQualityBean.setPageSize("10");
        bridgeQualityBean.setPageNo("1");
        return bridgeQualityBean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        maps = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> entry : maps) {
            Object build_id = entry.get("build_id");
            BridgeId.add((String) build_id);
        }
        BridgeIds = new String[BridgeId.size()];
        for (int i = 0; i < BridgeId.size(); i++) {
            BridgeIds[i] = BridgeId.get(i);
        }
    }
}
