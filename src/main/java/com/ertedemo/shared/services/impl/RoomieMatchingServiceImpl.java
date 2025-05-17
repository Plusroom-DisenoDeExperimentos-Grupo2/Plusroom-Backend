package com.ertedemo.shared.services.impl;

import com.ertedemo.domain.model.entites.RoomiePreference;
import com.ertedemo.domain.model.entites.Tenant;
import com.ertedemo.domain.model.entites.User;
import com.ertedemo.domain.persistence.RoomiePreferenceRepository;
import com.ertedemo.domain.persistence.TenantRepository;
import com.ertedemo.domain.persistence.UserRepository;
import com.ertedemo.domain.services.RoomieMatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomieMatchingServiceImpl implements RoomieMatchingService {

    @Autowired
    private RoomiePreferenceRepository roomiePreferenceRepository;

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public List<Tenant> findMatches(Tenant tenant) {
        RoomiePreference tenantPreferences = roomiePreferenceRepository.findByTenantId(tenant.getId());
        List<RoomiePreference> allPreferences = roomiePreferenceRepository.findAll();
        return allPreferences.stream()
                .filter(pref -> !pref.getTenant().getId().equals(tenant.getId()))
                .filter(pref -> pref.getLocationPreference().equals(tenantPreferences.getLocationPreference()))
                .map(pref -> tenantRepository.findById(pref.getTenant().getId()).orElse(null))
                .collect(Collectors.toList());
    }
}