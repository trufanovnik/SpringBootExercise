package com.spring.boot.controllers;

import com.spring.boot.entities.Product;
import com.spring.boot.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.spring.boot.specifications.ProductSpecifications.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String showProductsList(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            Model model) {

        Specification<Product> spec = Specification.where(hasTitle(title))
                .and(hasMinPrice(minPrice))
                .and(hasMaxPrice(maxPrice));

        List<Product> products = productService.getAllProducts(spec);

        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model){
        Product product = new Product();
        model.addAttribute(product);
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product")Product product){
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(Model model, @PathVariable(value = "id") Long id){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable(value = "id") Long id, @ModelAttribute Product product){
        product.setId(id);
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/show/{id}")
    public String showProduct(Model model, @PathVariable(value = "id") Long id){
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-page";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}
