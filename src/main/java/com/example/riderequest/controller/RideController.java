package com.example.riderequest.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.example.riderequest.model.Ride;
import com.example.riderequest.repository.RideRepository;
import com.example.riderequest.Exception.RideNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.riderequest.services.RideRequestService;
@RestController
public class RideController {

@Autowired
    private RideRequestService service;

    @GetMapping("/allrides")
     public ResponseEntity<List<Ride>> getRides(){
        return new ResponseEntity<List<Ride>>(service.getRides(),HttpStatus.OK) ;
    }


    @GetMapping("/search")
    public ResponseEntity<List<Ride>> searchMany(@ModelAttribute Ride criteria) {
        List<Ride> searchList = service.searchRides(criteria);
        if(searchList.size()==0){
            throw new RideNotFoundException("the given data");
        }
        return new ResponseEntity<List<Ride>>(service.searchRides(criteria),HttpStatus.OK) ;
    }

    @GetMapping("/searchRideSource")
    public ResponseEntity<List<Ride>> searchName(@RequestParam("source") String source) {

        return new ResponseEntity<List<Ride>>(service.searchBySource(source),HttpStatus.OK) ;
    }




    @PostMapping("/add_ride")
    public ResponseEntity<Ride> newEmployee(@RequestBody Ride newRide ) {
        return new ResponseEntity<Ride>(service.addRide(newRide),HttpStatus.OK) ;
    }


    @PutMapping("/editRide")
    public ResponseEntity<Ride> replaceEmployee(@RequestBody Ride newRide, @RequestParam("id") Long id) {

        return new ResponseEntity<Ride>(service.replaceRide(newRide,id),HttpStatus.OK) ;
    }

    @DeleteMapping("/cancelRide")
    public ResponseEntity<String> cancel(@RequestParam("id") List <Long> id) {

            service.cancelRide(id);
             return new ResponseEntity<String>("Cancellation Successful",HttpStatus.OK) ;
        }

    }
