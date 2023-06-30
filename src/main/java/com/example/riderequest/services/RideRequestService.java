package com.example.riderequest.services;
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
    @Autowired
    RideRequestService(RideRepository repository) {
        this.repository = repository;
    }
    public List<Ride> getRidesService(Long id){

        return repository.findAllByCustomerid(id);
    }
    public Ride addRideService(Long customerid,Ride newEmployee) {
        newEmployee.setCustomerid(customerid);
        return repository.save(newEmployee);

    }

    // Single item

    public Ride findOneService(Long customerid,Long id) {

        return repository.findByRideidAndCustomerid(customerid,id);
//                .orElseThrow(id);
    }

    public List<Ride> searchBySourceService(Long customerid,String src) {
        return repository.findBySourceLike(src);
    }


    public List<Ride> searchRidesService(Long customerid,Ride criteria) {
        Specification<Ride> spec = buildSpecification( customerid,criteria);
        return repository.findAll(spec);
    }
    private Specification<Ride> buildSpecification(Long customerid,Ride criteria) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Add predicates based on the provided search criteria

            if (!StringUtils.isEmpty(criteria.getSource())) {
                predicates.add(builder.equal(root.get("source"), criteria.getSource()));
            }

            if (!StringUtils.isEmpty(criteria.getStatus())) {
                predicates.add(builder.equal(root.get("status"), criteria.getStatus()));
            }

            if (!StringUtils.isEmpty(criteria.getRideid())) {
                predicates.add(builder.equal(root.get("rideid"), criteria.getRideid()));
            }

            if (!StringUtils.isEmpty(criteria.getDestination())) {
                predicates.add(builder.equal(root.get("destination"), criteria.getDestination()));
            }
            if (!StringUtils.isEmpty(criteria.getDate())) {
                predicates.add(builder.equal(root.get("start_date"), criteria.getDate()));
            }



            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }


    public Ride replaceRideService(Ride newRide, Long id) {
        Ride existingRide = repository.findByRideid(id).orElse(null);

        if (existingRide != null) {
            // Update the properties of the existing ride with the values from the updated ride
            existingRide.setCustomerid(newRide.getCustomerid());
            existingRide.setSource(newRide.getSource());
            existingRide.setDestination(newRide.getDestination());
            existingRide.setTime(newRide.getTime());
            existingRide.setDate(newRide.getDate());
            existingRide.setPassengerCount(newRide.getPassengerCount());
            existingRide.setStatus(newRide.getStatus());


            // Save the updated ride to the database
            return repository.save(existingRide);
        }

        return null;
    }

    @Transactional
    public void cancelRideService(Long customerid,List <Long> id) {
            repository.deleteUsersWithIds(id);
        }
        public void cancelAllRideService(Long customerid){
        repository.deleteAllByCustomerid(customerid);
        }


    }


