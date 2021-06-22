package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.Function;

/**
 * CreateBy: Liurenquan
 * CreateDate: 2020/1/3
 */
public class SecdModelMaxCreditLimitAmountNoClose implements Function<JSONObject, Object>, RhParamKey {


    public static String resultKey = "secd_model_max_creditlimitamount_no_close";

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

        if(essentialinformation==null){
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

        if(thelatestperformance==null){
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

        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        List<Long> list = new ArrayList<>();
        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if(set.contains(account.getString("accountNumber"))&&!StringUtils.isEmpty(account.getString("creditLineOfAccount"))){
                String creditLineOfAccount = account.getString("creditLineOfAccount");
                if(!StringUtils.isEmpty(creditLineOfAccount)){
                    list.add(Long.valueOf(creditLineOfAccount));
                }
            }
        }
        return list.isEmpty()?result:String.valueOf(Collections.max(list));
    }

    @Override
    public String getParamKey() {
        return resultKey;
    }
}
