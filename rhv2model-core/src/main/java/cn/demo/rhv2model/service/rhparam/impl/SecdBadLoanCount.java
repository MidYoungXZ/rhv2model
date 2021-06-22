package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

/**
 * @program configurescript-parent
 * @description: 不良贷款账户数（所有贷款）
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdBadLoanCount implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_BadLoanCount";
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
     * 1、当信贷交易信息明细（CreditDetail ）—》  基本信息段（Crt2_P_EssentialInformation） —》 账户类型（AccountType）分别等于D1-非循环贷账户、R1-循环贷账户、R4-循环额度下分账户时，
     * 取 最近一次月度表现信息（crt2_p_lastmonthly）—》 五级分类（classification） =3-次级 or 4-可疑 or 5-损失 账户数
     *
     *
     * 1、当信贷交易信息明细（CreditDetail ）—》  基本信息段（Crt2_P_EssentialInformation） —》 账户类型（AccountType）分别等于D1-非循环贷账户、R1-循环贷账户、R4-循环额度下分账户时，
     * 取 最近一次月度表现信息（Crt2_P_LastMonthly）—》 五级分类（Classification） =3-次级 or 4-可疑 or 5-损失 账户数 或
     *
     * 最新表现信息（Crt2_P_TheLatestPerformance）—》五级分类（Classification）=3-次级 or 4-可疑 or 5-损失 的账户数
     * 满足条件的账户数汇总
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        int accountNum=0;
        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return accountNum;
        }
        Set account = new HashSet();
        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_lastmonthly"))) {
            String[] accountState={"3","4","5"};
            JSONArray astmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_lastmonthly"));
            for(int i=0;i<astmonthly.size();i++){

                JSONObject jsonObject1 = (JSONObject)astmonthly.get(i);
                if(set.contains(jsonObject1.getString("accountNumber"))&&Arrays.asList(accountState).contains(jsonObject1.getString("classification"))){
                    account.add(jsonObject1.getString("accountNumber"));
                }
            }
        }

        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_thelatestperformance"))) {
            String[] accountState={"3","4","5"};
            JSONArray astmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_thelatestperformance"));
            for(int i=0;i<astmonthly.size();i++){

                JSONObject jsonObject1 = (JSONObject)astmonthly.get(i);
                if(set.contains(jsonObject1.getString("accountNumber"))&&Arrays.asList(accountState).contains(jsonObject1.getString("classification"))){

                    account.add(jsonObject1.getString("accountNumber"));
                }
            }
        }

        return account.size();
    }
}
