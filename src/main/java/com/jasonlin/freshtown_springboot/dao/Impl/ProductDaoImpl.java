package com.jasonlin.freshtown_springboot.dao.Impl;

import com.jasonlin.freshtown_springboot.dao.ProductDao;
import com.jasonlin.freshtown_springboot.model.Product;
import com.jasonlin.freshtown_springboot.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class ProductDaoImpl implements ProductDao {

@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Product getProductById(Integer mealNo) {
        String sql = "SELECT mealNo, mealName, mealPrice, mealTypeNo, mealOnsale, storeId, mealPicture, cookingTime FROM meals WHERE mealNO = :mealNo";

        Map<String, Object> map = new HashMap<>();
        map.put("mealNo", mealNo);

        List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());

        if (productList.size() > 0) {
            return productList.get(0);
        } else {
            return null;
        }
    }
}
