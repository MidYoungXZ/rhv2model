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
 * @description: 个人汽车贷款总合同金额
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdCarLoanAmtSum implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_CarLoanAmtSum";
    static String accountType = "R2";

    @Override
    public String getParamKey() {
        return resultKey;
    }

    /**
     * 判断账户类型
     *
     * @param jsonObject
     * @return
     */
    public Set checkAccountType(JSONObject jsonObject) {
        Set set = new HashSet();
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_thelatestperformance"))) {
            return set;
        }
        JSONArray thelatestperformance = JSONObject.parseArray(jsonObject.getString("crt2_p_thelatestperformance"));

        for (int i = 0; i < thelatestperformance.size(); i++) {
            JSONObject account = (JSONObject) thelatestperformance.get(i);
            if (!"3".equals(account.getString("accountState"))) {
                set.add(account.getString("accountNumber"));
            }
        }
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            return new HashSet();
        }
        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        Set resultSet = new HashSet();
        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if (set.contains(account.getString("accountNumber"))&&Arrays.asList(new String[]{"21"}).contains(account.getString("businessTypes"))) {
                resultSet.add(account.getString("accountNumber"));
            }
        }

        return resultSet;
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
     * 1、信贷交易信息明细（CreditDetail ）—》最新表现信息段（Crt2_P_TheLatestPerformance）—》账户状态 （AccountState） 非 3-结清   and 信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》业务种类 =21-个人汽车消费贷款
     * 2、当  信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户类型（AccountType）=D1-非循环贷账户  或 R4-循环额度下分账户 时
     * 取 信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》借款金额 （LoanAmount）汇总
     * 3、当  信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户类型（AccountType）= R1-循环贷账户 时
     * 取 信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户授信额度（CreditLineOfAccount）  汇总
     * 4、所有满足条件的金额汇总
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        BigDecimal totalAmount =BigDecimal.ZERO;

        Set set = checkAccountType(jsonObject);
        if (set.isEmpty()) {
            return totalAmount.toString();
        }
        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));
            for (int i = 0; i < essentialinformation.size(); i++) {
                JSONObject repayment = (JSONObject) essentialinformation.get(i);
                if(set.contains(repayment.getString("accountNumber"))&&Arrays.asList(new String[]{"D1","R4"}).contains(repayment.getString("accountType"))){
                    totalAmount=totalAmount.add(repayment.getBigDecimal("loanAmount"));
                }else if(set.contains(repayment.getString("accountNumber"))&&Arrays.asList(new String[]{"R1"}).contains(repayment.getString("accountType"))){
                    totalAmount=totalAmount.add(repayment.getBigDecimal("creditLineOfAccount"));
                }
            }
        }
        return totalAmount;
    }
}
