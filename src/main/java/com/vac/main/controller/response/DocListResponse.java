package com.vac.main.controller.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocListResponse extends BaseResponse {

    private Long pageNumber;
    private Long pageSize;
    private Long totalPages;
    private List<DocEntryResponse> docEntires;

}
