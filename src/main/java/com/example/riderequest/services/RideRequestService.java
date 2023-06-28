package com.example.riderequest.services;

import com.example.riderequest.Exception.RideNotFoundException;
import com.example.riderequest.model.Ride;
import com.example.riderequest.repository.RideRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
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
    public List<Ride> Many( List<Long> id) {

        return repository.findAllByIdIn(id);

    }
    public List<Ride> searchBySource( String src) {
        List<Ride> ridesFilter  = repository.findBySourceLike(src);
        if(ridesFilter.size()==0){
            throw new RideNotFoundException(src);
        }
        else {
            return ridesFilter;
        }


    }
    public List<Ride> searchByName( String name) {
        List<Ride> ridesFilter  = repository.findByNameLike(name);
        if(ridesFilter.size()==0){
            throw new RideNotFoundException(name);
        }
        else {
            return ridesFilter;
        }


    }

    public List<Ride> searchRides(Ride criteria) {
        Specification<Ride> spec = buildSpecification(criteria);
        return repository.findAll(spec);
    }
    private Specification<Ride> buildSpecification(Ride criteria) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Add predicates based on the provided search criteria

            if (!StringUtils.isEmpty(criteria.getName())) {
                predicates.add(builder.equal(root.get("name"), criteria.getName()));
            }

            if (!StringUtils.isEmpty(criteria.getSource())) {
                predicates.add(builder.equal(root.get("source"), criteria.getSource()));
            }

            if (!StringUtils.isEmpty(criteria.getId())) {
                predicates.add(builder.equal(root.get("id"), criteria.getId()));
            }

            if (!StringUtils.isEmpty(criteria.getTime())) {
                predicates.add(builder.equal(root.get("starttime"), criteria.getTime()));
            }
            if (!StringUtils.isEmpty(criteria.getDate())) {
                predicates.add(builder.equal(root.get("start_date"), criteria.getDate()));
            }



            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

//    public List<Ride> search()
    public Ride replaceRide(Ride newRide, Long id) {

        return repository.findById(id)
                .map(ride -> {
                    ride.setName(newRide.getName());
                    ride.setDestination(newRide.getDestination());
                    ride.setSource(newRide.getSource());
                    ride.setPhoneno(newRide.getPhoneno());
                    ride.setDate(newRide.getDate());
                    ride.setTime(newRide.getTime());
                    ride.setPassengerCount(newRide.getPassengerCount());
                    return repository.save(ride);
                })
                .orElseGet(() -> {
                    newRide.setId(id);
                    return repository.save(newRide);
                });
    }

    @Transactional
    public void cancelRide(List <Long> id) {
        repository.deleteUsersWithIds(id);
    }
    public void cancelRide() {
        repository.deleteAll();
    }


    }


