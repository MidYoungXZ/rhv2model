package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @program configurescript-parent
 * @description: 贷记卡当前逾期总额
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdCardOverdueAmtSum implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_CardOverdueAmtSum";
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
     * 当信贷交易信息明细（CreditDetail ）—》 基本信息段（Crt2_P_EssentialInformation） —》 账户类型（AccountType）==R2-贷记卡账户 时
     * 信贷交易信息明细（CreditDetail ）—》最近一次月度表现信息（crt2_p_lastmonthly） —》当前逾期总额（currentOverdueTotal）汇总
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        BigDecimal currentOverdueTotal =BigDecimal.ZERO;
        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return currentOverdueTotal.toString();
        }
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_lastmonthly"))) {
            return currentOverdueTotal.toString();
        }
        JSONArray lastTwentyfourMonth = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));

        for (int i = 0; i < lastTwentyfourMonth.size(); i++) {
            JSONObject repayment = (JSONObject) lastTwentyfourMonth.get(i);
            if(set.contains(repayment.getString("accountNumber"))&&!StringUtils.isEmpty(repayment.getString("currentOverdueTotal"))){
                currentOverdueTotal=currentOverdueTotal.add(repayment.getBigDecimal("currentOverdueTotal"));
            }
        }

        return currentOverdueTotal.toString();
    }
}
