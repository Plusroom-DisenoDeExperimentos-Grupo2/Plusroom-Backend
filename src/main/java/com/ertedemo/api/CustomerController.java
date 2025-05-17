package com.ertedemo.api;

import com.ertedemo.api.resource.customer.CreateCustomerResource;
import com.ertedemo.api.resource.customer.CustomerResponse;
import com.ertedemo.domain.model.entites.Customer;
import com.ertedemo.domain.model.entites.Landlord;
import com.ertedemo.domain.model.entites.Tenant;
import com.ertedemo.domain.services.CustomerService;
import com.ertedemo.domain.services.LandlordService;
import com.ertedemo.domain.services.TenantService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final LandlordService landlordService;
    private final TenantService tenantService;

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CreateCustomerResource resource) {
        Optional<Landlord> landlord = landlordService.getById(resource.getLandlordId());
        Optional<Tenant> tenant = tenantService.getById(resource.getTenantId());

        if (landlord.isPresent() && tenant.isPresent()) {
            Customer customer = new Customer(resource, landlord.get(), tenant.get());
            customerService.create(customer);
            return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerResponse(customer));
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getById(id);
        return customer.map(value -> ResponseEntity.ok(new CustomerResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
