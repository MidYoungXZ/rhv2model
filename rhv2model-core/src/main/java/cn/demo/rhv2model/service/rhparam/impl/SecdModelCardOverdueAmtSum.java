package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * CreateBy: Liurenquan
 * CreateDate: 2020/1/3
 */
public class SecdModelCardOverdueAmtSum implements Function<JSONObject, Object> , RhParamKey {

    public static String resultKey = "secd_model_CardOverdueAmtSum";

    static String[] accountType = {"R2","R3"};

    /**
     * 判断账户类型
     *
     * @param jsonObject
     * @param accountType
     * @return
     */
    public Set checkAccountType(JSONObject jsonObject, String[] accountType) {
        Set set = new HashSet();
        String essentialinformation = jsonObject.getString("crt2_p_essentialinformation");
        if (StringUtils.isBlank(essentialinformation)) {
            return set;
        }
        JSONArray array = JSONObject.parseArray(essentialinformation);

        for (int i = 0; i < array.size(); i++) {
            JSONObject account = (JSONObject) array.get(i);
            if (Arrays.asList(accountType).contains(account.getString("accountType"))){
                if(StringUtils.isNotBlank(account.getString("accountNumber"))){
                    set.add(account.getString("accountNumber"));
                }

            }
        }
        return set;
    }

    @Override
    public Object apply(JSONObject jsonObject) {
        String result = "-999";

        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return result;
        }

        String lastmonthly = jsonObject.getString("crt2_p_lastmonthly");
        if (StringUtils.isBlank(lastmonthly)) {
            return set;
        }
        JSONArray array = JSONObject.parseArray(lastmonthly);
        int count = 0;
        for(Object o:array){
            JSONObject account = (JSONObject) o;
            if(set.contains(account.getString("accountNumber"))){
                String currentOverdueTotal = account.getString("currentOverdueTotal");
                if(StringUtils.isNotBlank(currentOverdueTotal)){
                    count+=Integer.valueOf(currentOverdueTotal);
                }
            }
        }
        return count==0?result:String.valueOf(count);
    }

    @Override
    public String getParamKey() {
        return resultKey;
    }
}
