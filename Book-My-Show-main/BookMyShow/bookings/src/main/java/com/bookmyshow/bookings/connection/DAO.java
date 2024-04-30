package com.bookmyshow.bookings.connection;

import com.bookmyshow.bookings.entity.Customer;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DAO {

    public ShowEntity showEntity;
    public Customer customer;


    public void setShowEntity(ShowEntity showEntity) {
        this.showEntity = showEntity;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
