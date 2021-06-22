package cn.demo.rhv2model;

import cn.demo.rhv2model.constant.RhInputTypeEnum;
import cn.demo.rhv2model.service.RhGradeModelService;
import cn.demo.rhv2model.service.rule.impl.coderule.*;
import cn.yxzmmm.rhv2model.service.rule.impl.coderule.*;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Base64;
import java.util.Map;


public class RhModelHtmlTest {

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
    public void testRhModel00() throws Exception {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("00.html");
        String parseHtml = new String(Base64.getEncoder().encode(IOUtils.toString(in, "UTF-8").getBytes("UTF-8")));

        Map<String, Object> rhParamMap = rhGradeModelService.getRhParamMap(RhInputTypeEnum.HTML, parseHtml);
    }
}
