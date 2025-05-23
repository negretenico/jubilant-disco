package com.jubilant_disco.service.JubliantDisco.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jubilant_disco.service.JubliantDisco.model.FeatureFlag;
import com.jubilant_disco.service.JubliantDisco.model.Result;
import com.jubilant_disco.service.JubliantDisco.service.FeatureFlagService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api/v1/feature-flags")
public class FeatureFlagController {
    FeatureFlagService featureFlagService;
    ObjectMapper simpleObjectMapper;

    public FeatureFlagController(FeatureFlagService featureFlagService, ObjectMapper simpleObjectMapper) {
        this.featureFlagService = featureFlagService;
        this.simpleObjectMapper = simpleObjectMapper;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FeatureFlag> createFlag(@RequestBody FeatureFlag request) {
        Result<FeatureFlag> result = featureFlagService.save(request);
        if (result.isFailure()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(result.data());
    }

    @PatchMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FeatureFlag> updateFlag(@PathVariable UUID id, @RequestBody FeatureFlag updateFlag) {
        Consumer<FeatureFlag> patcher = (flag) -> {
            try {
                simpleObjectMapper.updateValue(flag, updateFlag);
            } catch (JsonMappingException e) {
                throw new RuntimeException(e);
            }
        };
        Result<FeatureFlag> res = featureFlagService.update(id, patcher);
        if (res.isFailure() && res.errorMsg().contains("find flag with")) {
            return ResponseEntity.notFound().build();
        }
        if (res.isFailure()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(res.data());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FeatureFlag> getFeatureFlag(@PathVariable UUID id) {
        Result<FeatureFlag> flagResult = featureFlagService.getById(id);
        if (flagResult.isFailure()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flagResult.data());
    }

}
