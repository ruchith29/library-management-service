package com.nextrow.sampleApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class Controller {

    @Autowired
    private Details details;

    @GetMapping(value = "/welcome")
    public String welcomePage(){
        return "Home";
    }

    @GetMapping(value="/details")
    public List<StudentTable> getDetails(){
        List<StudentTable> l= details.findAll();
        return l;
    }

    @PostMapping(value = "/save")
    public String enterValues(@RequestBody StudentTable table){
        details.save(table);
        return "Success";
    }
}
