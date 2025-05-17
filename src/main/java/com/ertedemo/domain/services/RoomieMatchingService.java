package com.ertedemo.domain.services;

import com.ertedemo.domain.model.entites.Tenant;
import com.ertedemo.domain.model.entites.User;

import java.util.List;

public interface RoomieMatchingService {
    List<Tenant> findMatches(Tenant tenant);
}