package com.vac.main.controller.response;

import java.math.BigInteger;

import com.vac.main.data.dto.WorkListDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkListEntryResponse {

    private BigInteger worklistId;
    private String name;

    public WorkListEntryResponse(WorkListDto dto) {
        worklistId = dto.worklistId();
        name = dto.worklistName();
    }
}
