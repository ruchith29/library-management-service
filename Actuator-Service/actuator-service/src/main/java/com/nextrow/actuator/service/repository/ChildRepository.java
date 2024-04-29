package com.nextrow.actuator.service.repository;


import com.nextrow.actuator.service.entity.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Children,Integer> {

}
