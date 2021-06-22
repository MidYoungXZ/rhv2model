package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.function.Function;

/**
 * @program configurescript-parent
 * @description: 当月本人查询次数
 * @author: liuyafei
 * @create: 2019/10/23 19:05
 */
public class SecdQueryBySelfLastMonContSum implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_QueryBySelfLastMonContSum";


    @Override
    public String getParamKey() {
        return resultKey;
    }

    /**
     * 信息概要（InfoSummary）—》查询记录概要（Crt2_P_QueryRecordSummary）—》最近 1 个月内的查询次数（本人查询）（IqueryTimes）对应的数值
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
       int num=0;
        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_queryrecordsummary"))) {
            JSONArray queryrecordsummary = JSONObject.parseArray(jsonObject.getString("crt2_p_queryrecordsummary"));
            if(queryrecordsummary.size()>0){
                if(!StringUtils.isEmpty(queryrecordsummary.getJSONObject(0).getString("iqueryTimes"))){
                    num= queryrecordsummary.getJSONObject(0).getInteger("iqueryTimes");
                }
            }
        }
        return num;
    }



}
