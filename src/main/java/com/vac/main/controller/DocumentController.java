package com.vac.main.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vac.main.controller.request.PageRequest;
import com.vac.main.controller.response.DocEntryResponse;
import com.vac.main.controller.response.DocListResponse;
import com.vac.main.data.dto.DocumentDto;
import com.vac.main.services.DocumentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/doc")
public class DocumentController {

    @Autowired
    private DocumentService docService;

    @GetMapping("/list")
    public DocListResponse getDocList(@Valid PageRequest request) {
        List<DocumentDto> dtoList = docService.getList(request.getPageNumber(), request.getPageSize());
        Long pageCount = docService.getPageCount(request.getPageSize());
        List<DocEntryResponse> docList = dtoList.stream()
                .map(dto -> new DocEntryResponse(dto))
                .collect(Collectors.toList());
        var response = new DocListResponse();
        response.setDocEntires(docList);
        response.setPageNumber(request.getPageNumber());
        response.setPageSize(request.getPageSize());
        response.setTotalPages(pageCount);
        return response;
    }

}
