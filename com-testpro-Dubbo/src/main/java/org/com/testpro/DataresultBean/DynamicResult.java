package org.com.testpro.DataresultBean;

import lombok.Data;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonMethod;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
@JsonAutoDetect(JsonMethod.FIELD)
public class DynamicResult {

//    @JsonProperty(value = "PK")
//    private String PK;
//    private String location;
    private String oneLevelAlarm_max;
    private String oneLevelAlarm_min;
//    private String sensor;
    private String thrLevelAlarm_min;
    private String thrLevelAlarm_max;
    private String time;
    private String twoLevelAlarm_max;
    private String twoLevelAlarm_min;
}



