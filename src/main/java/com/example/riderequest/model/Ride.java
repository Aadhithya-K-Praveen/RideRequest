package com.example.riderequest.model;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;

import java.sql.Time;


@Entity
@Table(name = "rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rideid;

    private String drivername;
    private String phoneno;
    private String currlocation;
    private Integer InRide;
    private Integer maxcount;



    public Ride(){}
    public Ride(String name, String phoneno, String currlocation, Integer inRide, Integer maxcount){
        this.drivername = name;
        this.phoneno = phoneno;
        this.InRide = inRide;
        this.currlocation = currlocation;
        this.maxcount = maxcount;
    }


    public Long getRideid() {
        return rideid;
    }

    public void setRideid(Long id) {
        this.rideid = id;
    }

    public Integer getMaxcount() {
        return maxcount;
    }

    public void setMaxcount(Integer count) {
        this.maxcount = count;
    }


    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String ph) {
        this.phoneno = ph;
    }
    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String name) {
        this.drivername = name;
    }


    public void setInRide(Integer inRide) {
        this.InRide = inRide;
    }
    public Integer getInRide() {
        return InRide;
    }

    public void setmaxcount(Integer maxcount) {
        this.maxcount = maxcount;
    }
    public Integer getmaxcount() {
        return maxcount;
    }

}