package com.ertedemo.domain.persistence;

import com.ertedemo.domain.model.entites.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}