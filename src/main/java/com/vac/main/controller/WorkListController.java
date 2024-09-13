package com.vac.main.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vac.main.controller.request.PageRequest;
import com.vac.main.controller.response.WorkListEntryResponse;
import com.vac.main.controller.response.WorkListResponse;
import com.vac.main.data.dto.WorkListDto;
import com.vac.main.services.WorkListService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/worklist")
public class WorkListController {

    @Autowired
    private WorkListService workListService;

    @GetMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public WorkListResponse getWorkLists(@Valid PageRequest request) {
        List<WorkListDto> dtoList = workListService.getWorkLists(request.getPageNumber(), request.getPageSize());
        Long pageCount = workListService.getWorkListPageCount(request.getPageSize());
        return new WorkListResponse(request.getPageNumber(), request.getPageSize(), pageCount,
                dtoList.stream()
                        .map(dto -> new WorkListEntryResponse(dto))
                        .collect(Collectors.toList()));
    }

}
