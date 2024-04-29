package com.nextrow.actuator.service.controller;

/*import com.nextrow.actuator.service.CustomActuator;*/
import com.nextrow.actuator.service.entity.Children;
import com.nextrow.actuator.service.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("/children")
@RestController
public class ChildController {

    @Autowired
    private ChildRepository childRepository;


    @Autowired
    private RestTemplate restTemplate;

   // @Autowired
    //private CustomActuator customActuator;


    @GetMapping("/home")
    public String welcomePage(){
        return "Welcome Home Children!" ;
    }

    @GetMapping("/getAll")
    public List<Children> getAll(){
        List<Children> ls=childRepository.findAll();
        return ls;
    }

    @PostMapping("/addChildren")
    public ResponseEntity<String> addChildren(@RequestBody Children children){
        childRepository.save(children);
        return ResponseEntity.ok("Child Added Successfully");
    }

}
