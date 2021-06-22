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
 * @program configurescript-parent
 * @description: 准贷记卡呆账\止付个数
 * @author: liuyafei
 * @create: 2019/10/23 19:05
 */
public class SecdStandardCardBaddebtCount implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_StandardCardBaddebtCount";
    static String accountType = "R3";

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
    public Set checkAccountType(JSONObject jsonObject, String accountType) {
        Set set = new HashSet();
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            return set;
        }
        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if (accountType.equals(account.getString("accountType"))) {
                set.add(account.getString("accountNumber"));
            }
        }
        return set;
    }


    /**
     * 1、当信贷交易信息明细（CreditDetail ）—》  基本信息段（Crt2_P_EssentialInformation） —》 账户类型（AccountType）=R3-准贷记卡账户时，
     * 取信贷交易信息明细（CreditDetail ）—》最新表现信息段（crt2_p_thelatestperformance）—》账户状态 （accountState） （accountState）= 5-呆账 的账户数
     * 取信贷交易信息明细（CreditDetail ）—》最近一次月度表现信息（crt2_p_lastmonthly）—》账户状态（accountState） = 3-止付、31-银行止付、8-司法追偿的账户数
     * 满足条件的账户数汇总
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        int accountNum=0;

        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return accountNum;
        }
        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_thelatestperformance"))) {
            JSONArray thelatestperformance = JSONObject.parseArray(jsonObject.getString("crt2_p_thelatestperformance"));
            for(int i=0;i<thelatestperformance.size();i++){
                JSONObject jsonObject1=(JSONObject)thelatestperformance.get(i);
                if(set.contains(jsonObject1.getString("accountNumber"))&&"5".equals(jsonObject1.getString("accountState"))){
                    accountNum+=1;
                }
            }

        }
        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_lastmonthly"))) {
            JSONArray astmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));
            for(int i=0;i<astmonthly.size();i++){
                JSONObject jsonObject1 = (JSONObject)astmonthly.get(i);
                if(set.contains(jsonObject1.getString("accountNumber"))&&Arrays.asList(new String[]{"3", "31", "8"}).contains(jsonObject1.getString("accountState"))){
                    accountNum+=1;
                }
            }

        }
        return accountNum;
    }



}
