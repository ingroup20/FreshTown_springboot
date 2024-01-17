package com.jasonlin.freshtown_springboot.rowmapper;

import com.jasonlin.freshtown_springboot.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setMealNo(rs.getInt("mealNo"));
        product.setMealName(rs.getString("mealName"));
        product.setMealPrice(rs.getInt("mealPrice"));
        product.setMealTypeNo(rs.getInt("mealTypeNo"));
        product.setMealOnsale(rs.getInt("mealOnsale"));
        product.setStoreId(rs.getInt("storeId"));
        product.setMealPicture(rs.getString("mealPicture"));
        product.setCookingTime(rs.getTime("cookingTime"));
        return product;
    }
}
