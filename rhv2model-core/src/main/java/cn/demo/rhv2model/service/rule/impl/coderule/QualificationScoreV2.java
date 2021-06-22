package cn.demo.rhv2model.service.rule.impl.coderule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class QualificationScoreV2 implements CodeScoreRule<QualificationScoreV2.QualificationRuleParam> {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QualificationRuleParam extends CodeRuleParam {

        //查无人行
        private boolean hasRhReport;

        //首张贷记卡发放至今月数
        private Integer cardFirstMonthNum;

        //个人住房贷款合同金额
        private String secdModelHousingLoanAmtSum;

        //近24个月内贷款的额度使用率
        private String secdModelHb01BlsAmt24m;

        // 贷记卡当前逾期金额
        private String secdModelCardOverdueAmtSum;

        // 当前最大贷款逾期金额
        private String secdModelMaxLoanCurrOverdueAmount;

        // 贷款逾期最长逾期月数
        private String secdModelLoanSumMaxDuration;

        // 近3个月最高逾期期数
        private String secdModelMaxLatestSortRnCnt3m;

        // 近12个月最高逾期期数
        private String secdModelMaxLatestSortRnCnt12m;

        // 未销户的贷记卡总额度使用率
        private String secdModelUsedCreditLimitAmountRatioNoClosed;

        // 未销户贷记卡最大额度
        private String secdModelMaxCreditLimitAmountNoClose;

        // 最近一次消费金融公司贷款距今时长
        private String secdModelLastXiaojinLoanDays;

        // 最早贷款发放距今天数
        private String secdModelLnOldDateDiff;
        // 人行报告学历
        private String secdModelAcademic;

        // 人行报告婚姻状况
        private String secdModelHb01Maritalstate;

        // 人行身份证号解析年龄
        private String secdModelAge;

        // 人行身份证号解析性别
        private String secdModelGender;

        // 人行报告户籍地址
        private String secdModelHb01RegisteredAddress;

        // 未销户的贷记卡总额度
        private String secdModelCardLmtNoClose;

        // 未销户的贷记卡已用额度
        private String secdModelCardLmtUsedRateNoClose;

    }

    @Override
    public QualificationRuleParam getCodeRuleParam(Map<String, Object> facts) {
        Object hasRhReport = facts.get("HasRhReport");
        Object cardFirstMonthNum = facts.get("secd_CardFirstMonthNum");

        Object secdModelHousingLoanAmtSum = facts.get("secd_model_HousingLoanAmtSum");
        Object secdModelHb01BlsAmt24m = facts.get("secd_model_hb_01_bls_amt_24m");
        Object secdModelCardOverdueAmtSum = facts.get("secd_model_CardOverdueAmtSum");
        Object secdModelMaxLoanCurrOverdueAmount = facts.get("secd_model_max_loan_curroverdueamount");
        Object secdModelLoanSumMaxDuration = facts.get("secd_model_loansummaxduration");
        Object secdModelMaxLatestSortRnCnt3m = facts.get("secd_model_max_latest_sort_rn_cnt_3m");
        Object secdModelMaxLatestSortRnCnt12m = facts.get("secd_model_max_latest_sort_rn_cnt_12m");
        Object secdModelUsedCreditLimitAmountRatioNoClosed = facts.get("secd_model_usedcreditlimitamount_ratio_no_closed");
        Object secdModelMaxCreditLimitAmountNoClose = facts.get("secd_model_max_creditlimitamount_no_close");
        Object secdModelLastXiaojinLoanDays = facts.get("secd_model_last_xiaojin_loan_days");
        Object secdModelLnOldDateDiff = facts.get("secd_model_ln_old_datediff");
        Object secdModelAcademic = facts.get("secd_model_academic");
        Object secdModelHb01Maritalstate = facts.get("secd_model_hb_01_maritalstate");
        Object secdModelAge = facts.get("secd_model_age");
        Object secdModelGender = facts.get("secd_model_gender");
        Object secdModelHb01RegisteredAddress = facts.get("secd_model_hb_01_registeredaddress");
        Object secdModelCardLmtNoClose = facts.get("secd_model_CardLmtNoClose");
        Object secdModelCardLmtUsedRateNoClose = facts.get("secd_model_CardLmtUsedRateNoClose");

        return QualificationRuleParam.builder()
                .hasRhReport(hasRhReport == null ? true : Boolean.valueOf(hasRhReport.toString()))
                .cardFirstMonthNum(cardFirstMonthNum == null ? -999 : Integer.parseInt(cardFirstMonthNum.toString()))
                .secdModelHousingLoanAmtSum(secdModelHousingLoanAmtSum == null ? "-999" : secdModelHousingLoanAmtSum.toString())
                .secdModelHb01BlsAmt24m(secdModelHb01BlsAmt24m == null ? "-999" : secdModelHb01BlsAmt24m.toString())
                .secdModelCardOverdueAmtSum(secdModelCardOverdueAmtSum == null ? "-999" : secdModelCardOverdueAmtSum.toString())
                .secdModelMaxLoanCurrOverdueAmount(secdModelMaxLoanCurrOverdueAmount == null ? "-999" : secdModelMaxLoanCurrOverdueAmount.toString())
                .secdModelLoanSumMaxDuration(secdModelLoanSumMaxDuration == null ? "-999" : secdModelLoanSumMaxDuration.toString())
                .secdModelMaxLatestSortRnCnt3m(secdModelMaxLatestSortRnCnt3m == null ? "-999" : secdModelMaxLatestSortRnCnt3m.toString())
                .secdModelMaxLatestSortRnCnt12m(secdModelMaxLatestSortRnCnt12m == null ? "-999" : secdModelMaxLatestSortRnCnt12m.toString())
                .secdModelUsedCreditLimitAmountRatioNoClosed(secdModelUsedCreditLimitAmountRatioNoClosed == null ? "-999" : secdModelUsedCreditLimitAmountRatioNoClosed.toString())
                .secdModelMaxCreditLimitAmountNoClose(secdModelMaxCreditLimitAmountNoClose == null ? "-999" : secdModelMaxCreditLimitAmountNoClose.toString())
                .secdModelLastXiaojinLoanDays(secdModelLastXiaojinLoanDays == null ? "-999" : secdModelLastXiaojinLoanDays.toString())
                .secdModelLnOldDateDiff(secdModelLnOldDateDiff == null ? "-999" : secdModelLnOldDateDiff.toString())
                .secdModelAcademic(secdModelAcademic == null ? "-999" : secdModelAcademic.toString())
                .secdModelHb01Maritalstate(secdModelHb01Maritalstate == null ? "-999" : secdModelHb01Maritalstate.toString())
                .secdModelAge(secdModelAge == null ? "-999" : secdModelAge.toString())
                .secdModelGender(secdModelGender == null ? "-999" : secdModelGender.toString())
                .secdModelHb01RegisteredAddress(secdModelHb01RegisteredAddress == null ? "-999":secdModelHb01RegisteredAddress.toString())
                .secdModelCardLmtNoClose(secdModelCardLmtNoClose == null ? "-999" : secdModelCardLmtNoClose.toString())
                .secdModelCardLmtUsedRateNoClose(secdModelCardLmtUsedRateNoClose == null ? "-999" : secdModelCardLmtUsedRateNoClose.toString())
                .build();
    }

    @Override
    public Map<String, Object> getCodeRuleScore(QualificationRuleParam codeRuleParam) {
        Map<String, Object> res = new HashMap<>();

        BigDecimal qualificationScore = null;


        if (!codeRuleParam.hasRhReport) {
            qualificationScore = new BigDecimal("-997");
            res.put("rhGradeModel2", qualificationScore);
        } else if (codeRuleParam.getCardFirstMonthNum() <= 0) {
            qualificationScore = getRhModelV2Score(codeRuleParam);
            res.put("rhGradeModel2",getExpValue(qualificationScore).add(new BigDecimal(2)));
        } else {
            qualificationScore = getRhModelV2Score(codeRuleParam);
            res.put("rhGradeModel2", getExpValue(qualificationScore));
        }
        return res;

    }


    private BigDecimal getRhModelV2Score(QualificationRuleParam codeRuleParam){

        BigDecimal secdModelHousingLoanAmtSum = null;
        Integer housingLoanAmtSum = Integer.valueOf(codeRuleParam.getSecdModelHousingLoanAmtSum());
        if(housingLoanAmtSum <= -1){
            secdModelHousingLoanAmtSum = new BigDecimal("-0.18218776").multiply(new BigDecimal("-0.78502182"));
        }else if(housingLoanAmtSum<=0){
            secdModelHousingLoanAmtSum = new BigDecimal("-0.04264729").multiply(new BigDecimal("-0.78502182"));
        }else if(housingLoanAmtSum<=360000){
            secdModelHousingLoanAmtSum = new BigDecimal("0.23148071").multiply(new BigDecimal("-0.78502182"));
        }else{
            secdModelHousingLoanAmtSum = new BigDecimal("0.3724467").multiply(new BigDecimal("-0.78502182"));
        }

        BigDecimal secdModelHb01BlsAmt24m = null;
        Double  secdModelHb01BlsAmt24mum = Double.valueOf(codeRuleParam.getSecdModelHb01BlsAmt24m());
        if(secdModelHb01BlsAmt24mum <= -999){
            secdModelHb01BlsAmt24m = new BigDecimal("-0.209121808").multiply(new BigDecimal("-0.34660963"));
        }else if(secdModelHb01BlsAmt24mum <= 0.5){
            secdModelHb01BlsAmt24m = new BigDecimal("0.295101089").multiply(new BigDecimal("-0.34660963"));
        }else if(secdModelHb01BlsAmt24mum <= 0.7){
            secdModelHb01BlsAmt24m = new BigDecimal("0.232410617").multiply(new BigDecimal("-0.34660963"));
        }else if(secdModelHb01BlsAmt24mum <= 0.907){
            secdModelHb01BlsAmt24m = new BigDecimal("0.190588142").multiply(new BigDecimal("-0.34660963"));
        }else {
            secdModelHb01BlsAmt24m = new BigDecimal("0.006646277").multiply(new BigDecimal("-0.34660963"));
        }

        BigDecimal secdModelCardOverdueAmtSum = null;
        Double  secdModelCardOverdueAmt = Double.valueOf(codeRuleParam.getSecdModelCardOverdueAmtSum());
        if(secdModelCardOverdueAmt<=0){
            secdModelCardOverdueAmtSum = new BigDecimal("0.05575291").multiply(new BigDecimal("-0.39065881"));
        }else {
            secdModelCardOverdueAmtSum = new BigDecimal("-1.16594527").multiply(new BigDecimal("-0.39065881"));
        }


        BigDecimal secdModelMaxLoanCurrOverdueAmount = null;
        Double  secdModelMaxLoanCurrOverdueAmt = Double.valueOf(codeRuleParam.getSecdModelMaxLoanCurrOverdueAmount());
        if(secdModelMaxLoanCurrOverdueAmt <= -999){
            secdModelMaxLoanCurrOverdueAmount = new BigDecimal("-0.3083154").multiply(new BigDecimal("-0.6559749"));
        }else if(secdModelMaxLoanCurrOverdueAmt <= 0){
            secdModelMaxLoanCurrOverdueAmount = new BigDecimal("0.1623491").multiply(new BigDecimal("-0.6559749"));
        }else {
            secdModelMaxLoanCurrOverdueAmount = new BigDecimal("-1.5693128").multiply(new BigDecimal("-0.6559749"));
        }


        BigDecimal secdModelLoanSumMaxDuration = null;
        Integer secdModelLoanSumMaxDurationNum = Integer.valueOf(codeRuleParam.getSecdModelLoanSumMaxDuration());
        if(secdModelLoanSumMaxDurationNum <= 0){
            secdModelLoanSumMaxDuration = new BigDecimal("0.1373848").multiply(new BigDecimal("-0.76888123"));
        }else if(secdModelLoanSumMaxDurationNum <= 1){
            secdModelLoanSumMaxDuration = new BigDecimal("-0.3356576").multiply(new BigDecimal("-0.76888123"));
        }else{
            secdModelLoanSumMaxDuration = new BigDecimal("-1.1874275").multiply(new BigDecimal("-0.76888123"));
        }

        BigDecimal secdModelMaxLatestSortRnCnt3m = null;
        Double secdModelMaxLatestSortRnCnt3mAmt = Double.valueOf(codeRuleParam.getSecdModelMaxLatestSortRnCnt3m());
        if(secdModelMaxLatestSortRnCnt3mAmt <= 0){
            secdModelMaxLatestSortRnCnt3m = new BigDecimal("0").multiply(new BigDecimal("0.52448424"));
        }else{
            secdModelMaxLatestSortRnCnt3m = new BigDecimal("1").multiply(new BigDecimal("0.52448424"));
        }


        BigDecimal secdModelMaxLatestSortRnCnt12m = null;
        Double secdModelMaxLatestSortRnCnt12mAmt = Double.valueOf(codeRuleParam.getSecdModelMaxLatestSortRnCnt12m());
        if(secdModelMaxLatestSortRnCnt12mAmt <= -1){
            secdModelMaxLatestSortRnCnt12m = new BigDecimal("0.1438683").multiply(new BigDecimal("-0.47844346"));
        }else{
            secdModelMaxLatestSortRnCnt12m = new BigDecimal("-0.4708124").multiply(new BigDecimal("-0.47844346"));
        }

        BigDecimal secdModelUsedCreditLimitAmountRatioNoClosed = null;
        Double rate = Double.valueOf(codeRuleParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        if(rate <= -499){
            secdModelUsedCreditLimitAmountRatioNoClosed = new BigDecimal("-0.2388922").multiply(new BigDecimal("-0.69028218"));
        }else if(rate <= 0.2){
            secdModelUsedCreditLimitAmountRatioNoClosed = new BigDecimal("0.2912356").multiply(new BigDecimal("-0.69028218"));
        }else if(rate <= 0.5){
            secdModelUsedCreditLimitAmountRatioNoClosed = new BigDecimal("0.2186525").multiply(new BigDecimal("-0.69028218"));
        }else if(rate <= 0.7){
            secdModelUsedCreditLimitAmountRatioNoClosed = new BigDecimal("0.1356482").multiply(new BigDecimal("-0.69028218"));
        }else if(rate <= 0.9){
            secdModelUsedCreditLimitAmountRatioNoClosed = new BigDecimal("-0.1401126").multiply(new BigDecimal("-0.69028218"));
        }else{
            secdModelUsedCreditLimitAmountRatioNoClosed = new BigDecimal("-0.3809863").multiply(new BigDecimal("-0.69028218"));
        }

        BigDecimal secdModelMaxCreditLimitAmountNoClose = null;
        Integer secdModelMaxCreditLimitAmountNoCloseAmt = Integer.valueOf(codeRuleParam.getSecdModelMaxCreditLimitAmountNoClose());
        if(secdModelMaxCreditLimitAmountNoCloseAmt <= -1){
            secdModelMaxCreditLimitAmountNoClose = new BigDecimal("0.5426447").multiply(new BigDecimal("-0.44083671"));
        }else if(secdModelMaxCreditLimitAmountNoCloseAmt <= 0){
            secdModelMaxCreditLimitAmountNoClose = new BigDecimal("-0.75820243").multiply(new BigDecimal("-0.44083671"));
        }else if(secdModelMaxCreditLimitAmountNoCloseAmt <= 5000){
            secdModelMaxCreditLimitAmountNoClose = new BigDecimal("-0.47130169").multiply(new BigDecimal("-0.44083671"));
        }else if(secdModelMaxCreditLimitAmountNoCloseAmt <= 10000){
            secdModelMaxCreditLimitAmountNoClose = new BigDecimal("-0.17291491").multiply(new BigDecimal("-0.44083671"));
        }else if(secdModelMaxCreditLimitAmountNoCloseAmt <= 20000){
            secdModelMaxCreditLimitAmountNoClose = new BigDecimal("-0.04198671").multiply(new BigDecimal("-0.44083671"));
        }else if(secdModelMaxCreditLimitAmountNoCloseAmt <= 50000){
            secdModelMaxCreditLimitAmountNoClose = new BigDecimal("0.14693281").multiply(new BigDecimal("-0.44083671"));
        }else if(secdModelMaxCreditLimitAmountNoCloseAmt <= 100000){
            secdModelMaxCreditLimitAmountNoClose = new BigDecimal("0.17307295").multiply(new BigDecimal("-0.44083671"));
        }else if(secdModelMaxCreditLimitAmountNoCloseAmt <= 200000){
            secdModelMaxCreditLimitAmountNoClose = new BigDecimal("0.11185988").multiply(new BigDecimal("-0.44083671"));
        }else{
            secdModelMaxCreditLimitAmountNoClose = new BigDecimal("-0.15938759").multiply(new BigDecimal("-0.44083671"));
        }


        BigDecimal secdModelLastXiaojinLoanDays = null;
        Integer secdModelLastXiaojinLoanDaysNum = Integer.valueOf(codeRuleParam.getSecdModelLastXiaojinLoanDays());
        if(secdModelLastXiaojinLoanDaysNum <= -1){
            secdModelLastXiaojinLoanDays = new BigDecimal("0.09206").multiply(new BigDecimal("-0.55778537"));
        }else if(secdModelLastXiaojinLoanDaysNum <= 30){
            secdModelLastXiaojinLoanDays = new BigDecimal("0.08564574").multiply(new BigDecimal("-0.55778537"));
        }else if(secdModelLastXiaojinLoanDaysNum <= 90){
            secdModelLastXiaojinLoanDays = new BigDecimal("0.06877454").multiply(new BigDecimal("-0.55778537"));
        }else if(secdModelLastXiaojinLoanDaysNum <= 360){
            secdModelLastXiaojinLoanDays = new BigDecimal("-0.07745014").multiply(new BigDecimal("-0.55778537"));
        }else{
            secdModelLastXiaojinLoanDays = new BigDecimal("-0.40820049").multiply(new BigDecimal("-0.55778537"));
        }


        BigDecimal secdModelLnOldDateDiff = null;
        Integer secdModelLnOldDateDiffNum = Integer.valueOf(codeRuleParam.getSecdModelLnOldDateDiff());
        if(secdModelLnOldDateDiffNum <= -1){
            secdModelLnOldDateDiff = new BigDecimal("-0.18201793").multiply(new BigDecimal("-0.08743946"));
        }else if(secdModelLnOldDateDiffNum <= 730){
            secdModelLnOldDateDiff = new BigDecimal("-0.00940977").multiply(new BigDecimal("-0.08743946"));
        }else{
            secdModelLnOldDateDiff = new BigDecimal("0.06230578").multiply(new BigDecimal("-0.08743946"));
        }

        BigDecimal secdModelAcademic = null;
        if("-999".equals(codeRuleParam.getSecdModelAcademic())){
            secdModelAcademic = new BigDecimal("0.1584947").multiply(new BigDecimal("-0.82988853"));
        }else if("10".equals(codeRuleParam.getSecdModelAcademic())){
            secdModelAcademic = new BigDecimal("0.8470191").multiply(new BigDecimal("-0.82988853"));
        }else if("20".equals(codeRuleParam.getSecdModelAcademic())){
            secdModelAcademic = new BigDecimal("0.2956406").multiply(new BigDecimal("-0.82988853"));
        }else if("30".equals(codeRuleParam.getSecdModelAcademic())){
            secdModelAcademic = new BigDecimal("0.1141413").multiply(new BigDecimal("-0.82988853"));
        }else{
            secdModelAcademic = new BigDecimal("-0.2647015").multiply(new BigDecimal("-0.82988853"));
        }

        BigDecimal secdModelHb01Maritalstate = null;
        if("-999".equals(codeRuleParam.getSecdModelHb01Maritalstate())){
            secdModelHb01Maritalstate = new BigDecimal("0.2008023").multiply(new BigDecimal("-0.56611024"));
        }else if("10".equals(codeRuleParam.getSecdModelHb01Maritalstate())){
            secdModelHb01Maritalstate = new BigDecimal("-0.2106315").multiply(new BigDecimal("-0.56611024"));
        }else {
            secdModelHb01Maritalstate = new BigDecimal("0.1481829").multiply(new BigDecimal("-0.56611024"));
        }

        BigDecimal secdModelAge = null;
        Integer age = Integer.valueOf(codeRuleParam.getSecdModelAge());
        if(age<=28){
            secdModelAge = new BigDecimal("-0.14753912").multiply(new BigDecimal("-0.5507525"));
        }else if(age<=35){
            secdModelAge = new BigDecimal("-0.05031426").multiply(new BigDecimal("-0.5507525"));
        }else{
            secdModelAge = new BigDecimal("0.32892712").multiply(new BigDecimal("-0.5507525"));
        }


        BigDecimal secdModelGender = new BigDecimal(0);
        if("-999".equals(codeRuleParam.getSecdModelGender())){
            secdModelGender = new BigDecimal(0);
        }else if("0".equals(codeRuleParam.getSecdModelGender())){
            secdModelGender = new BigDecimal(0);
        }else if("1".equals(codeRuleParam.getSecdModelGender())){
            secdModelGender = new BigDecimal("0.62793329");
        }


        BigDecimal secdModelHb01RegisteredAddress = new BigDecimal(0);
        if("-999".equals(codeRuleParam.getSecdModelHb01RegisteredAddress())){
            secdModelHb01RegisteredAddress = new BigDecimal("-0.09320554").multiply(new BigDecimal("-0.49216725"));
        }else if("0".equals(codeRuleParam.getSecdModelHb01RegisteredAddress())){
            secdModelHb01RegisteredAddress = new BigDecimal("0.15896764").multiply(new BigDecimal("-0.49216725"));
        }else if("1".equals(codeRuleParam.getSecdModelHb01RegisteredAddress())){
            secdModelHb01RegisteredAddress = new BigDecimal("-0.17902416").multiply(new BigDecimal("-0.49216725"));
        }

        return secdModelHousingLoanAmtSum
                .add(secdModelHb01BlsAmt24m)
                .add(secdModelCardOverdueAmtSum)
                .add(secdModelMaxLoanCurrOverdueAmount)
                .add(secdModelLoanSumMaxDuration)
                .add(secdModelMaxLatestSortRnCnt3m)
                .add(secdModelMaxLatestSortRnCnt12m)
                .add(secdModelUsedCreditLimitAmountRatioNoClosed)
                .add(secdModelMaxCreditLimitAmountNoClose)
                .add(secdModelLastXiaojinLoanDays)
                .add(secdModelLnOldDateDiff)
                .add(secdModelAcademic)
                .add(secdModelHb01Maritalstate)
                .add(secdModelAge)
                .add(secdModelGender)
                .add(secdModelHb01RegisteredAddress)
                .add(new BigDecimal("-3.42202177"));
    }


    private BigDecimal getExpValue(BigDecimal qualificationScore){
        double tempNum =  Double.valueOf(qualificationScore.multiply(new BigDecimal(-1)).toString());
        double result = Math.pow(Math.E,tempNum);

        BigDecimal num1 =  new BigDecimal(result).add(new BigDecimal(1));

        BigDecimal decimal = new BigDecimal(1).divide(num1,8,BigDecimal.ROUND_HALF_UP);
        return decimal;
    }


}
