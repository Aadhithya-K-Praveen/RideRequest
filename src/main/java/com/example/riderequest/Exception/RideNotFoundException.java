package com.example.riderequest.Exception;

public class RideNotFoundException extends RuntimeException {

    public RideNotFoundException(Long id) {
        super("Could not find Ride " + id);
    }
    public RideNotFoundException(String src) {
        super("Could not find Ride from " + src);
    }
}