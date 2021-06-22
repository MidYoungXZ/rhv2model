package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @program configurescript-parent
 * @description: 贷记卡近12月逾期总次数
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdCardOverdueLast12MonContSum implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_CardOverdueLast12MonContSum";
    static String accountType = "R2";

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
     * 1、信贷交易信息明细（CreditDetail ）—》 基本信息段（Crt2_P_EssentialInformation） —》 账户类型（AccountType）=R2-贷记卡账户
     * 2、信贷交易信息明细（CreditDetail ）—》最近24个月还款状态信息Crt2_P_LastTwentyfourMonth —》月份（month）取近12个月，还款状态（RepaymentState） G/D/B/Z及数字个数汇总
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
            if(set.contains(repayment.getString("accountNumber"))&&diffDateMonths(repayment.getString("month"),repayment.getString("endYear"))<=12){
                String state = repayment.getString("repaymentState");
                if ("GDZB".contains(state)) {
                    repaymentState += 1;
                }else if(isNumeric(state)){
                    repaymentState += 1;

                }
            }


        }

        return repaymentState;
    }
}
