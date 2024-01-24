package com.zayzou.services;

import com.zayzou.domain.Product;
import com.zayzou.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product getProduct(long id) {
        return repository.findById(id).orElseThrow();
    }

    public Iterable<Product> getProducts() {
        return repository.findAll();
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Product update(Product product, long id) {
        Product found = getProduct(id);
        found.setName(product.getName());
        found.setCustomer(product.getCustomer());

        return save(product);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }
}
