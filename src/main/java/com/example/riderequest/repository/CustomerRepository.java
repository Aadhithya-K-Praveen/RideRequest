package com.example.riderequest.repository;

import com.example.riderequest.model.Ride;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Ride, Long> {
    List<Ride> findBySourceLike(String source);
    List<Ride> findByNameLike(String name);

    List<Ride> findAll(Specification<Ride> spec);

    @Modifying
    @Query("delete from Ride u where u.customer in ?1")
    void deleteUsersWithIds(List<Long> ids);
}

