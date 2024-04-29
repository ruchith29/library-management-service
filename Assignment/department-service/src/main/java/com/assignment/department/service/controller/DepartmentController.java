package com.assignment.department.service.controller;

import com.assignment.department.service.RestComm.DataObjects;
import com.assignment.department.service.RestComm.Employee;
import com.assignment.department.service.entity.DepartmentEntity;
import com.assignment.department.service.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/home")
    public String getHome(){
        return "Welcome to Department Service!";
    }

    @PostMapping("/saveDep")
    public String saveDepartment(@RequestBody DepartmentEntity departmentEntity){
        departmentRepository.save(departmentEntity);
        return "Department Added Successfully!";
    }

    @GetMapping("/{id}")
    public DepartmentEntity findUserById(@PathVariable("id") int dep_id){
        return departmentRepository.findById(dep_id);
    }

    @GetMapping("/ed/{id}")
    public DataObjects increaseSalary(@PathVariable("id") int dep_id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(dep_id);
        Employee employee = restTemplate.getForObject("http://localhost:7000/employee/" + departmentEntity.getDep_id(dep_id), Employee.class);
        DataObjects o=new DataObjects();
        o.setDepartmentEntity(departmentEntity);
        o.setEmployee(employee);
        int currentSalary=o.getDepartmentEntity().getSalary();
        int updatedSalary=currentSalary;
        if (o.getEmployee().getDate_of_join()<2015 && o.getDepartmentEntity().getDep_name().equals("Development")){
            updatedSalary+= (currentSalary/10);
            o.getDepartmentEntity().setSalary(updatedSalary);
            o.getString("The Salary of employee Is Increased by 10%... i.e from "+currentSalary+" to "+ updatedSalary +"Congratulations "+o.getEmployee().getName()+" !!");
        }
        departmentEntity.setSalary(updatedSalary);
        departmentRepository.save(departmentEntity);
        return o;
    }
}


