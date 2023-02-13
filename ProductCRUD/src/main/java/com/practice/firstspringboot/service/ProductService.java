package com.practice.firstspringboot.service;

import com.practice.firstspringboot.entity.Product;
import com.practice.firstspringboot.error.ProductNotFoundExceptionHandler;
import com.practice.firstspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> fetchAllProduct() {
        return productRepository.findAll();
    }

    public Product fetchProductById(Long productId) throws ProductNotFoundExceptionHandler {
        Optional<Product> p = productRepository.findById(productId);
        if(!p.isPresent()){
            throw new ProductNotFoundExceptionHandler("Product is not found with id: "+productId);
        }
        return p.get();
    }

    public Product fetchProductByName(String productName) throws ProductNotFoundExceptionHandler {
        Optional<Product> p = Optional.ofNullable(productRepository.findByProductNameIgnoreCase(productName));
        if(!p.isPresent()){
            throw new ProductNotFoundExceptionHandler(productName+" product is not found");
        }
        return p.get();
    }

    public Product updateProduct(Long productId, Product product) {
        Product p = productRepository.findById(productId).get();
        if(Objects.nonNull(product.getProductName()) && !"".equalsIgnoreCase(product.getProductName())){
            p.setProductName(product.getProductName());
        }
        if(Objects.nonNull(product.getProductName()) && !"".equalsIgnoreCase(product.getProductName())){
            p.setProductCategory(product.getProductCategory());
        }
        if(Objects.nonNull(product.getProductPrice())){
            p.setProductPrice(product.getProductPrice());
        }
        if(Objects.nonNull(product.getProductStock())){
            p.setProductStock(product.getProductStock());
        }
        return productRepository.save(p);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
