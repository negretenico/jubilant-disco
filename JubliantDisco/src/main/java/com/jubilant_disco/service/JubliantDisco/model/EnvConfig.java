package com.jubilant_disco.service.JubliantDisco.model;

import java.util.List;
import java.util.Map;

public record EnvConfig(Map<String, Object> variants, List<Rule> rules, Object defaultValue, String description) {
}