package com.hackbright.houseeApp.services;

import com.hackbright.houseeApp.dtos.ManagerDto;
import com.hackbright.houseeApp.entities.Manager;
import com.hackbright.houseeApp.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Optional;


@Service
public class ManagerServiceIMPL implements ManagerService {
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //a few more...
    @Override
    @Transactional
    public List<String> addManager(ManagerDto managerDto){
        List<String> response = new ArrayList<>();

        Manager manager = new Manager(managerDto);
        managerRepository.saveAndFlush(manager);
        response.add("http://localhost:8080/loginmanager.html");
        return response;
    }

    @Override
    public List<String> managerLogin(ManagerDto managerDto){
        List<String> response = new ArrayList<>();
        Optional<Manager> managerOptional = managerRepository.findByManagername(managerDto.getManagername());

        if (managerOptional.isPresent()){
            // check to see if the password matches the hash by two conditional statements
            if (passwordEncoder.matches(managerDto.getPassword(), managerOptional.get().getPassword())){
                response.add("http://localhost:8080/managerhome.html");
                response.add(String.valueOf(managerOptional.get().getId()));
            } else {
                response.add("Manager Name or password incorrect");
            }
        } else {
            response.add("Manager Name or password incorrect");
        }
        return response;
    }
}
