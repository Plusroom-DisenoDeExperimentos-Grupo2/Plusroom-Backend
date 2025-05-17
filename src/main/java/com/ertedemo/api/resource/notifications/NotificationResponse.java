package com.ertedemo.api.resource.notifications;

import com.ertedemo.domain.model.entites.Notification;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class NotificationResponse {
    private Long id;
    private List<Long> tenantIds;
    private Long landlordId;
    private Long postId;
    private Date date;

    public NotificationResponse(Notification notification) {
        this.id = notification.getId();
        this.tenantIds = notification.getTenantIds();
        this.landlordId = notification.getLandlord().getId();
        this.postId = notification.getPostId();
        this.date = notification.getDate();
    }
}