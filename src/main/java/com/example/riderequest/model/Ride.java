package com.example.riderequest.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ride")
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rideid;
    private String date;
    private Integer passengerCount;
    private Integer rating;
    private String source;
    private String destination;
    private Long customerid;

    private String time;
    private Integer status;


    public Ride orElse(Object o) {
        return null;
    }

}