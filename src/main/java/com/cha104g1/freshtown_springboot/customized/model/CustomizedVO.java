package com.cha104g1.freshtown_springboot.customized.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.customized.model.CustomizedVO.CompositeDetail;



@Entity
@Table(name = "customized")
@IdClass(CompositeDetail.class)
public class CustomizedVO {
	@Id
	@JoinColumn(name = "mealNo", referencedColumnName = "mealNo")
	private Integer mealNo;
	
	@Id
	@JoinColumn(name = "custedItemsNo", referencedColumnName = "custedItemsNo")
	private Integer custedItemsNo;
	
	public CustomizedVO() {
		super();
	}

	public CompositeDetail getCompositeKey() {
		return new CompositeDetail(mealNo, custedItemsNo);
	}

	public void setCompositeKey(CompositeDetail key) {
		this.mealNo = key.getMealNo();
		this.custedItemsNo = key.getCustedItemsNo();
	}
	
	public Integer getMealNo() {
		return mealNo;
	}

	public void setMealNo(Integer mealNo) {
		this.mealNo = mealNo;
	}

	public Integer getCustedItemsNo() {
		return custedItemsNo;
	}

	public void setCustedItemsNo(Integer custedItemsNo) {
		this.custedItemsNo = custedItemsNo;
	}
	
	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer mealNo;
		private Integer custedItemsNo;
		
		// 一定要有無參數建構子
		public CompositeDetail() {
			super();
		}

		public CompositeDetail(Integer mealNo, Integer custedItemsNo) {
			super();
			this.mealNo = mealNo;
			this.custedItemsNo = custedItemsNo;
		}

		public Integer getMealNo() {
			return mealNo;
		}

		public void setMealNo(Integer mealNo) {
			this.mealNo = mealNo;
		}

		public Integer getCustedItemsNo() {
			return custedItemsNo;
		}

		public void setCustedItemsNo(Integer custedItemsNo) {
			this.custedItemsNo = custedItemsNo;
		}

		// 一定要 override 此類別的 hashCode() 與 equals() 方法！
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((custedItemsNo == null) ? 0 : custedItemsNo.hashCode());
			result = prime * result + ((mealNo == null) ? 0 : mealNo.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;

			if (obj != null && getClass() == obj.getClass()) {
				CompositeDetail compositeKey = (CompositeDetail) obj;
				if (mealNo.equals(compositeKey.mealNo) && custedItemsNo.equals(compositeKey.custedItemsNo)) {
					return true;
				}
			}

			return false;
		}

	}

}
