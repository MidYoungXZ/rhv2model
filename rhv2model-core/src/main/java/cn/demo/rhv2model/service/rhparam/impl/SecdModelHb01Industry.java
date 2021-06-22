package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * CreateBy: Liurenquan
 * CreateDate: 2020/1/3
 */
public class SecdModelHb01Industry implements Function<JSONObject, Object> , RhParamKey {

    public static String resultKey = "secd_model_hb_01_industry";


    @Override
    public Object apply(JSONObject jsonObject) {
        String result = "-999";

        String str = jsonObject.getString("crt2_p_professional");
        if(StringUtils.isBlank(str)){
            return result;
        }
        JSONArray array = JSON.parseArray(str);
        if(array.isEmpty()){
            return result;
        }
        Map<String,String> map = new HashMap<>();
        for(Object o:array){
            JSONObject json = (JSONObject) o;
            if(StringUtils.isNotBlank(json.getString("getTime"))){
                map.put(json.getString("getTime"),json.getString("industry"));
            }
        }
        if(map.isEmpty()){
           return result;
        }
        String key = getMaxKey(map);
        return map.get(key);
    }


    private String getMaxKey(Map<String, String> map) {
        Set<String> set = map.keySet();
        Object[] obj = set.toArray();
        Arrays.sort(obj);
        return obj[obj.length-1].toString();
    }

    @Override
    public String getParamKey() {
        return resultKey;
    }
}
