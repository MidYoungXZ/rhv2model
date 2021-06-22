package cn.demo.rhv2model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;

/**
 * CreateBy: Liurenquan
 * CreateDate: 2020/1/16
 */
public class CodeGenerationTest {

    @Test
    public void getCodeGeneration() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("109个案例20200117.txt");
        String parseJsonArray = IOUtils.toString(in, "UTF-8");

        JSONArray array = JSON.parseArray(parseJsonArray);

        File rhfile =new File("/Users/liurenquan/Desktop/测试用例.txt");
        FileWriter fileWriter = new FileWriter(rhfile.getAbsoluteFile());

        BufferedWriter bufferWritter = new BufferedWriter(fileWriter);

        for(int i=0;i<array.size();i++){

            JSONObject json = (JSONObject)array.get(i);

            String template = "@Test\n" +
                    "    public void testRhModelCase"+(i+1)+"() throws Exception {\n" +
                    "        InputStream in = this.getClass().getClassLoader().getResourceAsStream("+"\""+getSplit(json.getString("fileName"))+"\""+");\n" +
                    "        String parseHtml = IOUtils.toString(in, \"UTF-8\");\n" +
                    "\n" +
                    "        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);\n" +
                    "\n" +
                    "        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);\n" +
                    "        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);\n" +
                    "\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("normalCardLmtMax")+"), incomeParam.getNormalCardLmtMax());\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("normalCardAvgLmt")+"), incomeParam.getNormalCardAvgLmt());\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("housingLoanAmtSum")+"), incomeParam.getHousingLoanAmtSum());\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("carLoanAmtSum")+"), incomeParam.getCarLoanAmtSum());\n" +
                    "        Assert.assertEquals("+json.getInteger("rhGradeModel1")+", incomeMap.get(\"rhGradeModel1\"));\n" +
                    "\n" +
                    "        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2\n" +
                    "                .getCodeRuleParam(rhParamMap);\n" +
                    "        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);\n" +
                    "\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelAcademic"))+",qualificationParam.getSecdModelAcademic());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelAge"))+", qualificationParam.getSecdModelAge());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelCardOverdueAmtSum"))+", qualificationParam.getSecdModelCardOverdueAmtSum());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelGender"))+", qualificationParam.getSecdModelGender());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelHb01BlsAmt24m"))+",  qualificationParam.getSecdModelHb01BlsAmt24m());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelHb01Maritalstate"))+", qualificationParam.getSecdModelHb01Maritalstate());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelHb01RegisteredAddress"))+",qualificationParam.getSecdModelHb01RegisteredAddress());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelHousingLoanAmtSum"))+",qualificationParam.getSecdModelHousingLoanAmtSum());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelLastXiaojinLoanDays"))+",qualificationParam.getSecdModelLastXiaojinLoanDays());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelLnOldDateDiff"))+",qualificationParam.getSecdModelLnOldDateDiff());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelLoanSumMaxDuration"))+", qualificationParam.getSecdModelLoanSumMaxDuration());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelMaxCreditLimitAmountNoClose"))+", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelMaxLatestSortRnCnt3m"))+", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelMaxLatestSortRnCnt12m"))+", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelMaxLoanCurrOverdueAmount"))+",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelUsedCreditLimitAmountRatioNoClosed"))+",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("cardFirstMonthNum")+"), qualificationParam.getCardFirstMonthNum());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelCardLmtNoClose"))+",qualificationParam.getSecdModelCardLmtNoClose());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("secdModelCardLmtUsedRateNoClose"))+",qualificationParam.getSecdModelCardLmtUsedRateNoClose());\n" +
                    "        Assert.assertEquals(new BigDecimal("+addPrefixV2(json.getBigDecimal("rhGradeModel2"))+"), qualificationMap.get(\"rhGradeModel2\"));\n" +
                    "\n" +
                    "        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);\n" +
                    "        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);\n" +
                    "\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("repayAmtCard")+"), liabilityParam.getRepayAmtCard());\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("repayAmtLoan")+"),liabilityParam.getRepayAmtLoan());\n" +
                    "        Assert.assertEquals(new BigDecimal("+ addPrefixV2(json.getBigDecimal("rhGradeModel3"))+"),liabilityMap.get(\"rhGradeModel3\"));\n" +
                    "\n" +
                    "        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);\n" +
                    "        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);\n" +
                    "\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("queryForCardLast12monContSum")+"), emptyReportParam.getQueryForCardLast12monContSum());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("housingLoanAmtSum")+"), emptyReportParam.getHousingLoanAmtSum());\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("rhGradeModel4")+"),emptyReportMap.get(\"rhGradeModel4\"));\n" +
                    "\n" +
                    "        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);\n" +
                    "        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);\n" +
                    "\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("standardCardOverdueNumLast24MonMax")+"),reasonParam.getStandardCardOverdueNumLast24MonMax());\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("standardCardOverdueAmt")+"),reasonParam.getStandardCardOverdueAmt());\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("standardCardBaddebtCount")+"),reasonParam.getStandardCardBaddebtCount());\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("cardBaddebtCount")+"),reasonParam.getCardBaddebtCount());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("cardOverdueNumLast24MonMax")+"),reasonParam.getCardOverdueNumLast24MonMax());\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("loanBaddebtCount")+"),reasonParam.getLoanBaddebtCount());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("loanOverdueNumLast24MonMax")+"),reasonParam.getLoanOverdueNumLast24MonMax());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("badLoanCount")+"),reasonParam.getBadLoanCount());\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("fellbackCount")+"),reasonParam.getFellbackCount());\n" +
                    "        Assert.assertEquals(new Integer("+json.getInteger("cardOverdueNumLast12MonMax")+"),reasonParam.getCardOverdueNumLast12MonMax());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("cardOverdueNumLast6MonMax")+"),reasonParam.getCardOverdueNumLast6MonMax());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("cardOverdueLast12MonContSum")+"),reasonParam.getCardOverdueLast12MonContSum());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("cardOverdueLast6MonContSum")+"),reasonParam.getCardOverdueLast6MonContSum());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("farmingLoanAmtSum")+"),reasonParam.getFarmingLoanAmtSum());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("guaranteeAmtSum")+"),reasonParam.getGuaranteeAmtSum());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("judgementCount")+"),reasonParam.getJudgementCount());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("cardOverdueAmtSum")+"),reasonParam.getCardOverdueAmtSum());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("queryForloanCardLast3monContSum")+"),reasonParam.getQueryForloanCardLast3monContSum());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("secdCustomerLoanOverdueLast6MonContSum")+"),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("secdCustomerLoanOverdueLast12MonContSum")+"),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());\n" +
                    "        Assert.assertEquals(new Integer("+ json.getInteger("secdLoanOverdueNumLast12MonMax")+"),reasonParam.getSecdLoanOverdueNumLast12MonMax());\n" +
                    "        Assert.assertEquals("+addPrefixV2(json.getString("rigidRefuse"))+",reasonMap.get(\"rigidRefuse\"));\n" +
                    "    }\n";

            bufferWritter.write(template);
            bufferWritter.newLine();
            bufferWritter.flush();
        }
        bufferWritter.close();

    }

    private String addPrefixV2(Object source){
        return "\""+source.toString()+"\"";
    }

    private  String getSplit(String str){

        String prefix = "";

        if(str.contains("secd_model_HousingLoanAmtSum")){
            prefix = "val_1_secd_model_HousingLoanAmtSum";
        }else if(str.contains("secd_model_hb_01_bls_amt_24m")){
            prefix = "val_2_secd_model_hb_01_bls_amt_24m";
        }else if(str.contains("secd_model_CardOverdueAmtSum")){
            prefix = "val_3_secd_model_CardOverdueAmtSum";
        }else if(str.contains("secd_model_max_loan_curroverdueamount")){
            prefix = "val_4_secd_model_max_loan_curroverdueamount";
        }else if(str.contains("secd_model_loansummaxduration")){
            prefix = "val_5_secd_model_loansummaxduration";
        }else if(str.contains("secd_model_max_latest_sort_rn_cnt_3m")){
            prefix = "val_6_secd_model_max_latest_sort_rn_cnt_3m";
        }else if(str.contains("secd_model_max_latest_sort_rn_cnt_12m")){
            prefix = "val_7_secd_model_max_latest_sort_rn_cnt_12m";
        }else if(str.contains("secd_model_CardLmtUsedRateNoClose")){
            prefix = "val_8_secd_model_CardLmtUsedRateNoClose";
        }else if(str.contains("secd_model_CardLmtNoClose")){
            prefix = "val_9_secd_model_CardLmtNoClose";
        }else if(str.contains("secd_model_max_creditlimitamount_no_close")){
            prefix = "val_10_secd_model_max_creditlimitamount_no_close";
        }else if(str.contains("secd_model_last_xiaojin_loan_days")){
            prefix = "val_11_secd_model_last_xiaojin_loan_days";
        }else if(str.contains("secd_model_ln_old_datediff")){
            prefix = "val_12_secd_model_ln_old_datediff";
        }else if(str.contains("secd_model_academic")){
            prefix = "val_13_secd_model_academic";
        }else if(str.contains("secd_model_hb_01_maritalstate")){
            prefix = "val_14_secd_model_hb_01_maritalstate";
        }else if(str.contains("secd_model_hb_01_industry")){
            prefix = "val_15_secd_model_hb_01_industry";
        }else if(str.contains("secd_model_age")){
            prefix = "val_16_secd_model_age";
        }else if(str.contains("secd_model_gender")){
            prefix = "val_17_secd_model_gender";
        }else if(str.contains("secd_model_hb_01_registeredaddress")){
            prefix = "val_18_secd_model_hb_01_registeredaddress";
        }
        return prefix+"/"+str;

    }



}
