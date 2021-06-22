package cn.demo.rhv2model.service.rule.impl.coderule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class NanjingEmptyReportScore implements CodeScoreRule<NanjingEmptyReportScore.EmptyReportRuleParam> {

    private Map<String, Integer> emptyReportScoreMap;

    public NanjingEmptyReportScore() {
        emptyReportScoreMap = new HashMap<>();
        emptyReportScoreMap.put("V1_H1", 10);
        emptyReportScoreMap.put("V1_H2", 20);
        emptyReportScoreMap.put("V1_H3", 30);
        emptyReportScoreMap.put("V1_H4", 40);

        emptyReportScoreMap.put("V2_H1", 50);
        emptyReportScoreMap.put("V2_H2", 60);
        emptyReportScoreMap.put("V2_H3", 70);
        emptyReportScoreMap.put("V2_H4", 80);

        emptyReportScoreMap.put("V3_H1", 90);
        emptyReportScoreMap.put("V3_H2", 100);
        emptyReportScoreMap.put("V3_H3", 110);
        emptyReportScoreMap.put("V3_H4", 120);

        emptyReportScoreMap.put("V4_H1", 130);
        emptyReportScoreMap.put("V4_H2", 140);
        emptyReportScoreMap.put("V4_H3", 150);
        emptyReportScoreMap.put("V4_H4", 160);

        emptyReportScoreMap.put("V5_H1", 170);
        emptyReportScoreMap.put("V5_H2", 180);
        emptyReportScoreMap.put("V5_H3", 190);
        emptyReportScoreMap.put("V5_H4", 200);

        emptyReportScoreMap.put("V6_H1", 210);
        emptyReportScoreMap.put("V6_H2", 220);
        emptyReportScoreMap.put("V6_H3", 230);
        emptyReportScoreMap.put("V6_H4", 240);

        emptyReportScoreMap.put("V7_H1", 250);
        emptyReportScoreMap.put("V7_H2", 260);
        emptyReportScoreMap.put("V7_H3", 270);
        emptyReportScoreMap.put("V7_H4", 280);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmptyReportRuleParam extends CodeRuleParam {

        //信用/免担保贷款本月应还款
        private Integer housingLoanAmtSum;

        //贷记卡本月应还款
        private Integer queryForCardLast12monContSum;
    }

    @Override
    public EmptyReportRuleParam getCodeRuleParam(Map<String, Object> facts) {

        Object housingLoanAmtSum = facts.get("secd_HousingLoanAmtSum");
        Object queryForCardLast12monContSum = facts.get("secd_QueryForCardLast12monContSum");

        return EmptyReportRuleParam.builder()
                .housingLoanAmtSum(housingLoanAmtSum == null ? -9999 : Integer.parseInt(housingLoanAmtSum.toString()))
                .queryForCardLast12monContSum(queryForCardLast12monContSum == null ? -9999 : Integer.parseInt(queryForCardLast12monContSum.toString()))
                .build();
    }

    @Override
    public Map<String, Object> getCodeRuleScore(EmptyReportRuleParam codeRuleParam) {
        Map<String, Object> res = new HashMap<>();
        String vStr;
        if (codeRuleParam.getQueryForCardLast12monContSum() <= 0) {
            vStr = "V1";
        } else if (codeRuleParam.getQueryForCardLast12monContSum() <= 2) {
            vStr = "V2";
        } else if (codeRuleParam.getQueryForCardLast12monContSum() <= 4) {
            vStr = "V3";
        } else if (codeRuleParam.getQueryForCardLast12monContSum() <= 6) {
            vStr = "V4";
        } else if (codeRuleParam.getQueryForCardLast12monContSum() <= 8) {
            vStr = "V5";
        } else if (codeRuleParam.getQueryForCardLast12monContSum() <= 10) {
            vStr = "V6";
        } else {
            vStr = "V7";
        }

        String hStr;
        if (codeRuleParam.getHousingLoanAmtSum() <= 0) {
            hStr = "H1";
        } else if (codeRuleParam.getHousingLoanAmtSum() <= 250000) {
            hStr = "H2";
        } else if (codeRuleParam.getHousingLoanAmtSum() <= 600000) {
            hStr = "H3";
        } else {
            hStr = "H4";
        }
        BigDecimal returnScore = null;
        int score = emptyReportScoreMap.get(vStr + "_" + hStr);
        if(score<=0){
            returnScore=new BigDecimal(-999); ;
        }else{
            returnScore=new BigDecimal(98)
                    .multiply(new BigDecimal(Double.valueOf(Math.log((new BigDecimal(score))
                            .add(new BigDecimal(1)).doubleValue())).toString())
                            .subtract(new BigDecimal(Double.valueOf(Math.log(2)))))
                    .divide(new BigDecimal(Double.valueOf(Math.log(51)).toString())
                            .subtract(new BigDecimal(Double.valueOf(Math.log(2)))),4, BigDecimal.ROUND_HALF_UP)
                    .add(new BigDecimal(1));;
        }
        res.put("rhGradeModel4", returnScore);
        return res;
    }
}
