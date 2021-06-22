package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @program configurescript-parent
 * @description: 本月应还款额
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdRepayAmtSum implements Function<JSONObject, Object>, RhParamKey {
    static String resultKey="secd_RepayAmtSum";



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
     * 信用免担保贷款本月应还款+贷记卡本月应还款
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {


        Map<String, Object> amtCard = applyRepayAmtCard(jsonObject);




        BigDecimal amount = BigDecimal.ZERO;
        amount = amount.add(getAmount1(jsonObject));
        amount = amount.add(new BigDecimal(amtCard.get(resultKey).toString()));
        amount=amount.add(getAmount2(jsonObject));
        return amount.toString();
    }


    public BigDecimal getAmount2(JSONObject jsonObject){
        String[] accountType = {"D1","R4","R1"};
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
        String[] accountType = {"D1","R4","R1"};
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





    /**
     * 贷记卡本月应还款
     * @param jsonObject
     * @return
     */
    public Map<String, Object> applyRepayAmtCard(JSONObject jsonObject) {
        Map<String, Object> res = new HashMap<>();
        res.put(resultKey, "0");
        String[] accountType = {"R2"};
        Set set = checkAccountTypeRepayAmtCar(jsonObject, accountType);
        if (set.isEmpty()) {
            return res;
        }

        BigDecimal amount = BigDecimal.ZERO;
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

        res.put(resultKey, amount.toString());
        return res;
    }

    /**
     * 判断账户类型
     *
     * @param jsonObject
     * @param accountType
     * @return
     */
    public Set checkAccountTypeRepayAmtCar(JSONObject jsonObject, String[] accountType) {
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

    @Override
    public String getParamKey() {
        return resultKey;
    }
}
