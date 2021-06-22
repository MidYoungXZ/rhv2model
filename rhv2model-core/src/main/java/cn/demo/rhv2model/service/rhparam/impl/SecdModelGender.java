package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.function.Function;

/**
 * CreateBy: Liurenquan
 * CreateDate: 2020/1/3
 */
public class SecdModelGender implements Function<JSONObject, Object> , RhParamKey {

    public static String resultKey = "secd_model_gender";


    @Override
    public Object apply(JSONObject jsonObject) {

        String result = "-999";

        String str = jsonObject.getString("crt2_p_identification");
        if(StringUtils.isBlank(str)){
            return result;
        }

        JSONObject identification = JSON.parseObject(str);
        String certtype = identification.getString("certtype");

        if("10".equals(certtype)){
            String certno = identification.getString("certno");
            if(StringUtils.isBlank(certno)){
                return result;
            }
            Integer sex  = Integer.valueOf(certno.substring(certno.length()-2,certno.length()-1));
            if(sex%2 == 1){
                return "1";
            }
            return "0";
        }
        return result;
    }

    @Override
    public String getParamKey() {
        return resultKey;
    }
}
