package com.nextrow.bookservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String book_Id;
    private String book_Title;
    private String book_Genre;
    private String book_Author;
    private Double book_Cost;
    private Integer book_Availability;


    public String getBook_Title() {
        return book_Title;
    }

    public void setBook_Title(String book_Title) {
        this.book_Title = book_Title;
    }

    public String getBook_Genre() {
        return book_Genre;
    }

    public void setBook_Genre(String book_Genre) {
        this.book_Genre = book_Genre;
    }

    public String getBook_Author() {
        return book_Author;
    }

    public void setBook_Author(String book_Author) {
        this.book_Author = book_Author;
    }

    public Double getBook_Cost() {
        return book_Cost;
    }

    public void setBook_Cost(Double book_Cost) {
        this.book_Cost = book_Cost;
    }

    public Integer getBook_Availability() {
        return book_Availability;
    }

    public void setBook_Availability(Integer book_Availability) {
        this.book_Availability = book_Availability;
    }

    public String getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(String book_Id) {
        this.book_Id = book_Id;
    }
}
