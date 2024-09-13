package com.vac.main.services;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vac.main.data.dto.DocumentDto;
import com.vac.main.data.entity.DocumentEntity;
import com.vac.main.repositories.DocumentRepository;

@Service
public class DocumentService extends IBService {

    @Autowired
    private DocumentRepository docRepo;

    @Transactional(readOnly = true)
    public List<DocumentDto> getList(Long pageNumber, Long pageSize) {
        List<DocumentEntity> docEntityList = docRepo.getList(getUser().getUserId(), pageNumber, pageSize);
        return docEntityList.stream().map(entity -> new DocumentDto(entity.getDocId(), entity.getFileName()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Long getPageCount(Long pageSize) {
        Long rowCount = docRepo.getRowCount(getUser().getUserId());
        return rowCount / pageSize;
    }

    @Transactional(readOnly = true)
    public List<DocumentDto> getDocForWorkList(Long pageNumber, Long pageSize, BigInteger workListId) {

        List<DocumentEntity> docEntityList = docRepo.getDocForWorklist(getUser().getUserId(), pageNumber, pageSize,
                workListId);
        return docEntityList.stream().map(entity -> new DocumentDto(entity.getDocId(), entity.getFileName()))
                .collect(Collectors.toList());
    }
}
