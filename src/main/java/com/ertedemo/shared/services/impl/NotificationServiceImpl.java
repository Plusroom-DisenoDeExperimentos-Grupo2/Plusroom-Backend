package com.ertedemo.shared.services.impl;

import com.ertedemo.domain.model.entites.Customer;
import com.ertedemo.domain.model.entites.Notification;
import com.ertedemo.domain.persistence.NotificationRepository;
import com.ertedemo.domain.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Notification create(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    @Override
    public void notifyLandlord(Customer customer) {
        Notification notification = new Notification(List.of(customer.getTenant().getId()), customer.getId(), new Date());
        create(notification);
    }

    @Override
    public void notifyTenant(Customer customer) {
        Notification notification = new Notification(List.of(customer.getTenant().getId()), customer.getId(), new Date());
        create(notification);
    }
}