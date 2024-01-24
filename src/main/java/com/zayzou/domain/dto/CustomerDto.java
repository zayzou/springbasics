package com.zayzou.domain.dto;

import java.io.Serializable;

import com.zayzou.domain.Customer;

/**
 * DTO for {@link Customer}
 */
public record CustomerDto(Long id, String name, int age) implements Serializable {
}
