package com.jasonlin.freshtown_springboot.service;

import com.jasonlin.freshtown_springboot.model.Product;

public interface ProductService {

    Product getProductById(Integer mealNo);
}
