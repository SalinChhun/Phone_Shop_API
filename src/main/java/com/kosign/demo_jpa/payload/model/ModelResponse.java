package com.kosign.demo_jpa.payload.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ModelResponse {

    private Integer id;

    private String name;

    @Builder
    public ModelResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
