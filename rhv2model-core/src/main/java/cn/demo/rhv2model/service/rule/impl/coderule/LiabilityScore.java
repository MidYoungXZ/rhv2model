package cn.demo.rhv2model.service.rule.impl.coderule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class LiabilityScore implements CodeScoreRule<LiabilityScore.LiabilityRuleParam> {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LiabilityRuleParam extends CodeRuleParam {

        //信用/免担保贷款本月应还款
        private Integer repayAmtLoan;

        //贷记卡本月应还款
        private Integer repayAmtCard;
    }

    @Override
    public LiabilityRuleParam getCodeRuleParam(Map<String, Object> facts) {
        Object repayAmtLoan = facts.get("secd_RepayAmtLoan");
        Object repayAmtCard = facts.get("secd_RepayAmtCard");

        return LiabilityRuleParam.builder()
                .repayAmtLoan(repayAmtLoan == null ? -9999 : new Double(repayAmtLoan.toString()).intValue())
                .repayAmtCard(repayAmtCard == null ? -9999 : new Double(repayAmtCard.toString()).intValue())
                .build();
    }

    @Override
    public Map<String, Object> getCodeRuleScore(LiabilityRuleParam codeRuleParam) {
        Map<String, Object> res = new HashMap<>();
        BigDecimal liability = new BigDecimal(
                codeRuleParam.getRepayAmtCard() + codeRuleParam.getRepayAmtLoan())
                .divide(new BigDecimal("10000"));

        BigDecimal liabilityScore = null;

        if (liability.compareTo(new BigDecimal(0)) <= 0) {
            liabilityScore = new BigDecimal(-999);
        } else if (liability.compareTo(new BigDecimal(50)) <= 0) {

            liability = liability.setScale(4, BigDecimal.ROUND_HALF_UP);

            //98/(ln(51)-ln(2))*(ln(现有负债评分+1)-ln(2))+1
            liabilityScore = new BigDecimal(98)
                    .multiply(new BigDecimal(Double.valueOf(Math.log(liability
                            .add(new BigDecimal(1)).doubleValue())).toString())
                            .subtract(new BigDecimal(Double.valueOf(Math.log(2)))))
                    .divide(new BigDecimal(Double.valueOf(Math.log(51)).toString())
                            .subtract(new BigDecimal(Double.valueOf(Math.log(2)))),4, BigDecimal.ROUND_HALF_UP)
                    .add(new BigDecimal(1));
        } else {
            liabilityScore = new BigDecimal(100);
        }

        res.put("rhGradeModel3", liabilityScore);
        return res;
    }
}
