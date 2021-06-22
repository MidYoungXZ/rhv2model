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
 * @description: 近12个月查询次数（总）
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdQueryLast12MonContSum implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_QueryLast12MonContSum";

    @Override
    public String getParamKey() {
        return resultKey;
    }

    public static long getDaySub(String beginDateStr,String endDateStr)
    {
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = format.parse(beginDateStr);
            endDateStr=endDateStr.replace(".","-");
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
     * 1、查询记录（QueryRecord）—》 查询记录信息（Crt2_P_QueryRecord） —》查询日期（QueryDate）距离报告头（Crt2_P_Identification）—》报告日期（ReportCreateTime）<=12个月的查询次数
     * 2、时间统计口径（报告查询日期-查询日期）/30<=12
     * 注、相同日期且同一查询操作员的多次查询按一次计算，若同一天内非同一查询操作员的多次查询按实际计算
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
                if(getDaySub(queryDate,reportCreateTime)/30.0<=12){
                    queryNum.put(queryDate+jsonObject1.getString("inquiringOrganization"),"1");
                }
            }

        }
        return queryNum.size();
    }
}
