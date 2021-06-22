package cn.demo.rhv2model;

import cn.demo.rhv2model.constant.RhInputTypeEnum;
import cn.demo.rhv2model.service.RhGradeModelService;
import cn.demo.rhv2model.service.rule.impl.coderule.*;
import cn.yxzmmm.rhv2model.service.rule.impl.coderule.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RhModelXmlTest {

    private RhGradeModelService rhGradeModelService;
    private IncomeScore incomeScore;
    private QualificationScoreV2 qualificationScoreV2;
    private LiabilityScore liabilityScore;
    private EmptyReportScore emptyReportScore;
    private ReasonScore reasonScore;

    @Before
    public void setUp() {
        rhGradeModelService = new RhGradeModelService();
        incomeScore = new IncomeScore();
        qualificationScoreV2 = new QualificationScoreV2();
        liabilityScore = new LiabilityScore();
        emptyReportScore = new EmptyReportScore();
        reasonScore = new ReasonScore();
    }

    @Test
    public void testRhModel01() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("01.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("-999", qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("0", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("0", qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0", qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("860000", qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("0", qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132", qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(127), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("-2.080265"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"), liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(0), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(40, emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3), reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0), reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0), reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0), reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7), reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1), reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8), reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2), reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2), reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7), reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1), reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4), reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1), reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0), reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1595838), reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1), reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550), reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(-9999), reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(-9999),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(-9999),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(-9999),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,D1,D3,E3", reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModel02() throws Exception {

//        rhGradeModel1 =0
//        queryBySelfLastMonContSum =8
//        secdModelAcademic =-999
//        secdModelAge =27
//        secdModelCardOverdueAmtSum =0
//        secdModelGender =0
//        secdModelHb01BlsAmt24m =0
//        secdModelHb01Maritalstate =-999
//        secdModelHb01RegisteredAddress =0
//        secdModelHousingLoanAmtSum =10
//        secdModelLastXiaojinLoanDays =0
//        secdModelLnOldDateDiff =0
//        secdModelLoanSumMaxDuration =2
//        secdModelMaxCreditLimitAmountNoClose =0
//        secdModelMaxLatestSortRnCnt3m =0
//        secdModelMaxLatestSortRnCnt12m =0
//        secdModelMaxLoanCurrOverdueAmount =24550
//        secdModelUsedCreditLimitAmountRatioNoClosed =0
//        cardFirstMonthNum =-9999
//        rhGradeModel2 =-998
//        rhGradeModel3 =-999
//        rhGradeModel4 =20
//        rigidRefuse =A3,A5,A6,B1,D3,E3



        List<File> list = new ArrayList<>();
        getFiles(list,"/Users/liurenquan/idea/rhv2model/rhv2model-core/src/test/resources");
        System.out.println(list.size());


        File rhfile =new File("/Users/liurenquan/Desktop/109个案例20200117.txt");

        if(!rhfile.exists()){
            rhfile.createNewFile();
        }

        //使用true，即进行append file

        FileWriter fileWriter = new FileWriter(rhfile.getAbsoluteFile());

        BufferedWriter bufferWritter = new BufferedWriter(fileWriter);

        int count = 0;

        bufferWritter.write("[");
        for(File file : list) {

            try {
                InputStream in = new FileInputStream(file);
                String parseHtml = IOUtils.toString(in, "UTF-8");
                Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);
                IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
                Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

                bufferWritter.newLine();
                bufferWritter.write("{");
                bufferWritter.write(addPrefixV2("fileName") + addPrefixV1(file.getName()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("carLoanAmtSum")+ addPrefixV1(incomeParam.getCarLoanAmtSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("housingLoanAmtSum") + addPrefixV1(incomeParam.getHousingLoanAmtSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("normalCardAvgLmt") + addPrefixV1(incomeParam.getNormalCardAvgLmt()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("normalCardLmtMax") + addPrefixV1(incomeParam.getNormalCardLmtMax()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("rhGradeModel1") + addPrefixV1(incomeMap.get("rhGradeModel1")));
                bufferWritter.newLine();


                QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                        .getCodeRuleParam(rhParamMap);
                Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);


                bufferWritter.write(addPrefixV2("secdModelAcademic") + addPrefixV1(qualificationParam.getSecdModelAcademic()));
                bufferWritter.newLine();


                bufferWritter.write(addPrefixV2("secdModelAge") + addPrefixV1(qualificationParam.getSecdModelAge()));
                bufferWritter.newLine();


                bufferWritter.write(addPrefixV2("secdModelCardOverdueAmtSum") + addPrefixV1(qualificationParam.getSecdModelCardOverdueAmtSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdModelCardLmtNoClose") + addPrefixV1(qualificationParam.getSecdModelCardLmtNoClose()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdModelCardLmtUsedRateNoClose") + addPrefixV1(qualificationParam.getSecdModelCardLmtUsedRateNoClose()));
                bufferWritter.newLine();


                bufferWritter.write(addPrefixV2("secdModelGender") + addPrefixV1(qualificationParam.getSecdModelGender()));
                bufferWritter.newLine();


                bufferWritter.write(addPrefixV2("secdModelHb01BlsAmt24m") + addPrefixV1(qualificationParam.getSecdModelHb01BlsAmt24m()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdModelHb01Maritalstate") + addPrefixV1(qualificationParam.getSecdModelHb01Maritalstate()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdModelHb01RegisteredAddress") + addPrefixV1(qualificationParam.getSecdModelHb01RegisteredAddress()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdModelHousingLoanAmtSum") + addPrefixV1(qualificationParam.getSecdModelHousingLoanAmtSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdModelLastXiaojinLoanDays") + addPrefixV1(qualificationParam.getSecdModelLastXiaojinLoanDays()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdModelLnOldDateDiff") + addPrefixV1(qualificationParam.getSecdModelLnOldDateDiff()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdModelLoanSumMaxDuration") + addPrefixV1(qualificationParam.getSecdModelLoanSumMaxDuration()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdModelMaxCreditLimitAmountNoClose") + addPrefixV1(qualificationParam.getSecdModelMaxCreditLimitAmountNoClose()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdModelMaxLatestSortRnCnt3m") + addPrefixV1(qualificationParam.getSecdModelMaxLatestSortRnCnt3m()));
                bufferWritter.newLine();


                bufferWritter.write(addPrefixV2("secdModelMaxLatestSortRnCnt12m") + addPrefixV1(qualificationParam.getSecdModelMaxLatestSortRnCnt12m()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdModelMaxLoanCurrOverdueAmount") + addPrefixV1(qualificationParam.getSecdModelMaxLoanCurrOverdueAmount()));
                bufferWritter.newLine();


                bufferWritter.write(addPrefixV2("secdModelUsedCreditLimitAmountRatioNoClosed") + addPrefixV1(qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("cardFirstMonthNum") + addPrefixV1(qualificationParam.getCardFirstMonthNum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("rhGradeModel2") + addPrefixV1(qualificationMap.get("rhGradeModel2")));
                bufferWritter.newLine();

                LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
                Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);
                bufferWritter.write(addPrefixV2("repayAmtCard") + addPrefixV1(liabilityParam.getRepayAmtCard()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("repayAmtLoan") + addPrefixV1(liabilityParam.getRepayAmtLoan()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("rhGradeModel3") + addPrefixV1(liabilityMap.get("rhGradeModel3")));
                bufferWritter.newLine();

                EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
                Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

                bufferWritter.write(addPrefixV2("queryForCardLast12monContSum") + addPrefixV1(emptyReportParam.getQueryForCardLast12monContSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("rhGradeModel4") + addPrefixV1(emptyReportMap.get("rhGradeModel4")));
                bufferWritter.newLine();

                ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
                Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

                bufferWritter.write(addPrefixV2("cardBaddebtCount") + addPrefixV1(reasonParam.getCardBaddebtCount()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("loanBaddebtCount") + addPrefixV1(reasonParam.getLoanBaddebtCount()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("badLoanCount") + addPrefixV1(reasonParam.getBadLoanCount()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("standardCardBaddebtCount") + addPrefixV1(reasonParam.getStandardCardBaddebtCount()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("judgementCount") + addPrefixV1(reasonParam.getJudgementCount()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("fellbackCount") + addPrefixV1(reasonParam.getFellbackCount()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("loanOverdueNumLast24MonMax") + addPrefixV1(reasonParam.getLoanOverdueNumLast24MonMax()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("cardOverdueNumLast24MonMax") + addPrefixV1(reasonParam.getCardOverdueNumLast24MonMax()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("standardCardOverdueNumLast24MonMax") + addPrefixV1(reasonParam.getStandardCardOverdueNumLast24MonMax()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("cardOverdueNumLast12MonMax") + addPrefixV1(reasonParam.getCardOverdueNumLast12MonMax()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("cardOverdueNumLast6MonMax") + addPrefixV1(reasonParam.getCardOverdueNumLast6MonMax()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("cardOverdueLast12MonContSum") + addPrefixV1(reasonParam.getCardOverdueLast12MonContSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("cardOverdueLast6MonContSum") + addPrefixV1(reasonParam.getCardOverdueLast6MonContSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("cardOverdueAmtSum") + addPrefixV1(reasonParam.getCardOverdueAmtSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("standardCardOverdueAmt") + addPrefixV1(reasonParam.getStandardCardOverdueAmt()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("queryForloanCardLast3monContSum") + addPrefixV1(reasonParam.getQueryForloanCardLast3monContSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("farmingLoanAmtSum") + addPrefixV1(reasonParam.getFarmingLoanAmtSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("guaranteeAmtSum") + addPrefixV1(reasonParam.getGuaranteeAmtSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("loanOverdueAmtSum") + addPrefixV1(reasonParam.getLoanOverdueAmtSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdCustomerLoanOverdueLast6MonContSum") + addPrefixV1(reasonParam.getSecdCustomerLoanOverdueLast6MonContSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdCustomerLoanOverdueLast12MonContSum") + addPrefixV1(reasonParam.getSecdCustomerLoanOverdueLast12MonContSum()));
                bufferWritter.newLine();

                bufferWritter.write(addPrefixV2("secdLoanOverdueNumLast12MonMax") + addPrefixV1(reasonParam.getSecdLoanOverdueNumLast12MonMax()));
                bufferWritter.newLine();


                // 中间变量可删除

                /*bufferWritter.write("secd_LoanOverdueLast6MonCount : " + reasonParam.getLoanOverdueLast6MonCount());
                bufferWritter.newLine();

                bufferWritter.write("secd_RepayAmtSum : " + reasonParam.getRepayAmtSum());
                bufferWritter.newLine();

                bufferWritter.write("secd_NormalCardCount : " + reasonParam.getNormalCardCount());
                bufferWritter.newLine();

                bufferWritter.write("secd_BaddebtCount : " + reasonParam.getBaddebtCount());
                bufferWritter.newLine();

                bufferWritter.write("secd_NormalCardLmtSum : " + reasonParam.getNormalCardLmtSum());
                bufferWritter.newLine();

                bufferWritter.write("secd_QueryLast12MonContSum : " + reasonParam.getQueryLast12MonContSum());
                bufferWritter.newLine();

                bufferWritter.write("secd_NormalCardLmtUsed : " + reasonParam.getNormalCardLmtUsed());
                bufferWritter.newLine();

                bufferWritter.write("secd_QueryForLoanLast3monCount : " + reasonParam.getQueryForLoanLast3monCount());
                bufferWritter.newLine();

                bufferWritter.write("secd_QueryForCardLast3monCount : " + reasonParam.getQueryForCardLast3monCount());
                bufferWritter.newLine();

                bufferWritter.write("secd_QueryBySelfLastMonContSum : " + reasonParam.getQueryBySelfLastMonContSum());
                bufferWritter.newLine();

                bufferWritter.write("secd_NormalCardLmtUsedRate : " + reasonParam.getNormalCardLmtUsedRate());
                bufferWritter.newLine();

                bufferWritter.write("secd_AssureerRepayCount : " + reasonParam.getAssureerRepayCount());
                bufferWritter.newLine();

                bufferWritter.write("secd_AssetDispositionCount : " + reasonParam.getAssetDispositionCount());
                bufferWritter.newLine();


                bufferWritter.write("secd_QueryForLoanLast6monContSum : " + reasonParam.getQueryForLoanLast6monContSum());
                bufferWritter.newLine();
*/

                bufferWritter.write(addPrefixV2("rigidRefuse") + "\""+reasonMap.get("rigidRefuse")+"\"");
                bufferWritter.newLine();
                count += 1;

                if(count==list.size()){
                    bufferWritter.write("}");
                }else{
                    bufferWritter.write("},");
                }

                bufferWritter.flush();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        bufferWritter.write("]");
        bufferWritter.close();


        System.out.println("count:"+count);
    }


    private  void getFiles(List<File> fileList, String path) {
        try {
            File file = new File(path);
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                for (File fileIndex : files) {
                    //如果这个文件是目录，则进行递归搜索
                    if (fileIndex.isDirectory()) {
                        getFiles(fileList, fileIndex.getPath());
                    } else {
                        //如果文件是普通文件，则将文件句柄放入集合中
                        fileList.add(fileIndex);
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    private String addPrefixV1(Object source){
        if(source instanceof String){
            return "\""+source+"\",";
        }
        return source.toString()+",";
    }

    private String addPrefixV2(String source){
        return "\""+source+"\""+" : ";
    }


    @Test
    public void testRhModel03() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_1_secd_model_HousingLoanAmtSum/secd_model_HousingLoanAmtSum_-999.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");
        JSONObject json = rhGradeModelService.getRhJsonObject(RhInputTypeEnum.XML, parseHtml);
        System.out.println(json);


    }

    @Test
    public void testRhModelCase1() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("/ecd_model_max_creditlimitamount_no_close_0.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.32113599"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase2() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_-999.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.21048286"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase3() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_10000.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(10000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(1430), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(471, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("10000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.0010",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("10010",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.20221460"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase4() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_100000.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(100000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(14287), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(4714, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("100000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.0001",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("100010",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.17872139"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase5() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_20.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(20), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(4), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("20", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.3333",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("30",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.23310382"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase6() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_20000.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(20000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(2858), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(943, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("20000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.0005",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("20010",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.19306315"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase7() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_200000.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(200000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(28572), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(9428, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("200000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.0000",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("200010",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.18271664"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase8() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_30.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(30), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(6), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("905",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("30", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.2500",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("40",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.22930654"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase9() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_300000.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(300000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(42858), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(11642, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("300000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.0000",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("300010",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.20125428"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase10() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_5000.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(5000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(715), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(235, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("5000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.0020",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("5010",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.22426714"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase11() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_50000.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(50000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(7144), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(2357, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("50000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.0002",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("50010",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.18041908"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase12() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_NA_1.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("4394",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.14039181"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase13() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_NA_2.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.21048286"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase14() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_10_secd_model_max_creditlimitamount_no_close/secd_model_max_creditlimitamount_no_close_NA_3.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(10), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(3), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.21048286"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase15() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_11_secd_model_last_xiaojin_loan_days/secd_model_last_xiaojin_loan_days_153.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(5280, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("9.6007",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("30",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("153",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("30000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(8), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("46000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.29235789"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C2,C3,C4,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase16() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_11_secd_model_last_xiaojin_loan_days/secd_model_last_xiaojin_loan_days_16.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(5280, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("9.6007",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("16",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("16",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("30000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(1), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("46000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.29235789"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C2,C3,C4,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase17() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_11_secd_model_last_xiaojin_loan_days/secd_model_last_xiaojin_loan_days_183.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(5280, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("2.9220",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("30",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("183",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("16000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(8), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("16000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.30988069"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C2,C3,C4,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase18() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_11_secd_model_last_xiaojin_loan_days/secd_model_last_xiaojin_loan_days_214.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("0.3364",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("61",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("244",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(6), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.14032775"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase19() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_11_secd_model_last_xiaojin_loan_days/secd_model_last_xiaojin_loan_days_244.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("0.3364",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("61",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("244",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(6), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.14032775"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase20() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_11_secd_model_last_xiaojin_loan_days/secd_model_last_xiaojin_loan_days_442.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(5280, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("30000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("46000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.31052682"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C2,C3,C4,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase21() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_11_secd_model_last_xiaojin_loan_days/secd_model_last_xiaojin_loan_days_77.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(5280, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("30000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("46000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.31052682"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C2,C3,C4,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase22() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_11_secd_model_last_xiaojin_loan_days/secd_model_last_xiaojin_loan_days_NA.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.16305135"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase23() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_12_secd_model_ln_old_datediff/secd_model_ln_old_datediff_153.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(5280, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("9.6007",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("153",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("30000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(8), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("46000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.29161825"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C2,C3,C4,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase24() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_12_secd_model_ln_old_datediff/secd_model_ln_old_datediff_1538.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("1538",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("1538",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.17755494"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase25() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_12_secd_model_ln_old_datediff/secd_model_ln_old_datediff_183.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(16000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(5280, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("2.9220",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("30",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("183",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("16000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(8), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("16000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.30988069"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C2,C3,C4,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase26() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_12_secd_model_ln_old_datediff/secd_model_ln_old_datediff_244.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("2.5835",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("30",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("244",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.13273634"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase27() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_12_secd_model_ln_old_datediff/secd_model_ln_old_datediff_NA.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.16305135"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase28() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_13_secd_model_academic/secd_model_academic_10.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("10",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.10768165"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase29() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_13_secd_model_academic/secd_model_academic_20.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase30() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_13_secd_model_academic/secd_model_academic_30.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("30",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.18146745"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase31() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_13_secd_model_academic/secd_model_academic_40.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("40",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.23289436"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase32() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_13_secd_model_academic/secd_model_academic_60.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("40",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.23289436"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase33() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_13_secd_model_academic/secd_model_academic_90.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("90",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.23289436"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase34() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_13_secd_model_academic/secd_model_academic_91.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("91",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.23289436"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase35() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_13_secd_model_academic/secd_model_academic_99.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("99",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.23289436"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase36() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_13_secd_model_academic/secd_model_academic_NA.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("-999",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.17606401"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase37() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_14_secd_model_hb_01_maritalstate/secd_model_hb_01_maritalstate_10.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("10", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.18939708"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase38() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_14_secd_model_hb_01_maritalstate/secd_model_hb_01_maritalstate_20.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase39() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_14_secd_model_hb_01_maritalstate/secd_model_hb_01_maritalstate_30.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("30", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase40() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_14_secd_model_hb_01_maritalstate/secd_model_hb_01_maritalstate_40.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("40", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase41() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_14_secd_model_hb_01_maritalstate/secd_model_hb_01_maritalstate_91.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("91", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase42() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_14_secd_model_hb_01_maritalstate/secd_model_hb_01_maritalstate_99.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("99", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase43() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_14_secd_model_hb_01_maritalstate/secd_model_hb_01_maritalstate_NA.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.15619062"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase44() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_15_secd_model_hb_01_industry/secd_model_hb_01_industry_A.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3600",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase45() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_15_secd_model_hb_01_industry/secd_model_hb_01_industry_B.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3600",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase46() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_15_secd_model_hb_01_industry/secd_model_hb_01_industry_NULL.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3600",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase47() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_16_secd_model_age/secd_model_age_27.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase48() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_16_secd_model_age/secd_model_age_35.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("35", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.15308489"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase49() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_16_secd_model_age/secd_model_age_45.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("45", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.12792001"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase50() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_16_secd_model_age/secd_model_age_NA_1.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("-999", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("-999", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase51() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_16_secd_model_age/secd_model_age_NA_2.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("-999", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("-999", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase52() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_17_secd_model_gender/secd_model_gender_0.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase53() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_17_secd_model_gender/secd_model_gender_1.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("1", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.26325324"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase54() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_17_secd_model_gender/secd_model_gender_NA_1.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("-999", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("-999", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase55() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_17_secd_model_gender/secd_model_gender_NA_2.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("-999", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("-999", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase56() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_18_secd_model_hb_01_registeredaddress/secd_model_hb_01_registeredaddress_-999.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.17756253"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase57() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_18_secd_model_hb_01_registeredaddress/secd_model_hb_01_registeredaddress_0_1.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase58() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_18_secd_model_hb_01_registeredaddress/secd_model_hb_01_registeredaddress_0_2.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase59() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_18_secd_model_hb_01_registeredaddress/secd_model_hb_01_registeredaddress_0_3.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase60() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_18_secd_model_hb_01_registeredaddress/secd_model_hb_01_registeredaddress_0_4.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase61() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_18_secd_model_hb_01_registeredaddress/secd_model_hb_01_registeredaddress_0_5.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase62() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_18_secd_model_hb_01_registeredaddress/secd_model_hb_01_registeredaddress_1_1.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("1",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.18381480"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase63() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_18_secd_model_hb_01_registeredaddress/secd_model_hb_01_registeredaddress_1_2.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("1",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.18381480"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase64() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_1_secd_model_HousingLoanAmtSum/secd_model_HousingLoanAmtSum_-999.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(10), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("4394",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.14039181"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(10), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(180),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase65() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_1_secd_model_HousingLoanAmtSum/secd_model_HousingLoanAmtSum_0.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("4394",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.14039181"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase66() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_1_secd_model_HousingLoanAmtSum/secd_model_HousingLoanAmtSum_10.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(10), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-989",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("4394",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.14039181"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(10), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(180),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase67() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_1_secd_model_HousingLoanAmtSum/secd_model_HousingLoanAmtSum_20.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(20), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-979",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("4394",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.14039181"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(20), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(180),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase68() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_1_secd_model_HousingLoanAmtSum/secd_model_HousingLoanAmtSum_30.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(30), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-969",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("4394",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.14039181"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(30), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(180),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase69() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_1_secd_model_HousingLoanAmtSum/secd_model_HousingLoanAmtSum_50.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(50), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(1, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-949",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("4394",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.14039181"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(50), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(180),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase70() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_1_secd_model_HousingLoanAmtSum/secd_model_HousingLoanAmtSum_500000.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(500000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(10000, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("499001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("4394",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.09557054"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(500000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(190),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase71() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_1_secd_model_HousingLoanAmtSum/secd_model_HousingLoanAmtSum_60.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(70), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(1, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-939",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("4394",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.14039181"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(70), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(180),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase72() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_2_secd_model_hb_01_bls_amt_24m/secd_model_hb_01_bls_amt_24m_0.125.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("1.0000",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("395",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.13232501"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase73() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_2_secd_model_hb_01_bls_amt_24m/secd_model_hb_01_bls_amt_24m_0.15.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(200), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(4, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("6100", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("0.1500",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-799",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("395",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("2", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("2", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(10), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.33521402"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(200), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(180),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(2),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(2),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(2),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(6100),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C2,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase74() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_2_secd_model_hb_01_bls_amt_24m/secd_model_hb_01_bls_amt_24m_0.5.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("0.8000",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("395",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.12517489"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase75() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_2_secd_model_hb_01_bls_amt_24m/secd_model_hb_01_bls_amt_24m_0.667.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(5), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("0.6667",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-994",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("395",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.12359609"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(5), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(180),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase76() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_2_secd_model_hb_01_bls_amt_24m/secd_model_hb_01_bls_amt_24m_0.8.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("0.8000",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("395",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.12517489"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase77() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_2_secd_model_hb_01_bls_amt_24m/secd_model_hb_01_bls_amt_24m_1.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(5), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("1.0000",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-994",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("1125",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.13160669"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(5), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(180),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase78() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_2_secd_model_hb_01_bls_amt_24m/secd_model_hb_01_bls_amt_24m_－999.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(5), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-994",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("2404",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.14039181"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(5), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(180),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase79() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_3_secd_model_CardOverdueAmtSum/secd_model_CardOverdueAmtSum_11.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("11", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("5.9120",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("274",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("2", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("2", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("30",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(13), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.35784846"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(10000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("1.0000"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(8),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(2),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(2),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(11),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(7),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B3,C2,C3,C4,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase80() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_3_secd_model_CardOverdueAmtSum/secd_model_CardOverdueAmtSum_15.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("15", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("0.8913",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("244",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("2", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.8469",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(13), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("160000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.20522805"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(29500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("21.5935"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(8),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(3),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(6),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(3),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(10),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(3),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B3,C1,C2,C3,C4,C7,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase81() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_3_secd_model_CardOverdueAmtSum/secd_model_CardOverdueAmtSum_－999.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("2.5835",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("395",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("30",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.13232501"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase82() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_4_secd_model_max_loan_curroverdueamount/secd_model_max_loan_curroverdueamount_0.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("7.8262",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("395",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("0",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(6), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.04668676"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase83() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_4_secd_model_max_loan_curroverdueamount/secd_model_max_loan_curroverdueamount_10.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("2.5835",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("395",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("10",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.13232501"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase84() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_4_secd_model_max_loan_curroverdueamount/secd_model_max_loan_curroverdueamount_30.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("60", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("7.8262",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("395",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("30",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(6), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.19729473"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(60),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase85() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_4_secd_model_max_loan_curroverdueamount/secd_model_max_loan_curroverdueamount_5.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("60", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("7.8262",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("395",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("5",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(6), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.19729473"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(60),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase86() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_4_secd_model_max_loan_curroverdueamount/secd_model_max_loan_curroverdueamount_－999.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("60", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("7.8262",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("395",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(6), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.09704731"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(5000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-7.7050"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(60),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase87() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_5_secd_model_loansummaxduration/secd_model_loansummaxduration_0.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("0", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.06442351"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase88() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_5_secd_model_loansummaxduration/secd_model_loansummaxduration_1.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("1", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.09013637"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase89() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_5_secd_model_loansummaxduration/secd_model_loansummaxduration_3.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(860000), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("24550", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("859001",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3588",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("3", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.16015691"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(24500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("17.4982"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(860000), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(200),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(3),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(4),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(1),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(24550),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A2,A3,A5,A6,B1,C1,C3,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase90() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_6_secd_model_max_latest_sort_rn_cnt_3m/secd_model_max_latest_sort_rn_cnt_3m_0_N.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.15125629"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(7),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(60),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(30),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase91() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_6_secd_model_max_latest_sort_rn_cnt_3m/secd_model_max_latest_sort_rn_cnt_3m_3.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(19700, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.7132",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("190000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.23142232"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(7),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(59),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(29),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase92() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_6_secd_model_max_latest_sort_rn_cnt_3m/secd_model_max_latest_sort_rn_cnt_3m_N_N.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(88000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(15380, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.6578",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("206000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.12840513"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(54),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(18),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase93() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_7_secd_model_max_latest_sort_rn_cnt_12m/secd_model_max_latest_sort_rn_cnt_12m_0_N.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(88000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(15380, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.6578",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("206000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.09892519"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(72),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(36),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase94() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_7_secd_model_max_latest_sort_rn_cnt_12m/secd_model_max_latest_sort_rn_cnt_12m_3.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(88000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(15380, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.6578",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("206000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.19930399"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(71),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(35),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase95() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_7_secd_model_max_latest_sort_rn_cnt_12m/secd_model_max_latest_sort_rn_cnt_12m_4.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(88000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(15380, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("1", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("4", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.6578",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("206000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.19930399"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(7),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(72),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(36),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase96() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_7_secd_model_max_latest_sort_rn_cnt_12m/secd_model_max_latest_sort_rn_cnt_12m_N_N.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(88000), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(15380, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.6578",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(128), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("206000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("135500",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.09892519"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase97() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_8_secd_model_CardLmtUsedRateNoClose/secd_model_CardLmtUsedRateNoClose_10.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(39428), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(11219, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("160000", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.0000",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("276000",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.18271664"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase98() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_8_secd_model_CardLmtUsedRateNoClose/secd_model_CardLmtUsedRateNoClose_NA_1.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("4394",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.14039181"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase99() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_8_secd_model_CardLmtUsedRateNoClose/secd_model_CardLmtUsedRateNoClose_NA_2.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.21048286"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase100() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_8_secd_model_CardLmtUsedRateNoClose/secd_model_CardLmtUsedRateNoClose_NA_3.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(160000), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(58666), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(13366, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.21048286"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase101() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_9_secd_model_CardLmtNoClose/secd_model_CardLmtNoClose_10.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(10), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(1), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("12200", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3833",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("10", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.48270355"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(15000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("7.7522"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(22),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(16),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(12200),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(7),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase102() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_9_secd_model_CardLmtNoClose/secd_model_CardLmtNoClose_10_2.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(10), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(1), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("12200", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3833",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("10", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.48270355"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(15000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("7.7522"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(22),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(16),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(12200),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(7),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase103() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_9_secd_model_CardLmtNoClose/secd_model_CardLmtNoClose_10_3.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(10), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(1), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("12200", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3833",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("10", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.48270355"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(15000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("7.7522"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(22),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(16),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(12200),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(7),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase104() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_9_secd_model_CardLmtNoClose/secd_model_CardLmtNoClose_20.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(10), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(1), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("12200", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("3833",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("10", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.48270355"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(15000), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("7.7522"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(7),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(22),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(16),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(12200),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(7),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,C7,D1,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase105() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_9_secd_model_CardLmtNoClose/secd_model_CardLmtNoClose_30.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(20), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(4), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("20", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.3333",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("30",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.23310382"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase106() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_9_secd_model_CardLmtNoClose/secd_model_CardLmtNoClose_40.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(20), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(5), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("20", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("0.2500",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("40",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("10",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.23310382"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase107() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_9_secd_model_CardLmtNoClose/secd_model_CardLmtNoClose_NA_1.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("-999", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("4394",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("0", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("24550",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(-9999), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("2.14039181"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(0), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("-999"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(2),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(8),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A3,A5,A6,B1,C7,D3,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase108() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_9_secd_model_CardLmtNoClose/secd_model_CardLmtNoClose_NA_2.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(0), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.21048286"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }

    @Test
    public void testRhModelCase109() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("val_9_secd_model_CardLmtNoClose/secd_model_CardLmtNoClose_NA_3.xml");
        String parseHtml = IOUtils.toString(in, "UTF-8");

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.XML, parseHtml);

        IncomeScore.IncomeCodeRuleParam incomeParam = incomeScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> incomeMap = incomeScore.getCodeRuleScore(incomeParam);

        Assert.assertEquals(new Integer(10), incomeParam.getNormalCardLmtMax());
        Assert.assertEquals(new Integer(3), incomeParam.getNormalCardAvgLmt());
        Assert.assertEquals(new Integer(0), incomeParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(0), incomeParam.getCarLoanAmtSum());
        Assert.assertEquals(0, incomeMap.get("rhGradeModel1"));

        QualificationScoreV2.QualificationRuleParam qualificationParam = qualificationScoreV2
                .getCodeRuleParam(rhParamMap);
        Map<String, Object> qualificationMap = qualificationScoreV2.getCodeRuleScore(qualificationParam);

        Assert.assertEquals("20",qualificationParam.getSecdModelAcademic());
        Assert.assertEquals("27", qualificationParam.getSecdModelAge());
        Assert.assertEquals("36750", qualificationParam.getSecdModelCardOverdueAmtSum());
        Assert.assertEquals("0", qualificationParam.getSecdModelGender());
        Assert.assertEquals("-999",  qualificationParam.getSecdModelHb01BlsAmt24m());
        Assert.assertEquals("20", qualificationParam.getSecdModelHb01Maritalstate());
        Assert.assertEquals("0",qualificationParam.getSecdModelHb01RegisteredAddress());
        Assert.assertEquals("-999",qualificationParam.getSecdModelHousingLoanAmtSum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLastXiaojinLoanDays());
        Assert.assertEquals("-999",qualificationParam.getSecdModelLnOldDateDiff());
        Assert.assertEquals("2", qualificationParam.getSecdModelLoanSumMaxDuration());
        Assert.assertEquals("-999", qualificationParam.getSecdModelMaxCreditLimitAmountNoClose());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt3m());
        Assert.assertEquals("3", qualificationParam.getSecdModelMaxLatestSortRnCnt12m());
        Assert.assertEquals("-999",qualificationParam.getSecdModelMaxLoanCurrOverdueAmount());
        Assert.assertEquals("-999",qualificationParam.getSecdModelUsedCreditLimitAmountRatioNoClosed());
        Assert.assertEquals(new Integer(146), qualificationParam.getCardFirstMonthNum());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtNoClose());
        Assert.assertEquals("-999",qualificationParam.getSecdModelCardLmtUsedRateNoClose());
        Assert.assertEquals(new BigDecimal("0.21048286"), qualificationMap.get("rhGradeModel2"));

        LiabilityScore.LiabilityRuleParam liabilityParam = liabilityScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> liabilityMap = liabilityScore.getCodeRuleScore(liabilityParam);

        Assert.assertEquals(new Integer(39500), liabilityParam.getRepayAmtCard());
        Assert.assertEquals(new Integer(0),liabilityParam.getRepayAmtLoan());
        Assert.assertEquals(new BigDecimal("28.4222"),liabilityMap.get("rhGradeModel3"));

        EmptyReportScore.EmptyReportRuleParam emptyReportParam = emptyReportScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> emptyReportMap = emptyReportScore.getCodeRuleScore(emptyReportParam);

        Assert.assertEquals(new Integer(8), emptyReportParam.getQueryForCardLast12monContSum());
        Assert.assertEquals(new Integer(0), emptyReportParam.getHousingLoanAmtSum());
        Assert.assertEquals(new Integer(170),emptyReportMap.get("rhGradeModel4"));

        ReasonScore.ReasonRuleParam reasonParam = reasonScore.getCodeRuleParam(rhParamMap);
        Map<String, Object> reasonMap = reasonScore.getCodeRuleScore(reasonParam);

        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardOverdueAmt());
        Assert.assertEquals(new Integer(0),reasonParam.getStandardCardBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getCardBaddebtCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanBaddebtCount());
        Assert.assertEquals(new Integer(0),reasonParam.getLoanOverdueNumLast24MonMax());
        Assert.assertEquals(new Integer(0),reasonParam.getBadLoanCount());
        Assert.assertEquals(new Integer(2),reasonParam.getFellbackCount());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast12MonMax());
        Assert.assertEquals(new Integer(8),reasonParam.getCardOverdueNumLast6MonMax());
        Assert.assertEquals(new Integer(26),reasonParam.getCardOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(17),reasonParam.getCardOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getFarmingLoanAmtSum());
        Assert.assertEquals(new Integer(1630656),reasonParam.getGuaranteeAmtSum());
        Assert.assertEquals(new Integer(1),reasonParam.getJudgementCount());
        Assert.assertEquals(new Integer(36750),reasonParam.getCardOverdueAmtSum());
        Assert.assertEquals(new Integer(41),reasonParam.getQueryForloanCardLast3monContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast6MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdCustomerLoanOverdueLast12MonContSum());
        Assert.assertEquals(new Integer(0),reasonParam.getSecdLoanOverdueNumLast12MonMax());
        Assert.assertEquals("A5,A6,B2,C1,C2,C3,C4,D1,E1,E3",reasonMap.get("rigidRefuse"));
    }





}
