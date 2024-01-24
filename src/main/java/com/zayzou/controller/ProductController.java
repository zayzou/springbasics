package com.zayzou.controller;

import com.zayzou.domain.Customer;
import com.zayzou.domain.Product;
import com.zayzou.domain.dto.ProductDto;
import com.zayzou.mapper.impl.ProductMapper;
import com.zayzou.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {

    ProductService service;
    ProductMapper mapper;

    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable final long id) {
        return new ResponseEntity<>(mapper.mapTo(service.getProduct(id)), HttpStatus.OK);
    }

    @GetMapping
    public Iterable<Product> getProducts() {
        return service.getProducts();
    }

    @PostMapping
    public ResponseEntity<ProductDto> saveProduct(@RequestBody final ProductDto product) {
        Product p = service.save(mapper.mapFrom(product));
        return new ResponseEntity<>(mapper.mapTo(p), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody final ProductDto product, @RequestParam final long id) {
        Product p = service.update(mapper.mapFrom(product), id);
        return new ResponseEntity<>(mapper.mapTo(p), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable final long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
