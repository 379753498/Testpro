package org.com.testpro.Dao;

import lombok.Getter;
import lombok.Setter;
import org.com.testpro.Bean.RedisHbaseVuale;
import org.com.testpro.StringUtil.Datautil;
import org.com.testpro.StringUtil.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


@Component
public class RadisDataJQ {


    @Autowired
    StringUtil StringUtil;
    @Autowired
    StringRedisTemplate stringRedisTemplatet;


    private String key;
    private String RADIS_KEY="BSD";
    private int offsetStart=0;
    private int offsetEnd=-1;




    /**
     * 获取某个设备的最新一条信息
     *
     * @param gateway
     * @param modular
     * @param pathn
     * @return
     */
    public Map <String, String> getEquipData(final String gateway, final String modular, final String pathn) {

        String key = getKeyString(gateway, modular, pathn);
        final Map <String, String> data = new HashMap <String, String>();
        Set <String> vaules = stringRedisTemplatet.opsForZSet().range(key, 0, 0);
        if (vaules.size() == 1) {
            for (String string : vaules) {
                String[] item_values = string.split("_");
                data.put("time", item_values[0]);
                data.put("value", item_values[1]);
                break;
            }
        } else {

            System.err.println("取值数据不是一个" + vaules.size());
            System.err.println(key);
        }
        return data;
    }

    private String getKeyString(String gateway, String modular, String pathn) {
        String key = String.format("%s_%s", gateway, modular + "_" + pathn);
        key = String.format("%s-[%s]", this.RADIS_KEY, key);
        return key;
    }

    /**
     * @param gateway 网关号
     * @param modular 模块号
     * @param pathn   通道号
     * @return HashMap<String,Integer>
     * @Description: 返回 一个设备 0.001精度秒传输了多少个数据
     */
    public HashMap <String, Integer> getEquipData1000_Ext(final String gateway,final String modular, final String pathn) {
        final HashMap <String, Integer> data = new HashMap <String, Integer>();
        String key = getKeyString(gateway, modular, pathn);
        Integer showNums = 1;
        String[] item_values = null;
        Set <String> vaules = stringRedisTemplatet.opsForZSet().range(key, this.offsetStart, this.offsetEnd);
        if (vaules.isEmpty()) {
            return null;
        }
        String timeSeq = null;
        String value = null;
        for (String item : vaules) {
            item_values = item.split("_");
            timeSeq = Datautil.LongTimeStampTODate(Long.parseLong(item_values[0]), "yyyy:MM:dd:HH:mm:ss SSS");
            value = item_values[1];
            if (data.containsKey(timeSeq)) {
                showNums = data.get(timeSeq);
                if (null != showNums) {
                    data.put(timeSeq, showNums + 1);
                } else {
                    data.put(timeSeq, 1);
                }
            } else {
                data.put(timeSeq, 1);
            }
        }
        return data;
    }


    public LinkedHashMap <String, RedisHbaseVuale> getEquipData1000_ExtForlist(String gateway,final String modular, final String pathn) {

        gateway = StringUtil.Bridgenamechangegetawy(gateway);
        final LinkedHashMap <String, RedisHbaseVuale> data = new LinkedHashMap <String, RedisHbaseVuale>();
        String key = getKeyString(gateway, modular, pathn);
        String[] item_values = null;
        Set <String> vaules = stringRedisTemplatet.opsForZSet().range(key, 0, 1000);
        if (vaules.isEmpty()) {
            return null;
        }
        String timeSeq = null;
        String value = null;
        for (String item : vaules) {
            item_values = item.split("_");
            timeSeq = item_values[0];
            value = item_values[1];
            data.put(timeSeq, new RedisHbaseVuale(null, value, false));
        }
        return data;
    }


}
