package com.nextrow.administrator.paymentservice.paymententity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;

@Entity
public class PaymentEntity {

    @Id
    private int payment_Id;
    private String payment_Type;
    private Date payment_Time;
    private long user_Contact;
    private String payment_Status;

    public int getPayment_Id() {
        return payment_Id;
    }

    public void setPayment_Id(int payment_Id) {
        this.payment_Id = payment_Id;
    }

    public String getPayment_Type() {
        return payment_Type;
    }

    public void setPayment_Type(String payment_Type) {
        this.payment_Type = payment_Type;
    }

    public Date getPayment_Time() {
        return payment_Time;
    }

    public void setPayment_Time(Date payment_Time) {
        this.payment_Time = payment_Time;
    }

    public long getUser_Contact() {
        return user_Contact;
    }

    public void setUser_Contact(long user_Contact) {
        this.user_Contact = user_Contact;
    }

    public String getPayment_Status() {
        return payment_Status;
    }

    public void setPayment_Status(String payment_Status) {
        this.payment_Status = payment_Status;
    }
}
