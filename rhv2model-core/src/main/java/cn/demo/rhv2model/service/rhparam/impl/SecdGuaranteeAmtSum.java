package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * @program configurescript-parent
 * @description: 担保贷款总金额
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdGuaranteeAmtSum implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_GuaranteeAmtSum";

    @Override
    public String getParamKey() {
        return resultKey;
    }

    /**
     * 1、信贷交易信息明细（CreditDetail ）—》相关还款责任汇总信息（Crt2_P_TotalRelatedRepayment）—》还款责任限额（RepaymentLiabilityLimit）汇总
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        BigDecimal amount = BigDecimal.ZERO;
        if(StringUtils.isEmpty(jsonObject.getString("crt2_p_totalrelatedrepayment"))){
            return amount.toString();
        }

        JSONArray queryrecord = JSONObject.parseArray(jsonObject.getString("crt2_p_totalrelatedrepayment"));

        for(int i=0;i<queryrecord.size();i++){
            JSONObject jsonObject1=queryrecord.getJSONObject(i);
            if(!StringUtils.isEmpty(jsonObject1.getString("repaymentLiabilityLimit"))){
                amount=amount.add(jsonObject1.getBigDecimal("repaymentLiabilityLimit"));
            }
        }
        return amount.toString();
    }
}
