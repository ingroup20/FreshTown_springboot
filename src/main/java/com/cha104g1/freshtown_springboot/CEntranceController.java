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
@RequestMapping("/cEntrance")
public class CEntranceController {

	@Autowired
	StoresService storesSvc;

	@Autowired
	MealsService mealsSvc;
	

	//=====oneStore===============================================
//    @GetMapping("/onceStorePage")
//    public String onceStorePage(Model model) {
//    	return "onceStorePage"; //view
//    }
    
    @GetMapping("/cFunction/cEntrancePass")
    public String onceStoreMealPage1(Model model) {
    	return "cFunction/cEntrancePass"; //view
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
    
    
    
    
   //==include個人資料管理=================== 
   
   
	
}