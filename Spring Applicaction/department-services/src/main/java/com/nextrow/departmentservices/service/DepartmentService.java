package com.nextrow.departmentservices.service;

import com.nextrow.departmentservices.entity.Department;
import com.nextrow.departmentservices.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(int id) {
        return departmentRepository.getDepartmentById(id);
    }
}
