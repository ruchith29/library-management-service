package com.nextrow.administrator.adminservice.controller;

import com.nextrow.administrator.adminservice.Entity.Book;
import com.nextrow.administrator.adminservice.Entity.StudentEntity;
import com.nextrow.administrator.adminservice.repository.BookRepository;
import com.nextrow.administrator.adminservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/addBook")
    public String addBook(@RequestBody Book book){
        bookRepository.save(book);
        //Book book1=restTemplate.getForObject("http://localhost:9092/book/save", Book.class);
        return book.getBook_Title()+" Book Added Successfully!";
    }

    @DeleteMapping("/removeBook/{book_Id}")
    public String removeBook(@PathVariable String book_Id){
        Book book=bookRepository.findById(book_Id).orElseThrow(RuntimeException::new);
        bookRepository.deleteById(book_Id);
        return book.getBook_Title()+" Book Removed Successfully!";
    }

    @DeleteMapping("/removeStudent/{student_Id}")
    public String removeStudent(@PathVariable int student_Id){
        StudentEntity studentEntity =studentRepository.findById(student_Id).orElseThrow(RuntimeException::new);
        studentRepository.deleteById(student_Id);
        return studentEntity.getStudent_Name()+" Removed Successfully!";
    }



    @PutMapping("/{book_Id}/increaseBookCost/{cost}")
    public String increaseBookCost(@PathVariable String book_Id,@PathVariable int cost){
        Book book=bookRepository.findById(book_Id).orElseThrow(RuntimeException::new);
        int currentCost=book.getBook_Cost();
        book.setBook_Cost(cost);
        bookRepository.save(book);
        return "Cost of "+ book.getBook_Title() + " is increased from " +currentCost+" to" +book.getBook_Cost();
    }

    @PutMapping("/{book_Id}/decreaseBookCost/{cost}")
    public String decreaseBookCost(@PathVariable String book_Id,@PathVariable int cost){
        Book book=bookRepository.findById(book_Id).orElseThrow(RuntimeException::new);
        int currentCost=book.getBook_Cost();
        book.setBook_Cost(cost);
        bookRepository.save(book);
        return "Cost of "+ book.getBook_Title() + " is decreased from " +currentCost+" to" +book.getBook_Cost();
    }



    @GetMapping("/getBooksByGenre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable String genre){

        Book[] books=restTemplate.getForObject("http://localhost:9092/book/getBooksByGenre/"+genre, Book[].class);
        List<Book> ls= List.of(books);
        return ls;

    }



    // Not working

    @GetMapping("/getBooksByAuthor/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author){

        Book[] books=restTemplate.getForObject("http://localhost:9092/book/getBooksByAuthor/"+author, Book[].class);
        if (books==null)
        {
            System.out.println("Null");
            System.exit(0);
        }
        List<Book> ls= List.of(books);
        for(Book i:ls){
            System.out.println(i.getBook_Author());
        }
        return ls;
    }
}