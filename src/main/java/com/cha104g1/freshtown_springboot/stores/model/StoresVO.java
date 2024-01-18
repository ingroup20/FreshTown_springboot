package com.cha104g1.freshtown_springboot.stores.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cha104g1.freshtown_springboot.itemsclass.model.model.ItemsClassVO;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.material.model.model.MaterialVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.picking.model.PickingVO;
import com.cha104g1.freshtown_springboot.service.model.model.ServiceVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.suporder.model.SupOrderVO;


@Entity
@Table(name = "stores")
public class StoresVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "storeId", updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer	storeId;
	
	@Column(name = "storeAccount")
	@NotEmpty(message="店家帳號: 請勿空白")
	@Pattern(regexp = "^[a-zA-Z0-9_]{2,15}$", message = "店家帳號: 只能是英文字母、數字和_ , 且長度必需在2到15之間")
	private String	storeAccount;
	
	@Column(name = "storePw")
	@NotEmpty(message="店家密碼: 請勿空白")
	@Pattern(regexp = "^[a-zA-Z0-9_]{6,15}$", message = "店家密碼: 只能是中文、英文字母、數字和_ , 且長度必需在6到15之間")
	private String	storePw;
	
	@Column(name = "storeName")
	@NotEmpty(message="店家名稱: 請勿空白")
	@Size(min=1,max=8,message="店家名稱: 長度必需在{min}到{max}之間")
	private String	storeName;
	
	@Column(name = "storeGui")
	@Pattern(regexp = "^[0-9]{8}$", message = "店家統一編號: 只能是8位數字")
	private String	storeGui;
	
	@Column(name = "storeAddress")
	@NotEmpty(message="店家地址: 請勿空白")
	@Size(min=1,max=128,message="店家地址: 長度必需在1到128之間")
	private String	storeAddress;
	
	@Column(name = "storePhone")
	@NotEmpty(message="店家電話: 請勿空白")
	@Pattern(regexp = "^[0-9]{7,10}$", message = "店家電話: 只能是數字 , 且長度不符合規定")
	private String	storePhone;
	
	@Column(name = "storeState")
	@Pattern(regexp = "^[0-4]{1}$", message = "店家狀態: 只能是數字(0: 審核中 1: 審核未通過 2:帳號開通 3: 停權 4: 作廢帳號)")
	private String storeState;
	
	@Column(name = "storeLv")
	@NotEmpty(message="店家等級: 請勿空白")
	@Pattern(regexp = "^[01]$", message = "店家等級: 只能是數字(0:初始-餐點功能, 1:高級-加採購庫存功能)")
	private String storeLv;
	
	@Column(name = "createDate", updatable = false)
	@NotNull(message="開通時間: 請勿空白")
	private Date	createDate;
	
	@Column(name = "payDate")
	@NotNull(message="付費日期: 請勿空白")
	private Date	payDate;
	
	@Column(name = "photo", columnDefinition = "longblob")
	private byte[]	photo;
	
	@Column(name = "storeDesc")
	@Size(min=0,max=1000,message="店家簡介: 長度必需在小於1000")
	private String	storeDesc;
	
	@Column(name = "pushUp")
	@NotNull(message="平台開店: 請勿空白")
	@Min(value=0, message = "平台開店: 只能是數字(0:準備中 1:開店 2: 店休)  DEFAULT 0")
	@Max(value=2, message = "平台開店: 只能是數字(0:準備中 1:開店 2: 店休)  DEFAULT 0")
	private Integer	pushUp;
	
	@Column(name = "ownerName")
	@NotEmpty(message="店家負責人: 請勿空白")
	@Size(min=1,max=15,message="店家地址: 長度必需在1到15之間")
	private String	ownerName;
	
	@Column(name = "ownerMob")
	@NotEmpty(message="負責人手機號碼: 請勿空白")
	@Pattern(regexp = "^[0-9]{10}$", message = "負責人手機號碼: 只能是10位數字")
	private String	ownerMob;
	
	@Column(name = "ownerId")
	@NotEmpty(message="身分證: 請勿空白")
	@Pattern(regexp = "^[a-zA-Z][12][0-9]{8}$", message = "身分證: 輸入有誤格式不符")
	private String	ownerId;
	
	@Column(name = "ownerAddress")
	@NotEmpty(message="負責人地址: 請勿空白")
	@Size(min=1,max=128,message="負責人地址: 長度必需在1到128之間")
	private String	ownerAddress;
	
	@Column(name = "ownerEmail")
	@NotEmpty(message="EMAIL: 請勿空白")
	@Size(min=1,max=128,message="EMAIL: 長度必需在1到128之間")
	private String	ownerEmail;
	
	@Column(name = "scorePeople")//評分總人數
	private Integer	scorePeople;
	
	@Column(name = "totalScore")//總評分
	private Integer	totalScore;
	
	@Column(name = "storeLat", columnDefinition = "Decimal")
	@NotNull(message="店家緯度: 請勿空白")
	@DecimalMin(value = "-90.00000000", message = "店家緯度: 不能小於{value}")
	@DecimalMax(value = "90.00000000", message = "店家緯度: 不能超過{value}")
	private BigDecimal	storeLat;
	
	@Column(name = "storeLag", columnDefinition = "Decimal")
	@NotNull(message="店家經度: 請勿空白")
	@DecimalMin(value = "-180.00000000", message = "店家經度: 不能小於{value}")
	@DecimalMax(value = "180.0000000", message = "店家經度: 不能超過{value}")
	private BigDecimal	storeLag;
	
	@Column(name = "openTime")
	@Pattern(regexp = "^[a-zA-Z0-9:,~]{0,225}$", message = "開店/休息時間點:格式範例11:00~14:00,17:00~19:00")
	private String	openTime;
	
	@Column(name = "restDay")
	@NotEmpty(message="固定店休日: 請勿空白")
	@Pattern(regexp = "^[01]{7}$", message = "固定店休日: 0:店休 1:正常營業 (星期一 ~ 星期日)EX.六日店休1111100")
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
	public String getStoreLv() {
		return storeLv;
	}
	public void setStoreLv(String storeLv) {
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
	public String getStoreState() {
		return storeState;
	}
	public void setStoreState(String storeState) {
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
	
//	//採購單
//	@OneToMany(mappedBy = "storesVO" ,cascade= CascadeType.ALL)
//	private Set<SupOrderVO> supOrderVO;
//	
//	public Set<SupOrderVO> getSupOrderVO(){
//		return supOrderVO;
//	}
//	
//	public void setSupOrderVO(Set<SupOrderVO> supOrderVO) {
//		this.supOrderVO = supOrderVO;
//	}
//	
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
