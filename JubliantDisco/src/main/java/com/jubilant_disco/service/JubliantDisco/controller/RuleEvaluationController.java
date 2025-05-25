package com.jubilant_disco.service.JubliantDisco.controller;

import com.jubilant_disco.service.JubliantDisco.model.EvaluationResult;
import com.jubilant_disco.service.JubliantDisco.model.Result;
import com.jubilant_disco.service.JubliantDisco.service.FeatureFlagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/rule")
public class RuleEvaluationController {
    FeatureFlagService featureFlagService;

    public RuleEvaluationController(FeatureFlagService featureFlagService) {
        this.featureFlagService = featureFlagService;
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<Result<EvaluationResult>> evaluateFlag(
            @PathVariable UUID id,
            @RequestBody Map<String, Object> contextParams
    ) {
        Result<EvaluationResult> result = featureFlagService.evaluateRules(id, contextParams);
        if (result.isFailure()) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }
}
