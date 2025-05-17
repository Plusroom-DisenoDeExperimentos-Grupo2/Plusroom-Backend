package com.ertedemo.domain.services;

import com.ertedemo.domain.model.entites.Customer;
import com.ertedemo.domain.model.valueobjects.Status;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAll();
    Optional<Customer> getById(Long id);
    Customer create(Customer customer);
    Optional<Customer> update(Customer customer);
    void delete(Long id);

    void updateStatus(Customer customer, Status status);
}
