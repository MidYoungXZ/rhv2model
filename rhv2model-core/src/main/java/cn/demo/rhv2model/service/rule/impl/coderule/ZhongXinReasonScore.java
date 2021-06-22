package cn.demo.rhv2model.service.rule.impl.coderule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public class ZhongXinReasonScore implements CodeScoreRule<ZhongXinReasonScore.ReasonRuleParam> {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReasonRuleParam extends CodeRuleParam {

        //贷记卡呆账\止付个数
        private Integer cardBaddebtCount;

        //贷款呆账个数
        private Integer loanBaddebtCount;

        //不良贷款账户数（所有贷款）
        private Integer badLoanCount;

        //准贷记卡呆账\止付个数
        private Integer standardCardBaddebtCount;

        //法院判决总数目
        private Integer judgementCount;

        //违约信息数
        private Integer fellbackCount;

        //最近24个月贷款最高逾期期数
        private Integer loanOverdueNumLast24MonMax;

        //最近24个月贷记卡最高逾期期数
        private Integer cardOverdueNumLast24MonMax;

        //最近24个月准贷记卡最高逾期期数
        private Integer standardCardOverdueNumLast24MonMax;

        //贷记卡近12月最高逾期期数
        private Integer cardOverdueNumLast12MonMax;

        //贷记卡近6月最高逾期期数
        private Integer cardOverdueNumLast6MonMax;

        //贷记卡近12月逾期总次数
        private Integer cardOverdueLast12MonContSum;

        //贷记卡近6月逾期总次数
        private Integer cardOverdueLast6MonContSum;

        //贷记卡当前逾期总额
        private Integer cardOverdueAmtSum;

        //准贷记卡透支未付总额
        private Integer standardCardOverdueAmt;

        //近3个月（贷款+贷记卡）审批查询次数
        private Integer queryForloanCardLast3monContSum;

        //农户贷款总合 同金额
        private Integer farmingLoanAmtSum;

        //担保贷款总金额
        private Integer guaranteeAmtSum;

        //贷款当前逾期总额
        private Integer loanOverdueAmtSum;


        private Integer secdCustomerLoanOverdueLast6MonContSum;

        private Integer secdCustomerLoanOverdueLast12MonContSum;

        private Integer secdLoanOverdueNumLast12MonMax;


        // 中间量，可删
        private Integer loanOverdueLast6MonCount;

        private Integer repayAmtSum;

        private Integer normalCardCount;

        private Integer baddebtCount;

        private Integer normalCardLmtSum;

        private Integer normalCardLmtUsed;

        private Integer queryForLoanLast3monCount;

        private Integer queryForCardLast3monCount;

        private Integer queryLast12MonContSum;

        private Integer queryBySelfLastMonContSum;

        private Double normalCardLmtUsedRate;

        private Integer assureerRepayCount;

        private Integer assetDispositionCount;

        private Integer queryForLoanLast6monContSum;



    }

    @Override
    public ReasonRuleParam getCodeRuleParam(Map<String, Object> facts) {
        Object cardBaddebtCount = facts.get("secd_CardBaddebtCount");
        Object loanBaddebtCount = facts.get("secd_LoanBaddebtCount");
        Object badLoanCount = facts.get("secd_BadLoanCount");
        Object standardCardBaddebtCount = facts.get("secd_StandardCardBaddebtCount");
        Object judgementCount = facts.get("secd_JudgementCount");
        Object fellbackCount = facts.get("secd_FellbackCount");
        Object loanOverdueNumLast24MonMax = facts.get("secd_LoanOverdueNumLast24MonMax");
        Object cardOverdueNumLast24MonMax = facts.get("secd_CardOverdueNumLast24MonMax");
        Object standardCardOverdueNumLast24MonMax = facts.get("secd_StandardCardOverdueNumLast24MonMax");
        Object cardOverdueNumLast12MonMax = facts.get("secd_CardOverdueNumLast12MonMax");
        Object cardOverdueNumLast6MonMax = facts.get("secd_CardOverdueNumLast6MonMax");
        Object cardOverdueLast12MonContSum = facts.get("secd_CardOverdueLast12MonContSum");
        Object cardOverdueLast6MonContSum = facts.get("secd_CardOverdueLast6MonContSum");
        Object cardOverdueAmtSum = facts.get("secd_CardOverdueAmtSum");
        Object standardCardOverdueAmt = facts.get("secd_StandardCardOverdueAmt");
        Object queryForloanCardLast3monContSum = facts.get("secd_QueryForloanCardLast3monContSum");
        Object farmingLoanAmtSum = facts.get("secd_FarmingLoanAmtSum");
        Object guaranteeAmtSum = facts.get("secd_GuaranteeAmtSum");
        Object loanOverdueAmtSum = facts.get("secd_LoanOverdueAmtSum");
        Object secdCustomerLoanOverdueLast6MonContSum = facts.get("Secd_CustomerLoanOverdueLast6MonContSum");
        Object secdCustomerLoanOverdueLast12MonContSum = facts.get("Secd_CustomerLoanOverdueLast12MonContSum");
        Object secdLoanOverdueNumLast12MonMax = facts.get("Secd_LoanOverdueNumLast12MonMax");

        Object loanOverdueLast6MonCount = facts.get("secd_LoanOverdueLast6MonCount");
        Object repayAmtSum = facts.get("secd_RepayAmtSum");
        Object normalCardCount = facts.get("secd_NormalCardCount");
        Object baddebtCount = facts.get("secd_BaddebtCount");
        Object normalCardLmtSum = facts.get("secd_NormalCardLmtSum");
        Object queryLast12MonContSum = facts.get("secd_QueryLast12MonContSum");
        Object normalCardLmtUsed = facts.get("secd_NormalCardLmtUsed");

        Object queryForLoanLast3monCount = facts.get("secd_QueryForLoanLast3monCount");
        Object queryForCardLast3monCount = facts.get("secd_QueryForCardLast3monCount");
        Object queryBySelfLastMonContSum = facts.get("secd_QueryBySelfLastMonContSum");
        Object normalCardLmtUsedRate = facts.get("secd_NormalCardLmtUsedRate");
        Object assureerRepayCount = facts.get("secd_AssureerRepayCount");
        Object assetDispositionCount = facts.get("secd_AssetDispositionCount");
        Object queryForLoanLast6monContSum = facts.get("secd_QueryForLoanLast6monContSum");




        return ReasonRuleParam.builder()
                .cardBaddebtCount(cardBaddebtCount == null ? -9999 : Integer.parseInt(cardBaddebtCount.toString()))
                .loanBaddebtCount(loanBaddebtCount == null ? -9999 : Integer.parseInt(loanBaddebtCount.toString()))
                .badLoanCount(badLoanCount == null ? -9999 : Integer.parseInt(badLoanCount.toString()))
                .standardCardBaddebtCount(standardCardBaddebtCount == null ? -9999 : Integer.parseInt(standardCardBaddebtCount.toString()))
                .judgementCount(judgementCount == null ? -9999 : Integer.parseInt(judgementCount.toString()))
                .fellbackCount(fellbackCount == null ? -9999 : Integer.parseInt(fellbackCount.toString()))
                .loanOverdueNumLast24MonMax(loanOverdueNumLast24MonMax == null ? -9999 : Integer.parseInt(loanOverdueNumLast24MonMax.toString()))
                .cardOverdueNumLast24MonMax(cardOverdueNumLast24MonMax == null ? -9999 : Integer.parseInt(cardOverdueNumLast24MonMax.toString()))
                .standardCardOverdueNumLast24MonMax(standardCardOverdueNumLast24MonMax == null ? -9999 : Integer.parseInt(standardCardOverdueNumLast24MonMax.toString()))
                .cardOverdueNumLast12MonMax(cardOverdueNumLast12MonMax == null ? -9999 : Integer.parseInt(cardOverdueNumLast12MonMax.toString()))
                .cardOverdueNumLast6MonMax(cardOverdueNumLast6MonMax == null ? -9999 : Integer.parseInt(cardOverdueNumLast6MonMax.toString()))
                .cardOverdueLast12MonContSum(cardOverdueLast12MonContSum == null ? -9999 : Integer.parseInt(cardOverdueLast12MonContSum.toString()))
                .cardOverdueLast6MonContSum(cardOverdueLast6MonContSum == null ? -9999 : Integer.parseInt(cardOverdueLast6MonContSum.toString()))
                .cardOverdueAmtSum(cardOverdueAmtSum == null ? -9999 : Integer.parseInt(cardOverdueAmtSum.toString()))
                .standardCardOverdueAmt(standardCardOverdueAmt == null ? -9999 : Integer.parseInt(standardCardOverdueAmt.toString()))
                .queryForloanCardLast3monContSum(queryForloanCardLast3monContSum == null ? -9999 : Integer.parseInt(queryForloanCardLast3monContSum.toString()))
                .farmingLoanAmtSum(farmingLoanAmtSum == null ? -9999 : Integer.parseInt(farmingLoanAmtSum.toString()))
                .guaranteeAmtSum(guaranteeAmtSum == null ? -9999 : Integer.parseInt(guaranteeAmtSum.toString()))
                .loanOverdueAmtSum(loanOverdueAmtSum == null ? -9999 : Integer.parseInt(loanOverdueAmtSum.toString()))
                .secdCustomerLoanOverdueLast6MonContSum(secdCustomerLoanOverdueLast6MonContSum == null ? -9999 : Integer.parseInt(secdCustomerLoanOverdueLast6MonContSum.toString()))
                .secdCustomerLoanOverdueLast12MonContSum(secdCustomerLoanOverdueLast12MonContSum == null ? -9999 : Integer.parseInt(secdCustomerLoanOverdueLast12MonContSum.toString()))
                .secdLoanOverdueNumLast12MonMax(secdLoanOverdueNumLast12MonMax == null? -9999 : Integer.parseInt(secdLoanOverdueNumLast12MonMax.toString()))
                .loanOverdueLast6MonCount(loanOverdueLast6MonCount == null ? -9999 :Integer.parseInt(loanOverdueLast6MonCount.toString()))
                .repayAmtSum(repayAmtSum == null ? -9999 :new Double(repayAmtSum.toString()).intValue())
                .normalCardCount(normalCardCount == null ? -9999 :Integer.parseInt(normalCardCount.toString()))
                .baddebtCount(baddebtCount == null ? -9999 :Integer.parseInt(baddebtCount.toString()))
                .normalCardLmtSum(normalCardLmtSum == null ? -9999 :Integer.parseInt(normalCardLmtSum.toString()))
                .normalCardLmtUsed(normalCardLmtUsed == null ? -9999 :Integer.parseInt(normalCardLmtUsed.toString()))
                .queryForLoanLast3monCount(queryForLoanLast3monCount == null ? -9999 :Integer.parseInt(queryForLoanLast3monCount.toString()))
                .queryForCardLast3monCount(queryForCardLast3monCount == null ? -9999 :Integer.parseInt(queryForCardLast3monCount.toString()))
                .queryLast12MonContSum(queryLast12MonContSum == null ? -9999 :Integer.parseInt(queryLast12MonContSum.toString()))
                .queryBySelfLastMonContSum(queryBySelfLastMonContSum == null ? -9999 :Integer.parseInt(queryBySelfLastMonContSum.toString()))
                .normalCardLmtUsedRate(normalCardLmtUsedRate == null ? -9999 :Double.parseDouble(normalCardLmtUsedRate.toString()))
                .assureerRepayCount(assureerRepayCount == null ? -9999 :Integer.parseInt(assureerRepayCount.toString()))
                .assetDispositionCount(assetDispositionCount == null ? -9999 :Integer.parseInt(assetDispositionCount.toString()))
                .queryForLoanLast6monContSum(queryForLoanLast6monContSum == null ? -9999 :Integer.parseInt(queryForLoanLast6monContSum.toString()))
                .build();
    }

    @Override
    public Map<String, Object> getCodeRuleScore(ReasonRuleParam codeRuleParam) {
        Map<String, Object> res = new HashMap<>();
        StringBuilder reasonScore = new StringBuilder();

        if (codeRuleParam.getCardBaddebtCount() > 0||codeRuleParam.getLoanBaddebtCount() > 0||codeRuleParam.getBadLoanCount() > 0||codeRuleParam.getStandardCardBaddebtCount() > 0) {
            reasonScore.append(",A1234");
        }


        if (codeRuleParam.getJudgementCount() > 0||codeRuleParam.getFellbackCount() > 0) {
            reasonScore.append(",A56");
        }



        if (codeRuleParam.getLoanOverdueNumLast24MonMax() > 7||codeRuleParam.getCardOverdueNumLast24MonMax() > 7||codeRuleParam.getStandardCardOverdueNumLast24MonMax() > 7) {
            reasonScore.append(",B123");
        }



        if (codeRuleParam.getCardOverdueNumLast12MonMax() > 2||codeRuleParam.getCardOverdueNumLast6MonMax()>1||codeRuleParam.getSecdLoanOverdueNumLast12MonMax() > 1) {
            reasonScore.append(",C127");
        }




        if (codeRuleParam.getCardOverdueLast12MonContSum() > 3||codeRuleParam.getCardOverdueLast6MonContSum() > 2||codeRuleParam.getSecdCustomerLoanOverdueLast6MonContSum() > 0||codeRuleParam.getSecdCustomerLoanOverdueLast12MonContSum() > 1) {
            reasonScore.append(",C3456");
        }





        if (codeRuleParam.getCardOverdueAmtSum() > 0||codeRuleParam.getStandardCardOverdueAmt() > 500||codeRuleParam.getLoanOverdueAmtSum() > 0) {
            reasonScore.append(",D123");
        }



        if (codeRuleParam.getQueryForloanCardLast3monContSum() > 12) {
            reasonScore.append(",E1");
        }

        if (codeRuleParam.getFarmingLoanAmtSum() > 300000) {
            reasonScore.append(",E2");
        }

        if (codeRuleParam.getGuaranteeAmtSum() > 500000) {
            reasonScore.append(",E3");
        }



        res.put("rigidRefuse", reasonScore.length() > 0 ? reasonScore.toString().substring(1) : "");

        return res;
    }
}
