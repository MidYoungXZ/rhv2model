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
 * @description: 准贷记卡透支未付总额
 * @author: liuyafei
 * @create: 2019/10/23 17:35
 */
public class SecdStandardCardOverdueAmt implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_StandardCardOverdueAmt";
    static String accountType = "R3";

    @Override
    public String getParamKey() {
        return resultKey;
    }


    /**
     * 1、当信贷交易信息明细（CreditDetail ）—》  基本信息段（Crt2_P_EssentialInformation） —》 账户类型（AccountType）=R3-准贷记卡账户时，
     * 取 最近一次月度表现（crt2_p_lastmonthly）—》透支 180 天以上未还本金（overdraftDay） 汇总
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
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_lastmonthly"))) {
            return amount.toString();
        }
        JSONArray lastTwentyfourMonth = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));
        for(int i=0;i<lastTwentyfourMonth.size();i++){
            JSONObject jsonObject1=(JSONObject)lastTwentyfourMonth.get(i);
            if(set.contains(jsonObject1.getString("accountNumber"))){
                BigDecimal overdraftDay = jsonObject1.getBigDecimal("overdraftDay");
                if(overdraftDay!=null){
                    amount=amount.add(overdraftDay);
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
            if (accountType.equals(account.getString("accountType"))) {
                set.add(account.getString("accountNumber"));
            }
        }
        return set;
    }


}
