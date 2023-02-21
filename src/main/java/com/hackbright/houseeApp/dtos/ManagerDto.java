package com.hackbright.houseeApp.dtos;

import com.hackbright.houseeApp.entities.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ManagerDto implements Serializable {
    private Long id;
    private String managername;
    private String password;
    private Set<RequestDto> requestDtoSet = new HashSet<>();

    public ManagerDto(Manager manager) {
        if (manager.getId() != null) {
            this.id = manager.getId();
        }
        if (manager.getManagername() != null) {
            this.managername = manager.getManagername();
        }
        if (manager.getPassword() != null) {
            this.password = manager.getPassword();
        }
    }
}
