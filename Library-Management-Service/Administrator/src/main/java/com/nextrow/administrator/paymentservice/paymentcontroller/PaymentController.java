package com.nextrow.administrator.paymentservice.paymentcontroller;

import com.nextrow.administrator.paymentservice.paymententity.PaymentEntity;
import com.nextrow.administrator.paymentservice.paymentrepository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/home")
    public String welcomePage(){
        return "Welcome Admin!";
    }

    @GetMapping("/status/{payment_Id}")
    public String getStatus(@PathVariable int payment_Id){
        PaymentEntity paymentEntity = paymentRepository.findById(payment_Id).orElseThrow(RuntimeException::new);
        return paymentEntity.getPayment_Status();
    }

}
