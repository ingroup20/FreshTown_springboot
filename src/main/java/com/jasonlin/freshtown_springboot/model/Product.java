package com.jasonlin.freshtown_springboot.model;

import java.sql.Time;
import java.util.Date;

public class Product {
    private Integer mealNo;
    private String mealName;
    private Integer mealPrice;
    private Integer mealTypeNo;
    private Integer mealOnsale;
    private Integer storeId;
    private String mealPicture;
    private Time cookingTime;

    public Integer getMealNo() {
        return mealNo;
    }

    public void setMealNo(Integer mealNo) {
        this.mealNo = mealNo;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Integer getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(Integer mealPrice) {
        this.mealPrice = mealPrice;
    }

    public Integer getMealTypeNo() {
        return mealTypeNo;
    }

    public void setMealTypeNo(Integer mealTypeNo) {
        this.mealTypeNo = mealTypeNo;
    }

    public Integer getMealOnsale() {
        return mealOnsale;
    }

    public void setMealOnsale(Integer mealOnsale) {
        this.mealOnsale = mealOnsale;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getMealPicture() {
        return mealPicture;
    }

    public void setMealPicture(String mealPicture) {
        this.mealPicture = mealPicture;
    }

    public Time getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Time cookingTime) {
        this.cookingTime = cookingTime;
    }
}
