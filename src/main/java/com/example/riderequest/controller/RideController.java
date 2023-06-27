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
@CrossOrigin(origins = {"http://localhost:8080","https://ab2d-14-142-185-230.ngrok-free.app"},methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
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
        if(service.one(id)!=null){
            return new ResponseEntity<Ride>(service.replaceRide(newRide,id),HttpStatus.OK) ;
        }
        else{
            throw new RideNotFoundException(id);
        }
    }

    @DeleteMapping("/cancelRides")
    public ResponseEntity<Map<String,String>> cancel(@RequestParam("id") List <Long> id) {
        Map<String, String> map = new HashMap<>() {{
            put("message", "Cancellation Successful");
        }};
            service.cancelRide(id);
             return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK) ;
        }

    @DeleteMapping("/cancelAllRides")
    public ResponseEntity<Map<String,String>> cancelAll() {
        Map<String, String> map = new HashMap<>() {{
            put("message", "Ride Database cleared");

        }};
        service.cancelAllRide();
        return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK) ;
    }

    }
