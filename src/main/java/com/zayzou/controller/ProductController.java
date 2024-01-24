package com.zayzou.controller;

import com.zayzou.model.Customer;
import com.zayzou.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {

    @GetMapping()
    public Product getProduct() {
        Customer customer = Customer
                .builder()
                .id(1L)
                .name("Craig Walls")
                .age(63)
                .build();
        return Product.builder()
                .id(1L)
                .name("Spring in action")
                .customer(customer)
                .build();
    }

    @PostMapping
    public Product saveProduct(@RequestBody final Product product) {
        log.info(String.format("Saved product :  %s", product));
        return product;
    }
}
