package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * CreateBy: Liurenquan
 * CreateDate: 2020/1/3
 */
public class SecdModelLoanSumMaxDuration implements Function<JSONObject, Object> , RhParamKey {

    public static String resultKey = "secd_model_loansummaxduration";

    static String[] businessType = {"1","2","3"};

    /**
     * 判断账户类型
     *
     * @param jsonObject
     * @param accountType
     * @return
     */
    public List<Long> checkAccountType(JSONObject jsonObject, String[] accountType) {
        List<Long> list = new ArrayList<>();
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_beoverdue"))) {
            return list;
        }
        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_beoverdue"));

        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if (Arrays.asList(accountType).contains(account.getString("businessType"))){
                if(!StringUtils.isEmpty(account.getString("theLongestOverdue"))){
                    list.add(Long.valueOf(account.getString("theLongestOverdue")));
                }

            }
        }
        return list;
    }


    @Override
    public Object apply(JSONObject jsonObject) {
        String result = "-999";

        List<Long> list = checkAccountType(jsonObject, businessType);
        if (list.isEmpty()) {
            return result;
        }

        return String.valueOf(Collections.max(list));
    }

    @Override
    public String getParamKey() {
        return resultKey;
    }
}
