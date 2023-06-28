package com.example.riderequest.model;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rides")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phoneno;
    private String source;
    private String destination;
    private String time;
    private String date;
    private Integer passengerCount;

    public Ride(){}
    public Ride(String name, String phoneno, String source, String destination, String time, String endtime, Integer passengerCount){
        this.name = name;
        this.phoneno = phoneno;
        this.source = source;
        this.destination = destination;
        this.time = time;
        this.date = endtime;
        this.passengerCount = passengerCount;
    }

}