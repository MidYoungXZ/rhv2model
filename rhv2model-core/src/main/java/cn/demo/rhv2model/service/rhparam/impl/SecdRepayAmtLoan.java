package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @program configurescript-parent
 * @description: 信用/免担保贷款本月应还款
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdRepayAmtLoan implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_RepayAmtLoan";
    static String[] accountType = {"D1","R4","R1"};
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
                    &&"4".equals(account.getString("guaranteeMethod"))
                    &&"03".equals(account.getString("repaymentFrequency"))){
                set.add(account.getString("accountNumber"));
            }
        }
        return set;
    }


    public Set checkAccountType1(JSONObject jsonObject, String[] accountType) {
        Set set = new HashSet();
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            return set;
        }
        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if (Arrays.asList(accountType).contains(account.getString("accountType"))
                    &&"4".equals(account.getString("guaranteeMethod"))
                    &&!"03".equals(account.getString("repaymentFrequency"))){
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

    public static long getDaySub(String beginDateStr,String endDateStr)
    {
        long day=30;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            beginDateStr=beginDateStr.replace(".","-");
            endDateStr=endDateStr.replace(".","-");

            Date beginDate = format.parse(beginDateStr);
            Date endDate= format.parse(endDateStr);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e) {

        }
        return day;
    }
    /**
     * 1、当 信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》 账户类型（AccountType）分别等于D1-非循环贷账户、R4-循环额度下分账户、R1 -循环贷账户—》
     * 担保方式（GuaranteeMethod）为【4-信用/免担保】，还款频率（RepaymentFrequency）为【03-月】
     * and 信贷交易信息明细（CreditDetail ）—》最新表现信息（Crt2_P_TheLatestPerformance—》账户状态 （AccountState）不为【3-结清】
     * 取 信贷交易信息明细（CreditDetail ）—》 最近一次月度表现信息（Crt2_P_LastMonthly） —》本月应还款（Reimbursement）
     * 2、当 信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》 账户类型（AccountType）分别等于D1-非循环贷账户、R4-循环额度下分账户、R1 -循环贷账户—》
     * 担保方式（GuaranteeMethod）为【4-信用/免担保】，还款频率（RepaymentFrequency）不为【03-月】
     * and 信贷交易信息明细（CreditDetail ）—》最新表现信息（Crt2_P_TheLatestPerformance）—》账户状态 （AccountState）不为【3-结清】
     * 取 信贷交易信息明细（CreditDetail ）—》 基本信息段（Crt2_P_EssentialInformation） —》 借款金额（LoanAmount）/【（到期日期（ExpirationDate）-开立日期（IssuanceDate）)/30】计算得出本月应还款
     * 3、满足条件的本月应还款汇总
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {

        return getAmount2(jsonObject).add(getAmount1(jsonObject));
    }
    public BigDecimal getAmount2(JSONObject jsonObject){
        BigDecimal amount =BigDecimal.ZERO;
        Set set = checkAccountType1(jsonObject, accountType);
        if (set.isEmpty()) {
            return amount;
        }
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_thelatestperformance"))) {
            return amount;
        }

        JSONArray thelatestperformance = JSONObject.parseArray(jsonObject.getString("crt2_p_thelatestperformance"));
        Set account = new HashSet();
        for (int i = 0; i < thelatestperformance.size(); i++) {
            JSONObject repayment = (JSONObject) thelatestperformance.get(i);
            if(set.contains(repayment.getString("accountNumber"))&&!"3".equals(repayment.getString("accountState"))){
                account.add(repayment.getString("accountNumber"));
            }
        }
        if(account.isEmpty()){
            return amount;
        }

        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            JSONArray lastmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));
            for(int i=0;i<lastmonthly.size();i++){
                JSONObject jsonObject1 = lastmonthly.getJSONObject(i);
                if(account.contains(jsonObject1.getString("accountNumber"))&&!StringUtils.isEmpty(jsonObject1.getString("loanAmount"))){
                    BigDecimal iss = BigDecimal.valueOf(Math.round(getDaySub(jsonObject1.getString("issuanceDate"),jsonObject1.getString("expirationDate"))/30.0));
                    if(iss.equals(BigDecimal.ZERO)){
                        continue;
                    }
                    amount = amount.add(jsonObject1.getBigDecimal("loanAmount").divide(BigDecimal.valueOf(Math.round(getDaySub(jsonObject1.getString("issuanceDate"),jsonObject1.getString("expirationDate"))/30.0)),2, RoundingMode.HALF_UP));
                }
            }
        }
        return amount;
    }



    public BigDecimal getAmount1(JSONObject jsonObject){
        BigDecimal amount =BigDecimal.ZERO;
        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return amount;
        }
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_thelatestperformance"))) {
            return amount;
        }
        JSONArray thelatestperformance = JSONObject.parseArray(jsonObject.getString("crt2_p_thelatestperformance"));
        Set account = new HashSet();
        for (int i = 0; i < thelatestperformance.size(); i++) {
            JSONObject repayment = (JSONObject) thelatestperformance.get(i);
            if(set.contains(repayment.getString("accountNumber"))&&!"3".equals(repayment.getString("accountState"))){
                account.add(repayment.getString("accountNumber"));
            }
        }
        if(account.isEmpty()){
            return amount;
        }

        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_lastmonthly"))) {
            JSONArray lastmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));
            for(int i=0;i<lastmonthly.size();i++){
                JSONObject jsonObject1 = lastmonthly.getJSONObject(i);
                if(account.contains(jsonObject1.getString("accountNumber"))){
                    if(!StringUtils.isEmpty(jsonObject1.getString("reimbursement"))){

                        amount = amount.add(jsonObject1.getBigDecimal("reimbursement"));

                    }
                }
            }
        }
        return amount;
    }

    @Override
    public String getParamKey() {
        return resultKey;
    }
}
