package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @program configurescript-parent
 * @description: 首张贷记卡发放至今月数
 * @author: liuyafei
 * @create: 2019/10/23 19:24
 */
public class SecdCardFirstMonthNum implements Function<JSONObject, Object>, RhParamKey {

    static String resultKey="secd_CardFirstMonthNum";

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
            Date beginDate = format.parse(beginDateStr);
            Date endDate= format.parse(endDateStr);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    public static int compareToDay(String beginDateStr,String endDateStr){
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        try {
            Date beginDate = format.parse(beginDateStr);
            Date endDate= format.parse(endDateStr);
            return beginDate.compareTo(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 信贷交易信息明细（CreditDetail ）—》基本信息段（crt2_p_essentialinformation）—》账户类型为（AccountType）=【R2-贷记卡】
     * —》遍历开立日期（IssuanceDate），取最早开立日期距离报告头（Crt2_P_Identification）—》
     * 报告日期（ReportCreateTime）的月份差
     * 2、时间统计口径：（报告日期-最早开立日期）/30，结果四舍五入取整，当结果首张贷记卡发放至今月数=0或为开立日期缺失时，定义为征信白户
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {

        String defaultValue="-9999";

        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_identification"))) {
            return defaultValue;
        }
        String reportCreateTime = JSONObject.parseObject(jsonObject.getString("crt2_p_identification")).getString("reportCreateTime");
        if(StringUtils.isEmpty(reportCreateTime)){
            return defaultValue;
        }
        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_essentialinformation"))) {
            return defaultValue;
        }

        JSONArray queryrecord = JSONObject.parseArray(jsonObject.getString("crt2_p_essentialinformation"));
        Map<String,String> queryNum = new HashMap<>();
        String minDay = null;
        for(int i=0;i<queryrecord.size();i++){
            JSONObject jsonObject1=queryrecord.getJSONObject(i);
            if("R2".equals(jsonObject1.getString("accountType"))&& StringUtils.isNotBlank(jsonObject1.getString("issuanceDate"))){
                String queryDate = jsonObject1.getString("issuanceDate").replace(".","-");
                if(StringUtils.isEmpty(minDay)||compareToDay(queryDate,minDay)<0){
                    minDay=queryDate;
                }
            }
        }
        if(StringUtils.isEmpty(minDay)){
            return defaultValue;
        }

        return Math.round(getDaySub(minDay,reportCreateTime)/30.0);
    }
}
