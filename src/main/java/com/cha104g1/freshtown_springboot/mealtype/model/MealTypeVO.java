package com.cha104g1.freshtown_springboot.mealtype.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.cha104g1.freshtown_springboot.meals.model.MealsVO;


//@Component
@Entity
@Table(name="meal_type")
public class MealTypeVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	

	@Id
	@Column(name = "mealTypeNo")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer mealTypeNo;

	@Column(name = "mealTypeName")
	@NotEmpty(message="分類名稱: 請勿空白")
	@Size(min=1,max=31,message="分類名稱: 長度必需在小於31")
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
