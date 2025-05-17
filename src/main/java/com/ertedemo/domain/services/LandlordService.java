package com.ertedemo.domain.services;


import com.ertedemo.domain.model.entites.Landlord;

import java.util.List;
import java.util.Optional;

public interface LandlordService {
    List<Landlord> getAll();
    Optional<Landlord> getById(Long landlordId);
    Landlord create(Landlord landlord);
    Landlord update(Landlord landlord);
    void delete(Long landlordId);
    Long login(String email, String password);
}
