package com.jubilant_disco.service.JubliantDisco.controller;

import com.jubilant_disco.service.JubliantDisco.model.EvaluationResult;
import com.jubilant_disco.service.JubliantDisco.model.Result;
import com.jubilant_disco.service.JubliantDisco.service.FeatureFlagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/rule")
public class RuleEvaluationController {
    FeatureFlagService featureFlagService;

    public RuleEvaluationController(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EvaluationResult> evaluateFlag(
            @PathVariable UUID id,
            @RequestParam Map<String, String> contextParams
    ) {
        Result<EvaluationResult> result = featureFlagService.evaluateRules(id, new HashMap<>(contextParams));
        if (result.isFailure()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result.data());
    }
}
