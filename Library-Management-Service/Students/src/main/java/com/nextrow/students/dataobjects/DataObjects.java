package com.nextrow.students.dataobjects;

import com.nextrow.students.entity.BorrowEntity;
import com.nextrow.students.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DataObjects {

    @Autowired
    private Book book;

    @Autowired
    private BorrowRepository borrowRepository;


    public BorrowRepository getBorrowRepository() {
        return borrowRepository;
    }

    public void setBorrowRepository(BorrowEntity borrowEntity) {
        borrowRepository.save(borrowEntity);
    }
}
