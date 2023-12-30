package com.kosign.phone_shop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginationDTO {
    private int pageSize;
    private int pageNumber;
    private int totalPages;
    private long totalElements;
    private long numberOfElement;
    private boolean first;
    private boolean last;
    private boolean empty;

}
