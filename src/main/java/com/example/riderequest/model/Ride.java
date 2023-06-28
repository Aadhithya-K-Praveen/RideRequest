package com.example.riderequest.model;


import com.example.riderequest.Exception.RideNotFoundException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

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
    private String start_date;
    private Integer passengerCount;
    private Integer rating;
    private String source;
    private String destination;
    private Long customerid;

    private String startTime;
    private Integer status;


    public Ride orElse(Object o) {
        return null;
    }

    public Ride orElseThrow(Long id) {
        throw new RideNotFoundException(id);
    }
}