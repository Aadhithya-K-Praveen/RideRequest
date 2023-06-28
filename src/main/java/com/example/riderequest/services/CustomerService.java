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
    public List<Customer> getCustomerService(Long id){

        return repository.findAll();
    }
    public Customer addCustomerService(Customer newCustomer) {
        String sha256hex = sha256()
                .hashString(newCustomer.getPassword(), StandardCharsets.UTF_8)
                .toString();
        newCustomer.setPassword(sha256hex);
//        if(repository.findByCustomerphno(newCustomer.getCustomerphno())!=null){
            return repository.save(newCustomer);

//        }
//        else {
//        return null;
//        }

    }

    // Single item

    public Customer findCustomer(Long id) {
        return repository.findByCustomerId(id);
    }


    public Customer editCustomerService(Customer newCustomer, Long id) {
        String sha256hex = sha256()
                .hashString(newCustomer.getPassword(), StandardCharsets.UTF_8)
                .toString();
        newCustomer.setPassword(sha256hex);
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


