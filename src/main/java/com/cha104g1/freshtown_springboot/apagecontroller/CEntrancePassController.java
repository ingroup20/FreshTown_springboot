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

import com.cha104g1.freshtown_springboot.adao.CartService;
import com.cha104g1.freshtown_springboot.amodel.CartDetailVO;
import com.cha104g1.freshtown_springboot.amodel.CartVO;
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
@RequestMapping("/cFunction")
public class CEntrancePassController {

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
	@Autowired
	CartService cartSvc;
	
	
	@ModelAttribute("customizedDetailData")
	public List<CustomizedDetailVO> customizedListData(Model model){	
		return customizedDetailSvc.getAll();
	}
	
	@ModelAttribute//每次進入controller都會叫用
   public void whoareyou(HttpServletRequest req ,Model model) {
//		System.out.println("取得身份");
	HttpSession session = req.getSession(false);
	Object idVO =session.getAttribute("customerLogin");
	CustomerVO customerVO= (CustomerVO)idVO;

	model.addAttribute("customerVO", customerVO);
	model.addAttribute("customerId", customerVO.getCustomerId());
   }
   
	//登出
    @GetMapping("cEntrance")
    public String logout(HttpServletRequest req ,Model model) {
    	System.out.println("登出");
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("customerLogin") != null) {
            session.removeAttribute("customerLogin");
        }
    	return "cEntrance"; //view
    }

    
//====================================================================================
  //==include店家搜尋=================== 
	@PostMapping("searchStores")
   	public String searchStore(HttpServletRequest req,ModelMap model) {
   		/***************************1.接收請求↑ ************************/
		System.out.println("查看店家");
   		/***************************2.查詢*********************************************/
   		List<StoresVO> storesListData = storesSvc.getAll(); 	
   		model.addAttribute("storesListData", storesListData);     // for listOnePage.html 
   	
   		/***************************3.顯示*****************/
   		model.addAttribute("searchStores", "true"); // for cEnrance.html
   		
   		return "cFunction/cEntrancePass"; 	
   	}
    
   //==include個人資料管理=================== 
	@PostMapping("searchPersonalInfo")
   	public String searchPersonalInfo(HttpServletRequest req,ModelMap model) {
   		/***************************1.接收請求↑ ************************/
		System.out.println("個人資料管理");
    	Object idVO = req.getAttribute("customerLogin");
    	CustomerVO customerVO= (CustomerVO)idVO;
   		/***************************2.查詢*********************************************/
   		if (customerVO == null) {
   			model.addAttribute("errorMessage", "未登入");
   			return "cFunction/cEntrancePass";
   		}
   		/***************************3.顯示*****************/
   		model.addAttribute("customerVO", customerVO);
   		model.addAttribute("searchPersonalInfo", "true"); // for cEnrance.html
   		return "cFunction/cEntrancePass"; 	
   	}
    
    //==include收藏店家=================== 
	@PostMapping("searchLikeStore")
   	public String searchLikeStore(HttpServletRequest req,ModelMap model) {
   		/***************************1.接收請求↑ ************************/
		System.out.println("收藏店家");
		Integer customerId = (Integer)(model.getAttribute("customerId"));
   		/***************************2.查詢*********************************************/
   		List<LikeStoreVO> likeStoreListData = likeStoreSvc.getAllByCustomer(customerId,"L");
   		if (likeStoreListData == null) {
   			model.addAttribute("errorMessage", "無資料");
   			return "cFunction/cEntrancePass";
   		}
   		/***************************3.顯示*****************/
   		model.addAttribute("likeStoreListData", likeStoreListData); // for cEnrance.html
   		model.addAttribute("searchLikeStore", "true"); // for cEnrance.html
   		return "cFunction/cEntrancePass"; 	
   	}
    
    //==include黑名單店家=================== 
	@PostMapping("searchBlackStore")
   	public String searchBlackStore(HttpServletRequest req,ModelMap model) {
   		/***************************1.接收請求↑ ************************/
		System.out.println("黑名單店家");
		Integer customerId = (Integer)(model.getAttribute("customerId"));
		/***************************2.查詢*********************************************/
   		List<LikeStoreVO> likeStoreListData = likeStoreSvc.getAllByCustomer(customerId,"U");
   		if (likeStoreListData == null) {
   			model.addAttribute("errorMessage", "無資料");
   			return "cFunction/cEntrancePass";
   		}
   		/***************************3.顯示*****************/
   		model.addAttribute("likeStoreListData", likeStoreListData); // for cEnrance.html
   		model.addAttribute("searchBlackStore", "true"); // for cEnrance.html	
   		return "cFunction/cEntrancePass"; 	
   	}
    
    //==include下單紀錄=================== 
	@PostMapping("searchOrderHistory")
   	public String searchOrderHistory(HttpServletRequest req,ModelMap model) {
   		/***************************1.接收請求↑ ************************/
		System.out.println("下單紀錄");
		Integer customerId = (Integer)(model.getAttribute("customerId"));
    	System.out.println("身分="+customerId);
   		/***************************2.查詢*********************************************/
    	List<OrdersVO> ordersListData = ordersSvc.getAllByCustomer(customerId);
    	if (ordersListData == null) {
            model.addAttribute("errorMessage", "無效的客戶資訊");
            System.out.println("發生null");
            return "cFunction/cEntrancePass";
        }	
   		/***************************3.顯示*****************/
   		model.addAttribute("ordersListData", ordersListData); 
   		model.addAttribute("searchOrderHistory", "true"); // for cEnrance.html
   		return "cFunction/cEntrancePass"; 	
   	}

//========================================================================================		
	    //==insert 加入購物車===================    
//		@PostMapping("checkMealDetail")
//		public String putInCart(@RequestParam("mealNo") String mealNo,@RequestParam("priceBought") String priceBought,@RequestParam("mealQty") String mealQty,ModelMap model) {
//			/***************************1.接收請求↑ ************************/
//			System.out.println("下單紀錄");
//			Integer oneTotalPrice = Integer.valueOf(priceBought) *Integer.valueOf(mealQty);//單項總金額
//			/***************************2.查詢*********************************************/
//			List<CustomizedVO> customizedListData = customizedSvc.getAll(Integer.valueOf(mealNo));
//			if(model.getAttribute("totalPrice")==null) {
//				Integer totalPrice=oneTotalPrice;	
//				model.addAttribute("totalPrice",totalPrice);
//			}else {
//				Integer totalPrice = (Integer)(model.getAttribute("totalPrice"));
//				totalPrice =totalPrice+oneTotalPrice;//整車總金額
//				model.addAttribute("totalPrice",totalPrice);
//			}
//			model.addAttribute("mealsVO",mealsSvc.getMealsVOByMealNo(Integer.valueOf(mealNo)));
//			model.addAttribute("oneTotalPrice",oneTotalPrice);
//			model.addAttribute("customizedListData",customizedListData);
//			/***************************3.顯示*****************/
//			model.addAttribute("putInCart", "true"); // for cEnrance.html
//			return "cFunction/cEntrancePass"; 	
//		}
	    
//==include進入店家===================    
//		@SuppressWarnings("unused")
		@GetMapping("storeMenu")
		public String getOneStoreMeal(@RequestParam("storeId") String storeId,ModelMap model) {
			/***************************1.接收請求↑ ************************/
			System.out.println("進入店家看菜單");
			if(model.getAttribute("storeId")!=null) {
				String re_storeId= (String)model.getAttribute("storeId");
				System.out.println("有storeId="+storeId);
				return "redirect:/cFunction/storeMenu?storeId=" + re_storeId; 	
			}else {
				StoresVO storesVO = storesSvc.getOneStores(Integer.valueOf(storeId));
				List<MealsVO> menuListS = mealsSvc.getAllByStoreId(Integer.valueOf(storeId));
				//計算平均評分 
				double scoreAvg = storesVO.getTotalScore()/storesVO.getScorePeople();
				scoreAvg=(int)(scoreAvg*10)/10;
					   
				model.addAttribute("menuListS", menuListS);
				model.addAttribute("storeVO", storesVO);
				model.addAttribute("storeId", storesVO.getStoreId());
				model.addAttribute("storeName", storesVO.getStoreName());
				model.addAttribute("storeAddress", storesVO.getStoreAddress());
				model.addAttribute("storePhone", storesVO.getStorePhone());
				model.addAttribute("openTime", storesVO.getOpenTime());	
				model.addAttribute("scoreAvg", scoreAvg);		
				model.addAttribute("menuListS", menuListS);
				
				if (storesVO == null) {
					model.addAttribute("errorMessage", "無此店家");
					return "cFunction/cEntrancePass";
				}		
			return "cFunction/storeMenu"; 	
			}
			
		}
	    
	
//==========================================================================			
//		@GetMapping("cFunction/storeMenu")
//		public String seeMenu(Model model) {
//		System.out.println("轉2");
//			return "/cFunction/storeMenu";
//		}
			
		    
}