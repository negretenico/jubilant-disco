package com.jubilant_disco.service.JubliantDisco.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Document(collection = "feature-flags")
public class FeatureFlag {
    private Map<String, Object> variants;
    private List<Rule> rules;
    @Id
    private UUID id;
    @Field(name = "flag_name")
    @Indexed(unique = true)
    private String flagName;
    private Object defaultValue;
}
