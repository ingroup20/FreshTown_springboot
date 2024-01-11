package com.cha104g1.freshtown_springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeVO;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsService;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;
import com.cha104g1.freshtown_springboot.stores.model.StoresVO;

import java.util.*;


@Controller
public class IndexController_inSpringBoot {

	@Autowired
	RefundsService refundsSvc;
	
	@Autowired
	OrdersService ordersSvc;
	
	@Autowired
	StoresService storesSvc;
	
	@Autowired
	MealTypeService mealTypeSvc;
	
    @Value("${welcome.message}")
    private String message;
	
    private List<String> myList = Arrays.asList("refunds 官網 ", "mealtype 官網", "likestore 官網", "orders 官網", "stores 官網");
    @GetMapping("/")
    public String index(Model model) {
    	model.addAttribute("message", message);
        model.addAttribute("myList", myList);
        return "index"; //view
    }
    
    // http://......../hello?name=peter1
    @GetMapping("/hello")
    public String indexWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("message", name);
        return "index"; //view
    }
    
  
    //=========== refunds ===================   
    @GetMapping("/pFunction/refunds/select_page")
	public String select_page1(Model model) {
		return "pFunction/refunds/select_page";
	}
    
    @GetMapping("/pFunction/refunds/listAllRefunds")
	public String listAllRefunds(Model model) {
		return "pFunction/refunds/listAllRefunds";
	}
    
    @ModelAttribute("refundsListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
	protected List<RefundsVO> referenceListData_Refunds(Model model) {
		
    	List<RefundsVO> list = refundsSvc.getAll();
		return list;
	}
    

	
    //=========== mealtype  要使用的資料 ===================   
    @GetMapping("/pFunction/mealtype/select_page")
	public String select_page2(Model model) {
		return "pFunction/mealtype/select_page";
	}
    
    @GetMapping("/pFunction/mealtype/listAllMealType")
	public String listAllMealtype(Model model) {
		return "pFunction/mealtype/listAllMealType";
	}
    
    @ModelAttribute("mealTypeListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第85行用
	protected List<MealTypeVO> referenceListData_MealType(Model model) {
		
    	List<MealTypeVO> list = mealTypeSvc.getAll();
		return list;
	}
    
    //=========== orders  要使用的資料 ===================   
    @GetMapping("/pFunction/orders/select_page")
	public String select_page3(Model model) {
		return "pFunction/orders/select_page";
	}
    
    @GetMapping("/pFunction/orders/listAllOrders")
	public String listAllOrders(Model model) {
		return "pFunction/orders/listAllOrders";
	}
    
	@ModelAttribute("ordersListData") // for select_page.html 第135行用
	protected List<OrdersVO> referenceListData_Orders(Model model) {
		List<OrdersVO> list = ordersSvc.getAll();
		return list;
	}

    //=========== stores  要使用的資料 ===================   
    @GetMapping("/pFunction/stores/select_page")
	public String select_page4(Model model) {
		return "pFunction/stores/select_page";
	}
    
    @GetMapping("/pFunction/stores/listAllStores")
	public String listAllStores(Model model) {
		return "pFunction/stores/listAllStores";
	}
    
	@ModelAttribute("storesListData") // for select_page.html 第135行用
	protected List<StoresVO> referenceListData_Stores(Model model) {
		List<StoresVO> list = storesSvc.getAll();
		return list;
	}
	
//    //=========== stores  要使用的資料 ===================   
//    @GetMapping("/cFunction/stores/addStoresC")
//	public String select_page5(Model model) {
//		return "cFunction/stores/addStores";
//	}

}