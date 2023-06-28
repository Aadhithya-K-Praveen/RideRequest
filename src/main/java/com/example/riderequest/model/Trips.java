package com.example.riderequest.model;


import jakarta.persistence.*;


@Entity
@Table(name = "Trips")
public class Trips {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rideid;
    private String start_date;
    private Integer passengerCount;
    private Long tripid;
    private String source;
    private String destination;
    private Long customerid;

    private String startTime;
    private Integer status;



    public Trips(){}
    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String name) {
        this.start_date = name;
    }
    public Trips(Long rideid, Long tripid, Long customerid, String source, String destination, String startTime,Integer status){
        this.rideid = rideid;
        this.tripid = tripid;
        this.customerid = customerid;
        this.startTime = startTime;
        this.source = source;
        this.destination = destination;
        this.status = status;
    }

    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer count) {
        this.passengerCount = count;
    }
    public Long getTripid() {
        return tripid;
    }

    public void setTripid(Long id) {
        this.tripid = id;
    }
    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long id) {
        this.customerid = id;
    }


    public Long getRideid() {
        return rideid;
    }

    public void setRideid(Long id) {
        this.rideid = id;
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

    public void setStartTime(String dest) {
        this.startTime = dest;
    }


    public String getStartTime() {
        return startTime;
    }

    public void setDestination(String dest) {
        this.destination = dest;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer count) {
        this.status = count;
    }





}