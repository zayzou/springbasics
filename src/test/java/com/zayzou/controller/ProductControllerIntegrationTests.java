package com.zayzou.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zayzou.domain.Customer;
import com.zayzou.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ProductControllerIntegrationTests {

    ObjectMapper mapper;
    MockMvc mockMvc;
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


    @Autowired
    public ProductControllerIntegrationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
        this.mapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateProductReturn201Created() throws Exception {
        String productJson = mapper.writeValueAsString(product);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson)

        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateProductSuccessfullyReturnSavedProduct() throws Exception {
        String productJson = mapper.writeValueAsString(product);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Spring in action")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.customer").value(customer)
        );
    }
}
