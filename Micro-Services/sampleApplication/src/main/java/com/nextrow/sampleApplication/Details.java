package com.nextrow.sampleApplication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Details extends JpaRepository<StudentTable,Integer> {
}
