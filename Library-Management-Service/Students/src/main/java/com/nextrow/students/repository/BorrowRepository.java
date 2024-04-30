package com.nextrow.students.repository;

import com.nextrow.students.entity.BorrowEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<BorrowEntity,Integer> {
}
