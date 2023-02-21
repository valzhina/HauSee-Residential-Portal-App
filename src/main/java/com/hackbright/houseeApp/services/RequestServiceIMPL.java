package com.hackbright.houseeApp.services;

import com.hackbright.houseeApp.dtos.RequestDto;
import com.hackbright.houseeApp.entities.Manager;
import com.hackbright.houseeApp.entities.Request;
import com.hackbright.houseeApp.entities.User;
import com.hackbright.houseeApp.repositories.ManagerRepository;
import com.hackbright.houseeApp.repositories.RequestRepository;
import com.hackbright.houseeApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RequestServiceIMPL implements RequestService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<RequestDto> getAllRequestsByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()){
            List<Request> requestList = requestRepository.findAllByUserEquals(userOptional.get());
            return requestList.stream().map(request -> new RequestDto(request)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<RequestDto> getAllRequestsByManagerId(Long managerId){
        Optional<Manager> managerOptional = managerRepository.findById(managerId);
        if (managerOptional.isPresent()){
            List<Request> requestList = requestRepository.findAllByManagerEquals(managerOptional.get());
            return requestList.stream().map(request -> new RequestDto(request)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void addRequest(RequestDto requestDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Manager> managerOptional = managerRepository.findById((long) 1);
        Request request = new Request(requestDto);
        userOptional.ifPresent(request::setUser);
        managerOptional.ifPresent(request::setManager);
        requestRepository.saveAndFlush(request);

    }

    @Override
    public Optional<RequestDto> getRequestById(Long requestId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if (requestOptional.isPresent()){
            return Optional.of(new RequestDto(requestOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteRequestById(Long requestId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        requestOptional.ifPresent(request -> requestRepository.delete(request));
    }

    @Override
    @Transactional
    public void changeStatusRequestById(Long requestId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        requestOptional.ifPresent(request -> {
            request.setStatus("closed");
            requestRepository.saveAndFlush(request);
        });
    }

    @Override
    @Transactional
    public void updateRequestById(RequestDto requestDto) {
        Optional<Request> requestOptional = requestRepository.findById(requestDto.getId());
        requestOptional.ifPresent(request -> {
//            request.setId(requestDto.getId());
            request.setUnit(requestDto.getUnit());
            request.setProblem(requestDto.getProblem());
            request.setRDate(requestDto.getRDate());
            request.setRTime(requestDto.getRTime());
            request.setCategory(requestDto.getCategory());
            request.setDescription(requestDto.getDescription());
            request.setImgUrl(requestDto.getImgUrl());
            requestRepository.saveAndFlush(request);
        });
    }
}
