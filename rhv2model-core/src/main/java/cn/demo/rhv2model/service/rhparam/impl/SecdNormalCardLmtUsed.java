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
 * @description: 流通贷记卡已用额度
 * @author: liuyafei
 * @create: 2019/10/23 17:35
 */
public class SecdNormalCardLmtUsed implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_NormalCardLmtUsed";
    static String accountType = "R2";


    @Override
    public String getParamKey() {
        return resultKey;
    }

    /**
     * 1、当信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户类型（AccountType）=R2-贷记卡账户 且币种（Currency） 为CNY人民币
     * 且信贷交易信息明细（CreditDetail ）—》最新表现信息段（Crt2_P_TheLatestPerformance）—》账户状态 （AccountState）=1-正常  或
     * 最近一次月度表现信息（Crt2_P_LastMonthly）—》账户状态 （AccountState）=1-正常
     * 2、取 信贷交易信息明细CreditDetail—》最近一次月度表现信息（Crt2_P_LastMonthly）—》已用额度（AmountUsed）汇总
     *
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        BigDecimal amount = BigDecimal.ZERO;

        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return amount.toString();
        }

        Set account = new HashSet();
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


        if (account.isEmpty()||StringUtils.isEmpty(jsonObject.getString("crt2_p_lastmonthly"))) {
            return amount.toString();
        }



        JSONArray lastmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));
        for(int i=0;i<lastmonthly.size();i++){
            JSONObject jsonObject1=(JSONObject)lastmonthly.get(i);
            if(account.contains(jsonObject1.getString("accountNumber"))){
                BigDecimal amountUsed = jsonObject1.getBigDecimal("amountUsed");
                if(amountUsed!=null){
                    amount=amount.add(amountUsed);
                }

            }

        }
        return amount.toString();
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
