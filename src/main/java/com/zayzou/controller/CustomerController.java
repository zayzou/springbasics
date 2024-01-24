package com.zayzou.controller;

import com.zayzou.domain.Customer;
import com.zayzou.domain.dto.CustomerDto;
import com.zayzou.mapper.Mapper;
import com.zayzou.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customers")
@Slf4j
public class CustomerController {

    private final CustomerService service;
    private final Mapper<Customer, CustomerDto> mapper;

    public CustomerController(CustomerService service, Mapper<Customer, CustomerDto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping()
    public Iterable<Customer> getCustomers() {
        return service.getCustomers();
    }

    @GetMapping(path = "/{id}")
    public CustomerDto getCustomer(@PathVariable final long id) {
        Customer customer = service.getCustomer(id);
        return mapper.mapTo(customer);
    }


    @PostMapping(produces = "application/json")

    public ResponseEntity<CustomerDto> createCustomer(@RequestBody final CustomerDto customer) {
        Customer saveCustomer = service.saveCustomer(mapper.mapFrom(customer));
        return new ResponseEntity<>(mapper.mapTo(saveCustomer), HttpStatus.CREATED);
    }

    @PutMapping()
    public CustomerDto updateCustomer(@RequestBody final CustomerDto customer, @RequestParam final long id) {
        Customer updateCustomer = service.updateCustomer(mapper.mapFrom(customer), id);
        return mapper.mapTo(updateCustomer);
    }

    @DeleteMapping
    public void deleteCustomer(@RequestParam final long id) {
        service.deleteCustomer(id);
    }


}

