package com.nextrow.students.controller;

import com.nextrow.students.dataobjects.Book;
import com.nextrow.students.dataobjects.DataObjects;
import com.nextrow.students.entity.BorrowEntity;
import com.nextrow.students.entity.StudentEntity;
import com.nextrow.students.repository.BorrowRepository;
import com.nextrow.students.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

// to get diff in days
import java.time.temporal.ChronoUnit;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BorrowRepository borrowRepository;


    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/createAccount")
    public String createAccount(@RequestBody StudentEntity studentEntity) {
        studentRepository.save(studentEntity);
        return "The Account Creation is Successfully Done!";
    }

    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks() {
        Book[] books = restTemplate.getForObject("http://localhost:9092/book/getAllBooks", Book[].class);
        List<Book> ls = List.of(books);
        return ls;

    }


    @GetMapping("/{student_Id}/borrowBook/{book_Id}")
    public String borrowBook(@PathVariable int student_Id, @PathVariable String book_Id) {
        StudentEntity studentEntity=studentRepository.findById(student_Id).orElseThrow(RuntimeException::new);
        Book books = restTemplate.getForObject("http://localhost:9092/book/getBookById/" + book_Id, Book.class);
        int book_available = books.getBook_Availability();
        if (book_available > 0) {
            restTemplate.getForObject("http://localhost:9092/book/updateBorrowBook/" + book_Id, Book.class);
            studentEntity.setBookTaken(books.getBook_Title());

            LocalDate borrowDate=LocalDate.now();

            BorrowEntity borrowEntity=new BorrowEntity();
            borrowEntity.setBookId(book_Id);
            borrowEntity.setStudentName(studentEntity.getStudent_Name());
            borrowEntity.setBorrowDate(borrowDate);
            borrowEntity.setBookName(books.getBook_Title());
            //borrowEntity.setReturnDate();
            //borrowEntity.setMoneyCharged();


            borrowRepository.save(borrowEntity);

            // assigning borrowId to checkedOut to have reference
            studentEntity.setCheckedOut(borrowEntity.getBorrowId());
            studentRepository.save(studentEntity);

            return "The book titled: " + books.getBook_Title() + " Is successfully Borrowed";
        }
        return "Sorry! Stock Not Available";

    }



    @PutMapping("/{student_Id}/returnBook/{book_Id}")
    public String returnBook(@PathVariable int student_Id,@PathVariable String book_Id){
        StudentEntity studentEntity=studentRepository.findById(student_Id).orElseThrow(RuntimeException::new);
        Book books = restTemplate.getForObject("http://localhost:9092/book/getBookById/" + book_Id, Book.class);
        int borrow_Id=studentEntity.getCheckedOut();
        BorrowEntity borrowEntity=borrowRepository.findById(borrow_Id).orElseThrow(RuntimeException::new);
        LocalDate returnDate=LocalDate.now();
        borrowEntity.setReturnDate(returnDate);
        LocalDate borrowDate=borrowEntity.getBorrowDate();

        // Calculate the difference in days between the two dates
        long diffInDays = ChronoUnit.DAYS.between(borrowDate, returnDate);

        if (diffInDays>3){
            borrowEntity.setMoneyCharged(50);
        }
        else{
            borrowEntity.setMoneyCharged(100);
        }
        borrowRepository.save(borrowEntity);

        return "Book is Returned Successfully!";

    }



    @GetMapping("/getBooksByGenre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable String genre) {

        Book[] books = restTemplate.getForObject("http://localhost:9092/book/getBooksByGenre/" + genre, Book[].class);
        List<Book> ls = List.of(books);
        return ls;

    }
}

/*    @GetMapping("/getBooksByAuthor/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author){
        Book[] books=restTemplate.getForObject("http://localhost:9092/book/getBooksByAuthor/"+author,Book[].class);
        List<Book> ls= List.of(books);
        return ls;
    }*/