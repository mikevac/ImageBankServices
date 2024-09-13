package com.vac.main.controller.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkListResponse extends BaseResponse {

    private Long pageNumber;
    private Long pageSize;
    private Long totalPages;
    private List<WorkListEntryResponse> worklist;

    public WorkListResponse(Long pageNumber, Long pageSize, Long totalPages, List<WorkListEntryResponse> worklist) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.worklist = worklist;
    }
}
