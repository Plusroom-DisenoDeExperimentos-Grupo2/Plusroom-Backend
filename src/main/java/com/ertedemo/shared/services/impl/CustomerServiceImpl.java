package com.ertedemo.shared.services.impl;

import com.ertedemo.domain.model.entites.Customer;

import com.ertedemo.domain.model.valueobjects.Status;
import com.ertedemo.domain.persistence.CustomerRepository;
import com.ertedemo.domain.services.CustomerService;
import com.ertedemo.domain.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    private NotificationService notificationService;

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> update(Customer customer) {
        if (customerRepository.existsById(customer.getId())) {
            return Optional.of(customerRepository.save(customer));
        }
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void updateStatus(Customer customer, Status status) {
        customer.setStatus(status);
        customerRepository.save(customer);

        if (status == Status.PENDING) {
            notificationService.notifyLandlord(customer);
        } else if (status == Status.ACCEPTED || status == Status.REJECTED) {
            notificationService.notifyTenant(customer);
        }
    }
}
