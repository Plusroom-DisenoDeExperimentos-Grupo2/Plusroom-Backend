package com.ertedemo.domain.services;


import com.ertedemo.domain.model.entites.Tenant;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TenantService {
    List<Tenant> getAll();
    Optional<Tenant> getById(Long tenantId);
    Tenant create(Tenant tenant);
    Tenant update(Tenant tenant);
    ResponseEntity<?> delete(Long tenantId);
    Long login(String email, String password);
}
