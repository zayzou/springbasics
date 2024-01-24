package com.zayzou.repositories;

import com.zayzou.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> ageLessThan(int age);


    @Query("select c from Customer c where c.age>?1")
    List<Customer> findCustomerWithAgeGreaterThan(int age);
}
