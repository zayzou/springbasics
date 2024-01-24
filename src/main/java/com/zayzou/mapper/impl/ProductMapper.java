package com.zayzou.mapper.impl;

import com.zayzou.domain.Product;
import com.zayzou.domain.dto.ProductDto;
import com.zayzou.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper implements Mapper<Product, ProductDto> {

    ModelMapper mapper;

    public ProductMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ProductDto mapTo(Product product) {
        return mapper.map(product, ProductDto.class);
    }

    @Override
    public Product mapFrom(ProductDto productDto) {
        return mapper.map(productDto, Product.class);
    }
}
