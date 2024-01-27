package com.cha104g1.freshtown_springboot.apagecontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cha104g1.freshtown_springboot.amodel.CartVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orderdetail.model.OrderDetailVO;

@Controller
public class CartController {
	CartVO cartVO ;

	@ModelAttribute
	public int cartCount(Model model) {
		Integer cartIndex = (Integer) model.getAttribute("cartIndex");
	    return (cartIndex != null) ? cartIndex : 0;
	}
	

//	@PostMapping("addOneInCart")
//	public void addOneInCart(MealsVO mealsVO,OrderDetailVO orderDetailVO ,Model model) {	
//		
//		
//		cartVO.setId(cartCount(model)+1);
//		cartVO.setCustomerId((Integer)model.getAttribute("cLogin") );
//		cartVO.setMealsVO(mealsVO);
//		cartVO.setOrderDetailVO(orderDetailVO);
//		cartVO.setStoreId(mealsVO.getStoresVO().getStoreId());
//		//======================================
//		model.addAttribute("cartIndex", cartVO.getId());//更新cart搜尋碼
//		
//		List<CartVO> cartList =new ArrayList<>();
//		cartList.add(cartVO);
//		model.addAttribute("cartList", cartList);
//		
//
//	}
	
	@PostMapping("updateCart")
	public void updateCart(Integer quality ,Model model) {	

	}
}
