package cn.demo.rhv2model.service.rule.impl.coderule;

import java.util.Map;

public interface CodeScoreRule<T extends CodeRuleParam> {

    T getCodeRuleParam(Map<String, Object> facts);

    Map<String, Object> getCodeRuleScore(T codeRuleParam);
}
