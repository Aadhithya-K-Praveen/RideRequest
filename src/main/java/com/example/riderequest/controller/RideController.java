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

    @GetMapping("/{customerid}/allrides")
     public ResponseEntity<List<Ride>> getRides(@PathVariable("customerid") Long customerid){
        return new ResponseEntity<List<Ride>>(service.getRidesService(customerid),HttpStatus.OK) ;
    }


    @GetMapping("/{customerid}/rides/search")
    public ResponseEntity<List<Ride>> searchMany(@PathVariable("customerid") Long customerid,@ModelAttribute Ride criteria) {
        List<Ride> searchList = service.searchRidesService(customerid,criteria);
        if(searchList.size()==0){
            throw new RideNotFoundException("the given data");
        }
        return new ResponseEntity<List<Ride>>(service.searchRidesService(customerid,criteria),HttpStatus.OK) ;
    }

    @GetMapping("/rides/search/source")
    public ResponseEntity<List<Ride>> searchName(@PathVariable("customerid") Long customerid,@RequestParam("source") String source) {

        return new ResponseEntity<List<Ride>>(service.searchBySourceService(customerid,source),HttpStatus.OK) ;
    }




    @PostMapping("/{customerid}/rides/add")
    public ResponseEntity<Ride> newEmployee(@PathVariable("customerid") Long customerid,@RequestBody Ride newRide ) {
        return new ResponseEntity<Ride>(service.addRideService(customerid,newRide),HttpStatus.OK) ;
    }


    @PutMapping("/{customerid}/rides/edit")
    public ResponseEntity<Ride> replaceEmployee(@PathVariable("customerid") Long customerid,@RequestBody Ride newRide, @RequestParam("id") Long id) {
        if(service.findOneService(customerid,id)!=null){
            return new ResponseEntity<Ride>(service.replaceRideService(newRide,id),HttpStatus.OK) ;
        }
        else{
            throw new RideNotFoundException(id);
        }
    }

    @DeleteMapping("/{customerid}/rides/cancel")
    public ResponseEntity<Map<String,String>> cancel(@PathVariable("customerid") Long customerid,@RequestParam("id") List <Long> id) {
        Map<String, String> map = new HashMap<>() {{
            put("message", "Cancellation Successful");
        }};
            service.cancelRideService(customerid,id);
             return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK) ;
        }

    @DeleteMapping("/{customerid}/rides/cancelAll")
    public ResponseEntity<Map<String,String>> cancelAll(@PathVariable("customerid") Long customerid) {
        Map<String, String> map = new HashMap<>() {{
            put("message", "Ride Database cleared");

        }};
        service.cancelAllRideService(customerid);
        return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK) ;
    }

    }
