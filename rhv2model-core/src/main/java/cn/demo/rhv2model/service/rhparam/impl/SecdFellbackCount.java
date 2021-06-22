package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.function.Function;

/**
 * @program configurescript-parent
 * @description: 违约信息数
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdFellbackCount implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_FellbackCount";

    @Override
    public String getParamKey() {
        return resultKey;
    }


    /**
     * 呆账信息笔数+资产处置信息笔数+保证人代偿笔数
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        int accountNum=0;

        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_baddebt"))) {
            JSONArray astmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_baddebt"));
            if(astmonthly.size()>0&&!StringUtils.isEmpty(((JSONObject)astmonthly.get(0)).getString("accountNumber"))){
                accountNum += Integer.valueOf(((JSONObject)astmonthly.get(0)).getString("accountNumber"));
            }
        }

        if (!StringUtils.isEmpty(jsonObject.getString("crt2_p_recourse"))) {
            JSONArray astmonthly = JSONObject.parseArray(jsonObject.getString("crt2_p_recourse"));
            for(int i=0;i<astmonthly.size();i++){
                JSONObject jsonObject1 = (JSONObject)astmonthly.get(i);
                if("1".equals(jsonObject1.getString("businessType"))||"2".equals(jsonObject1.getString("businessType"))){
                    if(!StringUtils.isEmpty(jsonObject1.getString("accountNumber"))){
                        accountNum+=Integer.valueOf(jsonObject1.getString("accountNumber"));
                    }
                }
            }
        }

        return accountNum;
    }
}
