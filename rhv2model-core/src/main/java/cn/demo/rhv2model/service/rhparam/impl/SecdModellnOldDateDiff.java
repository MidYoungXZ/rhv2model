package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;

/**
 * CreateBy: Liurenquan
 * CreateDate: 2020/1/3
 */
public class SecdModellnOldDateDiff implements Function<JSONObject, Object> , RhParamKey {

    public static String resultKey = "secd_model_ln_old_datediff";

    static String[] accountType = {"R1","D1","R4"};


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
            if (Arrays.asList(accountType).contains(account.getString("accountType"))){
                if(!StringUtils.isEmpty(account.getString("issuanceDate"))){
                    set.add(account.getString("issuanceDate"));
                }
            }
        }
        return set;
    }

    @Override
    public Object apply(JSONObject jsonObject) {
        String result = "-999";

        String identification = jsonObject.getString("crt2_p_identification");
        if(StringUtils.isEmpty(identification)){
            return result;
        }

        String reportCreateTime = JSON.parseObject(identification).getString("reportCreateTime");
        if(StringUtils.isEmpty(reportCreateTime)){
            return result;
        }

        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return result;
        }
        String min = (String) Collections.min(set);
        return getDaySub(min,reportCreateTime)==0?result:String.valueOf(getDaySub(min,reportCreateTime));
    }

    public static long getDaySub(String beginDateStr,String endDateStr) {
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = format.parse(beginDateStr.replace(".","-"));
            endDateStr=endDateStr.replace(".","-");
            Date endDate= format.parse(endDateStr.substring(0,10));
            Calendar fromCalendar = Calendar.getInstance();
            fromCalendar.setTime(startDate);
            fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
            fromCalendar.set(Calendar.MINUTE, 0);
            fromCalendar.set(Calendar.SECOND, 0);
            fromCalendar.set(Calendar.MILLISECOND, 0);

            Calendar toCalendar = Calendar.getInstance();
            toCalendar.setTime(endDate);
            toCalendar.set(Calendar.HOUR_OF_DAY, 0);
            toCalendar.set(Calendar.MINUTE, 0);
            toCalendar.set(Calendar.SECOND, 0);
            toCalendar.set(Calendar.MILLISECOND, 0);

            return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    @Override
    public String getParamKey() {
        return resultKey;
    }
}
