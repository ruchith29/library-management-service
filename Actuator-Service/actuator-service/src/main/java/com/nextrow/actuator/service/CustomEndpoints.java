package com.nextrow.actuator.service;


import com.nextrow.actuator.service.entity.Children;
import com.nextrow.actuator.service.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Component
@Endpoint(id = "creator-details")
public class  CustomEndpoints{

    @Autowired
    private ChildRepository childRepository;

   @WriteOperation
   public ResponseEntity<String> addChildren(@RequestBody Children children){
       childRepository.save(children);
       return ResponseEntity.ok("Child Added Successfully");
   }

/*    public void addChildren(Children children){
       this.childRepository.save(children);
    }*/

    @ReadOperation
    public List<Children> postDetails(){
        List<Children> ls=childRepository.findAll();
        return ls;
    }

    @DeleteOperation
    public String deleteChild(int id){
        Children children=childRepository.findById(id).orElseThrow(RuntimeException::new);
        childRepository.delete(children);
        return "Child Deleted Successfully!";
    }


/*  Map<String, List<String>> mp=new HashMap<>();

    List<String> ls=new ArrayList<>();

    @WriteOperation
    public void addDetails(@Selector List<String> names){
        ls.addAll(names);
    }

    @ReadOperation
    public List<String> postDetails(){
        return ls;
    }

    @DeleteOperation
    public void DeleteChild(@Selector String name){
        ls.remove(name);
    }*/

}