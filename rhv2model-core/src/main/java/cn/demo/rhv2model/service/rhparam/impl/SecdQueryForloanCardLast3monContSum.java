package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @program configurescript-parent
 * @description: 近3个月（贷款+贷记卡）审批查询次数
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdQueryForloanCardLast3monContSum implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_QueryForloanCardLast3monContSum";

    @Override
    public String getParamKey() {
        return resultKey;
    }

    public static long getDaySub(String beginDateStr,String endDateStr)
    {
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            endDateStr=endDateStr.replace(".","-");
            Date startDate = format.parse(beginDateStr);
            Date endDate= format.parse(endDateStr);
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

    /**
     * 近3个月贷款审批查询次数+近3个月贷记卡审批查询次数
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        Map<String,String> queryNum = new HashMap<>();



        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_identification"))) {
            return queryNum.size();
        }
        String reportCreateTime = JSONObject.parseObject(jsonObject.getString("crt2_p_identification")).getString("reportCreateTime");
        if(StringUtils.isEmpty(reportCreateTime)){
            return queryNum.size();
        }
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_queryrecord"))) {
            return queryNum.size();
        }

        JSONArray queryrecord = JSONObject.parseArray(jsonObject.getString("crt2_p_queryrecord"));
        for(int i=0;i<queryrecord.size();i++){
            JSONObject jsonObject1=queryrecord.getJSONObject(i);
            if(!StringUtils.isEmpty(jsonObject1.getString("queryDate"))){
                String queryDate = jsonObject1.getString("queryDate").replace(".","-");
                if(("03".equals(jsonObject1.getString("inquiryCause"))||"02".equals(jsonObject1.getString("inquiryCause")))&&getDaySub(queryDate,reportCreateTime)/30.0<=3){
                    queryNum.put(queryDate+jsonObject1.getString("inquiringOrganization")+jsonObject1.getString("inquiryCause"),"1");
                }
            }

        }
        return queryNum.size();
    }
}
