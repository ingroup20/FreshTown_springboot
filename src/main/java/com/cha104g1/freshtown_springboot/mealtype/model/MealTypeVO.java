package com.cha104g1.freshtown_springboot.mealtype.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.meals.model.MealsVO;


//@Component
@Entity
@Table(name="meal_type")
public class MealTypeVO {

	@Id
	@Column(name = "mealTypeNo")
	private Integer mealTypeNo;

	@Column(name = "mealTypeName")
	private String mealTypeName;

	public Integer getMealTypeNo() {
		return mealTypeNo;
	}

	public void setMealTypeNo(Integer mealTypeNo) {
		this.mealTypeNo = mealTypeNo;
	}

	public String getMealTypeName() {
		return mealTypeName;
	}

	public void setMealTypeName(String mealTypeName) {
		this.mealTypeName = mealTypeName;
	}

	//
	@OneToMany(mappedBy="mealTypeVO",cascade=CascadeType.ALL)
	private Set<MealsVO> mealsVO;

	public Set<MealsVO> getMealsVO() {
		return mealsVO;
	}

	public void setMealsVO(Set<MealsVO> mealsVO) {
		this.mealsVO = mealsVO;
	}
	
	
}
