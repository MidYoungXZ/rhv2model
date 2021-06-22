package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.function.Function;

/**
 * @program configurescript-parent
 * @description: 法院判决总数目
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdJudgementCount implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_JudgementCount";

    @Override
    public String getParamKey() {
        return resultKey;
    }

    /**
     * 1、公共信息明细(PublicInfo)—》强制执行记录（crt2_p_forceexecution）—》立案日期（RegisterDate）的记录数
     * 2、公共信息明细(PublicInfo)—》民事判决记录（crt2_p_civiljudgement）—》立案日期（RegisterDate）的记录数
     * 3、满足以上条件的记录汇总
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        int accountNum=0;
        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_forceexecution"))) {
            JSONArray astmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_forceexecution"));
            for(int i=0;i<astmonthly.size();i++){
                if(!StringUtils.isEmpty(astmonthly.getJSONObject(i).getString("registerDate"))){
                    accountNum+=1;
                }
            }

        }
        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_civiljudgement"))) {
            JSONArray astmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_civiljudgement"));
            for(int i=0;i<astmonthly.size();i++){
                if(!StringUtils.isEmpty(astmonthly.getJSONObject(i).getString("registerDate"))){
                    accountNum+=1;
                }
            }
        }
        return accountNum;
    }
}
