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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cha104g1.freshtown_springboot.adao.CartService;
import com.cha104g1.freshtown_springboot.amodel.CartVO;
import com.cha104g1.freshtown_springboot.customer.model.CustomerService;
import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.customized.model.CustomizedService;
import com.cha104g1.freshtown_springboot.customized.model.CustomizedVO;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailService;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsService;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;
import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderService;
import com.cha104g1.freshtown_springboot.customizedorder.model.CustomizedOrderVO;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreService;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

import java.io.StringReader;
import java.util.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
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
	//下單&購物車功能
	@Autowired
	CustomizedService customizedSvc;
	@Autowired
	CustomizedDetailService customizedDetailSvc;
	@Autowired
	CustomizedItemsService customizedItemsSvc;
	@Autowired
	CustomizedOrderService customizedOrderSvc;
	@Autowired
	CartService cartSvc;

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
	public String geMealCustDetail(@RequestParam("mealNo") String mealNo,
			@RequestParam("storeId") String storeId, 
			String quantity,
			ModelMap model,
			RedirectAttributes redirectAttributes) {
    	/***************************1.接收請求↑ ************************/	
		/***************************2.查詢*********************************************/
		List<CustomizedVO> customizedListData = customizedSvc.getAll(Integer.valueOf(mealNo));
		MealsVO mealsVO = mealsSvc.getMealsVOByMealNo(Integer.valueOf(mealNo));
		StoresVO storesVO =storesSvc.getOneStores(Integer.valueOf(storeId));
		
		if(customizedListData != null) {
			Map<String,List<CustomizedDetailVO>> customizedDetailData =new HashMap<>();
			for(CustomizedVO customizedVO: customizedListData) {
				CustomizedItemsVO customizedItemsVO=customizedItemsSvc.getCustomizedItemsVOByCustedItemsNo(customizedVO.getCustedItemsNo());
				List<CustomizedDetailVO> list=customizedDetailSvc.getAllByCustedItemsNo(customizedItemsVO.getCustedItemsNo());
				
				customizedDetailData.put(customizedItemsVO.getCustedName(), list) ;
			}
			model.addAttribute("storeName", storesVO.getStoreName());
			model.addAttribute("storeAddress", storesVO.getStoreAddress());
			model.addAttribute("storePhone", storesVO.getStorePhone());
			model.addAttribute("customizedListData",customizedListData);
			model.addAttribute("mealsVO",mealsVO);
			model.addAttribute("qty",quantity);
			model.addAttribute("customizedDetailData",customizedDetailData);
			/***************************3.顯示*****************/
			return "cFunction/cart/custMealDetail"; 
		}else {
	
//			無客制，直接加入購物車
			model.addAttribute("success","ture");//成功加入購物車
//			redirectAttributes.addAttribute("store",storesVO.getStoreId() );
			return "redirect:/cFunction/storeMenu?storeId=" + storesVO.getStoreId();   
		}
    
    }
    	
		
		@ModelAttribute("customizedDetailData")
		public List<CustomizedDetailVO> customizedListData(Model model){	
			return customizedDetailSvc.getAll();
		}

		 @GetMapping("/")
		 public String backStoreMenu(Model model) {
			 String storeId= (String)model.getAttribute("storeId");
			 return  "redirect:/cFunction/storeMenu?storeId=" + storeId; 
			    
		 }
		 
		 
		 
		 //====送出到購物車==============================	 
			

			
		 @PostMapping("/addOneInCart")
			public String addOneInCart(@RequestParam("mealNo") String mealNo, 
					@RequestParam("customizedOrderList") String customizedOrderList, 
					String mealQty,
					Model model,
					HttpServletRequest req,
					RedirectAttributes redirectAttributes) {
			 
				HttpSession session = req.getSession(); 
				CustomerVO customerVO = (CustomerVO) session.getAttribute("customerLogin");//
				Integer customerId=customerVO.getCustomerId();
			 	CartVO cartVO =new CartVO();
		    	MealsVO mealsVO = mealsSvc.getMealsVOByMealNo(Integer.valueOf(mealNo));
				StoresVO storesVO =mealsVO.getStoresVO();
				List<CustomizedOrderVO> list = new ArrayList<>();
				// 解析字符串	    
				if (customizedOrderList != null && !customizedOrderList.isEmpty()) {
					// 解析逗號分隔的字符串
				    String[] customizedOrders = customizedOrderList.split(",");
				    // 使用取得的數據進行其他操作
				    for (String custedDtlNo : customizedOrders) {
				        // 對每個 custedDtlNo 做一些處理
				    	CustomizedOrderVO customizedOrderVO =customizedOrderSvc.getOneCustomizedOrder(Integer.valueOf(custedDtlNo));
				        list.add(customizedOrderVO);
				    }
				    
				}else {
				    System.out.println("customizedOrderList為空值.");
				}
				
		    	cartVO.setId(cartSvc.getCartCount(req));
		    	System.out.println("cartCount="+cartVO.getId());
		    	cartVO.setMealNo(Integer.valueOf(mealNo));
		    	cartVO.setMealQty(Integer.valueOf(mealQty));
		    	cartVO.setCustomizedOrderNoList(customizedOrderList);
				cartVO.setCustomerId(customerId);
				cartVO.setStoreId(storesVO.getStoreId());				
				System.out.println("cartVO創建成功");
				
				cartSvc.addCartToRedis(cartVO,customerId);
				
//				redirectAttributes.addAttribute("store",storesVO.getStoreId() );
				return "redirect:/cFunction/storeMenu?storeId=" + storesVO.getStoreId(); 
		    }
		 

}