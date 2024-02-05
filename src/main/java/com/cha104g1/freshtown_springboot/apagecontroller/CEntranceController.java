package com.cha104g1.freshtown_springboot.apagecontroller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cha104g1.freshtown_springboot.adao.CartService;
import com.cha104g1.freshtown_springboot.amodel.CartDetailVO;
import com.cha104g1.freshtown_springboot.amodel.CartVO;
import com.cha104g1.freshtown_springboot.customer.model.CustomerService;
import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.customized.model.CustomizedService;
import com.cha104g1.freshtown_springboot.customized.model.CustomizedVO;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailService;
import com.cha104g1.freshtown_springboot.customizeddetail.model.CustomizedDetailVO;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsService;
import com.cha104g1.freshtown_springboot.customizeditems.model.CustomizedItemsVO;
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

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@Validated
@RequestMapping("/")
public class CEntranceController {

	@Autowired
	StoresService storesSvc;
	@Autowired
	MealsService mealsSvc;
	@Autowired
	CartService cartSvc;
	@Autowired	
	CustomizedService customizedSvc;
	@Autowired	
	CustomizedItemsService customizedItemsSvc;
	@Autowired	
	CustomizedDetailService customizedDetailSvc;
	@Autowired
	CustomerService customerSvc;
	
    @ModelAttribute("storesListData")
    public List<StoresVO> getStores(Model model){
    	List<StoresVO> list = storesSvc.getAll();
    	
    	return list;
    }

	//====================================================

	@GetMapping
    public String gotoCEntrance(HttpServletRequest req ,Model model) {
		System.out.println("未登入首頁");
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("customerLogin") != null) {
            session.removeAttribute("customerLogin");
        }
        return "cEntrance"; //首頁view
    }
    
    @GetMapping("addStoreC")
    public String gotoAddStores(Model model) {
    	System.out.println("店家註冊");
    	StoresVO storesVO = new StoresVO();
		model.addAttribute("storesVO", storesVO);
		System.out.println("轉交請求");
		model.addAttribute("addStoreC", "true"); 
		model.addAttribute("hasInsert", "true"); 
    	return "cEntrance"; //店家註冊view
    }
    
    @GetMapping("addCustomer")
    public String gotoAddustomer(Model model) {
    	System.out.println("顧客註冊");
    	CustomerVO customerVO = new CustomerVO();
		model.addAttribute("customerVO", customerVO);
		System.out.println("轉交請求");
		model.addAttribute("hasInsert", "true"); 
    	model.addAttribute("addCustomer", "true"); 
    	return "cEntrance"; //會員註冊view
    }
	
    @GetMapping("/cFunction/cEntrancePass")
    public String gotoCEntrancePass(Model model) {
    	System.out.println("登入首頁");
    	model.addAttribute("searchStores","ture");
    	CustomerVO customerVO =new CustomerVO();
    	model.addAttribute("customerVO",customerVO);
    	return "cFunction/cEntrancePass"; //登入view
    }
        
    @GetMapping("/sFunction/sEntrancePass")
    public String gotoSEntrancePass(Model model) {
    	System.out.println("店家後台");
    	return "sFunction/sEntrancePass"; //view
    }
    
    @GetMapping("/pFunction/pEntrancePass")
    public String gotoPEntrancePass(Model model) {
    	System.out.println("平台後台");
    	return "pFunction/pEntrancePass"; //view
    }
	
    @GetMapping("getOneStoreMeal")
    public String getOneStoreMeal(@RequestParam("storeId") String storeId,Model model) {
    	System.out.println("未登入查看菜單");
 	   List<MealsVO> menuListS = mealsSvc.getAllByStoreId(Integer.valueOf(storeId));
 	   StoresVO storesVO=storesSvc.getOneStores(Integer.valueOf(storeId));
 	  //計算平均評分 
 	   double scoreAvg = storesVO.getTotalScore()/storesVO.getScorePeople();
 	   scoreAvg=(int)(scoreAvg*10)/10;
 	   
 	   model.addAttribute("menuListS", menuListS);
 	   model.addAttribute("storeName", storesVO.getStoreName());
 	   model.addAttribute("storeVO", storesVO);
 	   model.addAttribute("storeAddress", storesVO.getStoreAddress());
 	   model.addAttribute("storePhone", storesVO.getStorePhone());
 	   model.addAttribute("openTime", storesVO.getOpenTime());	
 	   model.addAttribute("scoreAvg", scoreAvg);
 	   model.addAttribute("getOneStoreMeal", "true"); 
 	   model.addAttribute("hasInsert", "true"); 
 	   return "cEntrance";
    }
          
    //==========================================================
 	
 	
 	



    
    @PostMapping("addCustomer/insert")
    public String addCustomer(@Valid CustomerVO customerVO, BindingResult result, ModelMap model) throws IOException {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
    	System.out.println("新增顧客");
		if (result.hasErrors()) {
			System.out.println("資料有誤");
			return "pFunction/customer/addCustomer";
		}
		/*************************** 2.開始新增資料 *****************************************/
		customerSvc.addCustomer(customerVO);
		/*************************** 3.新增完成,準備轉交(Send the Success view) **************/
		List<CustomerVO> list = customerSvc.getAll();
		model.addAttribute("customerListData", list);
		model.addAttribute("success", "- (新增成功)");
		return "cFunction/cEntrancePass"; 
		}
       	
	
}