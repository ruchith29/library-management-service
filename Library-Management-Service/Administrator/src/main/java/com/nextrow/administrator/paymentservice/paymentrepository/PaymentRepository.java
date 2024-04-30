package com.nextrow.administrator.paymentservice.paymentrepository;

import com.nextrow.administrator.paymentservice.paymententity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
}
