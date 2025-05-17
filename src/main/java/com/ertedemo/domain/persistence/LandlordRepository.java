package com.ertedemo.domain.persistence;

import com.ertedemo.domain.model.entites.Landlord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LandlordRepository extends JpaRepository<Landlord, Long> {
    Optional<Landlord> findByEmail(String email);
    Optional<Landlord> findByEmailAndPassword(String email, String password);}
