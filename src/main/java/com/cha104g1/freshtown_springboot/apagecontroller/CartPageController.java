package com.cha104g1.freshtown_springboot.apagecontroller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.adao.CartService;
import com.cha104g1.freshtown_springboot.adao.OrderSocketService;
import com.cha104g1.freshtown_springboot.amodel.CartDetailVO;
import com.cha104g1.freshtown_springboot.amodel.CartVO;
import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;

@Controller
@RequestMapping("cFunction/cartPage")
public class CartPageController {
	
	@Autowired
	CartService cartSvc;
	@Autowired
	OrdersService ordersSvc;
	@Autowired
	OrderSocketService orderSocketSvc;
	
	@ModelAttribute//每次進入controller都會叫用
   public void whoareyou(HttpServletRequest req ,Model model) {
//		System.out.println("取得身份");
	HttpSession session = req.getSession(false);
	Object idVO =session.getAttribute("customerLogin");
	CustomerVO customerVO= (CustomerVO)idVO;

	 model.addAttribute("customerVO", customerVO);
	 model.addAttribute("customerId", customerVO.getCustomerId());
   }
	   
	
	@GetMapping
 	public String seeCart(HttpServletRequest req ,Model model) {
 		System.out.println("查看購物車");
 		List<CartDetailVO> cartDetailListData = new ArrayList<>();
 		Set<Integer> howManyStore =new HashSet<>();
 		List<CartDetailVO> separateCart=new ArrayList<>();
 		Map<Integer,List<CartDetailVO>> separateStoreCart = new HashMap<>();
 		
 		Integer totalPrice=0;
 		Integer totalQty=0;
 		
 		HttpSession session = req.getSession(false);
 		Object idVO =session.getAttribute("customerLogin");
 		CustomerVO customerVO= (CustomerVO)idVO;
 		model.addAttribute("customerId", customerVO.getCustomerId());
 		
 		List<CartVO> list = new ArrayList<>();
 		list=cartSvc.findCart(customerVO.getCustomerId());		
		
	 		for(CartVO cartVO :list ) {
	 			CartDetailVO cartDetailVO = new CartDetailVO();
	 			cartDetailVO=cartSvc.toCartDetailVO(cartVO);
	 			cartDetailListData.add(cartDetailVO);
	 		}
	 		for(CartDetailVO cartDetailVO :cartDetailListData ) {
	 			totalPrice += cartDetailVO.getQtyPrice();
	 			totalQty += cartDetailVO.getMealQty();
	 			howManyStore.add(cartDetailVO.getStoreId());	
	 		}
	 		for(Integer storeId :howManyStore ) {
	 			for(CartDetailVO cartDetailVO :cartDetailListData ) {
	 				if(cartDetailVO.getStoreId()==storeId) 
	 					separateCart.add(cartDetailVO);	
	 			}
	 			separateStoreCart.put(storeId, separateCart);
		 		//*********
		 		model.addAttribute("storeId", separateCart.get(0).getStoreId());	
	 		}		
	 		model.addAttribute("totalPrice", totalPrice);
	 		model.addAttribute("totalQty", totalQty);
	 		model.addAttribute("customerVO", customerVO);
	 		model.addAttribute("cartListData",list);
	 		model.addAttribute("cartDetailListData",cartDetailListData);	 		

// 		System.out.println("cartDetailListData.size="+cartDetailListData.size());//不知為何只要加上登入濾器就無法執行
 		return "cFunction/cart/cartPage";
 	}
	
	@GetMapping("deleteCart")
	public String deleteCart(@RequestParam String cartId,Model model) {	
		System.out.println("刪除購物車項目");
		String customerId = String.valueOf(model.getAttribute("customerId"));
		CustomerVO customerVO = (CustomerVO)(model.getAttribute("customerVO"));
		cartSvc.deleteJedisCart(cartId, customerId);	
		//*****重取Jedis**************************
		List<CartDetailVO> cartDetailListData = new ArrayList<>();
 		List<CartVO> list = new ArrayList<>();
 		list=cartSvc.findCart(customerVO.getCustomerId());		

 		for(CartVO cartVO :list ) {
 			CartDetailVO cartDetailVO = new CartDetailVO();
 			cartDetailVO=cartSvc.toCartDetailVO(cartVO);
 			cartDetailListData.add(cartDetailVO);
 			System.out.println("3="+cartDetailVO.getMealsVO());
 		}
 		model.addAttribute("customerVO", customerVO);
 		model.addAttribute("cartListData",list);
 		model.addAttribute("cartDetailListData",cartDetailListData);
		return "redirect: /freshtown_springboot/cFunction/cartPage";
	}
	
	@PostMapping("payingOrder")
	public String payingOrder(String totalPrice,String storeId,Model model) {	
		System.out.println("前往繳費");
		String customerId = String.valueOf(model.getAttribute("customerId"));
		Integer newOrderId=cartSvc.addSQL(customerId,storeId);
		if(newOrderId!=null) 
			cartSvc.deleteAllJedisOrder(customerId);
	
		model.addAttribute("ordersVO",ordersSvc.getOneOrders(newOrderId));
		return "cFunction/cart/payPage";
	}

	@PostMapping("paidOrder")
	public String paidOrder(String payStatus,String orderId,Model model) {	
		System.out.println("繳費作業");
		OrdersVO ordersVO= ordersSvc.getOneOrders(Integer.valueOf(orderId));
		ordersVO.setPayState(1);
		ordersSvc.updateOrders(ordersVO);
		System.out.println("改PayState(1)");
		//==order socket通知
//		orderSocketSvc.customerSideConn(String.valueOf(ordersVO.getOrderId()));
//		System.out.println("有執行socket");
		return "redirect:/cFunction/cart/payPage";
	}
	
 	
 	
 	
 	
	
}
