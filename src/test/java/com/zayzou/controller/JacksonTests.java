package com.zayzou.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zayzou.domain.Customer;
import com.zayzou.domain.Product;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class JacksonTests {

    final Customer customer = Customer
            .builder()
            .id(1L)
            .name("Craig Walls")
            .age(63)
            .build();
    final Product product = Product.builder()
            .id(1L)
            .name("Spring in action")
            .customer(customer)
            .build();

    final String jsonObject = """
            {"id":1,"name":"Spring in action","customer":{"id":1,"name":"Craig Walls","age":63}}""";
    final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testThatObjectMapperCanCreateJsonFromJavaObject() throws JsonProcessingException {
        String result = objectMapper.writeValueAsString(product);
        assertThat(result).isEqualTo(jsonObject);
    }

    @Test
    void testThatObjectMapperCanCreateJavaObjectFromJsonObject() throws JsonProcessingException {
        Product result = objectMapper.readValue(jsonObject, Product.class);
        assertThat(result).isEqualTo(product);
    }
}