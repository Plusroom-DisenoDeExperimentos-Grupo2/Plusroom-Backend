package com.ertedemo.domain.persistence;

import com.ertedemo.domain.model.entites.RoomiePreference;
import com.ertedemo.domain.model.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomiePreferenceRepository extends JpaRepository<RoomiePreference, Long> {
    RoomiePreference findByTenantId(Long tenantId);
}