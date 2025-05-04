package com.spring.boot.controllers;

import com.spring.boot.entities.Product;
import com.spring.boot.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String showProductsList(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product){
        productService.add(product);
        return "redirect:/products";
    }
}
