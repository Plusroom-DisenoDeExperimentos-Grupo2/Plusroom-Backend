package com.ertedemo.api.resource.notifications;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateNotificationResource {
    private List<Long> tenantIds;
    private Long landlordId;
    private Long postId;
    private Date date;
}
