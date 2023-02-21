package com.hackbright.houseeApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hackbright.houseeApp.dtos.RequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Requests")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    @JsonBackReference
    private Manager manager;

    @Column
    private String unit;

    @Column(columnDefinition = "text")
    private String problem;

    @Column
    private java.sql.Date rDate;

    @Column
    private java.sql.Time rTime;

    @Column
    private String category;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String imgUrl;

    @Column
    private String status;

    public Request(RequestDto requestDto){

        if (requestDto.getUnit() != null){
            this.unit = requestDto.getUnit();
        }
        if (requestDto.getProblem() != null){
            this.problem = requestDto.getProblem();
        }

        if (requestDto.getDescription() != null){
            this.description = requestDto.getDescription();
        }

        if (requestDto.getImgUrl() != null){
            this.imgUrl = requestDto.getImgUrl();
        }

        if (requestDto.getCategory() != null){
            this.category = requestDto.getCategory();
        }

        if (requestDto.getStatus() != null){
            this.status = requestDto.getStatus();
        }

        if (requestDto.getRDate() != null){
            this.rDate = requestDto.getRDate();
        }

        if (requestDto.getRTime() != null){
            this.rTime = requestDto.getRTime();
        }

    }
}

