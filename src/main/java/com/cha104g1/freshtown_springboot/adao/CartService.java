package com.cha104g1.freshtown_springboot.adao;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.cha104g1.freshtown_springboot.amodel.CartVO;
import com.cha104g1.freshtown_springboot.customer.model.CustomerService;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;

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
	
	//查詢
	public String findCart(Integer customerNo) {
		String custCart = String.valueOf(customerNo);
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區
		System.out.println(jedis.ping());
		
		jedis.
		
		
		return "";
	}
	
	//新餐點加入&更新
	public String addCart(CartVO cartVO,Integer customerNo) {
		String custCart = String.valueOf(customerNo);
		String cartId = String.valueOf(cartVO.getId());
		String cartKey ="cart:"+custCart+":"+cartId;
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區
		System.out.println(jedis.ping());
		
		//檢查jedis存在的cart(無論有無都向後+物件)
		String jsonStrCart = new JSONObject(cartVO).toString();
		System.out.println("Object to JSON: " + jsonStrCart);			
		jedis.hset(cartKey,jsonStrCart);//加入同時創建動態custCart<List>
		jedis.expire("cart:"+custCart, 1814400);//資料存活時間7天	
		
		for (String cartDetail : jedis.lrange("cart:"+custCart, 0, -1))
			System.out.println(cartDetail);//全部印出瞧瞧

		jedis.close();
		return "1";
	}
	
	//jedis購物車刪除
	public String deleteCart(Integer cartId,Integer customerNo) {
		String custCart = String.valueOf(customerNo);
		String cartKey = "cart:"+custCart+":"+cartId;
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區
		System.out.println(jedis.ping());
		
		//檢查jedis存在的cart(無論有無都向後+物件)
		if(jedis.get(cartKey)!=null) {
			jedis.del(cartKey);
			System.out.println("刪除成功");
		}else
			System.out.println("刪除失敗");

		for (String cartDetail : jedis.lrange("cart:"+custCart, 0, -1))
			System.out.println(cartDetail);//全部印出瞧瞧

		jedis.close();
		return "1";
	}
	
	
	//訂單成立寫入sql
	public void findCartVO(Integer customerNo) {
		String custCart = String.valueOf(customerNo);
		String cartId = String.valueOf(cartVO.getId());
		String cartKey ="cart:"+custCart+":"+cartId;
		
		Jedis jedis = new Jedis("localhost", 6379);
		jedis.select(15);//資料第15區
		
		//取得物件
		jedis.srem("cart:"+custCart);//取出刪除
		JSONObject orderObj = new JSONObject(jedis.get("cart:"+custCart));
		
		
		
		
		//寫入sql
		
		
		
		
		jedis.close();
		
	}
	
	
	
	
	
}
