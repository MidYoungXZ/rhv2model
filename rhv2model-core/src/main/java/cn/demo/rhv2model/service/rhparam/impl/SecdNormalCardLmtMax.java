package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @program configurescript-parent
 * @description: 流通贷记卡最高授信额度
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdNormalCardLmtMax implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_NormalCardLmtMax";
    static String[] accountType = {"R2"};

    @Override
    public String getParamKey() {
        return resultKey;
    }

    /**
     * 判断账户类型
     *
     * @param jsonObject
     * @param accountType
     * @return
     */
    public Set checkAccountType(JSONObject jsonObject, String[] accountType) {
        Set set = new HashSet();
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            return set;
        }
        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if (Arrays.asList(accountType).contains(account.getString("accountType"))
                    &&"CNY".equals(account.getString("currency"))){
                set.add(account.getString("accountNumber"));
            }
        }
        return set;
    }

    /**
     * 是否是否纯数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str) {
        Pattern pattern = compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }


    /**
     *
     * 1、当信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户类型（AccountType）=R2-贷记卡账户 且币种（Currency） 为CNY人民币
     * 且信贷交易信息明细（CreditDetail ）—》最新表现信息段（Crt2_P_TheLatestPerformance）—》账户状态 （AccountState）=1-正常  或
     * 最近一次月度表现信息（Crt2_P_LastMonthly）—》账户状态 （AccountState）=1-正常
     * 2、取 信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation）—》账户授信额度（CreditLineOfAccount） 取最高
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
            JSONArray thelatestperformance = JSONObject.parseArray(jsonObject.getString("crt2_p_thelatestperformance"));
            for (int i = 0; i < thelatestperformance.size(); i++) {
                JSONObject repayment = (JSONObject) thelatestperformance.get(i);
                if(set.contains(repayment.getString("accountNumber"))&&"1".equals(repayment.getString("accountState"))){
                    account.add(repayment.getString("accountNumber"));
                }
            }
        }

        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_lastmonthly"))) {
            JSONArray thelatestperformance = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));
            for (int i = 0; i < thelatestperformance.size(); i++) {
                JSONObject repayment = (JSONObject) thelatestperformance.get(i);
                if(set.contains(repayment.getString("accountNumber"))&&"1".equals(repayment.getString("accountState"))){
                    account.add(repayment.getString("accountNumber"));
                }
            }
        }



        if(account.isEmpty()){
            return amount.toString();
        }
        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            JSONArray lastmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));
            for(int i=0;i<lastmonthly.size();i++){
                JSONObject jsonObject1 = lastmonthly.getJSONObject(i);
                if(account.contains(jsonObject1.getString("accountNumber"))&&"CNY".equals(jsonObject1.getString("currency"))){
                    if(!StringUtils.isEmpty(jsonObject1.getString("creditLineOfAccount"))){
                        if(jsonObject1.getBigDecimal("creditLineOfAccount").compareTo(amount)==1){
                            amount=jsonObject1.getBigDecimal("creditLineOfAccount");
                        }
                    }
                }
            }
        }

        return amount.toString();
    }
}
