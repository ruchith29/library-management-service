package com.assignment.employee.service.controller;

import com.assignment.employee.service.entity.Employee;
import com.assignment.employee.service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/home")
    public String getHome(){
        return "Welcome User!";
    }

    @GetMapping("/{id}")
    public Employee findUserById(@PathVariable("id") int emp_id){
        return employeeRepository.findById(emp_id);
    }

    @PostMapping("/saveEmployee")
    public String addEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
        return "Employee Added Successfully!";
    }
}
