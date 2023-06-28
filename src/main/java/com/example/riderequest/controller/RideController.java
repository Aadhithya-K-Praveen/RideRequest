package com.example.riderequest.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.riderequest.model.Ride;
import com.example.riderequest.Exception.RideNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.riderequest.services.RideRequestService;
@CrossOrigin(origins = {"*"},methods = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@RestController
public class RideController {

@Autowired
    private RideRequestService service;

    @GetMapping("/allrides")
     public ResponseEntity<List<Ride>> getRides(){
        return new ResponseEntity<List<Ride>>(service.getRidesService(),HttpStatus.OK) ;
    }


    @GetMapping("/search")
    public ResponseEntity<List<Ride>> searchMany(@ModelAttribute Ride criteria) {
        List<Ride> searchList = service.searchRidesService(criteria);
        if(searchList.size()==0){
            throw new RideNotFoundException("the given data");
        }
        return new ResponseEntity<List<Ride>>(service.searchRidesService(criteria),HttpStatus.OK) ;
    }

    @GetMapping("/searchRideSource")
    public ResponseEntity<List<Ride>> searchName(@RequestParam("source") String source) {

        return new ResponseEntity<List<Ride>>(service.searchBySourceService(source),HttpStatus.OK) ;
    }




    @PostMapping("/add_ride")
    public ResponseEntity<Ride> newEmployee(@RequestBody Ride newRide ) {
        return new ResponseEntity<Ride>(service.addRideService(newRide),HttpStatus.OK) ;
    }


    @PutMapping("/editRide")
    public ResponseEntity<Ride> replaceEmployee(@RequestBody Ride newRide, @RequestParam("id") Long id) {
        if(service.findOneService(id)!=null){
            return new ResponseEntity<Ride>(service.replaceRideService(newRide,id),HttpStatus.OK) ;
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
            service.cancelRideService(id);
             return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK) ;
        }

    @DeleteMapping("/cancelAllRides")
    public ResponseEntity<Map<String,String>> cancelAll() {
        Map<String, String> map = new HashMap<>() {{
            put("message", "Ride Database cleared");

        }};
        service.cancelAllRideService();
        return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK) ;
    }

    }
