package com.jubilant_disco.service.JubliantDisco.service;

import com.jubilant_disco.service.JubliantDisco.engine.RuleEvaluationEngine;
import com.jubilant_disco.service.JubliantDisco.model.EnvConfig;
import com.jubilant_disco.service.JubliantDisco.model.EvaluationResult;
import com.jubilant_disco.service.JubliantDisco.model.FeatureFlag;
import com.jubilant_disco.service.JubliantDisco.model.Result;
import com.jubilant_disco.service.JubliantDisco.repo.FeatureFlagRepo;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Service
public class FeatureFlagService {
    FeatureFlagRepo featureFlags;
    RuleEvaluationEngine ruleEvaluationEngine;

    public FeatureFlagService(FeatureFlagRepo featureFlags, RuleEvaluationEngine ruleEvaluationEngine) {
        this.featureFlags = featureFlags;
        this.ruleEvaluationEngine = ruleEvaluationEngine;
    }

    public Result<FeatureFlag> save(FeatureFlag featureFlag) {
        try {
            featureFlag.setId(UUID.randomUUID());
            return Result.success(featureFlags.insert(featureFlag));
        } catch (Exception e) {
            return Result.failure(e.getLocalizedMessage());
        }
    }

    public Result<FeatureFlag> update(UUID id, Function<FeatureFlag, FeatureFlag> updater) {
        Optional<FeatureFlag> possibleFlag = featureFlags.findById(id);
        if (possibleFlag.isEmpty()) {
            return Result.failure(String.format("Could not find flag with id={%s}", id));
        }
        FeatureFlag flag = possibleFlag.get();
        var updatedFlag = updater.apply(flag);
        try {
            return Result.success(featureFlags.save(updatedFlag));
        } catch (Exception e) {
            return Result.failure(e.getLocalizedMessage());
        }
    }

    public Result<Boolean> remove(UUID id) {
        try {
            featureFlags.deleteById(id);
            return Result.success(true);
        } catch (Exception e) {
            return Result.failure(e.getLocalizedMessage());
        }
    }

    public Result<FeatureFlag> getById(UUID id) {
        Optional<FeatureFlag> possibleFlag = featureFlags.findById(id);
        return possibleFlag.map(Result::success).orElseGet(() -> Result.failure("Could not find this feature flag " + id));
    }

    public Result<EvaluationResult> evaluateRules(UUID id, String env, Map<String, Object> context) {
        Result<FeatureFlag> result = getById(id);
        if (result.isFailure()) {
            return Result.failure(result.errorMsg());
        }
        FeatureFlag flag = result.data();
        EnvConfig config = flag.getEnvironments().getOrDefault(env, null);
        if (Objects.isNull(config)) {
            return Result.failure("We do not have this env");
        }
        return config.rules().stream()
                .map(rule -> ruleEvaluationEngine.evaluate(rule, context))
                .filter(EvaluationResult::enabled)
                .findFirst()
                .map(Result::success)
                .orElseGet(() -> Result.success(new EvaluationResult(false, config.defaultValue())));
    }
}
