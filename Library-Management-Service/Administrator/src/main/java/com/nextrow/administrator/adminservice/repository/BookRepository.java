package com.nextrow.administrator.adminservice.repository;

import com.nextrow.administrator.adminservice.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
