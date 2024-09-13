package com.vac.main.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vac.main.data.dto.WorkListDto;
import com.vac.main.data.entity.WorkListEntity;
import com.vac.main.repositories.WorkListRepository;

@Service
public class WorkListService extends IBService {

    @Autowired
    private WorkListRepository workListRepo;

    @Transactional(readOnly = true)
    public List<WorkListDto> getWorkLists(Long pageNumber, Long pageSize) {
        List<WorkListEntity> entities = workListRepo.fetchPage(getUser().getUserId(), pageNumber, pageSize);
        return entities.stream().map(entity -> new WorkListDto(entity.getWorkListId(), entity.getName()))
                .collect(Collectors.toList());
    }

    public Long getWorkListPageCount(Long pageSize) {
        Long totalRowCount = workListRepo.count(getUser().getUserId());
        return totalRowCount / pageSize;
    }

}
