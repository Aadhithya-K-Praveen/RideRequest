package com.example.riderequest.repository;
import java.util.List;
import com.example.riderequest.model.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findBySource(String source);
    List<Ride> findByName(String name);
    void deleteAll(Iterable<? extends Ride> rides);
}

