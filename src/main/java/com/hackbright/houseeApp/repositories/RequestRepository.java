package com.hackbright.houseeApp.repositories;


import com.hackbright.houseeApp.entities.Request;
import com.hackbright.houseeApp.entities.User;
import com.hackbright.houseeApp.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByUserEquals(User user);
    List<Request> findAllByManagerEquals(Manager manager);
}