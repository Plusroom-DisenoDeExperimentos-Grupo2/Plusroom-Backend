package com.ertedemo.shared.services.impl;

import com.ertedemo.domain.model.entites.RoomiePreference;
import com.ertedemo.domain.model.entites.Tenant;
import com.ertedemo.domain.model.entites.User;
import com.ertedemo.domain.persistence.RoomiePreferenceRepository;
import com.ertedemo.domain.persistence.TenantRepository;
import com.ertedemo.domain.persistence.UserRepository;
import com.ertedemo.domain.services.RoomiePreferenceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomiePreferenceServiceImpl implements RoomiePreferenceService {

    @Autowired
    private RoomiePreferenceRepository roomiePreferenceRepository;

    @Autowired
    private TenantRepository tenantRepository;

    private ModelMapper modelMapper;

    @Override
    public RoomiePreference savePreferences(RoomiePreference preferences) {
        return roomiePreferenceRepository.save(preferences);
    }

    @Override
    public List<Tenant> findByLocationAndBudget(String location, Float budget) {
        List<RoomiePreference> preferences = roomiePreferenceRepository.findAll();
        return preferences.stream()
                .filter(pref -> (location == null || location.isEmpty() || pref.getLocationPreference().equals(location)) &&
                        (budget == null || pref.getBudget().equals(budget)))
                .map(pref -> tenantRepository.findById(pref.getTenant().getId()).orElse(null))
                .collect(Collectors.toList());
    }
    @Override
    public RoomiePreference findByTenantId(Long tenantId) {
        return roomiePreferenceRepository.findByTenantId(tenantId);
    }
}