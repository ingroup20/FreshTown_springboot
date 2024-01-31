package com.cha104g1.freshtown_springboot.adao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import com.cha104g1.freshtown_springboot.amodel.CartDetailVO;
import com.cha104g1.freshtown_springboot.amodel.CartVO;
import com.cha104g1.freshtown_springboot.customer.model.CustomerService;
import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailService;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsService;
import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderService;
import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailService;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

import redis.clients.jedis.Jedis;

@Service("cartService")
public class CartService {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	StoresService storesSvc;
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
	@Autowired
	CustomizedDetailService customizedDetailSvc;
	@Autowired
	CustomizedItemsService CustomizedItemsSvc;
	

//	public String findLastCartNo(Integer customerId) {
//		Jedis jedis = new Jedis("localhost", 6379);
//		jedis.select(15);//資料第15區cart
//		System.out.println(jedis.ping());
//
//		Set<String> keys2 = new TreeSet<>();
//		Map<String, String> hAll2 = jedis.hgetAll(String.valueOf(customerId));
//		for (String str : hAll2.keySet()) {
//    	  keys2.add(str);
//		}
//		String lastCart = findMaxString(keys2);
//	    jedis.close();	
//	      return lastCart;
//	}
	
	//查詢CART筆數
	public Integer getCartCount(HttpServletRequest req) {
		HttpSession session = req.getSession(); 
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customerLogin");//
		Integer customerId=customerVO.getCustomerId();
		String  customerIndex=String.valueOf(customerId);
		Integer cartCount;
		//進redis查
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區cart
		System.out.println(jedis.ping());

		
		Set<String> keys = jedis.keys(customerIndex+":*");
		int maxKeyValue=0;
		if(keys!=null) {
			for (String key : keys) {
	            String[] parts = key.split(":");
	            if (parts.length == 2) {
	                int value = Integer.parseInt(parts[1]);
	                maxKeyValue = Math.max(maxKeyValue, value);
	            }
	        }		
		}		
		cartCount=maxKeyValue+1;
	    jedis.close();	
	    return cartCount;
	}
	
	
	
	
	
	//排序
	private static String findMaxString(Set<String> strings) {
        if (strings.isEmpty()) {
            return null;
        }
        // 使用 TreeSet 自動排序
        TreeSet<String> sortedSet = new TreeSet<>(strings);
        // 最後一個元素即最大值
        return sortedSet.last();
    }
	

	
	//查詢
	public List<CartVO> findCart(Integer customerId) {
//		String custCart = String.valueOf(customerId);
		
		List<CartVO> list = new ArrayList<>();
		//叫用jedisS
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區cart
		System.out.println(jedis.ping());
		
		 // 找到所有以 customerId: 開頭的 key
        Set<String> keys = jedis.keys(customerId+":*");
        
        if(keys !=null) {
	     // 遍歷這些 key
	        for (String key : keys) {
	            // 取得對應的 hash 數據
	        	List<String> data = jedis.hmget(key,"mealNo", "mealQty","customizedOrderNoList","customerId","storeId");
	        	CartVO cartVO = new CartVO();
	        	//取第二碼
	            String[] parts = key.split(":");
	            Integer cartId =-1;
	            if (parts.length == 2) {
	            	cartId = Integer.parseInt(parts[1]);
	            	 System.out.println("cartId: " + cartId);
	            }
		            
	            // 輸出數據
//	            System.out.println("Key: " + key);
//	            System.out.println("mealNo: " + data.get(0));
//	            System.out.println("mealQty: " + data.get(1));
//	            System.out.println("customizedOrderNoList: " + data.get(2));
//	            System.out.println("customerId: " + data.get(3));
//	            System.out.println("storeId: " + data.get(4));
//	            System.out.println("=============================");
	            
	    		cartVO.setId(cartId);
	    		cartVO.setMealNo(!data.get(0).isEmpty()? Integer.valueOf(data.get(0)):null);
	    		cartVO.setMealQty(!data.get(1).isEmpty()? Integer.valueOf(data.get(1)):null);
	    		cartVO.setCustomizedOrderNoList(data.get(2));
	    		cartVO.setCustomerId(!data.get(3).isEmpty()? Integer.valueOf(data.get(3)):null);
	    		cartVO.setStoreId(!data.get(4).isEmpty()? Integer.valueOf(data.get(4)):null);
	    		list.add(cartVO);
	        }
        }
			jedis.close();	
			
			//重新裝成物件
			return list;
	}
	
	//新餐點喜好調整明細
//	@PostMapping
//	public List<CustomizedOrderVO> addCustomizedOrder(CustomizedOrderVO customizedOrderVO,Model model) {
//		Object obj = model.getAttribute("customizedOrderList");
//		List<CustomizedOrderVO> customizedOrderList=new ArrayList<CustomizedOrderVO>();
//	    if (obj != null && obj instanceof List<?>) {
//	        List<?> list = (List<?>) obj;
//	        // 然后你可以进一步判断 List 中的元素类型是否是 CustomizedOrderVO
//	        if (!list.isEmpty() && list.get(0) instanceof CustomizedOrderVO) 
//	             customizedOrderList = (List<CustomizedOrderVO>) list;	 	
//	    }
//	    customizedOrderList.add(customizedOrderVO);
//	    model.addAttribute("customizedOrderList",customizedOrderList);
//	    return customizedOrderList;
//	}
	
	//新餐點加入&更新
	public void addCartToRedis(CartVO cartVO,Integer customerId) {
		String custCart = String.valueOf(customerId);
		String cartId = String.valueOf(cartVO.getId());
		String cartKey =custCart+":"+cartId;
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區cart
		System.out.println(jedis.ping());
		
		//轉換成文字	
		String mealNo=String.valueOf(cartVO.getMealNo());
		String mealQty=String.valueOf(cartVO.getMealQty());
		String customizedOrderNoList=cartVO.getCustomizedOrderNoList();
		String customerNo=String.valueOf(cartVO.getCustomerId());
		String storeId=String.valueOf(cartVO.getStoreId());

		//檢查jedis存在的cart(無論有無，都向後+物件)	
		jedis.hset(cartKey,"mealNo",mealNo);//加入同時創建
		jedis.hset(cartKey,"mealQty",mealQty);//加入同時創建
		jedis.hset(cartKey,"customizedOrderNoList",customizedOrderNoList);//加入同時創建
		jedis.hset(cartKey,"customerId",customerNo);//加入同時創建
		jedis.hset(cartKey,"storeId",storeId);//加入同時創建
		
		jedis.expire(cartKey, 1814400);//資料存活時間7天	
		//全部印出瞧瞧
		findCart(customerId);

		jedis.close();
		System.out.println("加入Redis成功");
	}
	
	//jedis購物車刪除
	public String deleteJedisCart(String cartId,String customerId) {
		
		String cartKey = customerId+":"+cartId;
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區
		System.out.println(jedis.ping());
		System.out.println("到這~1");
		//檢查jedis存在的cart(無論有無都向後+物件)
		if(jedis.hexists(cartKey,"mealNo")) {
			jedis.del(cartKey);
			System.out.println("刪除成功");
		}else
			System.out.println("刪除失敗");

		jedis.close();
		return "1";
	}
	
	//jedis全部刪除
	public void deleteAllJedisOrder(String customerId) {
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區
		System.out.println(jedis.ping());
		
		Set<String> keys = jedis.keys(customerId+":*");     
        if(keys !=null) {
	       for (String key : keys) 
	    	   jedis.del(key);
	    }
        
        if(findCart(Integer.valueOf(customerId))==null)
        	System.out.println("全刪除成功");
        else
			System.out.println("刪除失敗");

		jedis.close();

	}
	
	
	//訂單成立寫入sql
	public Integer addSQL(String customerId,String storeId) {
		
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
       	    CustomerVO customerVO = customerSvc.getOneCustomer(Integer.valueOf(customerId));
       	ordersVO.setCustomerVO(customerVO);
       	ordersVO.setTotalPrice(getTotalPrice(customerId));
       			//商品的店家資料  	
       			StoresVO storesVO = storesSvc.getOneStores(Integer.valueOf(storeId));
       	ordersVO.setStoresVO(storesVO);
       	ordersVO.setRemitState("N");
	           	// 取得當前的日期（不包含時間）
	            LocalDate currentDate = LocalDate.now();
	            // 將 LocalDate 轉換為 java.sql.Date
	            java.sql.Date Date = java.sql.Date.valueOf(currentDate);
       	ordersVO.setPayDate(Date);
       	ordersVO.setPayMethod(1);
       	ordersVO.setPayState(0);
      //物件寫入sql
       	OrdersVO newOrdersVO =  ordersSvc.addOrders(ordersVO);
       	Integer newOrderId= newOrdersVO.getOrderId();
       	System.out.println("order新增完成");
		//==============================================================
			
//遍歷取得物件
       	List<CartVO> list=findCart(Integer.valueOf(customerId));		
       	for(CartVO cartVO:list) {
    		MealsVO mealsVO = mealsSvc.getMealsVOByMealNo(cartVO.getMealNo());

    		//存入OrderDetailVO物件
    		OrderDetailVO orderDetailVO = new OrderDetailVO();
    		orderDetailVO.setMealsVO(mealsVO);	
    		orderDetailVO.setMealQty(cartVO.getMealQty());
    		orderDetailVO.setOrdersVO(ordersVO);
    		orderDetailVO.setPriceBought(mealsVO.getMealPrice());
    		//物件寫入sql
    		orderDetailSvc.addOrderDetail(orderDetailVO);
           	System.out.println("orderDetail新增完成");
            //=================================================         	
            //處理customized_order餐點喜好調整明細  	
           	 String customizedDetail = cartVO.getCustomizedOrderNoList();
		          	 
		           	if (customizedDetail != null && !customizedDetail.isEmpty()) {
						// 解析逗號分隔的字符串
		           		String[] customizedDetailArray = customizedDetail.split(",");
					    // 使用取得的數據進行其他操作
					    for (String custedDtlNo : customizedDetailArray) {
					    	CustomizedDetailVO customizedDetailVO =customizedDetailSvc.getCustomizedDetailVOByCustedDtlNo(Integer.valueOf(custedDtlNo));
					    	//存入CustomizedOrderVO物件
					    	CustomizedOrderVO customizedOrderVO=new CustomizedOrderVO();
					    	customizedOrderVO.setCustomizedDetailVO(customizedDetailVO);
					    	customizedOrderVO.setOrderDetailVO(orderDetailVO);
					    	//物件寫入sql
			             	customizedOrderSvc.addCustomizedOrder(customizedOrderVO);
			             	System.out.println("CustomizedOrder新增完成");
					    }     		
		           	}
       	}

       	return newOrderId;
	}
	
	
	
	
	
	
	
	//訂單總金額
	public Integer getTotalPrice(String customerId) {
		Integer totalPrice =0;
		List<CartVO> list=findCart(Integer.valueOf(customerId));		
       	for(CartVO cartVO:list) {
    		MealsVO mealsVO = mealsSvc.getMealsVOByMealNo(cartVO.getMealNo());
    		Integer mealPrice =mealsVO.getMealPrice();
    		Integer mealQty =cartVO.getMealQty();
    		Integer mealTotalPrice = mealPrice*mealQty;
    		totalPrice += mealTotalPrice;
       	}
		return totalPrice;
	}
	
	
	
	//物件轉換
	public CartDetailVO toCartDetailVO(CartVO cartVO) {
		CartDetailVO cartDetailVO=new CartDetailVO();
		Integer qty =cartVO.getMealQty();    
	
		MealsVO mealsVO =mealsSvc.getMealsVOByMealNo(cartVO.getMealNo());
		System.out.println("mealsVO="+mealsVO);
		
		Integer price =mealsVO.getMealPrice();
		Integer QtyPrice =qty*price;
		
		String customizedOrderNoList=cartVO.getCustomizedOrderNoList();
		
		List<CustomizedDetailVO> list = new ArrayList<>();

		// 解析字符串	    
		if (customizedOrderNoList != null && !customizedOrderNoList.isEmpty()) {
			// 解析逗號分隔的字符串
		    String[] customizedOrderNo = customizedOrderNoList.split(",");
		    System.out.println("喜好="+customizedOrderNo.toString());
		    
		    // 使用取得的數據進行其他操作
		    for (int i=0;i<customizedOrderNo.length;i++) {
		        // 對每個 custedDtlNo 做一些處理
		    	//客制細項
		    	CustomizedDetailVO customizedDetailVO =customizedDetailSvc.getCustomizedDetailVOByCustedDtlNo(Integer.valueOf(customizedOrderNo[i]));
		    	list.add(customizedDetailVO);

		    }
		    cartDetailVO.setCustomizedDetailList(list);
		}

		cartDetailVO.setId(cartVO.getId());
		cartDetailVO.setMealsVO(mealsVO);
		cartDetailVO.setMealQty(cartVO.getMealQty());
		cartDetailVO.setQtyPrice(QtyPrice);
		cartDetailVO.setStoreId(cartVO.getStoreId());
		
		return cartDetailVO;
	}
}
