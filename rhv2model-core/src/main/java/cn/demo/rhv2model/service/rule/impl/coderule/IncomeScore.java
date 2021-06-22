package cn.demo.rhv2model.service.rule.impl.coderule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public class IncomeScore implements CodeScoreRule<IncomeScore.IncomeCodeRuleParam> {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IncomeCodeRuleParam extends CodeRuleParam {

        //流通贷记卡最高授信额度
        private Integer normalCardLmtMax;

        //流通贷记卡平均额度
        private Integer normalCardAvgLmt;

        //个人住房贷款总合同金额
        private Integer housingLoanAmtSum;

        //个人汽车贷款总合同金额
        private Integer carLoanAmtSum;
    }

    @Override
    public IncomeCodeRuleParam getCodeRuleParam(Map<String, Object> facts) {
        Object normalCardLmtMax = facts.get("secd_NormalCardLmtMax");
        Object normalCardAvgLmt = facts.get("secd_NormalCardAvgLmt");
        Object housingLoanAmtSum = facts.get("secd_HousingLoanAmtSum");
        Object carLoanAmtSum = facts.get("secd_CarLoanAmtSum");

        return IncomeCodeRuleParam.builder()
                .normalCardLmtMax(normalCardLmtMax == null ? -9999 : Integer.parseInt(normalCardLmtMax.toString()))
                .normalCardAvgLmt(normalCardAvgLmt == null ? -9999 : ((Double) Double.parseDouble(normalCardAvgLmt.toString())).intValue())
                .housingLoanAmtSum(housingLoanAmtSum == null ? -9999 : Integer.parseInt(housingLoanAmtSum.toString()))
                .carLoanAmtSum(carLoanAmtSum == null ? -9999 : Integer.parseInt(carLoanAmtSum.toString()))
                .build();
    }

    @Override
    public Map<String, Object> getCodeRuleScore(IncomeCodeRuleParam codeRuleParam) {

        Map<String, Object> res = new HashMap<>();

        Integer scoreA = 0;
        if (codeRuleParam.getNormalCardLmtMax() > 2000) {
            Integer creditCardAvgLimit = codeRuleParam.getNormalCardAvgLmt();
            if (creditCardAvgLimit > 0 && creditCardAvgLimit <= 30000) {
                scoreA = creditCardAvgLimit * 33 / 100;
            } else if (creditCardAvgLimit > 30000 && creditCardAvgLimit <= 40000) {
                scoreA = 300 * 33 + (creditCardAvgLimit - 30000) * 14 / 100;
            } else if (creditCardAvgLimit > 40000 && creditCardAvgLimit <= 50000) {
                scoreA = 300 * 33 + 100 * 14 + (creditCardAvgLimit - 40000) * 12 / 100;
            } else if (creditCardAvgLimit > 50000 && creditCardAvgLimit <= 60000) {
                scoreA = 300 * 33 + 100 * 14 + 100 * 12 + (creditCardAvgLimit - 50000) * 10 / 100;
            } else if (creditCardAvgLimit > 60000 && creditCardAvgLimit <= 70000) {
                scoreA = 300 * 33 + 100 * 14 + 100 * 12 + 100 * 10 + (creditCardAvgLimit - 60000) * 8 / 100;
            } else if (creditCardAvgLimit > 70000) {
                scoreA = 300 * 33 + 100 * 14 + 100 * 12 + 100 * 10 + 100 * 8 + (creditCardAvgLimit - 70000) * 6 / 100;
            }
        }

        Integer scoreB = codeRuleParam.getHousingLoanAmtSum() * 2 / 100;
        Integer scoreC = codeRuleParam.getCarLoanAmtSum() * 20 / 100;

        res.put("rhGradeModel1", Math.max(Math.max(scoreA, scoreB), scoreC));

        return res;
    }
}
