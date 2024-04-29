package com.bookmyshow.show.controller;

import com.bookmyshow.show.ShowApplication;
import com.bookmyshow.show.entity.ShowEntity;
import com.bookmyshow.show.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    private ShowRepository showRepository;

    // admin has power to add show
    @PostMapping("/add")
    public String addShow(@RequestBody ShowEntity showEntity){
        showRepository.save(showEntity);
        return "Show Added";
    }

    //admin
    @GetMapping("/getByID/{showId}")
    public ShowEntity getShowById(@PathVariable("showId") String showId){
        ShowEntity ls=showRepository.findById(showId).orElseThrow(()-> new RuntimeException("No movie found by "+showId+" This Id"));
        return ls;

    }

   @GetMapping("/tickets/{code}")
    public String ticketsAvailable(@PathVariable("code") String code){
        ShowEntity showEntity=showRepository.findById(code).orElseThrow(()-> new RuntimeException("Wrong Show Code"));
        int ticket=0;
        ticket=showEntity.getTicketsAvailable();
        return "Tickets Available for "+showEntity.getMovieName()+" are: "+ticket;
    }

    @GetMapping("/updateTickets/{code}")
    public void updateTickets(@PathVariable("code") String code){
        ShowEntity showEntity=showRepository.findById(code).orElseThrow(()-> new RuntimeException("Wrong Show Code"));
        int ticket=showEntity.getTicketsAvailable();
        ticket--;
        showEntity.setTicketsAvailable(ticket);
        showRepository.save(showEntity);
    }


    @GetMapping("/showAllAvailableShows")
    public List<ShowEntity> showAllAvailableShows(){
        List<ShowEntity> ls=showRepository.findAll();
        List<ShowEntity> list=new ArrayList<>();
        for(ShowEntity show: ls){
            if (show.getShowAvailability().equals("Yes")){
                list.add(show);
            }
        }
        return list;
    }

    @GetMapping("/showAll")
    public List<ShowEntity> showAll(){
        return showRepository.findAll();
    }

    @GetMapping("/get/{code}")
    public ShowEntity getShow(@PathVariable("code") String code){
        return showRepository.findById(code).orElseThrow(()-> new RuntimeException("No movie Found"));
    }

    @GetMapping("/{code}")
    public Optional<ShowEntity> getShowByCode(@PathVariable String code){
        return showRepository.findById(code);
    }
}
