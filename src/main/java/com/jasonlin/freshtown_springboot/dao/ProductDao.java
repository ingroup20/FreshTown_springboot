package com.jasonlin.freshtown_springboot.dao;

import com.jasonlin.freshtown_springboot.model.Product;

public interface ProductDao {
    Product getProductById(Integer mealNo);
}
