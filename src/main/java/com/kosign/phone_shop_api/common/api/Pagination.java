package com.kosign.phone_shop_api.common.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    private boolean last;

    private boolean first;

    private Integer size;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonProperty("current_total_elements")
    private Integer currentTotalElements;

    @JsonProperty("total_elements")
    private Long totalElements;

    private boolean empty;


    public Pagination(Page<?> page) {

        this.last = page.isLast();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.currentPage = page.getNumber();
        this.first = page.isFirst();
        this.size = page.getSize();
        this.empty = page.isEmpty();
        this.currentTotalElements = page.getNumberOfElements();
    }
}
