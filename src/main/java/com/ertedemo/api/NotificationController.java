package com.ertedemo.api;

import com.ertedemo.api.resource.notifications.CreateNotificationResource;
import com.ertedemo.api.resource.notifications.NotificationResponse;
import com.ertedemo.domain.model.entites.Notification;
import com.ertedemo.domain.services.LandlordService;
import com.ertedemo.domain.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private LandlordService landlordService;

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody CreateNotificationResource resource) {
        Notification notification = new Notification(resource.getTenantIds(), resource.getPostId(), resource.getDate());
        landlordService.getById(resource.getLandlordId()).ifPresent(notification::setLandlord);
        Notification createdNotification = notificationService.create(notification);
        return ResponseEntity.ok(new NotificationResponse(createdNotification));
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAll();
        List<NotificationResponse> response = notifications.stream()
                .map(NotificationResponse::new)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id) {
        Notification notification = notificationService.getById(id);
        return ResponseEntity.ok(new NotificationResponse(notification));
    }
}