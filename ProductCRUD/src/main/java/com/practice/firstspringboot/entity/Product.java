package com.practice.firstspringboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    @NotBlank(message = "Please Enter The Product Name")
    private String productName;
    @NotBlank(message = "Please Enter The Product Category")
    private String productCategory;
    @NotNull(message = "Please Enter The Product Price")
    private Integer productPrice;
    @NotNull(message = "Please Enter The Product Stock")
    private Integer productStock;
}
