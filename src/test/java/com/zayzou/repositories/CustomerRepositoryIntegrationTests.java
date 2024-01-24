package com.zayzou.repositories;

import com.zayzou.domain.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerRepositoryIntegrationTests {

    private final CustomerRepository underTest;

    @Autowired
    public CustomerRepositoryIntegrationTests(CustomerRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatCustomerCanBeCreatedAndRecalled() {

        Customer customer = Customer.builder()
                .name("zayzou")
                .age(22)
                .build();
        underTest.save(customer);

        Optional<Customer> optionalCustomer = underTest.findById(customer.getId());
        assertThat(optionalCustomer).isPresent();
        assertThat(optionalCustomer.get()).isEqualTo(customer);
    }


    @Test
    public void testThatMultipleCustomersCanBeCreatedAndRecalled() {

        Customer customer = Customer.builder()
                .name("zayzou")
                .age(23)
                .build();
        underTest.save(customer);
        Customer customer2 = Customer.builder()
                .name("zayzou")
                .age(23)
                .build();
        underTest.save(customer2);
        Customer customer3 = Customer.builder()
                .name("zayzou")
                .age(23)
                .build();
        underTest.save(customer3);
        Iterable<Customer> result = underTest.findAll();
        assertThat(result)
                .hasSize(3);

    }

    @Test
    public void testThatCustomerCanBeUpdated() {
        Customer customer = Customer.builder()
                .name("zayzou")
                .age(23)
                .build();
        underTest.save(customer);
        customer.setName("UPDATED");
        underTest.save(customer);

        Optional<Customer> result = underTest.findById(customer.getId());
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("UPDATED");
    }

    @Test
    public void testThatCustomerCanBeDeleted() {

        final var ID = 1L;

        //CREATE CustomerS
        Customer customer = Customer.builder()
                .name("bangou")
                .age(21)
                .build();
        underTest.save(customer);

        //DELETE Customer
        underTest.delete(customer);

        Optional<Customer> result = underTest.findById(ID);

        assertThat(result).isNotPresent();
        assertThat(result).isEmpty();

    }


    @Test
    public void testThatGetsCustomersWithAgeLessThan() {
        Customer customer = Customer.builder()
                .name("zayzou")
                .age(25)
                .build();
        underTest.save(customer);
        Customer customer2 = Customer.builder()
                .name("jonas")
                .age(15)
                .build();
        underTest.save(customer2);
        Customer customer3 = Customer.builder()
                .name("man")
                .age(26)
                .build();
        underTest.save(customer3);

        List<Customer> result = underTest.ageLessThan(23);

        assertThat(result).isNotEmpty();

    }

    @Test
    public void testThatGetsCustomersWithAgeGreaterThan() {
        Customer customer = Customer.builder()
                .name("zayzou")
                .age(25)
                .build();
        underTest.save(customer);
        Customer customer2 = Customer.builder()
                .name("jonas")
                .age(15)
                .build();
        underTest.save(customer2);
        Customer customer3 = Customer.builder()
                .name("man")
                .age(26)
                .build();
        underTest.save(customer3);

        List<Customer> result = underTest.findCustomerWithAgeGreaterThan(23);

        assertThat(result).isNotEmpty();

    }
}
