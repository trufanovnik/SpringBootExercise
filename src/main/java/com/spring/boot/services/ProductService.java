package com.spring.boot.services;

import com.spring.boot.entities.Product;
import com.spring.boot.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts(Specification<Product> spec){
        return productRepository.findAll(spec);
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Товар не найден"));
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
