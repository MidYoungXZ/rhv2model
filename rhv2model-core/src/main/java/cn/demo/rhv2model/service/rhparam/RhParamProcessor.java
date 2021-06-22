package cn.demo.rhv2model.service.rhparam;

import cn.demo.rhv2model.service.rhparam.impl.*;
import cn.yxzmmm.rhv2model.service.rhparam.impl.*;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class RhParamProcessor {

    private List<Function<JSONObject, Object>> paramFuns;

    public RhParamProcessor() {
        paramFuns = new ArrayList<>();
        paramFuns.add(new SecdAssetDispositionCount());
        paramFuns.add(new SecdAssureerRepayCount());
        paramFuns.add(new SecdBaddebtCount());
        paramFuns.add(new SecdBadLoanCount());
        paramFuns.add(new SecdCardBaddebtCount());
        paramFuns.add(new SecdCardFirstMonthNum());
        paramFuns.add(new SecdCardOverdueAmtSum());
        paramFuns.add(new SecdCardOverdueLast6MonContSum());
        paramFuns.add(new SecdCardOverdueLast12MonContSum());
        paramFuns.add(new SecdCardOverdueNumLast6MonMax());
        paramFuns.add(new SecdCardOverdueNumLast12MonMax());
        paramFuns.add(new SecdCardOverdueNumLast24MonMax());
        paramFuns.add(new SecdCarLoanAmtSum());
        paramFuns.add(new SecdFarmingLoanAmtSum());
        paramFuns.add(new SecdFellbackCount());
        paramFuns.add(new SecdGuaranteeAmtSum());
        paramFuns.add(new SecdHousingLoanAmtSum());
        paramFuns.add(new SecdJudgementCount());
        paramFuns.add(new SecdLoanBaddebtCount());
        paramFuns.add(new SecdLoanOverdueAmtSum());
        paramFuns.add(new SecdLoanOverdueLast6MonCount());
        paramFuns.add(new SecdLoanOverdueNumLast24MonMax());
        paramFuns.add(new SecdNormalCardAvgLmt());
        paramFuns.add(new SecdNormalCardCount());
        paramFuns.add(new SecdNormalCardLmtMax());
        paramFuns.add(new SecdNormalCardLmtSum());
        paramFuns.add(new SecdNormalCardLmtUsed());
        paramFuns.add(new SecdNormalCardLmtUsedRate());
        paramFuns.add(new SecdQueryBySelfLastMonContSum());
        paramFuns.add(new SecdQueryForCardLast3monCount());
        paramFuns.add(new SecdQueryForCardLast12monContSum());
        paramFuns.add(new SecdQueryForloanCardLast3monContSum());
        paramFuns.add(new SecdQueryForLoanLast3monCount());
        paramFuns.add(new SecdQueryForLoanLast6monContSum());
        paramFuns.add(new SecdQueryLast12MonContSum());
        paramFuns.add(new SecdRepayAmtCard());
        paramFuns.add(new SecdRepayAmtLoan());
        paramFuns.add(new SecdRepayAmtSum());
        paramFuns.add(new SecdStandardCardBaddebtCount());
        paramFuns.add(new SecdStandardCardOverdueAmt());
        paramFuns.add(new SecdStandardCardOverdueNumLast24MonMax());

        paramFuns.add(new SecdCustomerLoanOverdueLast6MonContSum());
        paramFuns.add(new SecdCustomerLoanOverdueLast12MonContSum());
        paramFuns.add(new SecdLoanOverdueNumLast12MonMax());
        paramFuns.add(new SecdModelAcademic());
        paramFuns.add(new SecdModelAge());
        paramFuns.add(new SecdModelCardLmtNoClose());
        paramFuns.add(new SecdModelCardLmtUsedRateNoClose());
        paramFuns.add(new SecdModelCardOverdueAmtSum());
        paramFuns.add(new SecdModelGender());
        paramFuns.add(new SecdModelhb01blsAmt24m());
        paramFuns.add(new SecdModelHb01Industry());
        paramFuns.add(new SecdModelHb01MaritalState());
        paramFuns.add(new SecdModelHb01RegisteredAddress());
        paramFuns.add(new SecdModelHousingLoanAmtSum());
        paramFuns.add(new SecdModelLastXiaoJinLoanDays());
        paramFuns.add(new SecdModellnOldDateDiff());
        paramFuns.add(new SecdModelLoanSumMaxDuration());
        paramFuns.add(new SecdModelMaxCreditLimitAmountNoClose());
        paramFuns.add(new SecdModelMaxLatestSortRnCnt3m());
        paramFuns.add(new SecdModelMaxLatestSortRnCnt12m());
        paramFuns.add(new SecdModelMaxLoanCurrOverdueAmount());
        paramFuns.add(new SecdModelUsedCreditLimitAmountRatioNoClosed());
    }

    public Map<String, Object> getRhParam(JSONObject rhJson) {
        Map<String, Object> res = new HashMap<>();

        for (Function<JSONObject, Object> paramFun : paramFuns) {
            RhParamKey paramKey = (RhParamKey) paramFun;

            res.put(paramKey.getParamKey(), paramFun.apply(rhJson));
        }

        return res;
    }
}
