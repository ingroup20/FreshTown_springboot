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
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreService;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreVO;
import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.meals.model.MealsVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.storeemp.model.StoreEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

import redis.clients.jedis.Jedis;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;


@Controller
@Validated
@RequestMapping("/sFunction")
public class SEntrancePassController {

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


	@ModelAttribute//每次進入controller都會叫用
   public void whoareyou(HttpServletRequest req ,Model model) {

	HttpSession session = req.getSession(false);
	Object idVO =session.getAttribute("storeEmpLogin");
	StoreEmpVO storeEmpVO= (StoreEmpVO)idVO;
	if (storeEmpVO == null) {
		System.out.println("出現null啦");
	}
	System.out.println("身分暱稱="+storeEmpVO.getsEmpName());
	 model.addAttribute("storeId", storeEmpVO.getStoresVO().getStoreId());
   }
   
	//登出
    @GetMapping("/logout")
    public String logout(HttpServletRequest req ,Model model) {
    	// 獲取 HttpSession，防止在會話不存在時創建新的會話。如果您確定會話一定存在，可以使用 getSession()。
        HttpSession session = req.getSession(false);
     // 檢查 HttpSession 是否存在，並且 platformEmpLogin 屬性是否存在
        if (session != null && session.getAttribute("storeEmpLogin") != null) {
            session.removeAttribute("storeEmpLogin");
        }
    	return "sFunction/sEntrancePass"; //view
    }
    

  //==insert訂單管理=================== 
	@PostMapping("manageOrders")
   	public String manageOrders(HttpServletRequest req,ModelMap model) {
   		/***************************1.接收請求↑ ************************/
   		/***************************2.查詢*********************************************/
//		Integer storeId = (Integer)model.getAttribute("storeId");
//   		List<OrdersVO> ordersListData = ordersSvc.getAllByStore(storeId); 	
//   		model.addAttribute("ordersListData", ordersListData);     // for listOnePage.html 
   	
   		/***************************3.顯示*****************/
   		model.addAttribute("manageOrders", "true"); // for cEnrance.html
   		
   		return "sFunction/sEntrancePass"; 	
   	}
    
	  //==insert訂單排程=================== 
		@PostMapping("orderOrders")
	   	public String orderOrders(HttpServletRequest req,ModelMap model) {
	   		/***************************1.接收請求↑ ************************/
	   		/***************************2.查詢*********************************************/
			Integer storeId = (Integer)model.getAttribute("storeId");
	   		List<OrdersVO> ordersListData = ordersSvc.getAllByStore(storeId); 	
	   		model.addAttribute("ordersListData", ordersListData);     // for listOnePage.html 
	   	
	   		/***************************3.顯示*****************/
	   		model.addAttribute("orderOrders", "true"); // for cEnrance.html
	   		
	   		return "sFunction/sEntrancePass"; 	
	   	}
		
		//===
				    @GetMapping("/listAllOrders")
					public String listAllOrders(Model model) {
						return "sFunction/orders/listAllOrders";
					}
				    @PostMapping("getOne_For_Display")
					public String getOne_For_Display(
						/***************************1.接收請求參數 - 輸入格式的錯誤處理*************************/
						@NotEmpty(message="訂單編號: 請勿空白")
						@RequestParam("orderId") String orderId,
						ModelMap model) {
						
						/***************************2.開始查詢資料*********************************************/
						OrdersVO ordersVO = ordersSvc.getOneOrders(Integer.valueOf(orderId));
						
						List<OrdersVO> list = ordersSvc.getAll();
						model.addAttribute("ordersListData", list);     // for select_page.html 第97 109行用
			
						if (ordersVO == null) {
							model.addAttribute("errorMessage", "查無資料");
							return "sFunction/manageOrders";
						}
						
						/***************************3.查詢完成,準備轉交(Send the Success view)*****************/
						model.addAttribute("ordersVO", ordersVO);
						model.addAttribute("getOne_For_Display", "true"); // 旗標getOne_For_Display見select_page.html的第156行 -->
			
						return "sFunction/manageOrders"; 
					}
				    
	    
	    
	    
   
		  //==insert店家資料=================== 
			@PostMapping("storeInfo")
		   	public String storeInfo(HttpServletRequest req,ModelMap model) {
		   		/***************************1.接收請求↑ ************************/
		   		/***************************2.查詢*********************************************/
//StoresVO原本就在model裡面
				//				StoresVO storesVO = (StoresVO)model.getAttribute("storesVO");
//		   		model.addAttribute("ordersListData", ordersListData);     // for listOnePage.html 
		   	
		   		/***************************3.顯示*****************/
		   		model.addAttribute("storeInfo", "true"); // for cEnrance.html
		   		
		   		return "sFunction/sEntrancePass"; 	
		   	}
			
			

	
}