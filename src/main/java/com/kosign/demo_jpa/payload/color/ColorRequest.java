package com.kosign.demo_jpa.payload.color;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonInclude
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
public class ColorRequest {
    private String colorName;

    @Builder
    public ColorRequest(String colorName) {
        this.colorName = colorName;
    }
}
