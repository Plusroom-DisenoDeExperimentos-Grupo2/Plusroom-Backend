package com.ertedemo.api.resource.customer;

import lombok.Data;

@Data
public class CreateCustomerResource {
    private Long landlordId;
    private Long tenantId;
    private String status;
}
