package com.example.riderequest.services;

import com.example.riderequest.model.Customer;
import com.example.riderequest.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.google.common.hash.Hashing.*;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }
    public List<Customer> getCustomerService(){

        return repository.findAll();
    }
    public Customer addCustomerService(Customer newCustomer) {

        newCustomer.setPassword(hashPass(newCustomer.getPassword()));
        if(repository.findByCustomerphno(newCustomer.getCustomerphno())==null){
            return repository.save(newCustomer);

        }
        else {
        return null;
        }

    }
    public String hashPass(String pass){
        String sha256hex = sha256()
                .hashString(pass, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }

    public Customer checkCustomerService(String phno, String pass){
        Customer currentCustomer = repository.findByCustomerphno(phno);
        if(currentCustomer!=null){
            if( currentCustomer.getPassword()==hashPass(pass)){
                return currentCustomer;
            }
            else{
                return null;
            }
        }
        return null;
    }

    // Single item

    public Customer findCustomer(Long id) {
        return repository.findByCustomerId(id);
    }


    public Customer editCustomerService(Customer newCustomer, Long id) {
        String sha256hex = sha256()
                .hashString(newCustomer.getPassword(), StandardCharsets.UTF_8)
                .toString();
        newCustomer.setPassword(hashPass(newCustomer.getPassword()));
        return repository.findById(id)
                .map(Customer -> {
                    Customer.setCustomername(newCustomer.getCustomername());
                    Customer.setCustomerphno(newCustomer.getCustomerphno());
                    Customer.setPassword(newCustomer.getPassword());
                    return repository.save(Customer);
                })
                .orElseGet(() -> {
                    newCustomer.setCustomerId(id);
                    return repository.save(newCustomer);
                });
    }

    @Transactional
    public void deleteUserService(List <Long> id) {
            repository.deleteAllByCustomerIdIn(id);
        }
        public void deleteAllUserService(){
        repository.deleteAll();
        }


    }


