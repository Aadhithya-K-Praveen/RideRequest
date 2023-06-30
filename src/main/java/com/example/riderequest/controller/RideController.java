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
@CrossOrigin(origins = "*",methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@RestController
public class RideController {

@Autowired
    private RideRequestService service;

    @GetMapping("/")
    public ResponseEntity<Map> checkConnection(){

        return new ResponseEntity<>( Map.of("message", "Connection Successful"), HttpStatus.OK) ;
    }

    @GetMapping("/rides")
     public ResponseEntity<List<Ride>> getRides(){
        return new ResponseEntity<List<Ride>>(service.getRides(),HttpStatus.OK) ;
    }


    @GetMapping("/rides/search")
    public ResponseEntity<List<Ride>> searchMany(@ModelAttribute Ride criteria) {
        List<Ride> searchList = service.searchRides(criteria);
        if(searchList.size()==0){
            throw new RideNotFoundException("the given data");
        }
        return new ResponseEntity<List<Ride>>(service.searchRides(criteria),HttpStatus.OK) ;
    }

    @GetMapping("/rides/search/source")
    public ResponseEntity<List<Ride>> searchName(@RequestParam("source") String source) {

        return new ResponseEntity<List<Ride>>(service.searchBySource(source),HttpStatus.OK) ;
    }




    @PostMapping("/rides/request")
    public ResponseEntity<Map> newEmployee(@RequestBody Ride newRide ) {
        if(service.addRide(newRide)!=null){
            return new ResponseEntity<>( Map.of("message", "Request successful"), HttpStatus.OK) ;
        }
        else {
            return new ResponseEntity<>( Map.of("message", "Request Failed"), HttpStatus.BAD_REQUEST) ;

        }


    }


    @PutMapping("/rides/edit")
    public ResponseEntity<Ride> replaceEmployee(@RequestBody Ride newRide, @RequestParam("id") Long id) {

        return new ResponseEntity<Ride>(service.replaceRide(newRide,id),HttpStatus.OK) ;
    }

    @DeleteMapping("/rides/cancel")
    public ResponseEntity<String> cancel(@RequestParam("id") List <Long> id) {
            if(service.Many(id).size()!=0){
                service.cancelRide(id);
                return new ResponseEntity<String>("Cancellation Successful",HttpStatus.OK) ;
            }
        return new ResponseEntity<String>("Cannot Find",HttpStatus.OK) ;

    }

    @DeleteMapping("/rides/cancelAll")
    public ResponseEntity<String> cancelAll(@RequestParam("id") List <Long> id) {
        service.cancelRide();
        return new ResponseEntity<String>("Database Cleared",HttpStatus.OK) ;

    }

    }
