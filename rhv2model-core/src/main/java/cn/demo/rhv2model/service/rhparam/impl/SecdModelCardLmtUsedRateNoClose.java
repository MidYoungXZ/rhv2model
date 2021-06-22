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
public class SecdModelCardLmtUsedRateNoClose implements Function<JSONObject, Object> , RhParamKey {
    public static String resultKey = "secd_model_CardLmtUsedRateNoClose";

    static String[] accountType = {"R2"};



    /**
     * 判断账户类型
     *
     * @param jsonObject
     * @param accountType
     * @return
     */
    public Set checkAccountType(JSONObject jsonObject, String[] accountType) {
        Set set = new HashSet();
        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        if(essentialinformation==null||essentialinformation.isEmpty()){
            return set;
        }

        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if (Arrays.asList(accountType).contains(account.getString("accountType"))
                    &&"CNY".equals(account.getString("currency"))){
                if(!StringUtils.isEmpty(account.getString("accountNumber"))){
                    set.add(account.getString("accountNumber"));
                }

            }
        }


        Set performanceSet = new HashSet();
        JSONArray thelatestperformance = JSONObject.parseArray(jsonObject.getString("crt2_p_thelatestperformance"));
        if(thelatestperformance==null||thelatestperformance.isEmpty()){
            return set;
        }

        for (int i = 0; i < thelatestperformance.size(); i++) {
            JSONObject account = (JSONObject) thelatestperformance.get(i);
            if (set.contains(account.getString("accountNumber"))&&!"4".equals(account.getString("accountState"))){
                performanceSet.add(account.getString("accountNumber"));
            }
        }
        return performanceSet;
    }



    @Override
    public Object apply(JSONObject jsonObject) {
        String result = "-999";

        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return result;
        }

        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));

        long count = 0;
        boolean bool=true;
        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if(set.contains(account.getString("accountNumber"))&&!StringUtils.isEmpty(account.getString("amountUsed"))){
                String amountUsed = account.getString("amountUsed");
                count+=Long.valueOf(amountUsed);
                bool=false;
            }
        }
        if(bool){
            return result;
        }
        return String.valueOf(count);
    }


    @Override
    public String getParamKey() {
        return resultKey;
    }
}
