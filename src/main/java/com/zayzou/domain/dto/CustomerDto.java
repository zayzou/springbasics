package com.zayzou.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.zayzou.domain.Customer}
 */

@NoArgsConstructor
@Data
public class CustomerDto implements Serializable {
    Long id;
    String name;
    int age;
}