package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.function.Function;

/**
 * @program configurescript-parent
 * @description: 呆账信息笔数
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdBaddebtCount implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_BaddebtCount";

    @Override
    public String getParamKey() {
        return resultKey;
    }

    /**
     * 信息概要（InfoSummary）—》呆账汇总信息段（crt2_p_baddebt）—》账户数（AccountNumber）
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        int accountNum=0;
        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_baddebt"))) {
            JSONArray astmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_baddebt"));
            if(astmonthly.size()>0){
                if(!StringUtils.isEmpty(((JSONObject)astmonthly.get(0)).getString("accountNumber"))){
                    accountNum = Integer.valueOf(((JSONObject)astmonthly.get(0)).getString("accountNumber"));
                }
            }
        }
        return accountNum;
    }
}
