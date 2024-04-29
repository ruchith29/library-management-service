package com.nextrow.departmentservices.controller;

import com.nextrow.departmentservices.entity.Department;
import com.nextrow.departmentservices.repository.DepartmentRepository;
import com.nextrow.departmentservices.service.DepartmentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    public static final String DeptService="DEPARTMENT_SERVICE";

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;


    @PostMapping("/save")
    public Department saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }
    //@RateLimiter(name="DeptService",fallbackMethod = "getHome")

    @CircuitBreaker(name = DeptService, fallbackMethod = "getHome")
    @Retry(name = DeptService,fallbackMethod = "getHome")
    @GetMapping("/home")
    public ResponseEntity<String> getValue() {
       /* int lmt = 1;
        System.out.println("Attempt : "+ lmt +" wait for few seconds");
        Thread.sleep(4000);*/
        return ResponseEntity.ok("Welcome to Geeks for Geeks");
    }

    public ResponseEntity<String> getHome(Exception e){
        return ResponseEntity.ok("Welcome Home!");
    }

    @GetMapping("/{id}")
    public Department getDepartmentById(@PathVariable("id") int id){
        return departmentService.getDepartmentById(id);
    }
}
