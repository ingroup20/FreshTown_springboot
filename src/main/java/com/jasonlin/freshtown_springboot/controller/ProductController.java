package com.jasonlin.freshtown_springboot.controller;

import com.jasonlin.freshtown_springboot.model.Product;
import com.jasonlin.freshtown_springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
@GetMapping("/products/{mealNo}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer mealNo){

    Product product = productService.getProductById(mealNo);

    if (product != null) {
        return ResponseEntity.status(HttpStatus.OK).body(product);
    } else {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    }
}
