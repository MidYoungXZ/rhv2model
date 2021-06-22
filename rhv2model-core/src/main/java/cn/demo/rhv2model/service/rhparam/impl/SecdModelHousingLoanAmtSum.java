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
 * @program configurescript
 * @description: 个人住房贷款合同金额
 * @author: liuyafei
 * @create: 2020/01/02 15:06
 */
public class SecdModelHousingLoanAmtSum implements Function<JSONObject,Object>, RhParamKey {
    static String resultKey="secd_model_HousingLoanAmtSum";
    /**
     * 判断账户类型
     *
     * @param
     * @return
     */
    public Set checkAccountType(JSONObject jsonObject) {
        Set set = new HashSet();
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            return set;
        }
        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if ("11".equals(account.getString("businessTypes"))) {
                set.add(account.getString("accountNumber"));
            }
        }

        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_thelatestperformance"))) {
            return new HashSet();
        }
        JSONArray thelatestperformance = JSONObject.parseArray(jsonObject.getString("crt2_p_thelatestperformance"));
        Set accoutSet = new HashSet();
        for (int i = 0; i < thelatestperformance.size(); i++) {
            JSONObject account = (JSONObject) thelatestperformance.get(i);
            if (!"3".equals(account.getString("accountState"))&&set.contains(account.getString("accountNumber"))) {
                accoutSet.add(account.getString("accountNumber"));
            }
        }

        return accoutSet;
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
     * 1、信贷交易信息明细（CreditDetail ）—》最新表现信息段（Crt2_P_TheLatestPerformance）—》账户状态 （AccountState） 非 3-结清
     * and 信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》业务种类 =11个人住房商业贷款
     * 2、当  信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户类型（AccountType）=D1-非循环贷账户或 R4-循环额度下分账户 时
     * 取 信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》借款金额（LoanAmount） 汇总
     * 3、当  信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户类型（AccountType）= R1-循环贷账户 时
     * 取 信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户授信额度（CreditLineOfAccount）  汇总
     * 4、所有满足条件的金额汇总
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        BigDecimal currentOverdueTotal = BigDecimal.ZERO;
        Set set = checkAccountType(jsonObject);
        if (set.isEmpty()) {
            return -999;
        }
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            return -999;
        }
        boolean bool=true;

        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));
        String[] accountType={"D1","R4"};
        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject repayment = (JSONObject) essentialinformation.get(i);
            if(set.contains(repayment.getString("accountNumber"))){
                if(Arrays.asList(accountType).contains(repayment.getString("accountType"))&&!StringUtils.isEmpty(repayment.getString("loanAmount"))){
                    currentOverdueTotal=currentOverdueTotal.add(repayment.getBigDecimal("loanAmount"));
                    bool=false;
                }else if("R1".equals(repayment.getString("accountType"))&&!StringUtils.isEmpty(repayment.getString("creditLineOfAccount"))){
                    currentOverdueTotal=currentOverdueTotal.add(repayment.getBigDecimal("creditLineOfAccount"));
                    bool=false;
                }
            }
        }
        if(bool){
            return -999;
        }
        return currentOverdueTotal.toString();
    }

    @Override
    public String getParamKey() {
        return resultKey;
    }
}