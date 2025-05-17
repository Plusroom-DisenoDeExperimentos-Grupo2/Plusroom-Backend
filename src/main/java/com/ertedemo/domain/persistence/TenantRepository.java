package com.ertedemo.domain.persistence;


import com.ertedemo.domain.model.entites.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Optional<Tenant> findByEmail(String email);
    Optional<Tenant> findByEmailAndPassword(String email, String password);
}