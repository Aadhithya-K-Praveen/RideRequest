package com.example.riderequest.repository;
import java.util.List;
import com.example.riderequest.model.Ride;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findBySourceLike(String source);


    List<Ride> findAll(Specification<Ride> spec);

    List<Ride> findAllByCustomerid(Long id);
    Ride findByRideid(Long customerid);

    Ride findByRideidAndCustomerid(Long rideid, Long customerid);

    @Modifying
    @Query("delete from Ride u where u.rideid in ?1")
    void deleteUsersWithIds(List<Long> ids);

    void deleteAllByCustomerid(Long customerid);
}

