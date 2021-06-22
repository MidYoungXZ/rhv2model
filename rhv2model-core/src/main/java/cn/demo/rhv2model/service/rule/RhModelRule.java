package cn.demo.rhv2model.service.rule;

import cn.demo.rhv2model.resp.RhModelRes;
import cn.demo.rhv2model.service.rule.impl.coderule.CodeRuleParam;
import cn.demo.rhv2model.service.rule.impl.coderule.CodeScoreRule;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public abstract class RhModelRule {

    protected List<CodeScoreRule> codeScoreRules;

    public RhModelRes getRhModelRes(Map<String, Object> facts) {
        JSONObject ruleResult = new JSONObject();

        for (CodeScoreRule<? super CodeRuleParam> codeScoreRule : codeScoreRules) {
            ruleResult.putAll(codeScoreRule
                    .getCodeRuleScore(codeScoreRule
                            .getCodeRuleParam(facts)));
        }

        return JSONObject.toJavaObject(ruleResult, RhModelRes.class);
    }
}
