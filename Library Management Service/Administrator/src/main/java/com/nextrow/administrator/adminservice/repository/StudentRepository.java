package com.nextrow.administrator.adminservice.repository;

import com.nextrow.administrator.adminservice.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
}
