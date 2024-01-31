package com.kosign.demo_jpa.payload.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.demo_jpa.entity.Model;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude
public class ModelRequest {

    private Integer brandId;

    private String modelName;

    @Builder
    public ModelRequest(Integer brandId, String modelName) {
        this.brandId = brandId;
        this.modelName = modelName;
    }
}
