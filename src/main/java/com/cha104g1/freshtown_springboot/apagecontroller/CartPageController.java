package com.cha104g1.freshtown_springboot.apagecontroller;

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
	
	@ModelAttribute//每次進入controller都會叫用
   public void whoareyou(HttpServletRequest req ,Model model) {

	HttpSession session = req.getSession(false);
	Object idVO =session.getAttribute("customerLogin");
	CustomerVO customerVO= (CustomerVO)idVO;
	if (customerVO != null) {
		System.out.println("身分暱稱="+customerVO.getCustomerNic());
	}
	 model.addAttribute("customerVO", customerVO);
	 model.addAttribute("customerId", customerVO.getCustomerId());
   }
	   
	
	@GetMapping("deleteCart")
	public String deleteCart(@RequestParam String cartId,Model model) {	
		String customerId = String.valueOf(model.getAttribute("customerId"));
		CustomerVO customerVO = (CustomerVO)(model.getAttribute("customerVO"));
		System.out.println("到這~21");
		cartSvc.deleteJedisCart(cartId, customerId);
		System.out.println("到這~31");
		
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
	
	@PostMapping("payOrder")
	public String payOrder(String totalPrice,String storeId,Model model) {	
		String customerId = String.valueOf(model.getAttribute("customerId"));
		Integer newOrderId=cartSvc.addSQL(customerId,storeId);
		if(newOrderId!=null) 
			cartSvc.deleteAllJedisOrder(customerId);
	
		model.addAttribute("ordersVO",ordersSvc.getOneOrders(newOrderId));
		return "cFunction/cart/payPage";
	}

	@PostMapping("paidOrder")
	public String paidOrder(Model model) {	
		OrdersVO ordersVO= (OrdersVO)model.getAttribute("ordersVO");
		ordersVO.setPayState(1);
		ordersSvc.updateOrders(ordersVO);
		return "cFunction/cEntrancePass";
	}
	
 	@GetMapping
 	public String seeCart(HttpServletRequest req ,Model model) {
 		List<CartDetailVO> cartDetailListData = new ArrayList<>();
 		
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
 		Set<Integer> howManyStore =new HashSet<>();
 		Integer totalPrice=0;
 		Integer totalQty=0;
 		for(CartDetailVO cartDetailVO :cartDetailListData ) {
 			totalPrice += cartDetailVO.getQtyPrice();
 			totalQty += cartDetailVO.getMealQty();
 			howManyStore.add(cartDetailVO.getStoreId());	
 		}
 		List<CartDetailVO> separateCart=new ArrayList<>();
 		Map<Integer,List<CartDetailVO>> separateStoreCart = new HashMap<>();
 		for(Integer storeId :howManyStore ) {
 			for(CartDetailVO cartDetailVO :cartDetailListData ) {
 				if(cartDetailVO.getStoreId()==storeId) 
 					separateCart.add(cartDetailVO);	
 			}
 			separateStoreCart.put(storeId, separateCart);
 		}
 		
 		
 		//*********
 		model.addAttribute("storeId", separateCart.get(0).getStoreId());
 		//*********
 		model.addAttribute("totalPrice", totalPrice);
 		model.addAttribute("totalQty", totalQty);
 		model.addAttribute("customerVO", customerVO);
 		model.addAttribute("cartListData",list);
 		model.addAttribute("cartDetailListData",cartDetailListData);
 		return "cFunction/cart/cartPage";
 	}
 	
 	
 	
	
}
