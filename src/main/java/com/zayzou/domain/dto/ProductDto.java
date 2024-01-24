package com.zayzou.domain.dto;

import com.zayzou.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
@NoArgsConstructor
@Data
public class ProductDto implements Serializable {
    private long id;
    private String name;
    private CustomerDto customer;
}