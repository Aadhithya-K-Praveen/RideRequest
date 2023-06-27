package com.example.riderequest.controller;
import java.util.List;
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

    @GetMapping("/searchRide/{source}")
    public ResponseEntity<List<Ride>> one(@PathVariable String source) {

        return new ResponseEntity<List<Ride>>(service.searchBySource(source),HttpStatus.OK) ;
    }

    @GetMapping("/searchRide")
    public ResponseEntity<List<Ride>> searchName(@RequestParam("name") String name) {

        return new ResponseEntity<List<Ride>>(service.searchByName(name),HttpStatus.OK) ;
    }


    @PostMapping("/add_ride")
    public ResponseEntity<Ride> newEmployee(@RequestBody Ride newRide ) {
        return new ResponseEntity<Ride>(service.addRide(newRide),HttpStatus.OK) ;
    }

    // Single item

    @GetMapping("/rides")
    public ResponseEntity<Ride> one(@RequestParam("id") long id) {
//        System.out.println(id);
        return new ResponseEntity<Ride>(service.one(id),HttpStatus.OK) ;
    }

    @PutMapping("/editRide/{id}")
    public ResponseEntity<Ride> replaceEmployee(@RequestBody Ride newRide, @PathVariable Long id) {

        return new ResponseEntity<Ride>(service.replaceRide(newRide,id),HttpStatus.OK) ;
    }

    @DeleteMapping("/cancelRide/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id) {

            service.cancelRide(id);
             return new ResponseEntity<String>("Cancellation Successful",HttpStatus.OK) ;
        }

    }
