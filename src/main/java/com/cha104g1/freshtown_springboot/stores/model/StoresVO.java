package com.cha104g1.freshtown_springboot.stores.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.cha104g1.freshtown_springboot.itemsclass.model.ItemsClassVO;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.material.model.MaterialVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;
import com.cha104g1.freshtown_springboot.service.model.ServiceVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderVO;


@Entity
@Table(name = "stores")
public class StoresVO {
	@Id
	@Column(name = "storeId", updatable = false)
	private Integer	storeId;
	
	@Column(name = "storeAccount")
	private String	storeAccount;
	
	@Column(name = "storePw")
	private String	storePw;
	
	@Column(name = "storeName")
	private String	storeName;
	
	@Column(name = "storeGui")
	private String	storeGui;
	
	@Column(name = "storeAddress")
	private String	storeAddress;
	
	@Column(name = "storePhone")
	private String	storePhone;
	
	@Column(name = "storeState")
	private Integer	storeState;
	
	@Column(name = "storeLv")
	private Integer	storeLv;
	
	@Column(name = "createDate")
	private Date	createDate;
	
	@Column(name = "payDate")
	private Date	payDate;
	
	@Column(name = "photo", columnDefinition = "longblob")
	private byte[]	photo;
	
	@Column(name = "storeDesc")
	private String	storeDesc;
	
	@Column(name = "pushUp")
	private Integer	pushUp;
	
	@Column(name = "ownerName")
	private String	ownerName;
	
	@Column(name = "ownerMob")
	private String	ownerMob;
	
	@Column(name = "ownerId")
	private String	ownerId;
	
	@Column(name = "ownerAddress")
	private String	ownerAddress;
	
	@Column(name = "ownerEmail")
	private String	ownerEmail;
	
	@Column(name = "scorePeople")
	private Integer	scorePeople;
	
	@Column(name = "totalScore")
	private Integer	totalScore;
	
	@Column(name = "storeLat", columnDefinition = "Decimal")
	private BigDecimal	storeLat;
	
	@Column(name = "storeLag", columnDefinition = "Decimal")
	private BigDecimal	storeLag;
	
	@Column(name = "openTime")
	private String	openTime;
	
	@Column(name = "restDay")
	private String	restDay;
	
	
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getStoreAccount() {
		return storeAccount;
	}
	public void setStoreAccount(String storeAccount) {
		this.storeAccount = storeAccount;
	}
	public String getStorePw() {
		return storePw;
	}
	public void setStorePw(String storePw) {
		this.storePw = storePw;
	}
	public Integer getStoreLv() {
		return storeLv;
	}
	public void setStoreLv(Integer storeLv) {
		this.storeLv = storeLv;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getStoreDesc() {
		return storeDesc;
	}
	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}
	public Integer getPushUp() {
		return pushUp;
	}
	public void setPushUp(Integer pushUp) {
		this.pushUp = pushUp;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerMob() {
		return ownerMob;
	}
	public void setOwnerMob(String ownerMob) {
		this.ownerMob = ownerMob;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getOwnerEmail() {
		return ownerEmail;
	}
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreAddress() {
		return storeAddress;
	}
	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}
	public String getStorePhone() {
		return storePhone;
	}
	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	public Integer getStoreState() {
		return storeState;
	}
	public void setStoreState(Integer storeState) {
		this.storeState = storeState;
	}
	public Integer getScorePeople() {
		return scorePeople;
	}
	public void setScorePeople(Integer scorePeople) {
		this.scorePeople = scorePeople;
	}
	public Integer getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	public BigDecimal getStoreLat() {
		return storeLat;
	}
	public void setStoreLat(BigDecimal storeLat) {
		this.storeLat = storeLat;
	}
	public BigDecimal getStoreLag() {
		return storeLag;
	}
	public void setStoreLag(BigDecimal storeLag) {
		this.storeLag = storeLag;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	
	public String getRestDay() {
		return restDay;
	}
	public void setRestDay(String restDay) {
		this.restDay = restDay;
	}
	public String getStoreGui() {
		return storeGui;
	}
	public void setStoreGui(String storeGui) {
		this.storeGui = storeGui;
	}
	
		
	//收藏&黑名單表單
	@OneToMany(mappedBy = "storesVO" ,cascade= CascadeType.ALL)
	private Set<LikeStoreVO> likeStoreVO;
	
	public Set<LikeStoreVO> getLikeStoreVO(){
		return likeStoreVO;
	}
	
	public void setLikeStoreVO(Set<LikeStoreVO> likeStoreVO) {
		this.likeStoreVO = likeStoreVO;
	}
	
	//店家員工表單
	@OneToMany(mappedBy = "storesVO" ,cascade= CascadeType.ALL)
	private Set<StoreEmpVO> storeEmpVO;
	
	public Set<StoreEmpVO> getStoreEmpVO(){
		return storeEmpVO;
	}
	
	public void setStoreEmpVO(Set<StoreEmpVO> storeEmpVO) {
		this.storeEmpVO = storeEmpVO;
	}
	
	
	
	//線上諮詢紀錄
	@OneToMany(mappedBy = "storesVO" ,cascade= CascadeType.ALL)
	private Set<ServiceVO> serviceVO;
	
	public Set<ServiceVO> getSServiceVO(){
		return serviceVO;
	}
	
	public void setServiceVO(Set<ServiceVO> serviceVO) {
		this.serviceVO = serviceVO;
	}
	
	
	
	
	
	//餐點表單
	@OneToMany(mappedBy = "storesVO" ,cascade= CascadeType.ALL)
	private Set<MealsVO> mealsVO;
	
	public Set<MealsVO> getMealsVO(){
		return mealsVO;
	}
	
	public void setMealsVO(Set<MealsVO> mealsVO) {
		this.mealsVO = mealsVO;
	}
	
	//物料分類
	@OneToMany(mappedBy = "storesVO" ,cascade= CascadeType.ALL)
	private Set<ItemsClassVO> itemsClassVO;
	
	public Set<ItemsClassVO> getItemsClassVO(){
		return itemsClassVO;
	}
	
	public void setItemsClassVO(Set<ItemsClassVO> itemsClassVO) {
		this.itemsClassVO = itemsClassVO;
	}
	
	
	//物料
	@OneToMany(mappedBy = "storesVO" ,cascade= CascadeType.ALL)
	private Set<MaterialVO> materialVO;
	
	public Set<MaterialVO> getMaterialVO(){
		return materialVO;
	}
	
	public void setMaterialVO(Set<MaterialVO> materialVO) {
		this.materialVO = materialVO;
	}
	
	//領料表
	@OneToMany(mappedBy = "storesVO" ,cascade= CascadeType.ALL)
	private Set<PickingVO> pickingVO;
	
	public Set<PickingVO> getPickingVO(){
		return pickingVO;
	}
	
	public void setPickingVO(Set<PickingVO> pickingVO) {
		this.pickingVO = pickingVO;
	}
	
	//採購單
	@OneToMany(mappedBy = "storesVO" ,cascade= CascadeType.ALL)
	private Set<SupOrderVO> supOrderVO;
	
	public Set<SupOrderVO> getSupOrderVO(){
		return supOrderVO;
	}
	
	public void setSupOrderVO(Set<SupOrderVO> supOrderVO) {
		this.supOrderVO = supOrderVO;
	}
	
	//供應商
//	@OneToMany(mappedBy = "storesVO" ,cascade= CascadeType.ALL)
//	private Set<SupplierVO> supplierVO;
//	
//	public Set<SupplierVO> getSupplierVO(){
//		return supplierVO;
//	}
//	
//	public void setSupplierVO(Set<SupplierVO> supplierVO) {
//		this.supplierVO = supplierVO;
//	}
	
	//訂單
	@OneToMany(mappedBy = "storesVO" ,cascade= CascadeType.ALL)
	private Set<OrdersVO> ordersVO;
	
	public Set<OrdersVO> getOrdersVO(){
		return ordersVO;
	}
	
	public void setOrdersVO(Set<OrdersVO> ordersVO) {
		this.ordersVO = ordersVO;
	}
	

	



 
}
