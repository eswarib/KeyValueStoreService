package com.example.demo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import com.example.demo.demo.Repository.DataRepository;

@RestController
@RequestMapping("/")
public class DataController {

    @Autowired
    private DataRepository myRepo;

    @GetMapping("/get/{key}")
    public String getValue(@PathVariable(value = "key") String key)
    {
        //This function will return the value for the given key or null
        return myRepo.GetValue(key);

    }

    @PostMapping("/create/{key}/{value}")
    public boolean createRecord(@PathVariable(value = "key") String key,@PathVariable(value = "value") String val)
    {
        return myRepo.AddOrUpdateEntry(key,val);
    }

    @PostMapping("/update/{key}/{value}")
    public boolean updateRecord(@PathVariable(value = "key") String key,@PathVariable(value = "value") String val)
    {
        return myRepo.AddOrUpdateEntry(key,val);
    }

    @PostMapping("/delete/{key}")
    public boolean deleteRecord(@PathVariable(value = "key") String key)
    {
        return myRepo.DeleteEntry(key);
    }
}
