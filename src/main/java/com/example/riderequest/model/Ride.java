package com.example.riderequest.model;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;


@Entity
@Table(name = "rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phno;
    private String source;
    private String destination;
    private String starttime;
    private String start_date;
    private Integer passengerCount;

    public Ride(){}
    public Ride(String name, String phno, String source, String destination, String starttime, String endtime,Integer passengerCount){
        this.name = name;
        this.phno = phno;
        this.source = source;
        this.destination = destination;
        this.starttime = starttime;
        this.start_date = endtime;
        this.passengerCount = passengerCount;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer count) {
        this.passengerCount = count;
    }


    public String getPhoneno() {
        return name;
    }

    public void setPhoneno(String ph) {
        this.phno = ph;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String src) {
        this.source = src;
    }
    public String getDestination() {
        return destination;
    }

    public void setDestination(String dest) {
        this.destination = dest;
    }
    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String name) {
        this.starttime = name;
    }
    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String name) {
        this.start_date = name;
    }

}