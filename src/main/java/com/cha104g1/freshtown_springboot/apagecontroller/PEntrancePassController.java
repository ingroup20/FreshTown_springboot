package com.cha104g1.freshtown_springboot.apagecontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cha104g1.freshtown_springboot.customer.model.CustomerService;
import com.cha104g1.freshtown_springboot.customer.model.CustomerVO;
import com.cha104g1.freshtown_springboot.likestore.model.LikeStoreService;
import com.cha104g1.freshtown_springboot.meals.model.MealsService;
import com.cha104g1.freshtown_springboot.orders.model.OrdersService;
import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpService;
import com.cha104g1.freshtown_springboot.platformemp.model.PlatformEmpVO;
import com.cha104g1.freshtown_springboot.stores.model.StoresService;

@Controller
@Validated
@RequestMapping("/pFunction")
public class PEntrancePassController {

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
	PlatformEmpService platformEmpSvc;
	//缺下單&購物車功能

//	@SuppressWarnings("null")
//	@ModelAttribute//每次進入controller都會叫用
//   public void whoareyou(HttpServletRequest req ,Model model) {
//
//	HttpSession session = req.getSession(false);
//	Object idVO =session.getAttribute("platformEmpLogin");
//	PlatformEmpVO platformEmpVO= (PlatformEmpVO)idVO;
//	if (platformEmpVO != null) {
//		System.out.println("身分暱稱="+platformEmpVO.getpEmpName());
//	}else
//	 model.addAttribute("customerId", platformEmpVO.getCustomerId());
//   }
   
	//登出
//    @GetMapping("/logout")
//    public String logout(HttpServletRequest req ,Model model) {
//    	// 獲取 HttpSession，防止在會話不存在時創建新的會話。如果您確定會話一定存在，可以使用 getSession()。
//        HttpSession session = req.getSession(false);
//
//        // 檢查 HttpSession 是否存在，並且 platformEmpLogin 屬性是否存在
//        if (session != null && session.getAttribute("customerLogin") != null) {
//            // 移除 platformEmpLogin 屬性
////        	session.setAttribute("customerLogin", null);
//            session.removeAttribute("customerLogin");
//        }
//    	return "cEntrance"; //view
//    }
//    


	
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
  //==doget=================== 
	
	@GetMapping("logoutP")
	public String logoutP(HttpSession session, Model model) {
		System.out.println("登出");
        if (session != null && session.getAttribute("platformEmpLogin") != null) {
            session.removeAttribute("platformEmpLogin");
        }
		
		return "/pFunction/pEntrancePass";
	}
	
	
	//=========== PlatformEmp  要使用的資料 ===================  
	
	@GetMapping("/platformEmp/select_page")
	public String select_page1(Model model) {
		return "/pFunction/platformEmp/select_page";
	}
	
	@GetMapping("/platformEmp/listAllPlatformEmp")
	public String listAllPlatformEmp(Model model) {
		return "/pFunction/platformEmp/listAllPlatformEmp";
	}
	
	@ModelAttribute("platformEmpListData") // for select_page.html 第135行用
	protected List<PlatformEmpVO> referenceListData_PlatformEmp(Model model) {
		List<PlatformEmpVO> list = platformEmpSvc.getAll();
		return list;
	}
	
	//=========== Customer  要使用的資料 ===================   
			@GetMapping("/customer/select_page")
			public String select_page13(Model model) {
				return "/pFunction/customer/select_page";
			}
			
			@GetMapping("/customer/listAllCustomer")
			public String listAllCustomer(Model model) {
				return "/pFunction/customer/listAllCustomer";
			}
			
			@ModelAttribute("customerListData") // for select_page.html 第135行用
			protected List<CustomerVO> referenceListData_Customer(Model model) {
				List<CustomerVO> list = customerSvc.getAll();
				return list;
			}
   
	
}