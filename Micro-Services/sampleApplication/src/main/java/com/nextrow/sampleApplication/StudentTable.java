package com.nextrow.sampleApplication;

import jakarta.persistence.*;

@Entity
@Table(name = "student_table")
public class StudentTable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private int roll_no;
    @Column
    private String name;
    @Column
    private int contact;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public int getContact() {
        return contact;
    }

    public void setRoll_no(int roll_no) {
        this.roll_no = roll_no;
    }

    public int getRoll_no() {
        return roll_no;
    }

}
