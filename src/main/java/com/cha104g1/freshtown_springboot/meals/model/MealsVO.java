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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

@Entity
@Table(name = "meals")
public class MealsVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mealNo", updatable = false)
	private Integer mealNo;

	@Column(name = "mealName")
	@NotEmpty(message="餐點名稱: 請勿空白")
	private String mealName;

	@Column(name = "mealPrice")
	@NotNull(message="餐點價格: 請勿空白")
	private Integer mealPrice;

	@ManyToOne
	@JoinColumn(name = "mealTypeNo", referencedColumnName = "mealTypeNo")
	@NotEmpty(message="餐點分類: 請勿空白")
	private MealTypeVO mealTypeVO;

	@Column(name = "mealOnsale")
	@NotNull(message="餐點狀態: 請勿空白")
	@Pattern(regexp = "^[(012)]$", message = "餐點狀態: 只能是數字(0準備中 1上架中 2已下架)  ")
	private Integer mealOnsale;

	@ManyToOne
	@JoinColumn(name = "storeId", referencedColumnName = "storeId")
	@NotEmpty(message="店家流水號: 請勿空白")
	private StoresVO storesVO;

	@Column(name = "mealPicture", columnDefinition = "longblob")
	private byte[] mealPicture;

	@Column(name = "cookingTime")
	@NotNull(message="餐點製作時間: 請勿空白")
	@DateTimeFormat(pattern="HH:mm:ss") 
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
