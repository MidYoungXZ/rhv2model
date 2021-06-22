package cn.demo.rhv2model.service.rule.impl;

import cn.demo.rhv2model.service.rule.RhModelRule;
import cn.demo.rhv2model.service.rule.impl.coderule.*;
import cn.yxzmmm.rhv2model.service.rule.impl.coderule.*;

import java.util.ArrayList;

public class HaierRhModelCodeRule extends RhModelRule {

    public HaierRhModelCodeRule() {
        codeScoreRules = new ArrayList<>();
        codeScoreRules.add(new IncomeScore());
        codeScoreRules.add(new QualificationScoreV2());
        codeScoreRules.add(new LiabilityScore());
        codeScoreRules.add(new NanjingEmptyReportScore());
        codeScoreRules.add(new NanjingReasonScore());
    }
}
