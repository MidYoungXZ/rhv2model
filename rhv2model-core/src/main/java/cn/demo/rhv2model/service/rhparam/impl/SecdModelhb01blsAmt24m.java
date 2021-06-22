package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
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
 * @program configurescript
 * @description: 近24个月内贷款的额度使用率
 * @author: liuyafei
 * @create: 2020/01/02 15:54
 */
public class SecdModelhb01blsAmt24m implements Function<JSONObject,Object>, RhParamKey {
    static String resultKey="secd_model_hb_01_bls_amt_24m";
    static String[] accountType = {"R1","D1","R4"};
    /**
     * 判断账户类型
     *
     * @param jsonObject
     * @param accountType
     * @return
     */
    public Set checkAccountType(JSONObject jsonObject, String[] accountType) {
        Set set = new HashSet();
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_identification"))) {
            return set;
        }
        String reportCreateTime = JSONObject.parseObject(jsonObject.getString("crt2_p_identification")).getString("reportCreateTime");

        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            return set;
        }
        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if(StringUtils.isEmpty(account.getString("issuanceDate"))){
                continue;
            }
            String issuanceDate = account.getString("issuanceDate").replace(".","-");
            if (Arrays.asList(accountType).contains(account.getString("accountType"))&&Math.round(getDaySub(issuanceDate,reportCreateTime)/30.0)<=24) {
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
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            Date beginDate = format.parse(beginDateStr);
            endDateStr=endDateStr.replace(".","-");
            Date endDate= format.parse(endDateStr);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }
    /**
     * 1、信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation）—》账户类型为（AccountType）为【R1-循环贷账户、D1-非循环贷账户、R4-循环额度下分账户】几种—》遍历开立日期（IssuanceDate），取近24个月
     *  2、当  信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户类型（AccountType）=D1-非循环贷账户  或 R4-循环额度下分账户 时
     *  取 信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》借款金额（LoanAmount） 汇总
     *  3、当  信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户类型（AccountType）= R1-循环贷账户 时
     *  取 信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户授信额度（CreditLineOfAccount）  汇总
     *  4、所有满足条件1、2、3的额度、金额汇总
     *  5、满足信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation）—》账户类型为（AccountType）为【R1-循环贷账户、D1-非循环贷账户、R4-循环额度下分账户】几种—》遍历开立日期（IssuanceDate），满足近24个月的贷款。 取信贷交易信息明细（CreditDetail ）—》最新表现信息(Crt2_P_TheLatestPerformance) —》余额(Balance)汇总
     *  6、5计算的：余额(Balance)汇总／4计算的：(额度、金额汇总)
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        int repaymentState = -999;
        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return repaymentState;
        }

        BigDecimal creditAmount=BigDecimal.ZERO;
        JSONArray lastTwentyfourMonth = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));
        int num=lastTwentyfourMonth.size();
        String[] creditType={"D1","R4"};
        for (int i = 0; i < num; i++) {
            JSONObject repayment = (JSONObject) lastTwentyfourMonth.get(i);
            if(set.contains(repayment.getString("accountNumber"))){
                if(Arrays.asList(creditType).contains(repayment.getString("accountType"))&& StringUtils.isNotBlank(repayment.getString("loanAmount"))){
                    creditAmount=creditAmount.add(repayment.getBigDecimal("loanAmount"));
                }else if("R1".equals(repayment.getString("accountType"))&& StringUtils.isNotBlank(repayment.getString("creditLineOfAccount"))){
                    creditAmount=creditAmount.add(repayment.getBigDecimal("creditLineOfAccount"));
                }
            }
        }
        if(creditAmount.compareTo(BigDecimal.ZERO)==0){
            return repaymentState;
        }
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_thelatestperformance"))) {
            return repaymentState;
        }
        JSONArray thelatestperformance = JSONObject.parseArray(jsonObject.getString("crt2_p_thelatestperformance"));
        BigDecimal balance = BigDecimal.ZERO;
        for (int i = 0; i < num; i++) {
            try{
                JSONObject repayment = (JSONObject) thelatestperformance.get(i);
                if(set.contains(repayment.getString("accountNumber"))&&!StringUtils.isEmpty(repayment.getString("balance"))){
                    balance=balance.add(repayment.getBigDecimal("balance"));
                }
            }catch (Exception e){

            }

        }


        return balance.divide(creditAmount,4,BigDecimal.ROUND_HALF_DOWN).toString();
    }


    @Override
    public String getParamKey() {
        return resultKey;
    }
}
