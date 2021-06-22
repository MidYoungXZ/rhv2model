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
 * @description: 贷记卡本月应还款
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdRepayAmtCard implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_RepayAmtCard";
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
            if (Arrays.asList(accountType).contains(account.getString("accountType"))){
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
     * 1、当信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation）-》账户类型（AccountType）=R2-贷记卡账户
     * 取 当信贷交易信息明细（CreditDetail ）—》最近一次月度表现信息（Crt2_P_LastMonthly）—》本月应还款（Reimbursement）汇总
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

        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_lastmonthly"))) {
            JSONArray lastmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));
            for(int i=0;i<lastmonthly.size();i++){
                JSONObject jsonObject1 = lastmonthly.getJSONObject(i);
                if(set.contains(jsonObject1.getString("accountNumber"))){
                    if(!StringUtils.isEmpty(jsonObject1.getString("reimbursement"))){
                        amount = amount.add(jsonObject1.getBigDecimal("reimbursement"));

                    }
                }
            }
        }

        return amount.toString();
    }
}
