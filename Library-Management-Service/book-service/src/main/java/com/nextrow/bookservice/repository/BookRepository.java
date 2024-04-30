package com.nextrow.bookservice.repository;

import com.nextrow.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {


   /* @Query("SELECT b FROM Book b WHERE b.book_Author = :author")
    List<Book> findByBookAuthor(@Param("author") String author);*/
}
