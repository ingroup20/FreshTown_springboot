package com.cha104g1.freshtown_springboot.adao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.amodel.CartVO;
import com.cha104g1.freshtown_springboot.customer.model.CustomerService;
import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderService;
import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailService;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

import redis.clients.jedis.Jedis;

@Service("cartService")
public class CartService {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	OrderDetailService orderDetailSvc;
	@Autowired
	CustomerService customerSvc;
	@Autowired
	OrdersService ordersSvc;
	@Autowired
	MealsService mealsSvc;
	@Autowired
	CustomizedOrderService customizedOrderSvc;
	
	//查詢
	public Set<String> findCart(String customerNo) {
//		String custCart = String.valueOf(customerNo);
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區cart
		System.out.println(jedis.ping());
		
		 // 找到所有以 ppen: 開頭的 key
        Set<String> keys = jedis.keys(customerNo+":*");
       
     // 遍歷這些 key
        for (String key : keys) {
            // 取得對應的 hash 數據
        	List<String> data = jedis.hmget(key, "cartId", "mealsVO", "orderDetailVO","customizedOrderVO","customerId","storeId");
        	
            // 輸出數據
            System.out.println("Key: " + key);
            System.out.println("cartId: " + data.get(0));
            System.out.println("mealsVO: " + data.get(1));
            System.out.println("orderDetailVO: " + data.get(2));
            System.out.println("customizedOrderVO: " + data.get(3));
            System.out.println("customerId: " + data.get(4));
            System.out.println("storeId: " + data.get(5));
            System.out.println("=============================");
        }
		jedis.close();	
		return keys;
	}
	
	
	
	//新餐點加入&更新
	public String addCart(CartVO cartVO,Integer customerNo) {
		String custCart = String.valueOf(customerNo);
		String cartId = String.valueOf(cartVO.getId());
		String cartKey =custCart+":"+cartId;
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區cart
		System.out.println(jedis.ping());
		
		//轉換成文字
		
		String mealsVO=new JSONObject(cartVO.getMealsVO()).toString();
		String orderDetailVO=new JSONObject(cartVO.getOrderDetailVO()).toString();
		String customerId=String.valueOf(cartVO.getCustomerId());
		String storeId=String.valueOf(cartVO.getStoreId());
		String customizedOrderVO=String.valueOf(cartVO.getCustomizedOrderVO());
		
		
		
		
		
		//檢查jedis存在的cart(無論有無都向後+物件)
		String jsonStrCart = new JSONObject(cartVO).toString();
		System.out.println("Object to JSON: " + jsonStrCart);			
		jedis.hset(cartKey,"cartId",cartId);//加入同時創建
		jedis.hset(cartKey,"mealsVO",mealsVO);//加入同時創建
		jedis.hset(cartKey,"orderDetailVO",orderDetailVO);//加入同時創建
		jedis.hset(cartKey,"customizedOrderVO",customizedOrderVO);//加入同時創建
		jedis.hset(cartKey,"customerId",customerId);//加入同時創建
		jedis.hset(cartKey,"storeId",storeId);//加入同時創建
		
		jedis.expire(cartKey, 1814400);//資料存活時間7天	
		//全部印出瞧瞧
		findCart(custCart);
       

		jedis.close();
		return "1";
	}
	
	//jedis購物車刪除
	public String deleteCart(String cartId,Integer customerNo) {
		String custCart = String.valueOf(customerNo);
		String cartKey = custCart+":"+cartId;
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區
		System.out.println(jedis.ping());
		
		//檢查jedis存在的cart(無論有無都向後+物件)
		if(jedis.get(cartKey)!=null) {
			jedis.hdel(cartKey);
			System.out.println("刪除成功");
		}else
			System.out.println("刪除失敗");

		//全部印出瞧瞧
		findCart(custCart);

		jedis.close();
		return "1";
	}
	
	
	//訂單成立寫入sql
	public void findCartVO(String customerNo,String lastMealNo) {
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區
		
		//新增寫入sql-orders=======================================
       	OrdersVO ordersVO = new OrdersVO();
       	ordersVO.setOrderState(0);
	         // 取得當前的日期和時間
	            LocalDateTime now = LocalDateTime.now();	
	            // 將 LocalDateTime 轉換為 Timestamp
	            Timestamp timestamp = Timestamp.valueOf(now);
       	ordersVO.setOrderTime(timestamp);
       		//下單顧客資料
       	    CustomerVO customerVO = customerSvc.getByCustomerId(Integer.valueOf(customerNo));
       	ordersVO.setCustomerVO(customerVO);
       	ordersVO.setTotalPrice(getTotalPrice(customerNo));
       			//商品店家資料
       			MealsVO mealsVO = mealsSvc.getMealsVOByMealNo(Integer.valueOf(lastMealNo));      	
       			StoresVO storesVO = mealsVO.getStoresVO();
       	ordersVO.setStoresVO(storesVO);
       	ordersVO.setRemitState(customerNo);
	           	// 取得當前的日期（不包含時間）
	            LocalDate currentDate = LocalDate.now();
	            // 將 LocalDate 轉換為 java.sql.Date
	            java.sql.Date Date = java.sql.Date.valueOf(currentDate);
       	ordersVO.setPayDate(Date);
       	ordersVO.setPayMethod(1);
       	ordersVO.setPayState(0);
		//==============================================================
			
//遍歷取得物件
		Set<String> keys=findCart(customerNo);
		for (String key : keys) {
            // 取得對應的 hash 數據
        	List<String> data = jedis.hmget(key, "cartId", "mealsVO", "orderDetailVO","customizedOrderVO","customerId","storeId");        	
                
        	//處理orderDetail================================================
            JSONObject jsonOrderDetail = new JSONObject(data.get(2));
	            //取得-MealsVO
	            JSONObject jsonMeals = new JSONObject(data.get(1));
	            Integer mealNo = jsonMeals.getInt("mealNo");
	        MealsVO orderMealVO =mealsSvc.getMealsVOByMealNo(mealNo);
            Integer mealQty = jsonOrderDetail.getInt("mealQty");
            Integer priceBought = jsonOrderDetail.getInt("priceBought");
            //存入物件
    		OrderDetailVO orderDetailVO = new OrderDetailVO();
    		orderDetailVO.setMealsVO(orderMealVO);	
    		orderDetailVO.setMealQty(mealQty);
    		orderDetailVO.setOrdersVO(ordersVO);
    		orderDetailVO.setOrderDtlNo(priceBought);
    		//物件寫入sql
           	orderDetailSvc.addOrderDetail(orderDetailVO);
           	System.out.println("orderDetail新增完成");
          //=================================================
           	
            //處理customized_order餐點喜好調整明細
           	JSONObject jsonCustOrderDetail = new JSONObject(data.get(3));
           		//取得CustomizedDetailVO
//           		Integer custedDtlNo =jsonCustOrderDetail.getInt("mealNo");
           	
           		Object object = jsonCustOrderDetail;
           		CustomizedDetailVO customizedDetailVO =new CustomizedDetailVO();
           				if (object instanceof CustomizedDetailVO) {
           				    // 可以進行轉型
           					customizedDetailVO = (CustomizedDetailVO) object;
           					System.out.println("轉型成功");
           				} else {
           					System.out.println("轉型失敗");
           				}
         		           		
           	CustomizedOrderVO customizedOrderVO=new CustomizedOrderVO();
           	customizedOrderVO.setCustomizedDetailVO(customizedDetailVO);
           	customizedOrderVO.setOrderDetailVO(orderDetailVO);
           	customizedOrderSvc.addCustomizedOrder(customizedOrderVO);
           	System.out.println("CustomizedOrder新增完成");
		}	
		jedis.close();
	}
	
	
	
	
	
	//訂單總金額
	public Integer getTotalPrice(String customerNo) {
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區cart
		System.out.println(jedis.ping());
		int totalPrice =0;
		 // 找到所有keys
        Set<String> keys = jedis.keys(customerNo+":*");
       
     // 遍歷這些 key
        for (String key : keys) {
            // 取得對應的 hash 數據
        	List<String> data = jedis.hmget(key, "cartId", "mealsVO", "orderDetailVO","customerId","storeId");
            System.out.println("orderDetailVO: " + data.get(2));
            JSONObject jsonOrderDetail = new JSONObject(data.get(2));
            Integer mealQty = jsonOrderDetail.getInt("mealQty");
            Integer priceBought = jsonOrderDetail.getInt("priceBought");
            totalPrice =totalPrice+(mealQty*priceBought);
        }
		jedis.close();	
		return totalPrice;
	}
	
	
	
}
