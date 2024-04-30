package com.nextrow.students.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BorrowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int borrowId;
    private String bookId;
    private String studentName;
    private String bookName;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private int moneyCharged;

/*    @ManyToOne
    @JoinColumn(name = "borrowed_student_name", referencedColumnName = "student_Name", foreignKey = @ForeignKey(name = "student_entity"))
    private StudentEntity studentEntity;*/

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getMoneyCharged() {
        return moneyCharged;
    }

    public void setMoneyCharged(int moneyCharged) {
        this.moneyCharged = moneyCharged;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
