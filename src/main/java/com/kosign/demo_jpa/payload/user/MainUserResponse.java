package com.kosign.demo_jpa.payload.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.demo_jpa.common.api.Pagination;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@JsonInclude
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonPropertyOrder({"users", "pagination"})
public class MainUserResponse {

    private Pagination pagination;
    private List<AllUserResponse> users;

    @Builder
    public MainUserResponse(Page<?> page, List<AllUserResponse> users) {
        this.pagination = new Pagination(page);
        this.users = users;
    }
}
