package com.example.riderequest.services;

import com.example.riderequest.Exception.RideNotFoundException;
import com.example.riderequest.model.Ride;
import com.example.riderequest.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RideRequestService {
    @Autowired
    private RideRepository repository;
    RideRequestService(RideRepository repository) {
        this.repository = repository;
    }
    public List<Ride> getRides(){

        return repository.findAll();
    }
    public Ride addRide(Ride newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item

    public Ride one( Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RideNotFoundException(id));
    }
    public List<Ride> searchBySource( String src) {
        List<Ride> ridesFilter  = repository.findBySource(src);
        if(ridesFilter.size()==0){
            throw new RideNotFoundException(src);
        }
        else {
            return ridesFilter;
        }


    }
    public List<Ride> searchByName( String name) {
        List<Ride> ridesFilter  = repository.findByName(name);
        if(ridesFilter.size()==0){
            throw new RideNotFoundException(name);
        }
        else {
            return ridesFilter;
        }


    }

    public Ride replaceRide(Ride newRide, Long id) {

        return repository.findById(id)
                .map(ride -> {
                    ride.setName(newRide.getName());
                    ride.setDestination(newRide.getDestination());
                    ride.setSource(newRide.getSource());
                    ride.setPhoneno(newRide.getPhoneno());
                    ride.setStart_date(newRide.getStart_date());
                    ride.setStarttime(newRide.getStarttime());
                    ride.setPassengerCount(newRide.getPassengerCount());
                    return repository.save(ride);
                })
                .orElseGet(() -> {
                    newRide.setId(id);
                    return repository.save(newRide);
                });
    }

    public void cancelRide( Long id) {
        if(repository.findById(id)!=null) {
            repository.deleteById(id);
        }
        else {
        throw new RideNotFoundException(id);
        }
        }

    public void cancelRideMultiple( Long id) {
        if(repository.findById(id)!=null) {
            repository.deleteById(id);
        }
        else {
            throw new RideNotFoundException(id);
        }
    }
    }


