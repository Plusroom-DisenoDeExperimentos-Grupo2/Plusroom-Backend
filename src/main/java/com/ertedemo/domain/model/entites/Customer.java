package com.ertedemo.domain.model.entites;

import com.ertedemo.api.resource.customer.CreateCustomerResource;
import com.ertedemo.domain.model.valueobjects.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customers")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "landlord_id", nullable = false)
    private Landlord landlord;

    @ManyToOne
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status; // e.g., "accepted", "pending", "rejected"

    public Customer() {
    }

    public Customer(CreateCustomerResource resource, Landlord landlord, Tenant tenant) {
        this.landlord = landlord;
        this.tenant = tenant;
        this.status = Status.valueOf(resource.getStatus());
    }
}
