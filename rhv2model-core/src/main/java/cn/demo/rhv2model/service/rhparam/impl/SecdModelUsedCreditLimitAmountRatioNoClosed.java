package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * CreateBy: Liurenquan
 * CreateDate: 2020/1/6
 */
public class SecdModelUsedCreditLimitAmountRatioNoClosed implements Function<JSONObject, Object> , RhParamKey {

    public static String resultKey = "secd_model_usedcreditlimitamount_ratio_no_closed";

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


    private String getLmtUsedRateNoClose(JSONObject jsonObject){

        String result = "0";

        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return result;
        }

        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));

        long count = 0;
        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if(set.contains(account.getString("accountNumber"))&&!StringUtils.isEmpty(account.getString("amountUsed"))){
                String amountUsed = account.getString("amountUsed");
                count+=Long.valueOf(amountUsed);
            }
        }
        return String.valueOf(count);
    }


    private String getLmtNoClose(JSONObject jsonObject){

        String result = "0";

        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return result;
        }

        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        long count = 0;
        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if(set.contains(account.getString("accountNumber"))&&!StringUtils.isEmpty(account.getString("creditLineOfAccount"))){
                String creditLineOfAccount = account.getString("creditLineOfAccount");
                count+=Long.valueOf(creditLineOfAccount);
            }
        }
        return String.valueOf(count);
    }



    @Override
    public Object apply(JSONObject jsonObject) {

        String num = "-999";

        String used = getLmtUsedRateNoClose(jsonObject);
        String sum = getLmtNoClose(jsonObject);

        if("0".equals(sum)){
            return num;
        }

        if("0".equals(used)){
            return "0";
        }

        BigDecimal num1 = new BigDecimal(used);
        BigDecimal num2 = new BigDecimal(sum);

        num = num1.divide(num2,4, RoundingMode.HALF_UP).toString();
        return num;
    }

    @Override
    public String getParamKey() {
        return resultKey;
    }
}
