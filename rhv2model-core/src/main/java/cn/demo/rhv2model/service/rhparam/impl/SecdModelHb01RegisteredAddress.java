package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.function.Function;

/**
 * CreateBy: Liurenquan
 * CreateDate: 2020/1/3
 */
public class SecdModelHb01RegisteredAddress implements Function<JSONObject, Object> , RhParamKey {

    public static String resultKey = "secd_model_hb_01_registeredaddress";

    @Override
    public Object apply(JSONObject jsonObject) {

        String result = "-999";

        String str = jsonObject.getString("crt2_p_identity");
        if(StringUtils.isBlank(str)){
            return result;
        }
        JSONArray identification = JSON.parseArray(str);
        if(identification.isEmpty()){
            return result;
        }
        String householdAddress = identification.getJSONObject(0).getString("householdAddress");

        if(StringUtils.isBlank(householdAddress)){
            return result;
        }
        if(householdAddress.contains("村")&&(householdAddress.contains("镇")||householdAddress.contains("乡"))){
            return "1";
        }
        return "0";
    }

    @Override
    public String getParamKey() {
        return resultKey;
    }
}
