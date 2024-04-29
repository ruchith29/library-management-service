package com.example.demo.controller;

import com.example.demo.model.Details;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/home")
public class Controller {

    private final ArrayList<Details> detailsList = new ArrayList<>();

    @GetMapping("/all")
    public ArrayList<Details> getAllDetails() {
        return detailsList;
    }

    @PostMapping("/add")
    public String addDetails(@RequestBody Details details) {
        detailsList.add(details);
        return "Details added successfully";
    }
}
