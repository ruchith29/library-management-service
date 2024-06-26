package com.nextrow.administrator.adminservice.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StudentEntity {
    @Id
    private int student_Id;
    private String student_Name;
    private String student_Age;
    private String student_Email;
    private long student_Contact;
    private String bookTaken;
    private String checkedOut;

    public int getStudent_Id() {
        return student_Id;
    }

    public void setStudent_Id(int student_Id) {
        this.student_Id = student_Id;
    }

    public String getStudent_Name() {
        return student_Name;
    }

    public void setStudent_Name(String student_Name) {
        this.student_Name = student_Name;
    }

    public String getStudent_Age() {
        return student_Age;
    }

    public void setStudent_Age(String student_Age) {
        this.student_Age = student_Age;
    }

    public String getStudent_Email() {
        return student_Email;
    }

    public void setStudent_Email(String student_Email) {
        this.student_Email = student_Email;
    }

    public long getStudent_Contact() {
        return student_Contact;
    }

    public void setStudent_Contact(long student_Contact) {
        this.student_Contact = student_Contact;
    }

    public String getBookTaken() {
        return bookTaken;
    }

    public void setBookTaken(String bookTaken) {
        this.bookTaken = bookTaken;
    }

    public String getCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(String checkedOut) {
        this.checkedOut = checkedOut;
    }
}
