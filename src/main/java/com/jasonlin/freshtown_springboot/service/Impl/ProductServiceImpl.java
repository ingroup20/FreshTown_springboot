package com.jasonlin.freshtown_springboot.service.Impl;

import com.jasonlin.freshtown_springboot.dao.ProductDao;
import com.jasonlin.freshtown_springboot.model.Product;
import com.jasonlin.freshtown_springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

   @Autowired
    private ProductDao productDao;
    @Override
    public Product getProductById(Integer mealNo) {
        return productDao.getProductById(mealNo);
    }
}
