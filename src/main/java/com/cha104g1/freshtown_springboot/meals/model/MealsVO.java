package com.cha104g1.freshtown_springboot.meals.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Entity
@Table(name = "meals")
public class MealsVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mealNo", updatable = false)
	private Integer mealNo;

	@Column(name = "mealName")
	private String mealName;

	@Column(name = "mealPrice")
	private Integer mealPrice;

	@ManyToOne
	@JoinColumn(name = "mealTypeNo", referencedColumnName = "mealTypeNo")
	private MealTypeVO mealTypeVO;

	@Column(name = "mealOnsale")
	private Integer mealOnsale;

	@ManyToOne
	@JoinColumn(name = "storeId", referencedColumnName = "storeId")
	private StoresVO storesVO;

	@Column(name = "mealPicture")
	private byte[] mealPicture;

	@Column(name = "cookingTime")
	private Time cookingTime;
	
	public MealsVO() {
		super();
	}

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



	public Integer getMealOnsale() {
		return mealOnsale;
	}

	public void setMealOnsale(Integer mealOnsale) {
		this.mealOnsale = mealOnsale;
	}


	public byte[] getMealPicture() {
		return mealPicture;
	}

	public void setMealPicture(byte[] mealPicture) {
		this.mealPicture = mealPicture;
	}

	public Time getCookingTime() {
		return cookingTime;
	}

	public void setCookingTime(Time cookingTime) {
		this.cookingTime = cookingTime;
	}
	//
	public MealTypeVO getMealTypeVO() {
		return mealTypeVO;
	}
	//
	public void setMealTypeVO(MealTypeVO mealTypeVO) {
		this.mealTypeVO = mealTypeVO;
	}
	//
	public StoresVO getStoresVO() {
		return storesVO;
	}
	//
	public void setStoresVO(StoresVO storesVO) {
		this.storesVO = storesVO;
	}
	
	
	
	

}
