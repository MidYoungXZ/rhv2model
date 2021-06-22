package cn.demo.rhv2model.service.rule.impl;

import cn.demo.rhv2model.service.rule.RhModelRule;
import cn.demo.rhv2model.service.rule.impl.coderule.*;
import cn.yxzmmm.rhv2model.service.rule.impl.coderule.*;

import java.util.ArrayList;

public class ZhongXinRhModelCodeRule extends RhModelRule {

    public ZhongXinRhModelCodeRule() {
        codeScoreRules = new ArrayList<>();
        codeScoreRules.add(new ZhongXinIncomeScore());
        codeScoreRules.add(new QualificationScoreV2());
        codeScoreRules.add(new LiabilityScore());
        codeScoreRules.add(new EmptyReportScore());
        codeScoreRules.add(new ZhongXinReasonScore());
    }
}
