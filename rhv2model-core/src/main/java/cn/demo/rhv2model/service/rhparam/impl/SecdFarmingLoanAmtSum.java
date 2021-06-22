package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.function.Function;

/**
 * @program configurescript-parent
 * @description: 农户贷款总合同金额
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdFarmingLoanAmtSum implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_FarmingLoanAmtSum";

    @Override
    public String getParamKey() {
        return resultKey;
    }


    /**
     * 1、信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》业务种类（BusinessTypes） =51-农户贷款
     * 1-1、当  信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户类型（AccountType）=D1-非循环贷账户  或 R4-循环额度下分账户 时 取 借款金额 汇总
     * 1-2、当  信贷交易信息明细（CreditDetail ）—》基本信息段（Crt2_P_EssentialInformation） —》账户类型（AccountType）= R1-循环贷账户 时 取 账户授信额度（CreditLineOfAccount）  汇总
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        BigDecimal amount = BigDecimal.ZERO;

        if(StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))){
            return amount.toString();
        }

        JSONArray queryrecord = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));

        for(int i=0;i<queryrecord.size();i++){
            JSONObject jsonObject1=queryrecord.getJSONObject(i);
            if("51".equals(jsonObject1.getString("businessTypes"))){
                if(Arrays.asList(new String[]{"D1", "R4"}).contains(jsonObject1.getString("accountType"))){
                    amount=amount.add(jsonObject1.getBigDecimal("loanAmount"));
                }else if("R1".equals(jsonObject1.getString("accountType"))){
                    amount=amount.add(jsonObject1.getBigDecimal("creditLineOfAccount"));
                }

            }
        }
        return amount;
    }
}
