package com.example.riderequest.controller;

import com.example.riderequest.Exception.ExceptionHandlerController;
import com.example.riderequest.model.Customer;
import com.example.riderequest.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*"})
@RestController
public class CustomerController {

@Autowired
    private CustomerService service;

    @GetMapping("/allCustomer")
     public ResponseEntity<List<Customer>> getRides(){
        return new ResponseEntity<List<Customer>>(service.getCustomerService(),HttpStatus.OK) ;
    }

    @PostMapping("/customer/login")
    public ResponseEntity<Customer> checkCustomer(@RequestBody String phno, @RequestBody String pass ) {

        return new ResponseEntity<Customer>(service.checkCustomerService(phno,pass),HttpStatus.OK) ;
    }


    @PostMapping("/customer/signup")
    public ResponseEntity<Customer> newCustomer(@RequestBody Customer newCustomer ) {

        return new ResponseEntity<Customer>(service.addCustomerService(newCustomer),HttpStatus.OK) ;
    }


    @PutMapping("/customer/edit")
    public ResponseEntity<Customer> replaceCustomer(@RequestBody Customer newRide, @RequestParam("id") Long id) {
//        if(service.findCustomer(id)!=null){
            return new ResponseEntity<Customer>(service.editCustomerService(newRide,id),HttpStatus.OK) ;
//        }
//        else{
//            throw new ExceptionHandlerController(id);
//        }
    }

    @DeleteMapping("/customer/delete")
    public ResponseEntity<Map<String,String>> cancel(@RequestParam("id") List <Long> id) {
        Map<String, String> map = new HashMap<>() {{
            put("message", "Cancellation Successful");
        }};
            service.deleteUserService(id);
             return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK) ;
        }

    @DeleteMapping("/customerWipe")
    public ResponseEntity<Map<String,String>> cancelAll() {
        Map<String, String> map = new HashMap<>() {{
            put("message", "Customer Database cleared");

        }};
        service.deleteAllUserService();
        return new ResponseEntity<>(map, HttpStatus.OK) ;
    }

    }
