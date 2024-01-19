package com.cha104g1.freshtown_springboot;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreService;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpService;
import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpVO;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsService;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

import redis.clients.jedis.Jedis;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@Validated
@RequestMapping("/cEntrance/cFunction")
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
        if (session != null && session.getAttribute("platformEmpLogin") != null) {
            // 移除 platformEmpLogin 屬性
            session.removeAttribute("customerLogin");
        }
    	return "cEntrance"; //view
    }
    


	
//    @ModelAttribute("menuListS") 
//	protected List<MealsVO> getAllMenuListS(@RequestParam("storeId") String storeId,Model model) {
//
//	    if (storeId != null) {
//	        List<MealsVO> list = mealsSvc.getAllByStoreId(Integer.valueOf(storeId));
//	        System.out.println(list.size());
//	        model.addAttribute("storeName", list.get(0).getStoresVO().getStoreName());
//	        return list;
//	        
//	    } else {
//	     
//	       System.out.println("xxx");
//	    	return null;
//	    }
//	}
//    
  //==include店家搜尋=================== 
	@PostMapping("searchStore")
   	public String searchStore(HttpServletRequest req,ModelMap model) {
   		/***************************1.接收請求↑ ************************/

   		/***************************2.查詢*********************************************/
   		List<StoresVO> storesListData = storesSvc.getAll(); 	
   		model.addAttribute("storesListData", storesListData);     // for listOnePage.html 
   	
   		/***************************3.顯示*****************/
   		model.addAttribute("searchStore", "true"); // for cEnrance.html
   		
   		return "cFunction/cEntrancePass"; 	
   	}
    
    
    //==include進入店家===================    
	@PostMapping("getOneStoreMeal")
	public String getOneStoreMeal(@RequestParam("storeId") String storeId,ModelMap model) {
		/***************************1.接收請求↑ ************************/
	
		/***************************2.查詢*********************************************/
		StoresVO storesVO = storesSvc.getOneStores(Integer.valueOf(storeId));
		List<MealsVO> list = mealsSvc.getAllByStoreId(Integer.valueOf(storeId));

		System.out.println("shoe me="+list.size());
		model.addAttribute("storesVO", storesVO);     // for listOnePage.html 
		model.addAttribute("menuListS", list);  // for listOnePage.html 
		
		if (storesVO == null) {
			model.addAttribute("errorMessage", "無此店家");
			return "cFunction/cEntrancePass";
		}
		
		/***************************3.顯示*****************/
		model.addAttribute("getOneStoreMeal", "true"); // for cEnrance.html
		
		return "cFunction/cEntrancePass"; 	
	}
    
    
   //==include個人資料管理=================== 
	@PostMapping("searchPersonalInfo")
   	public String searchPersonalInfo(HttpServletRequest req,ModelMap model) {
   		/***************************1.接收請求↑ ************************/
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
    	Object idVO = req.getAttribute("customerLogin");
    	CustomerVO customerVO= (CustomerVO)idVO;
    	Integer customerId =customerVO.getCustomerId();
    	
   		/***************************2.查詢*********************************************/
   		List<OrdersVO> ordersList = ordersSvc.getAllByCustomer(customerId);
  		
   		if (ordersList == null) {
   			model.addAttribute("errorMessage", "無訂單");
   			return "cFunction/cEntrancePass";
   		}
   		
   		/***************************3.顯示*****************/
   		model.addAttribute("ordersList", ordersList); 
   		model.addAttribute("searchOrderHistory", "true"); // for cEnrance.html
   		
   		return "cFunction/cEntrancePass"; 	
   	}
   
	
}