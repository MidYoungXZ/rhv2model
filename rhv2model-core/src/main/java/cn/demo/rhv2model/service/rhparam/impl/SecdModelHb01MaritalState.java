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
public class SecdModelHb01MaritalState implements Function<JSONObject, Object>, RhParamKey {

    public static String resultKey = "secd_model_hb_01_maritalstate";

    @Override
    public Object apply(JSONObject jsonObject) {
        String result = "-999";

        String str = jsonObject.getString("crt2_p_spouse");
        if(StringUtils.isBlank(str)){
            return result;
        }
        JSONArray array = JSON.parseArray(str);
        String maritalStatus = "";
        if(!array.isEmpty()){
            JSONObject json = (JSONObject) array.get(0);
            maritalStatus = json.getString("maritalStatus");
        }

        return StringUtils.isBlank(maritalStatus)?result:maritalStatus;
    }


    @Override
    public String getParamKey() {
        return resultKey;
    }
}
