package com.cha104g1.freshtown_springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreService;
import com.cha104g1.freshtown_springboot.mealtype.model.MealTypeService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersVO;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsService;
import com.cha104g1.freshtown_springboot.refunds.model.RefundsVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;

import java.util.*;



//@PropertySource("classpath:application.properties") // 於https://start.spring.io建立Spring Boot專案時, application.properties文件預設已經放在我們的src/main/resources 目錄中，它會被自動檢測到
@Controller
public class IndexController_inSpringBoot {
	
	@Autowired
	StoresService storesSvc;
	
	@Autowired
	RefundsService refundsSvc;
	
	@Autowired
	LikeStoreService likeStoresSvc;
	
	@Autowired
	OrdersService ordersSvc;
	
	@Autowired
	MealTypeService mealTypeSvc;
	
    // inject(注入資料) via application.properties
    @Value("${welcome.message}")
    private String message;
	
   
    @GetMapping("/")
    public String index(Model model) {
    	model.addAttribute("message", message);

        return "index"; //view
    }
    
    // http://......../hello?name=peter1
    @GetMapping("/hello")
    public String indexWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") String name, Model model) {
        model.addAttribute("message", name);
        return "index"; //view
    }
    
    
    
    //
    //=========== 以下第57~62行是提供給 /src/main/resources/templates/haveToLogin/pFunction/refundPage.html  要使用的資料 ===================   
    @GetMapping("/haveToLogin/pFunction/refundPage")
	public String refundPage(Model model) {
		return "haveToLogin/pFunction/refundPage";
	}
    
    @GetMapping("/haveToLogin/custFunction/viewLikeStore")
	public String viewLikeStore(Model model) {
		return "haveToLogin/custFunction/viewLikeStore";
	}
    
    @GetMapping("/haveToLogin/pFunction/viewOrder")
	public String viewOrder(Model model) {
		return "haveToLogin/pFunction/viewOrder";
	}
    
    @ModelAttribute("refundsListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
	protected List<RefundsVO> referenceListData(Model model) {
		
    	List<RefundsVO> list = refundsSvc.getAll();
		return list;
	}
    
//    @ModelAttribute("ordersListData")  // for select_page.html 第97 109行用 // for listAllEmp.html 第117 133行用
//	protected List<OrdersVO> referenceListData(Model model) {
//		
//    	List<OrdersVO> list = refundsSvc.getAll();
//		return list;
//	}

}