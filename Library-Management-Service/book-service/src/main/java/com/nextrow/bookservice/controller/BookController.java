package com.nextrow.bookservice.controller;

import com.nextrow.bookservice.entity.Book;
import com.nextrow.bookservice.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {


    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/getAll")
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/getBookById/{book_Id}")
    public Book getBookById(@PathVariable String book_Id ){
        return bookRepository.findById(book_Id).orElseThrow(RuntimeException::new);
    }


    @GetMapping("/getAllBooks")
    public List<Book> getAllBooks(){
        List<Book> list=bookRepository.findAll();
        return list;
    }

/*    // Not Working
    @GetMapping("/getBooksByAuthor/{author}")
    public List<String> getBooksByAuthor(@PathVariable String author) {
        List<Book> books = bookRepository.findByBookAuthor(author);
        List<String> bookTitles = new ArrayList<>();
        for (Book book : books) {
            bookTitles.add(book.getBook_Title());
        }
        return bookTitles;
    }

   @GetMapping("/getBooksByAuthor/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author){
        List<Book> ls = bookRepository.findByBookAuthor(author);

        List<Book> list=bookRepository.findAll();
        List<Book> ls=new ArrayList<>();
        for(Book book: list){
            if (book.getBook_Genre().equals(author)){
                ls.add(book);
            }
        }

        return ls;
    }*/



    @GetMapping("/getBooksByGenre/{genre}")
    public List<Book> getBooksByGenre(@PathVariable String genre){
        List<Book> list=bookRepository.findAll();
        List<Book> ls=new ArrayList<>();
        for(Book book: list){
            if (book.getBook_Genre().equals(genre)){
                ls.add(book);
            }
        }

        return ls;
    }

    @GetMapping("/bookAvailability/{book_Id}")
    public int bookAvailability(@PathVariable String book_Id){
        Book book=bookRepository.findById(book_Id).orElseThrow(RuntimeException::new);
        return book.getBook_Availability();
    }



    @GetMapping("/updateBorrowBook/{book_Id}")
    public void updateBorrowBook(@PathVariable String book_Id){
        Book book=bookRepository.findById(book_Id).orElseThrow(RuntimeException::new);
        int book_available=book.getBook_Availability();
        if (book_available>0) {
            book_available -= 1;
            book.setBook_Availability(book_available);
            bookRepository.save(book);
        }
    }



    @PostMapping("/save")
    public Book saveBook(@RequestBody Book book){
        return bookRepository.save(book);
    }










/*



    @GetMapping("/id{id}")
    public Book findBookById(@PathVariable("id") Integer book_Id){
        return service.findBookById(book_Id);
    }

    @PutMapping("/update{id}")
    public Book updateBookCost(@PathVariable("id") Integer book_Id){
        return service.updateBookCost(book_Id);
    }

    @DeleteMapping("/delete{id}")
    public Book deleteBookById(@PathVariable("id") Integer book_Id){
        return service.deleteBookById(book_Id);
    }*/

}
