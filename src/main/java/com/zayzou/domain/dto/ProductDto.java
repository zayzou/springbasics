package com.zayzou.domain.dto;

import com.zayzou.domain.Product;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
public record ProductDto(Long id, String name, CustomerDto customer) implements Serializable {
}