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
 * @description: 最近24个月贷款最高逾期期数
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdLoanOverdueNumLast24MonMax implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_LoanOverdueNumLast24MonMax";
    static String[] accountType={"D1","R1","R4"};

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
     * 1、当 信贷交易信息明细（CreditDetail ）—》  基本信息段（Crt2_P_EssentialInformation） —》 账户类型（AccountType）分别等于D1-非循环贷账户、R1-循环贷账户、R4-循环额度下分账户时
     * 取 信贷交易信息明细（CreditDetail ）—》最近24个月还款状态信息（crt2_p_lasttwentyfourmonth） —》还款状态（RepaymentState） 最大数字（字母G/D/Z/B 按8计算）
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        int repaymentState = 0;

        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return repaymentState;
        }
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_lasttwentyfourmonth"))) {
            return repaymentState;
        }
        JSONArray lastTwentyfourMonth = JSONObject.parseArray(jsonObject.getString("crt2_p_lasttwentyfourmonth"));
        for (int i = 0; i < lastTwentyfourMonth.size(); i++) {
            JSONObject repayment = (JSONObject) lastTwentyfourMonth.get(i);
            if(set.contains(repayment.getString("accountNumber"))&&diffDateMonths(repayment.getString("month"),repayment.getString("endYear"))<=24){
                String state = repayment.getString("repaymentState");
                if ("GDZB".contains(state)) {
                    state = "8";
                } else if (!isNumeric(state)) {
                    state = "0";
                }
                if (Integer.valueOf(state) > repaymentState) {
                    repaymentState = Integer.valueOf(state);
                }
            }

        }

        return repaymentState;

    }
}
