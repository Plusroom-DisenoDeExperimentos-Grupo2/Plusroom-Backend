package com.ertedemo.api.resource.customer;

import com.ertedemo.domain.model.entites.Customer;
import com.ertedemo.domain.model.valueobjects.Status;
import lombok.Data;

@Data
public class CustomerResponse {
    private Long id;
    private Long landlordId;
    private Long tenantId;
    private Status status;

    public CustomerResponse(Customer customer) {
        this.id = customer.getId();
        this.landlordId = customer.getLandlord().getId();
        this.tenantId = customer.getTenant().getId();
        this.status = customer.getStatus();
    }
}
