package com.example.riderequest.repository;
import java.util.List;
import java.util.Map;

import com.example.riderequest.model.Ride;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findBySourceLike(String source);
    List<Ride> findByNameLike(String name);

    List<Ride> findAll(Specification<Ride> spec);
    List<Ride> findAllByIdIn(List <Long> id);

    @Modifying
    @Query("delete from Ride u where u.id in ?1")
    void deleteUsersWithIds(List<Long> ids);
}

