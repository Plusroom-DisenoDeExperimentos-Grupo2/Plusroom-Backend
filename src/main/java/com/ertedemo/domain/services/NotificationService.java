package com.ertedemo.domain.services;

import com.ertedemo.domain.model.entites.Customer;
import com.ertedemo.domain.model.entites.Notification;

import java.util.List;

public interface NotificationService {
    Notification create(Notification notification);
    List<Notification> getAll();
    Notification getById(Long id);
    void notifyLandlord(Customer customer);
    void notifyTenant(Customer customer);
}