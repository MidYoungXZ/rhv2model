package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @program configurescript-parent
 * @description: 最近24个月准贷记卡最高逾期期数
 * @author: liuyafei
 * @create: 2019/10/23 16:15
 */
public class SecdStandardCardOverdueNumLast24MonMax implements Function<JSONObject, Object>, RhParamKey {

    public static String resultKey = "secd_StandardCardOverdueNumLast24MonMax";
    static String accountType = "R3";

    @Override
    public String getParamKey() {
        return resultKey;
    }

    /**
     * 相差月份（四舍五入） * @param fromDate 开始日期 * @param toDate 截止日期 * @return 相差月份
     */
    private int diffDateMonths(String fromDate, String toDate) {


        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromLocalDate = LocalDate.parse(fromDate+"-01", formatter1);
        LocalDate toLocalDate = LocalDate.parse(toDate+"-01", formatter1);
        long diffDays = toLocalDate.toEpochDay() - fromLocalDate.toEpochDay();
        int months = (int) Math.round(diffDays / 30);
        return months;
    }

    /**
     * 1、当信贷交易信息明细（CreditDetail ）—》  基本信息段（Crt2_P_EssentialInformation） —》 账户类型（AccountType）=R3-准贷记卡账户时，
     * 取 信贷交易信息明细（CreditDetail ）—》最近24个月还款状态信息（Crt2_P_LastTwentyfourMonth） —》还款状态（RepaymentState） 最大数字（字母G/D/Z/B 按8计算）
     * 出现 N 或者其他符号类的*#/这种  都可以转成0
     *
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
        int num=lastTwentyfourMonth.size();
        for (int i = 0; i < num; i++) {
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


}
