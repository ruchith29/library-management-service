package com.bookmyshow.bookings.controller;

import com.bookmyshow.bookings.connection.DAO;
import com.bookmyshow.bookings.connection.ShowEntity;
import com.bookmyshow.bookings.entity.Customer;
import com.bookmyshow.bookings.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/create")
    public String createAccount(@RequestBody Customer customer){
        customerRepository.save(customer);
        int id=customer.getId();
        return "Account Created Successfully!.\n" +
                "Your Customer_Id is :" +id +
                "\nPlease don't forget to remember your Customer_Id"+
                "\nGo for booking your tickets.\n" +
                "Please find the below link to see available shows.\n" +
                "localhost:1000/customer/availableShows";
    }


    @GetMapping("/availableShows")
    public List<ShowEntity> availableShows(){

        // The link will get the details in form of list so we created an array to store the data
        ShowEntity[] showEntity;
        showEntity = restTemplate.getForObject("http://localhost:1001/show/showAllAvailableShows", ShowEntity[].class);

        List<ShowEntity> showEntityList = Arrays.asList(showEntity);

        return showEntityList;
    }


    @GetMapping("/allShows")
    public List<ShowEntity> showAll(){
        List<Customer> customers=customerRepository.findAll();
        // The link will get the details in form of list so we created an array to store the data
        ShowEntity[] showEntity;
        showEntity = restTemplate.getForObject("http://localhost:1001/show/showAll", ShowEntity[].class);

        List<ShowEntity> showEntityList = Arrays.asList(showEntity);

        return showEntityList;
    }

    @GetMapping("/checkTicketsFor/{code}")
    public String checkTickets(@PathVariable("code") String code){

        ShowEntity showEntity=restTemplate.getForObject("http://localhost:1001/show/get/"+code, ShowEntity.class);
        int ticket=showEntity.getTicketsAvailable();
        System.out.println(ticket);
        String res;
        String sult="To Book Click the link below now!\n" +"http://localhost:1000/enter_your_id/book/enter_movie_code";
        if (30<=ticket && ticket<=60){
            res="Tickets available :"+ticket+"\n";
        } else if (1<=ticket && ticket<30) {
            res="Boom! Tickets are selling out too Fast.\n"+"Tickets available :"+ticket+"\n";

        }
        else {
            res="Sorry. No Tickets are available Right Now!!!";
        }
        return res+sult;
    }

/*
// only admin has permission
    @GetMapping("/search/{id}")
    public Optional<Customer> getAccount(@PathVariable("id") int id){
        return customerRepository.findById(id);
    }*/


    // booking tickets
    @GetMapping("/{id}/book/{code}")
    public String bookShow(@PathVariable("code") String code,@PathVariable("id")int id){
        ShowEntity showEntity= restTemplate.getForObject("http://localhost:1001/show/"+code, ShowEntity.class);
        DAO d=new DAO();
        Customer customer= restTemplate.getForObject("http://localhost:1000/customer/search/"+id, Customer.class);
        customer.setShowCode(code);
        restTemplate.getForObject("http://localhost:1001/show/updateTickets/"+code, ShowEntity.class);
        d.setCustomer(customer);
        d.setShowEntity(showEntity);
        customerRepository.save(customer);
        return "Booking for "+showEntity.getMovieName()+" is Done Successfully!." +
                "\n The tickets will be sent to registered E-mail\n" +
                "Thank you Visit Again";
    }

    //update details
    @PutMapping("/update/{id}")
    public String updateAccount(@PathVariable("id") int id,@RequestBody Customer customer){
        Customer k = new Customer();
        k.setAge(customer.getAge());
        k.setContact(customer.getContact());
        k.setEmail(customer.getEmail());
        k.setName(customer.getName());

        customerRepository.save(k);
        return "All the changes are done Successfully";
    }

}
