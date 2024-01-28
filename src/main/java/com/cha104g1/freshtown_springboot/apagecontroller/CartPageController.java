package com.cha104g1.freshtown_springboot.apagecontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cha104g1.freshtown_springboot.amodel.CartVO;
import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;

@Controller("cFunction/cartPage")
public class CartPageController {
	
	@ModelAttribute//每次進入controller都會叫用
   public void whoareyou(HttpServletRequest req ,Model model) {

	HttpSession session = req.getSession(false);
	Object idVO =session.getAttribute("customerLogin");
	CustomerVO customerVO= (CustomerVO)idVO;
	if (customerVO != null) {
		System.out.println("身分暱稱="+customerVO.getCustomerNic());
	}
	 model.addAttribute("customerId", customerVO.getCustomerId());
   }
	   
	
	@GetMapping
	public String seeCart(Model model) {
		return "cFunction/cart/cartPage";
	}
	
	@PostMapping("addOneInCart")
	public void addOneInCart(MealsVO mealsVO,OrderDetailVO orderDetailVO ,Model model) {	


	}
	

}
