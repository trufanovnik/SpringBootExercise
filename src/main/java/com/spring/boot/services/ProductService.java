package com.spring.boot.services;

import com.spring.boot.entities.Product;
import com.spring.boot.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void add(Product product){
        productRepository.save(product);
    }
}
