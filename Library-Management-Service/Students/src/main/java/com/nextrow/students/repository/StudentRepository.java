package com.nextrow.students.repository;

import com.nextrow.students.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {
}
