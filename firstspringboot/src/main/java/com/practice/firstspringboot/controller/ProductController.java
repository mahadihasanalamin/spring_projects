package com.practice.firstspringboot.controller;

import com.practice.firstspringboot.entity.Product;
import com.practice.firstspringboot.error.ProductNotFoundExceptionHandler;
import com.practice.firstspringboot.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public Product addProduct(@Valid @RequestBody Product product){
        return productService.addProduct(product);
    }
    @GetMapping("/products")
    public List<Product> fetchAllProduct(){
        return productService.fetchAllProduct();
    }
    @GetMapping("/products/{id}")
    public Product fetchProductById(@PathVariable("id") Long productId) throws ProductNotFoundExceptionHandler {
        return productService.fetchProductById(productId);
    }
    @GetMapping("/products/name/{name}")
    public Product fetchProductByName(@PathVariable("name") String productName) throws ProductNotFoundExceptionHandler {
        return productService.fetchProductByName(productName);
    }
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody Product product){
        return productService.updateProduct(productId, product);
    }
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        return "Product Successfully Deleted!!";
    }
}
