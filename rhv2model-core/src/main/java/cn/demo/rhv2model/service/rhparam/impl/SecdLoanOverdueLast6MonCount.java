package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @program configurescript-parent
 * @description: 近6个月贷款逾期笔数
 * @author: liuyafei
 * @create: 2019/10/23 19:05
 */
public class SecdLoanOverdueLast6MonCount implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_LoanOverdueLast6MonCount";
    static String[] accountType = {"D1","R1","R4"};

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
    public Set checkAccountType(JSONObject jsonObject, String [] accountType) {
        Set set = new HashSet();
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            return set;
        }
        JSONArray essentialinformation = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        for (int i = 0; i < essentialinformation.size(); i++) {
            JSONObject account = (JSONObject) essentialinformation.get(i);
            if (Arrays.asList(accountType).contains(account.getString("accountType"))) {
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
     * 相差月份
     */
    private int diffDateMonths(String fromDate, String toDate) {
        String[] endDate = toDate.split("-");
        int endMonth = Integer.parseInt(endDate[0])*12+Integer.valueOf(endDate[1]);
        String[] startDate = fromDate.split("-");
        int startMonth = Integer.parseInt(startDate[0])*12+Integer.valueOf(startDate[1]);
        int numMonth = endMonth-startMonth+1;
        return numMonth;
    }


    /**
     * 1、当 信贷交易信息明细（CreditDetail ）—》  基本信息段（Crt2_P_EssentialInformation） —》
     * 账户类型（AccountType）分别等于D1-非循环贷账户、R1-循环贷账户、R4-循环额度下分账户时
     * 且最近24个月还款状态信息（Crt2_P_LastTwentyfourMonth） —》月份（Month）取近6个月
     * 2、取 信贷交易信息明细（CreditDetail ）—》最近24个月还款状态信息（Crt2_P_LastTwentyfourMonth） —》还款状态（RepaymentState） 包含数字及GDBZ的 账户数
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        Set accountNum = new HashSet();
        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return accountNum.size();
        }
        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_lasttwentyfourmonth"))) {
            JSONArray thelatestperformance = JSONObject.parseArray(jsonObject.getString("crt2_p_lasttwentyfourmonth"));
            for(int i=0;i<thelatestperformance.size();i++){
                JSONObject jsonObject1=(JSONObject)thelatestperformance.get(i);
                if(set.contains(jsonObject1.getString("accountNumber"))&&("GDBZ".contains(jsonObject1.getString("repaymentState"))||isNumeric(jsonObject1.getString("repaymentState")))&&diffDateMonths(jsonObject1.getString("month"),jsonObject1.getString("endYear"))<=6){
                    accountNum.add(jsonObject1.getString("accountNumber"));
                }
            }

        }

        return accountNum.size();
    }



}
