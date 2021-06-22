package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.Function;

public class SecdModelMaxLoanCurrOverdueAmount implements Function<JSONObject, Object> , RhParamKey {

    public static String resultKey = "secd_model_max_loan_curroverdueamount";

    static String[] accountType = {"R1","D1","R4"};

    public Set checkAccountType(JSONObject jsonObject, String[] accountType) {
        Set set = new HashSet();
        if (StringUtils.isBlank(jsonObject.getString("crt2_p_essentialinformation"))) {
            return set;
        }
        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if (Arrays.asList(accountType).contains(account.getString("accountType"))
                    &&"CNY".equals(account.getString("currency"))){
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

        if (StringUtils.isBlank(jsonObject.getString("crt2_p_lastmonthly"))) {
            return result;
        }
        JSONArray lastmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));

        List<Long> currentOverdueSet = new ArrayList<>();
        for (int i = 0; i < lastmonthly.size(); i++) {
            JSONObject account = (JSONObject) lastmonthly.get(i);
            if(set.contains(account.getString("accountNumber"))){
                String currentOverdueTotal = account.getString("currentOverdueTotal");
                if(StringUtils.isNotBlank(currentOverdueTotal)){
                    currentOverdueSet.add(Long.valueOf(currentOverdueTotal));
                }

            }
        }
        if(currentOverdueSet.isEmpty()){
            return result;
        }

        return String.valueOf(Collections.max(currentOverdueSet));
    }

    @Override
    public String getParamKey() {
        return resultKey;
    }
}
