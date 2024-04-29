package com.nextrow.user.controller;

import com.nextrow.user.entity.Users;
import com.nextrow.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/home")
    public String homePage(){
        return "Welcome";
    }

    @GetMapping("/{id}")
    public Users findByUserId(@PathVariable("id") int id){

        return userRepository.findByUserId(id);
    }

    @PostMapping("/save")
    public Users saveDetails(@RequestBody Users users){
        return userRepository.save(users);
    }

}
