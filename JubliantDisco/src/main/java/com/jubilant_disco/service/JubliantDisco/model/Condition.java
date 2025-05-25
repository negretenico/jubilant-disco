package com.jubilant_disco.service.JubliantDisco.model;

public record Condition(String field, Operator operator, String value) {
    public String toExpression() {
        return switch (operator) {
            case EQUALS -> field + " == \"" + value + "\"";
            case NOT_EQUALS -> field + " != \"" + value + "\"";
            case CONTAINS -> field + " != null && " + field + ".contains(\"" + value + "\")";
            case STARTS_WITH -> field + " != null && " + field + ".startsWith(\"" + value + "\")";
            case GREATER_THAN -> field + " > " + value;
            case LESS_THAN -> field + " < " + value;
        };
    }
}
