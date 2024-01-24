package com.zayzou.services;

import com.zayzou.domain.Customer;
import com.zayzou.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer getCustomer(long id) {
        return repository.findById(id).orElseThrow();
    }

    public Customer saveCustomer(Customer customer) {
        return repository.save(customer);
    }

    public Customer updateCustomer(Customer customer, long id) {
        Customer found = getCustomer(id);
        found.setName(customer.getName());
        found.setAge(customer.getAge());
        return saveCustomer(found);
    }

    public void deleteCustomer(long id) {
        repository.deleteById(id);
    }

    public Iterable<Customer> getCustomers() {
        return repository.findAll();
    }
}
