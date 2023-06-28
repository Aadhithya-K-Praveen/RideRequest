package com.example.riderequest.model;


import jakarta.persistence.*;


@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    private String customername;
    private String customerphno;
    private Integer rating;



    public Customer(){}


    public Long getId() {
        return customerId;
    }

    public void setId(Long id) {
        this.customerId = id;
    }
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer count) {
        this.rating = count;
    }



    public String getPhoneno() {
        return customerphno;
    }

    public void setPhoneno(String ph) {
        this.customerphno = ph;
    }
    public String getName() {
        return customername;
    }

    public void setName(String name) {
        this.customername = name;
    }




}