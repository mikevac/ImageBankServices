package com.vac.main.controller.response;

import java.math.BigInteger;

import com.vac.main.data.dto.DocumentDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocEntryResponse {
    private BigInteger docId;
    private String fileName;

    public DocEntryResponse(DocumentDto dto) {
        docId = dto.docId();
        fileName = dto.fileName();
    }

}
