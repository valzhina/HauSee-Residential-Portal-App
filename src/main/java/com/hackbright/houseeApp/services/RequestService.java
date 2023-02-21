package com.hackbright.houseeApp.services;

import com.hackbright.houseeApp.dtos.RequestDto;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface RequestService {
    List<RequestDto> getAllRequestsByUserId(Long userId);

    List<RequestDto> getAllRequestsByManagerId(Long managerId);

    @Transactional
    void addRequest(RequestDto requestDto, Long userId);

    Optional<RequestDto> getRequestById(Long requestId);

    @Transactional
    void deleteRequestById(Long requestId);

    @Transactional
    void updateRequestById(RequestDto requestDto);

    @Transactional
    void changeStatusRequestById(Long requestId);
}
