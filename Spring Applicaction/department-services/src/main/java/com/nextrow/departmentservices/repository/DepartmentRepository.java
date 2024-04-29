package com.nextrow.departmentservices.repository;

import com.nextrow.departmentservices.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Department getDepartmentById(int id);
}
