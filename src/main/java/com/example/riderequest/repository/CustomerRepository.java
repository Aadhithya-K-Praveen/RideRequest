package com.example.riderequest.repository;
import com.example.riderequest.model.Customer;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findBycustomernameLike(String name);
    Customer findByCustomerId(Long id);
    Customer findByCustomerphno(String customerphno);
    void deleteAllByCustomerIdIn(List <Long> id);

}

