package com.nextrow.userservices.controller;

import com.nextrow.userservices.VO.Department;
import com.nextrow.userservices.VO.ResponseTemplateVo;
import com.nextrow.userservices.entity.Users;
import com.nextrow.userservices.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    public static final String USER_SERVICE="userService";
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private WebClient webClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public int attempt=1;

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

//   @Retry(name = USER_SERVICE,fallbackMethod = "getHome")
//    @CircuitBreaker(name=USER_SERVICE,fallbackMethod = "getDetailsOfDepartment")
    @GetMapping("/ud/{id}")
    public ResponseTemplateVo getUserWithDep(@PathVariable("id") int userId){
        log.info("The program is running");

        ResponseTemplateVo vo =new ResponseTemplateVo();
        Users users=userRepository.findByUserId(userId);

        // web client
        Department departmentDto = webClient.get()
                .uri("http://localhost:8086/department/" + users.getUser_id())
                .retrieve()
                .bodyToMono(Department.class)
                .block();


        // rest template
        Department department = restTemplate.getForObject("http://localhost:8086/department/" + users.getUser_id(), Department.class);


        vo.setUser(users);
        vo.setDepartment(departmentDto);
        return vo;
    }


/*    public ResponseTemplateVo getDetailsOfDepartment(Exception e,@PathVariable("id") int userId){
        ResponseTemplateVo vi =new ResponseTemplateVo();
        Users users=userRepository.findByUserId(userId);
        Department department = restTemplate.getForObject("http://localhost:8086/department/" + users.getUser_id(), Department.class);
        vi.setDepartment(department);;
        return vi;
    }*/

}