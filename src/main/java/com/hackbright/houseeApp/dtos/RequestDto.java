package com.hackbright.houseeApp.dtos;

import com.hackbright.houseeApp.entities.Manager;
import com.hackbright.houseeApp.entities.Request;
import com.hackbright.houseeApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RequestDto implements Serializable {
    private Long id;
    private UserDto userDto;
    private ManagerDto managerDto;
    private String unit;
    private String problem;
    private java.sql.Date rDate;
    private java.sql.Time rTime;
    private String category;
    private String description;
    private String imgUrl;
    private String status;

    public RequestDto(Request request){
        if (request.getId() != null){
            this.id = request.getId();
        }
        if (request.getUnit() != null){
            this.unit = request.getUnit();
        }
        if (request.getProblem() != null){
            this.problem = request.getProblem();
        }
        if (request.getRDate() != null){
            this.rDate = request.getRDate();
        }
        if (request.getRTime() != null){
            this.rTime = request.getRTime();
        }
        if (request.getCategory() != null){
            this.category = request.getCategory();
        }
        if (request.getDescription() != null){
            this.description = request.getDescription();
        }
        if (request.getImgUrl() != null){
            this.imgUrl = request.getImgUrl();
        }
        if (request.getStatus() != null){
            this.status = request.getStatus();
        }
    }
}
