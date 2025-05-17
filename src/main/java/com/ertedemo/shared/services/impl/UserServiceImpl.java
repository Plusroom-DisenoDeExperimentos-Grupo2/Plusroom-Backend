package com.ertedemo.shared.services.impl;

import com.ertedemo.domain.model.entites.Landlord;
import com.ertedemo.domain.persistence.LandlordRepository;
import com.ertedemo.domain.services.LandlordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LandlordServiceImpl implements LandlordService {

    private final LandlordRepository landlordRepository;

    @Override
    public List<Landlord> getAll() {
        return landlordRepository.findAll();
    }

    @Override
    public Optional<Landlord> getById(Long landlordId) {
        return landlordRepository.findById(landlordId);
    }

    @Override
    public Landlord create(Landlord landlord) {
        return landlordRepository.save(landlord);
    }

    @Override
    public Landlord update(Landlord landlord) {
        return landlordRepository.save(landlord);
    }

    @Override
    public void delete(Long landlordId) {
        landlordRepository.deleteById(landlordId);
    }

    @Override
    public Long login(String email, String password) {
        Optional<Landlord> landlord = landlordRepository.findByEmail(email);
        if (landlord.isPresent() && landlord.get().getPassword().equals(password)) {
            return landlord.get().getId();
        }
        return null;
    }
}
