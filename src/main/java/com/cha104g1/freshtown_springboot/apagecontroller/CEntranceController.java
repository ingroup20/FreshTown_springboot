package com.cha104g1.freshtown_springboot.apagecontroller;

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

import com.cha104g1.freshtown_springboot.adao.CartService;
import com.cha104g1.freshtown_springboot.amodel.CartDetailVO;
import com.cha104g1.freshtown_springboot.amodel.CartVO;
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
@RequestMapping("/")
public class CEntranceController {

	@Autowired
	StoresService storesSvc;

	@Autowired
	MealsService mealsSvc;
	@Autowired
	CartService cartSvc;
	

	//===超連結=================================================

	@GetMapping
    public String gotoCEntrance(Model model) {
        return "cEntrance"; //首頁view
    }
    
    @GetMapping("addStoresC")
    public String gotoAddStores(Model model) {
		StoresVO storesVO = new StoresVO();
		model.addAttribute("storesVO", storesVO);
    	return "addStoresC"; //店家註冊view
    }
	
    @GetMapping("successPage")
    public String gotoSuccessPage(Model model) {
    	return "successPage"; //成功訊息頁面view
    }
	
	
    @GetMapping("/cFunction/cEntrancePass")
    public String gotoCEntrancePass(Model model) {
    	model.addAttribute("searchStores","ture");
    	CustomerVO customerVO =new CustomerVO();
    	model.addAttribute("customerVO",customerVO);
    	return "cFunction/cEntrancePass"; //view
    }
    
    @GetMapping("/index")
    public String gotoIndex(Model model) {
    	return "index"; //view
    }
    
    @GetMapping("/sFunction/sEntrancePass")
    public String gotoSEntrancePass(Model model) {
    	return "sFunction/sEntrancePass"; //view
    }
    
    @GetMapping("/pFunction/pEntrancePass")
    public String gotoPEntrancePass(Model model) {
    	return "pFunction/pEntrancePass"; //view
    }
	
    //==========================================================

    @ModelAttribute("storesListData")
    public List<StoresVO> getStores(Model model){
    	List<StoresVO> list = storesSvc.getAll();
    	
    	return list;
    }

   @GetMapping("getOneStoreMeal")
   public String getOneStoreMeal(@RequestParam("storeId") String storeId,Model model) {
	   List<MealsVO> menuListS = mealsSvc.getAllByStoreId(Integer.valueOf(storeId));
	   StoresVO storesVO=storesSvc.getOneStores(Integer.valueOf(storeId));
	  //計算平均評分 
	   double scoreAvg = storesVO.getTotalScore()/storesVO.getScorePeople();
	   scoreAvg=(int)(scoreAvg*10)/10;
	   
	   model.addAttribute("menuListS", menuListS);
	   model.addAttribute("storeName", storesVO.getStoreName());
	   model.addAttribute("storeAddress", storesVO.getStoreAddress());
	   model.addAttribute("storePhone", storesVO.getStorePhone());
	   model.addAttribute("openTime", storesVO.getOpenTime());	
	   model.addAttribute("scoreAvg", scoreAvg);
	   model.addAttribute("getOneStoreMeal", "true"); 
	   return "cEntrance";
   }
   

	@GetMapping("cFunction/cartPage")
	public String seeCart(HttpServletRequest req ,Model model) {
		List<CartDetailVO> cartDetailListData = new ArrayList<>();
		
		HttpSession session = req.getSession(false);
		Object idVO =session.getAttribute("customerLogin");
		CustomerVO customerVO= (CustomerVO)idVO;
		model.addAttribute("customerId", customerVO.getCustomerId());
		
		List<CartVO> list = new ArrayList<>();
		list=cartSvc.findCart(customerVO.getCustomerId());
		
		for(CartVO cartVO :list ) {
			System.out.println("getId"+cartVO.getId());
			System.out.println("getMealNo"+cartVO.getMealNo());
			System.out.println("getMealQty()"+cartVO.getMealQty());
			System.out.println("=============================");
            
		}
		
		
		
		for(CartVO cartVO :list ) {
			CartDetailVO cartDetailVO = new CartDetailVO();
			cartDetailVO=cartSvc.toCartDetailVO(cartVO);
			cartDetailListData.add(cartDetailVO);
			System.out.println("3="+cartDetailVO.getMealsVO());
		}
		
		model.addAttribute("cartListData",list);
		model.addAttribute("cartDetailListData",cartDetailListData);
		return "cFunction/cart/cartPage";
	}
	
}