package org.com.testpro;

import com.gsafety.bigdata.lifeline.pojo.BridgeParam;
import com.gsafety.bigdata.lifeline.service.BridgeQueryService;
import org.com.testpro.Bean.Result;
import org.com.testpro.DataMapper.DubboTestDataMap;
import org.com.testpro.DataresultBean.BridgeDataResult;
import org.com.testpro.StringUtil.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class Cortroller {
    @Autowired
    DubboTestDataMap dubboTestDataMap;
    @Autowired
    BridgeQueryService BridgeQueryService;

    @RequestMapping("/getsom")
    public Result <List <BridgeDataResult>> getsom() throws Exception {
        BridgeParam bridgeParam = dubboTestDataMap.CreateBridgeParam();
        String queryBridgeresult = BridgeQueryService.queryBridge(bridgeParam);
        List <BridgeDataResult> bridgeDataResults = JsonUtil.GsonStringToList(queryBridgeresult, BridgeDataResult.class);
        Result <List <BridgeDataResult>> listResult = new Result <>();
        listResult.setData(bridgeDataResults);
        listResult.setCode(200);
        listResult.setMessage("OJBKKKKK");
        String s = JsonUtil.GsonBeanToString(listResult);
        Result <List <BridgeDataResult>> listResult1 = JsonUtil.GsonStringToResultList(s, BridgeDataResult.class);
        return listResult1;

    }


}
