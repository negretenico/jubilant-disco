package com.jubilant_disco.service.JubliantDisco.model;

public record Condition(String field, Operator operator, String value) {
    public String toExpression() {
        String fieldExpression = buildFieldExpression(field);

        return switch (operator) {
            case EQUALS -> fieldExpression + " == \"" + value + "\"";
            case NOT_EQUALS -> fieldExpression + " != \"" + value + "\"";
            case CONTAINS -> fieldExpression + " != null && " + fieldExpression + ".contains(\"" + value + "\")";
            case STARTS_WITH -> fieldExpression + " != null && " + fieldExpression + ".startsWith(\"" + value + "\")";
            case GREATER_THAN -> fieldExpression + " > " + value;
            case LESS_THAN -> fieldExpression + " < " + value;
        };
    }

    private String buildFieldExpression(String field) {
        // Handle nested fields like "user.role" -> "['user']['role']"
        String[] parts = field.split("\\.");
        StringBuilder expression = new StringBuilder();

        for (String part : parts) {
            expression.append("['").append(part).append("']");
        }

        return expression.toString();
    }
}
