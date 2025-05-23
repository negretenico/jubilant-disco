package com.jubilant_disco.service.JubliantDisco.service;

import com.jubilant_disco.service.JubliantDisco.model.FeatureFlag;
import com.jubilant_disco.service.JubliantDisco.model.Result;
import com.jubilant_disco.service.JubliantDisco.repo.FeatureFlagRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class FeatureFlagService {
    FeatureFlagRepo featureFlags;

    public FeatureFlagService(FeatureFlagRepo featureFlags) {
        this.featureFlags = featureFlags;
    }

    public Result<FeatureFlag> save(FeatureFlag featureFlag) {
        try {
            featureFlag.setId(UUID.randomUUID());
            return Result.success(featureFlags.insert(featureFlag));
        } catch (Exception e) {
            return Result.failure(e.getLocalizedMessage());
        }
    }

    public Result<FeatureFlag> update(UUID id, Consumer<FeatureFlag> updater) {
        Optional<FeatureFlag> possibleFlag = featureFlags.findById(id);
        if (possibleFlag.isEmpty()) {
            return Result.failure(String.format("Could not find flag with id={%s}", id));
        }
        FeatureFlag flag = possibleFlag.get();
        updater.accept(flag);
        try {
            return Result.success(featureFlags.save(flag));
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
}
