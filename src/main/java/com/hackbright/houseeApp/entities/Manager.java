package com.hackbright.houseeApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hackbright.houseeApp.dtos.ManagerDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Managers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String managername;

    @Column
    private String password;

    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonBackReference
    private Set<Request> requestSet = new HashSet<>();

    public Manager(ManagerDto managerDto) {
        if (managerDto.getManagername() != null) {
            this.managername = managerDto.getManagername();
        }
        if (managerDto.getPassword() != null) {
            this.password = managerDto.getPassword();
        }
    }

}
