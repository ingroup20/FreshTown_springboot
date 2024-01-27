package com.cha104g1.freshtown_springboot.apagecontroller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.customer.model.CustomerService;
import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.customized.model.CustomizedService;
import com.cha104g1.freshtown_springboot.customized.model.CustomizedVO;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailService;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreService;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

import redis.clients.jedis.Jedis;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@Validated
@RequestMapping("/cFunction/storeMenu")
public class StoreMenuController {
	

	@Autowired
	StoresService storesSvc;
	@Autowired
	MealsService mealsSvc;
	@Autowired
	LikeStoreService likeStoreSvc;
	@Autowired
	CustomerService customerSvc;
	@Autowired
	OrdersService ordersSvc;
	@Autowired
	CustomizedService customizedSvc;
	@Autowired
	CustomizedDetailService customizedDetailSvc;
	//缺下單&購物車功能

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
   
	//登出
    @GetMapping("/logout")
    public String logout(HttpServletRequest req ,Model model) {
    	// 獲取 HttpSession，防止在會話不存在時創建新的會話。如果您確定會話一定存在，可以使用 getSession()。
        HttpSession session = req.getSession(false);

        // 檢查 HttpSession 是否存在，並且 platformEmpLogin 屬性是否存在
        if (session != null && session.getAttribute("customerLogin") != null) {
            // 移除 platformEmpLogin 屬性
//        	session.setAttribute("customerLogin", null);
            session.removeAttribute("customerLogin");
        }
    	return "cEntrance"; //view
    }
    
  
  //喜好調整
    @GetMapping("/custMealDetail")
	public String geMealCustDetail(@RequestParam("mealNo") String mealNo,@RequestParam("storeId") String storeId, String quantityInput,ModelMap model) {
    	/***************************1.接收請求↑ ************************/	
		/***************************2.查詢*********************************************/
		List<CustomizedVO> customizedListData = customizedSvc.getAll(Integer.valueOf(mealNo));
		MealsVO mealsVO = mealsSvc.getMealsVOByMealNo(Integer.valueOf(mealNo));
		StoresVO storesVO =storesSvc.getOneStores(Integer.valueOf(storeId));
		model.addAttribute("storeName", storesVO.getStoreName());
		model.addAttribute("storeAddress", storesVO.getStoreAddress());
		model.addAttribute("storePhone", storesVO.getStorePhone());
		model.addAttribute("customizedListData",customizedListData);
		model.addAttribute("mealsVO",mealsVO);
		model.addAttribute("qty",quantityInput);
		/***************************3.顯示*****************/
System.out.println("有跑");
System.out.println("quantityInput");
		return "cFunction/cart/custMealDetail"; 
    
    }
    	
		
		@ModelAttribute("customizedDetailData")
		public List<CustomizedDetailVO> customizedListData(Model model){	
			return customizedDetailSvc.getAll();
		}

}