package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @program configurescript-parent
 * @description: 流通贷记卡额度使用率
 * @author: liuyafei
 * @create: 2019/10/23 17:35
 */
public class SecdNormalCardLmtUsedRate implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_NormalCardLmtUsedRate";
    static String accountType = "R2";

    @Override
    public String getParamKey() {
        return resultKey;
    }

    /**
     * 流通贷记卡已用额度/流通贷记卡总额度
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        Set account = new HashSet();
        String defaultValue="-9999";
        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return defaultValue;
        }
        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_thelatestperformance"))) {
            JSONArray lastTwentyfourMonth = JSONObject.parseArray(jsonObject.getString("crt2_p_thelatestperformance"));
            for(int i=0;i<lastTwentyfourMonth.size();i++){
                JSONObject jsonObject1=(JSONObject)lastTwentyfourMonth.get(i);
                if(set.contains(jsonObject1.getString("accountNumber"))&&"1".equals(jsonObject1.getString("accountState"))){
                    account.add(jsonObject1.getString("accountNumber"));
                }

            }
        }

        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_lastmonthly"))) {
            JSONArray lastTwentyfourMonth = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));
            for(int i=0;i<lastTwentyfourMonth.size();i++){
                JSONObject jsonObject1=(JSONObject)lastTwentyfourMonth.get(i);
                if(set.contains(jsonObject1.getString("accountNumber"))&&"1".equals(jsonObject1.getString("accountState"))){
                    account.add(jsonObject1.getString("accountNumber"));
                }

            }
        }

        if (account.isEmpty()||StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))||StringUtils.isEmpty(jsonObject.getString("crt2_p_lastmonthly"))) {
            return defaultValue;
        }



        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));
        BigDecimal amountTotal = BigDecimal.ZERO;
        for(int i=0;i<essentialinformation.size();i++){
            JSONObject jsonObject1=(JSONObject)essentialinformation.get(i);
            if(account.contains(jsonObject1.getString("accountNumber"))&&"CNY".equals(jsonObject1.getString("currency"))){
                BigDecimal amountUsed = jsonObject1.getBigDecimal("creditLineOfAccount");
                if(amountUsed!=null){
                    amountTotal=amountTotal.add(amountUsed);
                }

            }

        }
        JSONArray lastmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));
        BigDecimal amount = BigDecimal.ZERO;
        for(int i=0;i<lastmonthly.size();i++){
            JSONObject jsonObject1=(JSONObject)lastmonthly.get(i);
            if(account.contains(jsonObject1.getString("accountNumber"))){
                BigDecimal amountUsed = jsonObject1.getBigDecimal("amountUsed");
                if(amountUsed!=null){
                    amount=amount.add(amountUsed);
                }

            }

        }

        if(amountTotal.compareTo(BigDecimal.ZERO)==0){
            return defaultValue;
        }
        if(amount.compareTo(BigDecimal.ZERO)==0){
            return BigDecimal.ZERO;
        }

        return amount.divide(amountTotal,4,BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 判断账户类型
     *
     * @param jsonObject
     * @param accountType
     * @return
     */
    /**
     * 判断账户类型
     *
     * @param jsonObject
     * @param accountType
     * @return
     */
    public Set checkAccountType(JSONObject jsonObject, String accountType) {
        Set set = new HashSet();
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            return set;
        }
        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if (accountType.equals(account.getString("accountType"))&&"CNY".equals(account.getString("currency"))) {
                set.add(account.getString("accountNumber"));
            }
        }
        return set;
    }


}
