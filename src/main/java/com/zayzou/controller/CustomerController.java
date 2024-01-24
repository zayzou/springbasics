package com.zayzou.controller;

import com.zayzou.domain.Customer;
import com.zayzou.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Customer> getCustomers() {
        return service.getCustomers();
    }

    @GetMapping(path = "/{}", produces = "application/json")
    @ResponseStatus(HttpStatus.FOUND)
    public Customer getCustomer(@RequestParam final long id) {
        return service.getCustomer(id);
    }


    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody final Customer customer) {
        return service.saveCustomer(customer);
    }


    @PutMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Customer updateCustomer(@RequestBody final Customer customer, @RequestParam final long id) {
        return service.updateCustomer(customer, id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteCustomer(@RequestParam final long id) {
        service.deleteCustomer(id);
    }


}

