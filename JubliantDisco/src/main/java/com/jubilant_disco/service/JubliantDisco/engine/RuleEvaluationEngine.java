package com.jubilant_disco.service.JubliantDisco.engine;

import com.jubilant_disco.service.JubliantDisco.model.EvaluationResult;
import com.jubilant_disco.service.JubliantDisco.model.Rule;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RuleEvaluationEngine {
    private final ExpressionParser parser = new SpelExpressionParser();

    public EvaluationResult evaluate(Rule rule, Map<String, Object> context) {
        try {
            String expression = rule.condition().toExpression();
            Expression parseExpression = parser.parseExpression(expression);
            StandardEvaluationContext spelContext = new StandardEvaluationContext(context);
            spelContext.addPropertyAccessor(new MapAccessor());
            return new EvaluationResult(Boolean.TRUE.equals(parseExpression.getValue(spelContext, Boolean.class)), rule.variant());
        } catch (Exception e) {
            return new EvaluationResult(false, null);
        }
    }
}