package cn.demo.rhv2model.service.rhparam.impl;

import cn.demo.rhv2model.service.rhparam.RhParamKey;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

/**
 * @program configurescript
 * @description: 近3个月最高逾期期数
 * @author: liuyafei
 * @create: 2020/01/06 14:54
 */
public class SecdModelMaxLatestSortRnCnt3m implements Function<JSONObject,Object> , RhParamKey {
    static String resultKey="secd_model_max_latest_sort_rn_cnt_3m";
    static String[] accountType = {"R2"};
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
            if (Arrays.asList(accountType).contains(account.getString("accountType"))) {
                set.add(account.getString("accountNumber"));
            }
        }
        return set;
    }

    /**
     * 是否是否纯数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str) {
        Pattern pattern = compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    /**
     * 相差月份
     */
    private int diffDateMonths(String fromDate, String toDate) {
        String[] endDate = toDate.split("-");
        int endMonth = Integer.parseInt(endDate[0])*12+Integer.valueOf(endDate[1]);
        String[] startDate = fromDate.split("-");
        int startMonth = Integer.parseInt(startDate[0])*12+Integer.valueOf(startDate[1]);
        int numMonth = endMonth-startMonth+1;
        return numMonth;
    }
    /**
     * 1、当信贷交易信息明细（CreditDetail ）—》 基本信息段（Crt2_P_EssentialInformation） —》 账户类型（AccountType）为【R2-贷记卡账户】 时
     * 2、信贷交易信息明细(CreditDetail) —》最近24个月还款状态信息(Crt2_P_LastTwentyfourMonth)  —》还款状态(RepaymentState)
     * 3、计算衍生字段LATESTNSTATE_Sort_Rn(N为1、2、3)，定义【S(-N)】为取还款状态(RepaymentState)倒数第N位
     *      3.1、取还款状态(RepaymentState)倒数第3位【S(-3)】，若【S(-3)】在［1-9］范围内，则计算LATEST3STATE_Sort_Rn＝(13-3-【S(-3)】),若不在该范围内，则LATEST3STATE_Sort_Rn＝0
     *      3.2、取还款状态(RepaymentState)倒数第2位【S(-2)】，若【S(-2)】在［1-9］范围内，则计算LATEST2STATE_Sort_Rn＝(13-2-【S(-2)】),若不在该范围内，则LATEST2STATE_Sort_Rn＝0
     *      3.3、取还款状态(RepaymentState)倒数第1位【S(-1)】，若【S(-1)】在［1-9］范围内，则计算LATEST1STATE_Sort_Rn＝(13-1-【S(-1)】),若不在该范围内，则LATEST1STATE_Sort_Rn＝0
     * 4、计算max_LATEST_Sort_Rn_cnt：对所有LATESTNSTATE_Sort_Rn(N为1、2、3)做分组聚合，统计每个LATESTNSTATE_Sort_Rn(N为1、2、3)出现的计数
     * 5、计算secd_max_latest_sort_rn_cnt_3m：取max_LATEST_Sort_Rn_cnt的最大值
     * @param jsonObject
     * @return
     */
    @Override
    public Object apply(JSONObject jsonObject) {
        int repaymentState = -999;
        Set set = checkAccountType(jsonObject, accountType);
        if (set.isEmpty()) {
            return repaymentState;
        }

        if (StringUtils.isEmpty(jsonObject.getString("crt2_p_lasttwentyfourmonth"))) {
            return repaymentState;
        }
        JSONArray thelatestperformance = JSONObject.parseArray(jsonObject.getString("crt2_p_lasttwentyfourmonth"));
        Map<String,Integer> latestnstate=new HashMap<>();
        for (int i = 0; i < thelatestperformance.size(); i++) {
            JSONObject repayment = (JSONObject) thelatestperformance.get(i);
            if(StringUtils.isEmpty(repayment.getString("endYear"))){
                continue;
            }
            int month =diffDateMonths(repayment.getString("month"),repayment.getString("endYear"));
            if(set.contains(repayment.getString("accountNumber"))&&month<=3){
                Integer stateValue=latest2StateSortRn(month,repayment.getString("repaymentState"));
                if(stateValue==null){
                    continue;
                }
                String stateKey =repayment.getString("accountNumber")+"|"+stateValue;
                Integer defaultValue=latestnstate.get(stateKey);
                if(defaultValue==null){
                    defaultValue=1;
                }else{
                    defaultValue+=1;
                }
                latestnstate.put(stateKey,defaultValue);

            }
        }
        if(latestnstate.isEmpty()){
            return repaymentState;
        }
        for(String key : latestnstate.keySet()){
            if(repaymentState<latestnstate.get(key)){
                repaymentState=latestnstate.get(key);
            }
        }
        return repaymentState;
    }

    private Integer latest2StateSortRn(Integer month,String repaymentState){
       int state=0;
        if ("GDZB".contains(repaymentState)) {
            state = 8;
        } else if (!isNumeric(repaymentState)) {
            state = 0;
        }else{
            state=Integer.valueOf(repaymentState);
        }
        if(state==0){
            return null;
        }
        return 13-month-state;




    }


    @Override
    public String getParamKey() {
        return resultKey;
    }
}
