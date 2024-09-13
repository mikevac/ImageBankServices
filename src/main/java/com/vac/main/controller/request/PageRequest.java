package com.vac.main.controller.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {

    @Size(min = 1, max = 1000)
    private Long pageNumber;

    @Size(min = 10, max = 50)
    private Long pageSize;

}
