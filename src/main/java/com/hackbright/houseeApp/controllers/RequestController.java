package com.hackbright.houseeApp.controllers;

import com.hackbright.houseeApp.dtos.RequestDto;
import com.hackbright.houseeApp.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/requests")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @GetMapping("/user/{userId}")
    public List<RequestDto> getRequestByUser(@PathVariable Long userId){
        return requestService.getAllRequestsByUserId(userId);
    }

    @GetMapping("/manager/{managerId}")
    public List<RequestDto> getRequestByManager(@PathVariable Long managerId){
        return requestService.getAllRequestsByManagerId(managerId);
    }

    //note ID need to be corrected
    @GetMapping("/{requestId}")
    public Optional<RequestDto> getRequestById(@PathVariable Long noteId){
        return requestService.getRequestById(noteId);
    }

    @PostMapping("/user/{userId}")
    public void addRequest(@RequestBody RequestDto requestDto,@PathVariable Long userId) {
        requestService.addRequest(requestDto, userId);
    }

    @DeleteMapping("/{requestId}")
    public void deleteRequestById(@PathVariable Long requestId){
        requestService.deleteRequestById(requestId);
    }

    @PutMapping("/{requestId}")
    public void changeStatusRequestById(@PathVariable Long requestId){
        requestService.changeStatusRequestById(requestId);
    }

    @PutMapping
    public void updateRequest(@RequestBody RequestDto requestDto){
        requestService.updateRequestById(requestDto);
    }
}
