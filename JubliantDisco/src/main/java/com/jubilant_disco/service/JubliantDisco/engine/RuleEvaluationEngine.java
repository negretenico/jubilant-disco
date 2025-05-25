package com.jubilant_disco.service.JubliantDisco.engine;

import com.jubilant_disco.service.JubliantDisco.model.EvaluationResult;
import com.jubilant_disco.service.JubliantDisco.model.Rule;
import org.mvel2.MVEL;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RuleEvaluationEngine {
    public EvaluationResult evaluate(Rule rule, Map<String, Object> context) {
        try {
            Object result = MVEL.eval(rule.condition().toExpression(), context);
            return new EvaluationResult(Boolean.TRUE.equals(result), rule.variant());
        } catch (Exception e) {
            // log and skip
            return new EvaluationResult(false, null);
        }
    }
}