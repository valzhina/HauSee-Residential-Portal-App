package com.hackbright.houseeApp.controllers;

import com.hackbright.houseeApp.dtos.ManagerDto;
import com.hackbright.houseeApp.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/managers")
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addManager(@RequestBody ManagerDto managerDto) {
        String passHash = passwordEncoder.encode(managerDto.getPassword());
        managerDto.setPassword(passHash);
        return managerService.addManager(managerDto);
    }
    @PostMapping("/login")
    public List<String> managerLogin(@RequestBody ManagerDto managerDto){

        return managerService.managerLogin(managerDto);
    }
}