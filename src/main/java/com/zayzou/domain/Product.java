package com.zayzou.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    @Id
    private Long id;
    //    @JsonProperty("author")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
