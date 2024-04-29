package com.assignment.department.service.repository;

import com.assignment.department.service.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Integer> {
    DepartmentEntity findById(int depId);
}
