package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.function.Function;

/**
 * @program configurescript-parent
 * @description: 保证人代偿笔数
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdAssureerRepayCount implements Function<JSONObject,Object>, RhParamKey {

    static String resultKey="secd_AssureerRepayCount";

    @Override
    public String getParamKey() {
        return resultKey;
    }

    /**
     * 信息概要（InfoSummary）—》被追偿汇总信息段（crt2_p_recourse）—》业务类型（BusinessType）=2-垫款业务—》账户数（AccountNumber）
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        int accountNum=0;
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_recourse"))) {
            return accountNum;
        }
        JSONArray astmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_recourse"));
        if(astmonthly.size()==0){
            return accountNum;
        }
        for(int i=0;i<astmonthly.size();i++){
            JSONObject jsonObject1 = (JSONObject)astmonthly.get(i);
            if("2".equals(jsonObject1.getString("businessType"))){
                if(!StringUtils.isEmpty(jsonObject1.getString("accountNumber"))){
                    accountNum=Integer.valueOf(jsonObject1.getString("accountNumber"));
                }
                break;
            }
        }
        return accountNum;
    }
}
