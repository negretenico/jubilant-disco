package com.jubilant_disco.service.JubliantDisco.repo;

import com.jubilant_disco.service.JubliantDisco.model.FeatureFlag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface FeatureFlagRepo extends MongoRepository<FeatureFlag, UUID> {
}
