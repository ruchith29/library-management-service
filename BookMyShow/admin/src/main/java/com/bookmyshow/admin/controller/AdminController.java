package com.bookmyshow.admin.controller;

import com.bookmyshow.admin.DataObjects.Customer;
import com.bookmyshow.admin.DataObjects.CustomerRepository;
import com.bookmyshow.admin.DataObjects.ShowEntity;
import com.bookmyshow.admin.DataObjects.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/welcome")
    public String homePage() {
        return "Welcome Admin";
    }

    @GetMapping("/makeShowAvailable/{code}")
    public String makeShowAvailable(@PathVariable("code") String code){
        ShowEntity showEntity=showRepository.findById(code).orElseThrow(()-> new RuntimeException("Wrong Show Code"));
        showEntity.setShowAvailability("Yes");
        showRepository.save(showEntity);
        return code+" Is Now Available On Screens.";
    }

    @GetMapping("/makeShowUnAvailable/{code}")
    public String makeShowUnAvailable(@PathVariable("code") String code){
        ShowEntity showEntity=showRepository.findById(code).orElseThrow(()-> new RuntimeException("Wrong Show Code"));
        showEntity.setShowAvailability("No");
        showRepository.save(showEntity);
        return code+" Is Not Available On Screens.";
    }

    @PutMapping("/changeShowTimings/{code}")
    public String changeShowTimings(@PathVariable("code") String code,@RequestBody Map<String, String> showTime){
        String updatedTime= showTime.get("showTime");
        ShowEntity showEntity=showRepository.findById(code).orElseThrow(()-> new RuntimeException("Wrong Show Code"));

        String oldTime=showEntity.getShowTime();
        showEntity.setShowTime(updatedTime);
        String newTime=showEntity.getShowTime();

        String[] timeParts = newTime.split(":");

        int hour = Integer.parseInt(timeParts[0]);
        if (hour >= 9 && hour < 12) {
            showEntity.setShowType("Morning");
        } else if (hour >= 12 && hour < 17) {
            showEntity.setShowType("Afternoon");
        } else if (hour >= 17 && hour < 20) {
            showEntity.setShowType("Evening");
        } else {
            showEntity.setShowType("Night");
        }

        showRepository.save(showEntity);
        return "The "+code+" Timings are changed from "+oldTime+" to "+showEntity.getShowTime();
    }

    // add show
    @PostMapping("/add")
    public String addShow(@RequestBody ShowEntity showEntity){
        showRepository.save(showEntity);
        return "Show Added";
    }

    // get show by Id
    @GetMapping("/getByID/{showId}")
    public ShowEntity getShowById(@PathVariable("showId") String showId){
        ShowEntity ls=showRepository.findById(showId)
                .orElseThrow(()-> new RuntimeException("No movie found by "+showId+" This Id"));
        return ls;

    }

    ///get all details of user
    @GetMapping("/getAll")
    public List<Customer> getAllCustomers() {
        List<Customer> l=customerRepository.findAll();
        return l;
    }

    // search customer by Id
    @GetMapping("/search/{id}")
    public Optional<Customer> getAccount(@PathVariable("id") int id){
        return customerRepository.findById(id);
    }

    //get customer based on movie
    @GetMapping("/getCustomerByShow/{code}")
    public List<Customer> getAllByShowCode(@PathVariable String code){
        List<Customer> l=customerRepository.findAll();
        List<Customer> ls=new ArrayList<Customer>();
        for(Customer i:l){
            if (i.getShowCode()!=null && i.getShowCode().equals(code)){
                ls.add(i);
            }
        }
        return ls;
    }

}
