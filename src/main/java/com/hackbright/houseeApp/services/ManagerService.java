package com.hackbright.houseeApp.services;

import com.hackbright.houseeApp.dtos.ManagerDto;

import javax.transaction.Transactional;
import java.util.List;

public interface ManagerService {
    @Transactional
    List<String> addManager(ManagerDto managerDto);

    List<String> managerLogin(ManagerDto managerDto);
}
