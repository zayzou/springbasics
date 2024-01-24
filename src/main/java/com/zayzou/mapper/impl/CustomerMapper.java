package com.zayzou.mapper.impl;

import com.zayzou.domain.Customer;
import com.zayzou.domain.dto.CustomerDto;
import com.zayzou.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper implements Mapper<Customer, CustomerDto> {

    private final ModelMapper modelMapper;

    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public CustomerDto mapTo(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    @Override
    public Customer mapFrom(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}
